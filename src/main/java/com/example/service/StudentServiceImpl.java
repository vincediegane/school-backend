package com.example.service;

import com.example.dto.StudentDTO;
import com.example.exceptions.StudentNotFoundException;
import com.example.mapper.MapperImpl;
import com.example.model.Student;
import com.example.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MapperImpl mapper;

    public StudentServiceImpl(StudentRepository studentRepository, MapperImpl mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(mapper::fromStudent).toList();
    }

    @Override
    public StudentDTO findStudentById(String id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id : "+ id + " not found"));
        return mapper.fromStudent(student);
    }

    @Override
    public List<StudentDTO> findStudentByProgramId(String programId) {
        List<Student> students = studentRepository.findByProgramId(programId);
        return students.stream().map(mapper::fromStudent).toList();
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student savedStudent = studentRepository.save(mapper.fromStudentDTO(studentDTO));
        return mapper.fromStudent(savedStudent);
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student savedStudent = studentRepository.save(mapper.fromStudentDTO(studentDTO));
        return mapper.fromStudent(savedStudent);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
