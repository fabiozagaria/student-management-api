package org.stopcode1.backendstudenti.controller;



import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stopcode1.backendstudenti.model.Student;
import org.stopcode1.backendstudenti.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);

        if(student != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(student);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody Student student) {

        boolean added = studentService.addStudent(student);

        if(added) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(student);
        }

        return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();



    }






}
