package com.example.service;

import com.example.dto.ClassDTO;

import java.util.List;

public interface ClasseService {
    List<ClassDTO> findAllClasses();
    ClassDTO findClassById(Long id);
    ClassDTO updateClass(ClassDTO classDTO);
    ClassDTO createClass(ClassDTO classDTO);
    void deleteClass(Long id);
}
