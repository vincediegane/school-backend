package com.example.web;

import com.example.dto.StudentDTO;
import com.example.exceptions.StudentNotFoundException;
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
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        studentDTO.setId(id);
        return studentService.updateStudent(studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
