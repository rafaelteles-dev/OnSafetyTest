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

@RestController
@RequestMapping("/Person")

public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public @ResponseBody Iterable<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @PutMapping("Atualizar")
    public @ResponseBody void updateUser(@RequestParam String cpf) {
        Iterable<Person> iterablePersonFromDb = personRepository.findAll();
        for (Person person : iterablePersonFromDb) {
            if (person.getCpf() == ValidaCpf.Formatado(cpf)) {
                person.setName("Nome2");
                person.setEmail("email@dois.com");
                person.setDataNascimento(LocalDate.of(1991, 01, 01));
                personRepository.save(person);
            }
        }
    }

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

        Person p = new Person();
        p.setName(name);
        p.setCpf(ValidaCpf.Formatado(cpf));
        p.setEmail(email);
        p.setDataNascimento(dataNascimento);
        personRepository.save(p);
        return "Saved";
    }
}
