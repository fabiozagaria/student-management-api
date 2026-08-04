package org.stopcode1.backendstudenti.controller;



import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stopcode1.backendstudenti.exception.ConflictStudentException;
import org.stopcode1.backendstudenti.exception.StudentNotFoundException;
import org.stopcode1.backendstudenti.model.Student;
import org.stopcode1.backendstudenti.repository.StudentRepository;
import org.stopcode1.backendstudenti.service.StudentService;

import java.util.List;

/*
GET   tutti gli studenti
GET   1 specifico studente
POST  aggiunta di uno studente
*/
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {

        this.studentService = studentService;

    }








}
