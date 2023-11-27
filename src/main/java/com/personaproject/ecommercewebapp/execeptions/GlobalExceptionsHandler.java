package com.personaproject.ecommercewebapp.execeptions;

import com.personaproject.ecommercewebapp.execeptions.customExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
//    @ExceptionHandler(value = UnverifiableTokenException.class)
//    public ResponseEntity<?> handleUnknownExceptions(Exception exception) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
//    }

    @ExceptionHandler
    public ResponseEntity<?> handleIllegalAccessException(Exception exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = unrecognizedAuthenticationJobException.class)
    public ResponseEntity<?> handleUnrecognizedAuthenticationJobException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = userAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = InvalidUserException.class)
    public ResponseEntity<?> handleInvalidUserException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = PasswordIncorrectException.class)
    public ResponseEntity<?> handlePasswordIncorrectException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = WrongTokenException.class)
    public ResponseEntity<?> handleWrongTokenException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
