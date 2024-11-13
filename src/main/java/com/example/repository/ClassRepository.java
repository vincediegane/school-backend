package com.example.repository;

import com.example.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Classe, Long> {
    Classe findByClassName(String className);
}
