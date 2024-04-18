package com.example.service;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findPaymentByStudentCode(String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @Override
    public List<Payment> findByType(PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @Override
    public List<Payment> findByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    @Override
    public Payment updatePaymentStatus(Long id, PaymentStatus status) {
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}
