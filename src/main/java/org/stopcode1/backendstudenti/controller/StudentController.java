package org.stopcode1.backendstudenti.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stopcode1.backendstudenti.dto.StudentRequest;
import org.stopcode1.backendstudenti.exception.ConflictStudentException;
import org.stopcode1.backendstudenti.exception.StudentNotFoundException;
import org.stopcode1.backendstudenti.model.Student;
import org.stopcode1.backendstudenti.repository.StudentRepository;
import org.stopcode1.backendstudenti.service.StudentService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {

        this.studentService = studentService;

    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.findAll();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable(name = "id") long id
    ) {
        Student student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> postStudent(
            @Valid @RequestBody StudentRequest studentRequest,
            HttpServletRequest request
            ) {
        Student newStudent = studentService.createStudent(studentRequest);
        return ResponseEntity.created(URI.create(request.getRequestURI()+ "/" + newStudent.getId())).body(newStudent);

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Void> putStudent(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody StudentRequest studentRequest
    ) {
        studentService.update(id, studentRequest);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable(name = "id") long id
    ) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }









}
