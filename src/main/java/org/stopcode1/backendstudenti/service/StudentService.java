package org.stopcode1.backendstudenti.service;

import org.springframework.stereotype.Service;
import org.stopcode1.backendstudenti.model.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentById(long id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean addStudent(Student newStudent) {
        for(Student student : students) {
            if(student.getMatricola().equals(newStudent.getMatricola())){
                return false;
            }
        }
        newStudent.setId(nextId++);
        students.add(newStudent);
        return true;
    }


}




