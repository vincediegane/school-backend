package com.example.web;

import com.example.dto.ClassDTO;
import com.example.dto.StudentDTO;
import com.example.mapper.MapperImpl;
import com.example.service.ClasseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    private final ClasseService classeService;
    private final MapperImpl mapper;

    public ClasseController(ClasseService classeService, MapperImpl mapper) {
        this.classeService = classeService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<ClassDTO> findAllClasses() {
        return classeService.findAllClasses();
    }
}
