package com.example.web;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
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

    @GetMapping(value = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment createPayment(@RequestParam MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        return paymentService.savePayment(file, date, amount, type, studentCode);
    }

    @PutMapping("/{id}/updateStatus")
    public Payment updatePaymentStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
        return paymentService.updatePaymentStatus(id, status);
    }
}
