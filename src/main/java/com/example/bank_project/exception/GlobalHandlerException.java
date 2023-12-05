package com.example.bank_project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

    @RestControllerAdvice
    @Slf4j
    public class GlobalHandlerException extends ResponseEntityExceptionHandler {

        @ExceptionHandler({NotFoundException.class})
        public ResponseEntity<Object> handleEntityNotFoundException(Exception ex) {
            log.error("Error 404 {}", ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler({ValidationException.class})
        public ResponseEntity<Object> handleEntityValidationException(Exception ex) {
            log.error("Error 400 {}", ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }



    }


