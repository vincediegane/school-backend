package com.example.repository;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentCode(String code);

    List<Payment> findByStatus(PaymentStatus status);

    List<Payment> findByType(PaymentType type);
}
