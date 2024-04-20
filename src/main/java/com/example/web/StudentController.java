package com.example.web;

import com.example.exceptions.StudentNotFoundException;
import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

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
    public Student findStudentById(@PathVariable String id) throws StudentNotFoundException {
        return studentService.findStudentById(id);
    }

    @GetMapping("/{programId}/program")
    public List<Student> findStudentsByProgramId(@PathVariable String programId) {
        return studentService.findStudentByProgramId(programId);
    }

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student createStudent(@PathVariable String id, @RequestBody Student student) {
        student.setId(id);
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }


}
