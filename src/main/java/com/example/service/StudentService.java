package com.example.service;

import com.example.exceptions.StudentNotFoundException;
import com.example.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    Student findStudentById(String id) throws StudentNotFoundException;
    List<Student> findStudentByProgramId(String programId);
}
