package com.example.service;

import com.example.exceptions.StudentNotFoundException;
import com.example.model.Student;
import com.example.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id : "+ id + " not found"));
    }

    @Override
    public List<Student> findStudentByProgramId(String programId) {
        return studentRepository.findByProgramId(programId);
    }
}
