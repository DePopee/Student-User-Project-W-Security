package com.example.demo.Service;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.DTO.Response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponse> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return students.stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getStudentsByStudentGroup(String studentGroup) {
        List<StudentEntity> students = studentRepository.findByStudentGroup(studentGroup);
        return students.stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
    }

    public void addNewStudent(StudentEntity student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, String name, String email) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));
        if (name != null && !name.isEmpty()) {
            student.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            student.setEmail(email);
        }
        studentRepository.save(student);
    }
    public  Optional<StudentResponse> getStudentResponseById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(StudentResponse::new);
    }



}
