package com.example.service;

import com.example.dto.PaymentDTO;
import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.model.Payment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    List<PaymentDTO> findAllPayments();
    List<PaymentDTO> findPaymentByStudentCode(String code);
    List<PaymentDTO> findByType(PaymentType type);
    List<PaymentDTO> findByStatus(PaymentStatus status);
    PaymentDTO findById(Long id);
    PaymentDTO updatePaymentStatus(Long id, PaymentStatus status);
    PaymentDTO savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException;
    byte[] getPaymentFile(Long paymentId) throws IOException;
}
