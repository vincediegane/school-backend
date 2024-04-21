package com.example.service;

import com.example.dto.StudentDTO;
import com.example.exceptions.StudentNotFoundException;
import com.example.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAllStudents();
    StudentDTO findStudentById(String id) throws StudentNotFoundException;
    List<StudentDTO> findStudentByProgramId(String programId);
    StudentDTO updateStudent(Student student);
    StudentDTO createStudent(Student student);
    void deleteStudent(String id);
}
