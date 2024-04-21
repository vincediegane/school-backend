package com.example.web;

import com.example.dto.ErrorDTO;
import com.example.dto.StudentDTO;
import com.example.exceptions.StudentNotFoundException;
import com.example.model.Student;
import com.example.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public List<StudentDTO> findAllStudent() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO findStudentById(@PathVariable String id) throws StudentNotFoundException {
        return studentService.findStudentById(id);
    }

    @GetMapping("/{programId}/program")
    public List<StudentDTO> findStudentsByProgramId(@PathVariable String programId) {
        return studentService.findStudentByProgramId(programId);
    }

    @PostMapping("/")
    public StudentDTO createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public StudentDTO createStudent(@PathVariable String id, @RequestBody Student student) {
        student.setId(id);
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
