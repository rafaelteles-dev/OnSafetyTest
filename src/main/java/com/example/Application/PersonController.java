package com.example.Application;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping(value = "Atualizar", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateUser(@RequestBody Person model) {

        var p = personRepository.findById(model.getId());
        if (p.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var person = p.get();

        if (model.getName() != null) {
            person.setName(model.getName());
        }
        if (model.getCpf() != null) {
            if (ValidaCpf.valida(model.getCpf()) == false) {
                return ResponseEntity.badRequest().body("Invalid CPF");
            }
            person.setCpf(ValidaCpf.formatado(model.getCpf()));
        }
        if (model.getEmail() != null) {
            person.setEmail(model.getEmail());
        }
        if (model.getDataNascimento() != null) {
            person.setDataNascimento(model.getDataNascimento());
        }
        personRepository.save(person);
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("/Delete/{id}")
    public @ResponseBody void deleteUser(@RequestParam Integer id) {
        personRepository.deleteById(id);
    }

    @PostMapping("/Add")
    public @ResponseBody String addNewPerson(@RequestParam String name,
            @RequestParam String cpf,
            @RequestParam String email,
            @RequestParam LocalDate dataNascimento) {

        if (ValidaCpf.valida(cpf) == false) {
            return "Cpf Error";
        }

        Person p = new Person();
        p.setName(name);
        p.setCpf(ValidaCpf.formatado(cpf));
        p.setEmail(email);
        p.setDataNascimento(dataNascimento);
        personRepository.save(p);
        return "Saved";
    }
}
