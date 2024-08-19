package com.example.repository;

import com.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student sampleStudent;

    @BeforeEach
    public void setUp() {
        sampleStudent = createStudent("INFO01", "STD01", "FAYE", "VINCENT");
        studentRepository.save(sampleStudent);
    }

    @Test
    public void testFindByProgramId() {
        List<Student> students = studentRepository.findByProgramId("INFO01");

        assertThat(students).isNotEmpty();
        assertThat(students.get(0)).usingRecursiveComparison().isEqualTo(sampleStudent);
    }

    @Test
    public void testFindByCode() {
        Student student = studentRepository.findByCode("STD01");

        assertThat(student).isNotNull();
        assertThat(student).usingRecursiveComparison().isEqualTo(sampleStudent);
    }

    private Student createStudent(String programId, String code, String lastName, String firstName) {
        return Student.builder()
            .id(UUID.randomUUID().toString())
            .programId(programId)
            .lastName(lastName)
            .firstName(firstName)
            .code(code)
            .build();
    }
}
