package com.example.web;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public List<Payment> findAllPayments() {
        return paymentService.findAllPayments();
    }

    @GetMapping("/{id}")
    public Payment findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @GetMapping("/{code}/students")
    public List<Payment> findPaymentByStudentCode(@PathVariable String code) {
        return paymentService.findPaymentByStudentCode(code);
    }

    @GetMapping("/byStatus")
    public List<Payment> findByStatus(@RequestParam PaymentStatus status) {
        return paymentService.findByStatus(status);
    }

    @GetMapping("/byType")
    public List<Payment> findByType(@RequestParam PaymentType type) {
        return paymentService.findByType(type);
    }

    @PutMapping("/{id}/updateStatus")
    public Payment updatePaymentStatus(@PathVariable Long id, @RequestBody PaymentStatus status) {
        return paymentService.updatePaymentStatus(id, status);
    }
}
