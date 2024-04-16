package com.example.web;

import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import com.example.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;


    public PaymentController(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/payments")
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment findById(@PathVariable Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    @GetMapping("/payments/{code}/students")
    public List<Payment> findPaymentByStudentCode(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }
}
