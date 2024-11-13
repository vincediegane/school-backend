package com.example.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassDTO {
    private Long id;
    private String className;
    private String schoolName;
    private Integer number_of_students;
    private List<CourseDTO> courses;
}
