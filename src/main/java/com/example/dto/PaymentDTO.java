package com.example.dto;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private String file;
    private StudentDTO studentDTO;

//    MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode
}
