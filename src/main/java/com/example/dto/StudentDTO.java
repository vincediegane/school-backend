package com.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String code;
    private String programId;
}
