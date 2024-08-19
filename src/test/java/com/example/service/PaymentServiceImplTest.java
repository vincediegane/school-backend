//package com.example.service;
//
//import com.example.dto.PaymentDTO;
//import com.example.enumeration.PaymentStatus;
//import com.example.enumeration.PaymentType;
//import com.example.exceptions.PaymentNotFoundException;
//import com.example.mapper.MapperImpl;
//import com.example.model.Payment;
//import com.example.model.Student;
//import com.example.repository.PaymentRepository;
//import com.example.repository.StudentRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class PaymentServiceImplTest {
//
//    @Mock
//    private PaymentRepository paymentRepository;
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @Mock
//    private MapperImpl mapper;
//
//    @Mock
//    private MultipartFile multipartFile;
//
//    @InjectMocks
//    private PaymentServiceImpl paymentService;
//
//    private Payment payment;
//    private PaymentDTO paymentDTO;
//    private Student student;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        student = new Student();
//        student.setId("1");
//        student.setCode("STD01");
//
//        payment = Payment.builder()
//                .id(1L)
//                .amount(1000.0)
//                .status(PaymentStatus.CREATED)
//                .type(PaymentType.CASH)
//                .date(LocalDate.now())
//                .file("sample-file-path")
//                .student(student)
//                .build();
//
//        paymentDTO = PaymentDTO.builder()
//                .id(1L)
//                .amount(1000.0)
//                .status(PaymentStatus.CREATED)
//                .type(PaymentType.CASH)
//                .date(LocalDate.now())
//                .file("sample-file-path")
//                .studentId("1")
//                .build();
//    }
//
//    @Test
//    @DisplayName("Test de récupération de tous les paiements")
//    void testFindAllPayments() {
//        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//
//        List<PaymentDTO> paymentDTOs = paymentService.findAllPayments();
//
//        assertThat(paymentDTOs).isNotEmpty();
//        assertThat(paymentDTOs.get(0)).isEqualToComparingFieldByField(paymentDTO);
//        verify(paymentRepository, times(1)).findAll();
//    }
//
//    @Test
//    @DisplayName("Test de récupération des paiements par code étudiant")
//    void testFindPaymentByStudentCode() {
//        when(paymentRepository.findByStudentCode("STD01")).thenReturn(Arrays.asList(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//
//        List<PaymentDTO> paymentDTOs = paymentService.findPaymentByStudentCode("STD01");
//
//        assertThat(paymentDTOs).isNotEmpty();
//        assertThat(paymentDTOs.get(0)).isEqualToComparingFieldByField(paymentDTO);
//        verify(paymentRepository, times(1)).findByStudentCode("STD01");
//    }
//
//    @Test
//    @DisplayName("Test de récupération des paiements par type")
//    void testFindByType() {
//        when(paymentRepository.findByType(PaymentType.CASH)).thenReturn(Arrays.asList(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//
//        List<PaymentDTO> paymentDTOs = paymentService.findByType(PaymentType.CASH);
//
//        assertThat(paymentDTOs).isNotEmpty();
//        assertThat(paymentDTOs.get(0)).isEqualToComparingFieldByField(paymentDTO);
//        verify(paymentRepository, times(1)).findByType(PaymentType.CASH);
//    }
//
//    @Test
//    @DisplayName("Test de récupération des paiements par statut")
//    void testFindByStatus() {
//        when(paymentRepository.findByStatus(PaymentStatus.CREATED)).thenReturn(Arrays.asList(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//
//        List<PaymentDTO> paymentDTOs = paymentService.findByStatus(PaymentStatus.CREATED);
//
//        assertThat(paymentDTOs).isNotEmpty();
//        assertThat(paymentDTOs.get(0)).isEqualToComparingFieldByField(paymentDTO);
//        verify(paymentRepository, times(1)).findByStatus(PaymentStatus.CREATED);
//    }
//
//    @Test
//    @DisplayName("Test de récupération d'un paiement par ID")
//    void testFindById() {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//
//        PaymentDTO foundPayment = paymentService.findById(1L);
//
//        assertThat(foundPayment).isEqualToComparingFieldByField(paymentDTO);
//        verify(paymentRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    @DisplayName("Test de récupération d'un paiement par ID avec exception")
//    void testFindById_NotFound() {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.findById(1L));
//        verify(paymentRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    @DisplayName("Test de mise à jour du statut d'un paiement")
//    void testUpdatePaymentStatus() {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//        when(mapper.fromPaymentDTO(paymentDTO)).thenReturn(payment);
//        when(paymentRepository.save(payment)).thenReturn(payment);
//        paymentDTO.setStatus(PaymentStatus.VALIDATED);
//
//        PaymentDTO updatedPayment = paymentService.updatePaymentStatus(1L, PaymentStatus.VALIDATED);
//
//        assertThat(updatedPayment.getStatus()).isEqualTo(PaymentStatus.VALIDATED);
//        verify(paymentRepository, times(1)).save(payment);
//    }
//
//    @Test
//    @DisplayName("Test de sauvegarde d'un paiement")
//    void testSavePayment() throws IOException {
//        Path folderPath = Paths.get(System.getProperty("user.home"), "bills", "payments");
//        Files.createDirectories(folderPath);
//
//        when(studentRepository.findByCode("STD01")).thenReturn(student);
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
//
//        PaymentDTO savedPayment = paymentService.savePayment(multipartFile, 1000.0, PaymentType.CASH, "STD01");
//
//        assertThat(savedPayment).isEqualToComparingFieldByField(paymentDTO);
//        verify(paymentRepository, times(1)).save(any(Payment.class));
//    }
//
//    @Test
//    @DisplayName("Test de récupération du fichier de paiement")
//    void testGetPaymentFile() throws IOException {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
//        when(mapper.fromPayment(payment)).thenReturn(paymentDTO);
//
//        byte[] fileData = paymentService.getPaymentFile(1L);
//
//        assertThat(fileData).isNotEmpty();
//        verify(paymentRepository, times(1)).findById(1L);
//    }
//}
