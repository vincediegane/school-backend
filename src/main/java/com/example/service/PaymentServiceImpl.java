package com.example.service;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.exceptions.PaymentNotFoundException;
import com.example.model.Payment;
import com.example.model.Student;
import com.example.repository.PaymentRepository;
import com.example.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
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

    @Override
    public Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "bills", "payments");
        if(!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "bills", "payments", fileName+".pdf");
        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
            .date(date)
            .amount(amount)
            .file(filePath.toUri().toString())
            .status(PaymentStatus.CREATED)
            .student(student)
            .type(type)
            .build();
        return paymentRepository.save(payment);
    }

    @Override
    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = findById(paymentId);
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
