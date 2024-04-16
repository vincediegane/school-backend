package com.example;

import com.example.enumeration.PaymentStatus;
import com.example.enumeration.PaymentType;
import com.example.model.Payment;
import com.example.model.Student;
import com.example.repository.PaymentRepository;
import com.example.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Bean
	CommandLineRunner run(StudentRepository studentRepository, PaymentRepository paymentRepository) {
		return args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Vincent").lastName("Faye").code("112233").programId("PCSM").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Babacar").lastName("Kadame").code("112244").programId("MPI").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Rokhaya").lastName("Diouf").code("112255").programId("BCG").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Aissatou").lastName("Kante").code("112266").programId("PCSM").build());

			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(student -> {
				for(int i = 0; i < 10; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
						.amount(1000+(int)(Math.random()+20000))
						.type(paymentTypes[index])
						.status(PaymentStatus.CREATED)
						.date(LocalDate.now())
						.build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
