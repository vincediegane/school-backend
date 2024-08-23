package com.example.service;

import com.example.dto.StudentDTO;
import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.exceptions.StudentNotFoundException;
import com.example.mapper.MapperImpl;
import com.example.model.Payment;
import com.example.model.Student;
import com.example.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MapperImpl mapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student sampleStudent;
    private StudentDTO sampleStudentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleStudent = Student.builder().id("1").code("STD01").firstName("Vincent").lastName("Faye").programId("INFO01").build();
        sampleStudentDTO = StudentDTO.builder().id("1").code("STD01").firstName("Vincent").lastName("Faye").programId("INFO01").build();

    }

    @Test
    public void testCreateStudent() {
        when(mapper.fromStudentDTO(sampleStudentDTO)).thenReturn(sampleStudent);
        when(studentRepository.save(sampleStudent)).thenReturn(sampleStudent);
        when(mapper.fromStudent(sampleStudent)).thenReturn(sampleStudentDTO);

        StudentDTO createdStudent = studentService.createStudent(sampleStudentDTO);

        assertThat(createdStudent).usingRecursiveComparison().isEqualTo(sampleStudentDTO);
        verify(studentRepository, times(1)).save(sampleStudent);
    }

    @Test
    public void testFindAllStudent() throws StudentNotFoundException {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(sampleStudent));
        when(mapper.fromStudent(sampleStudent)).thenReturn(sampleStudentDTO);

        List<StudentDTO> studentDTOs = studentService.findAllStudents();

        assertThat(studentDTOs).isNotEmpty();
        assertThat(studentDTOs.get(0)).usingRecursiveComparison().isEqualTo(sampleStudentDTO);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testFindStudentById() {
        when(studentRepository.findById("1")).thenReturn(Optional.of(sampleStudent));
        when(mapper.fromStudent(sampleStudent)).thenReturn(sampleStudentDTO);

        StudentDTO foundStudent = studentService.findStudentById("1");

        assertThat(foundStudent).usingRecursiveComparison().isEqualTo(sampleStudentDTO);
        verify(studentRepository, times(1)).findById("1");
    }

    @Test
    void testFindStudentById_NotFound() {
        when(studentRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById("1"));
        verify(studentRepository, times(1)).findById("1");
    }

    @Test
    public void testFindStudentByProgramId() {
        when(studentRepository.findByProgramId("INFO01")).thenReturn(Arrays.asList(sampleStudent));
        when(mapper.fromStudent(sampleStudent)).thenReturn(sampleStudentDTO);

        List<StudentDTO> studentDTOs = studentService.findStudentByProgramId("INFO01");

        assertThat(studentDTOs).isNotEmpty();
        assertThat(studentDTOs.get(0)).usingRecursiveComparison().isEqualTo(sampleStudentDTO);
        verify(studentRepository, times(1)).findByProgramId("INFO01");
    }

    @Test
    public void testUpdateStudent() {
        when(mapper.fromStudentDTO(sampleStudentDTO)).thenReturn(sampleStudent);
        when(studentRepository.save(sampleStudent)).thenReturn(sampleStudent);
        when(mapper.fromStudent(sampleStudent)).thenReturn(sampleStudentDTO);

        StudentDTO updatedStudent = studentService.updateStudent(sampleStudentDTO);

        assertThat(updatedStudent).usingRecursiveComparison().isEqualTo(sampleStudentDTO);
        verify(studentRepository, times(1)).save(sampleStudent);
    }

    @Test
    public void testdeleteStudent() {
        doNothing().when(studentRepository).deleteById("1");
        studentService.deleteStudent("1");
        verify(studentRepository, times(1)).deleteById("1");
    }
}
