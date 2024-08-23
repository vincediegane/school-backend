package com.example.web;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    void testFindAllStudents() throws Exception {
        StudentDTO student = StudentDTO.builder().id("1").firstName("Clemence").lastName("Diouf").programId("INFO1").build();

        Mockito.when(studentService.findAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[0].firstName").value("Vincent"));
    }
}
