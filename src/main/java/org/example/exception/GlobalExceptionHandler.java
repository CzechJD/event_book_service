package org.example.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorMessageResponse> handleValidationException(Exception e) {
        log.error("Error handling", e);
        String errorMessage = e instanceof MethodArgumentNotValidException ?
                ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage() :
                e.getMessage();

        return buildErrorResponse("Невалидный аргумент", errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorMessageResponse> handleNotExistException(EntityNotFoundException e) {
        log.error("Error handling", e);
        return buildErrorResponse("Сущность не найдена", e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorMessageResponse> handleCapacityException(IllegalArgumentException e) {
        log.error("Error handling", e);
        return buildErrorResponse("Невалидный аргумент", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorMessageResponse> handleGenericException(Exception e) {
        log.error("Error handling", e);
        return buildErrorResponse("Внутренняя ошибка сервера", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorMessageResponse> handleCredentialsException(BadCredentialsException e) {
        log.error("error handling", e);
        return buildErrorResponse("Неверный логин или пароль", e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ErrorMessageResponse> buildErrorResponse(String errorType, String errorMessage, HttpStatus httpStatus) {
        var error = new ErrorMessageResponse(errorType, errorMessage, LocalDateTime.now());
        return new ResponseEntity<>(error, httpStatus);
    }
}
