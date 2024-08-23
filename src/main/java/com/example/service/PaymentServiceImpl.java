package com.example.service;

import com.example.dto.PaymentDTO;
import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.exceptions.PaymentNotFoundException;
import com.example.mapper.MapperImpl;
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
    private final MapperImpl mapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentRepository studentRepository, MapperImpl mapper) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PaymentDTO> findAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(mapper::fromPayment).toList();
    }

    @Override
    public List<PaymentDTO> findPaymentByStudentCode(String code) {
        List<Payment> payments = paymentRepository.findByStudentCode(code);
        return payments.stream().map(mapper::fromPayment).toList();
    }

    @Override
    public List<PaymentDTO> findByType(PaymentType type) {
        List<Payment> payments = paymentRepository.findByType(type);
        return payments.stream().map(mapper::fromPayment).toList();
    }

    @Override
    public List<PaymentDTO> findByStatus(PaymentStatus status) {
        List<Payment> payments = paymentRepository.findByStatus(status);
        return payments.stream().map(mapper::fromPayment).toList();
    }

    @Override
    public PaymentDTO findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        return mapper.fromPayment(payment);
    }

    @Override
    public PaymentDTO updatePaymentStatus(Long id, PaymentStatus status) {
        PaymentDTO paymentDTO = findById(id);
        paymentDTO.setStatus(status);
        Payment payment = paymentRepository.save(mapper.fromPaymentDTO(paymentDTO));
        return mapper.fromPayment(payment);
    }

    @Override
    public PaymentDTO savePayment(MultipartFile file, double amount, PaymentType type, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "bills", "payments");
        if(!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "bills", "payments", fileName+".pdf");
        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
            .date(LocalDate.now())
            .amount(amount)
            .file(filePath.toUri().toString())
            .status(PaymentStatus.CREATED)
            .student(student)
            .type(type)
        .build();
        Payment savedPayment = paymentRepository.save(payment);
        return mapper.fromPayment(savedPayment);
    }

    @Override
    public byte[] getPaymentFile(Long paymentId) throws IOException {
        PaymentDTO paymentDTO = findById(paymentId);
        return Files.readAllBytes(Path.of(URI.create(paymentDTO.getFile())));
    }
}
