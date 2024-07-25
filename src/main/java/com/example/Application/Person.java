package com.example.Application;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity // This tells Hibernate to make a table out of this class
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  
  private Long id;

  @NotBlank(message = "The Name is required.")
  @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
  private String name;

  @NotBlank(message = "The CPF is required.")
  @Column(unique = true)
  private String cpf;

  
  private String email;

  @NotNull(message = "The date of birth is required.")
  @Past(message = "The date of birth must be in the past.")
  private LocalDate dataNascimento;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDataNascimento(){
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento){
    this.dataNascimento = dataNascimento;

  }
}