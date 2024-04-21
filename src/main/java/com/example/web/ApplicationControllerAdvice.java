package com.example.web;

import com.example.dto.ErrorDTO;
import com.example.exceptions.PaymentNotFoundException;
import com.example.exceptions.StudentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({StudentNotFoundException.class})
    public @ResponseBody ErrorDTO handleStudentNotFound(StudentNotFoundException exception) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({PaymentNotFoundException.class})
    public @ResponseBody ErrorDTO handlePaymentNotFound(PaymentNotFoundException exception) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
    public @ResponseBody ErrorDTO handleRuntimeException(RuntimeException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }
}
