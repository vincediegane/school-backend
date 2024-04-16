package com.example.service;

import com.example.model.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();
    List<Payment> findPaymentByStudentCode(String code);
    Payment findById(Long id);
}
