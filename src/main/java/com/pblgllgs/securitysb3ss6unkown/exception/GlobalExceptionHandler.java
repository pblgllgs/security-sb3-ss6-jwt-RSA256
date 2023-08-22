package com.pblgllgs.securitysb3ss6unkown.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(
//            Exception e,
//            HttpServletRequest request
//    ) {
//        ErrorResponse ErrorResponse = new ErrorResponse(
//                request.getRequestURI(),
//                e.getClass().getSimpleName(),
//                e.getMessage(),
//                HttpStatus.BAD_REQUEST.value(),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(ErrorResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(
            SQLIntegrityConstraintViolationException e,
            HttpServletRequest request
    ) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(ErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
