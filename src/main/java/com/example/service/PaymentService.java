package com.example.service;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.model.Payment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();
    List<Payment> findPaymentByStudentCode(String code);
    List<Payment> findByType(PaymentType type);
    List<Payment> findByStatus(PaymentStatus status);
    Payment findById(Long id);
    Payment updatePaymentStatus(Long id, PaymentStatus status);
    Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException;
    byte[] getPaymentFile(Long paymentId) throws IOException;
}
