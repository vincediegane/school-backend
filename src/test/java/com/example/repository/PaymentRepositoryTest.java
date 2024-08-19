package com.example.repository;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.model.Payment;
import com.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    private Student sampleStudent;

    @BeforeEach
    public void setUp() {
        sampleStudent = studentRepository.save(
            Student.builder()
                .id(UUID.randomUUID().toString())
                .code("STD01")
                .firstName("Vincent")
                .lastName("Faye")
                .programId("INFO01")
            .build()
        );
    }

    @Test
    public void testFindByStudentCode() {
        Payment payment = createPayment(sampleStudent, PaymentStatus.CREATED, PaymentType.CASH);
        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findByStudentCode("STD01");

        assertThat(payments).isNotEmpty();
        assertThat(payments.get(0).getStudent().getCode()).isEqualTo("STD01");
    }

    @Test
    public void testFindByStatus() {
        Payment payment = createPayment(null, PaymentStatus.CREATED, PaymentType.DEPOSIT);
        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findByStatus(PaymentStatus.CREATED);

        assertThat(payments).isNotEmpty();
        assertThat(payments.get(0).getStatus()).isEqualTo(PaymentStatus.CREATED);
    }

    @Test
    public void testFindByType() {
        Payment payment = createPayment(null, PaymentStatus.VALIDATED, PaymentType.CASH);
        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findByType(PaymentType.CASH);

        assertThat(payments).isNotEmpty();
        assertThat(payments.get(0).getType()).isEqualTo(PaymentType.CASH);
    }

    private Payment createPayment(Student student, PaymentStatus status, PaymentType type) {
        return Payment.builder()
            .student(student)
            .status(status)
            .type(type)
            .build();
    }
}
