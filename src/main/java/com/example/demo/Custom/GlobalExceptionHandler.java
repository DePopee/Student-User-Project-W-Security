package com.example.demo.Custom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<CustomBaseResponse<Void>> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        CustomBaseResponse<Void> response = new CustomBaseResponse<>("409", "Email already in use");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity<CustomBaseResponse<Void>> handleUsernameAlreadyInUseException(UsernameAlreadyInUseException ex) {
        CustomBaseResponse<Void> response = new CustomBaseResponse<>("409", "Username already in use");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CustomBaseResponse<Void>> handleNotFoundException(NoHandlerFoundException ex) {
        CustomBaseResponse<Void> response = new CustomBaseResponse<>("404", "Endpoint not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(DateTimeParseException.class)
//    public ResponseEntity<CustomBaseResponse<Void>> handleDateTimeParseException(DateTimeParseException ex) {
//        CustomBaseResponse<Void> response = new CustomBaseResponse<>("400", "Invalid date format", ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomBaseResponse<Void>> handleGenericException(Exception ex) {
        CustomBaseResponse<Void> response = new CustomBaseResponse<>("500", "Internal Server Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
