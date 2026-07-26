package org.stopcode1.backendstudenti.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class Student {
    private Long id;

    @NotBlank(message = "Nome obbligatorio")
    private String firstName;

    @NotBlank(message = "Cognome obbligatorio")
    private String lastName;

    @NotBlank(message = "Matricola obbligatorio")
    private String matricola;

    @NotNull(message = "Eta obbligatorio")
    @Min(18)
    private Integer age;

    @NotBlank(message = "Universita obbligatoria")
    private String university;

    public Student(Long id, String firstName, String lastName, String matricola, Integer age, String university) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setMatricola(matricola);
        setAge(age);
        setUniversity(university);
    }

    public Student() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
