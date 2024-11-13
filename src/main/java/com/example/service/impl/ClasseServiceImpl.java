package com.example.service.impl;

import com.example.dto.ClassDTO;
import com.example.mapper.MapperImpl;
import com.example.model.Classe;
import com.example.model.Student;
import com.example.repository.ClassRepository;
import com.example.service.ClasseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClasseServiceImpl implements ClasseService {
    private final ClassRepository classRepository;
    private final MapperImpl mapper;

    public ClasseServiceImpl(ClassRepository classRepository, MapperImpl mapper) {
        this.classRepository = classRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ClassDTO> findAllClasses() {
        List<Classe> classes = classRepository.findAll();
        return classes.stream().map(mapper::fromClass).toList();
    }

    @Override
    public ClassDTO findClassById(Long id) {
        return null;
    }

    @Override
    public ClassDTO updateClass(ClassDTO classDTO) {
        return null;
    }

    @Override
    public ClassDTO createClass(ClassDTO classDTO) {
        return null;
    }

    @Override
    public void deleteClass(Long id) {

    }
}
