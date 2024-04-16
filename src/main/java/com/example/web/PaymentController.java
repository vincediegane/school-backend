package com.example.web;

import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<Payment> findAllPayments() {
        return paymentService.findAllPayments();
    }

    @GetMapping("/payments/{id}")
    public Payment findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @GetMapping("/payments/{code}/students")
    public List<Payment> findPaymentByStudentCode(@PathVariable String code) {
        return paymentService.findPaymentByStudentCode(code);
    }
}
