package org.stopcode1.backendstudenti.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stopcode1.backendstudenti.dto.StudentRequest;
import org.stopcode1.backendstudenti.exception.ConflictStudentException;
import org.stopcode1.backendstudenti.exception.StudentNotFoundException;
import org.stopcode1.backendstudenti.model.Student;
import org.stopcode1.backendstudenti.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Studente con "+id+ " non trovato"));
    }

    @Transactional
    public Student createStudent(StudentRequest studentRequest) {


        Student student = new Student();
        student.setFirstName(studentRequest.firstName());
        student.setLastName(studentRequest.lastName());
        student.setMatricola(studentRequest.matricola());
        student.setAge(studentRequest.age());
        student.setUniversity(studentRequest.university());
        long idStudent = studentRepository.save(student);
        student.setId(idStudent);
        return student;
    }



    public void deleteById(long id) {
        int affectedRows =  studentRepository.deleteById(id);
        if (affectedRows == 0) {
            throw new StudentNotFoundException("Studente non disponibile");
        }
    }

    public void update(long id, StudentRequest studentRequest) {
        Student student = toStudent(id, studentRequest);
        int affectedRows = studentRepository.update(student);

        if(affectedRows == 0) {
            throw new StudentNotFoundException("Studente con id " + id + " non trovato");
        }

    }

    private Student toStudent(long id, StudentRequest studentRequest) {
        return new Student(
                id,
                studentRequest.firstName(),
                studentRequest.lastName(),
                studentRequest.matricola(),
                studentRequest.age(),
                studentRequest.university()
        );
    }







}




