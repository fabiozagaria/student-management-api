package org.stopcode1.backendstudenti.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@JsonIgnoreProperties(ignoreUnknown = false)
public class Student {
    private Long id;

    @NotBlank(message = "Nome obbligatorio")
    private String firstName;

    @NotBlank(message = "Cognome obbligatorio")
    private String lastName;

    @NotBlank(message = "Matricola obbligatorio")
    @Pattern(
            regexp = "^[0-9]{8,}$",
            message = "La matricola deve contenere solo numeri e deve essere lunga almeno 8 caratteri"
    )
    private String matricola;

    @NotNull(message = "Eta obbligatorio")
    @Min(18)
    private Integer age;

    @NotBlank(message = "Universita obbligatoria")
    private String university;

    public Student() {

    }

    public Student(Long id, String firstName, String lastName, String matricola, Integer age, String university) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setMatricola(matricola);
        setAge(age);
        setUniversity(university);
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
