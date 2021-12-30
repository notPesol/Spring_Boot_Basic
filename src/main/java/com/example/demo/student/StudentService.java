package com.example.demo.student;

import com.example.demo.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents() {
        return this.repository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent = repository.findByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("Email taken!");
        }

        if (!Util.patternMatches(Util.emailPattern, student.getEmail())) {
            throw new IllegalStateException("Email pattern invalid!");
        }

        if (!Util.validateDateFormat(student.getDob().toString())) {
            throw new IllegalStateException("Date of Birth invalid pattern!");
        }

        repository.save(student);


    }

    public void deleteStudent(Long studentId) {
        boolean exists = repository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exists!");
        }

        repository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = repository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("Student with id " + studentId + " does not exists!")
        );

        if (name != null && name.length() > 0 && !name.equals(student.getName())) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
            boolean isMatches = Util.patternMatches(
                    Util.emailPattern, email);

            if (!isMatches) {
                throw new IllegalStateException("Email pattern invalid!");
            }

            Optional<Student> optionalStudent = repository.findByEmail(email);
            if (optionalStudent.isPresent()) {
                throw new IllegalStateException("Email taken!");
            }

            student.setEmail(email);
        }
    }
}
