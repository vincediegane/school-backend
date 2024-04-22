package com.example.service;

import com.example.dto.StudentDTO;
import com.example.exceptions.StudentNotFoundException;
import com.example.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAllStudents();
    StudentDTO findStudentById(String id) throws StudentNotFoundException;
    List<StudentDTO> findStudentByProgramId(String programId);
    StudentDTO updateStudent(StudentDTO studentDTO);
    StudentDTO createStudent(StudentDTO studentDTO);
    void deleteStudent(String id);
}
