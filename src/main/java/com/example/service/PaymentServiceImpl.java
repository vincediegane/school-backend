package com.example.service;

import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }
}
