package com.example.Application;

import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Integer> {

    // public static String findByCpf(String cpf){
    //     Iterable<Person> persons = new this.findAll;
    //     return "";
    // }

}