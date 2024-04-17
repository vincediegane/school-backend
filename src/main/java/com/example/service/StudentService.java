package com.example.service;

import com.example.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    Student findStudentById(String id);
    List<Student> findStudentByProgramId(String programId);
}
