package com.example.web;

import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public List<Student> findAllStudent() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable String id) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/{programId}/program")
    public List<Student> findStudentsByProgramId(@PathVariable String programId) {
        return studentService.findStudentByProgramId(programId);
    }
}
