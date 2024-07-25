package com.example.Application;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.stella.tinytype.CPF;

@RestController
@RequestMapping("/Person")

public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public @ResponseBody Iterable<Person> getAllPerson() {
        return personRepository.findAll();
    }

    // @PutMapping("Person/{id}")
    // public @ResponseBody void updateUser(@PathVariable("id") Long id) {
    // Person personFromDb = personRepository.findById(id);
    // // crush the variables of the object found
    // personFromDb.setName("john");
    // personFromDb.setCpf("39970118854");
    // personFromDb.setEmail("email@email.com");
    // personFromDb.setDataNascimento(LocalDate.of(19,06,1999));
    // personRepository.save(personFromDb);
    // }

    @DeleteMapping("/Delete/{id}")
    public @ResponseBody void deleteUser(@RequestParam Integer id) {
        personRepository.deleteById(id);
    }

    @PostMapping("/Add")
    public @ResponseBody String addNewPerson(@RequestParam String name,
            @RequestParam String cpf,
            @RequestParam String email, @RequestParam LocalDate dataNascimento) {

        if (ValidaCpf.valida(cpf) == false) {
            return "Cpf Error";
        }
        ;
        CPF cpfFormatado = new CPF(cpf);

        Person p = new Person();
        p.setName(name);
        p.setCpf(cpfFormatado.getNumeroFormatado());
        p.setEmail(email);
        p.setDataNascimento(dataNascimento);
        personRepository.save(p);
        return "Saved";
    }
}
