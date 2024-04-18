package com.example.service;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.model.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();
    List<Payment> findPaymentByStudentCode(String code);
    List<Payment> findByType(PaymentType type);
    List<Payment> findByStatus(PaymentStatus status);
    Payment findById(Long id);
    Payment updatePaymentStatus(Long id, PaymentStatus status);
}
