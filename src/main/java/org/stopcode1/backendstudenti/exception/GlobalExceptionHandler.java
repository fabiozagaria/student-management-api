package org.stopcode1.backendstudenti.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //404
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<APIError> handleStudentNotFound(
            StudentNotFoundException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        APIError apiError = new APIError(
                Instant.now(),
                "STUDENT_NOT_FOUND",
                request.getRequestURI(),
                exception.getMessage(),
                status

        );
        return new ResponseEntity<>(apiError, status);

    }
    //409
    @ExceptionHandler(ConflictStudentException.class)
    public ResponseEntity<APIError> handleStudentConflict(
            ConflictStudentException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;
        APIError apiError = new APIError(
                Instant.now(),
                "CONFLICT_STUDENT",
                request.getRequestURI(),
                exception.getMessage(),
                status
        );
        return new ResponseEntity<>(apiError, status);

    }

    //400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorValidation> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        APIErrorValidation apiError = new APIErrorValidation(
                Instant.now(),
                "BAD_REQUEST",
                request.getRequestURI(),
                exception.getMessage(),
                status,
                getErrors(exception)

        );
        return new ResponseEntity<>(apiError, status);
    }

    //500
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<APIError> handleDatabaseEx(
            DatabaseException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        APIError apiError = new APIError(
                Instant.now(),
                "INTERNAL_ERROR",
                request.getRequestURI(),
                exception.getMessage(),
                status

        );

        return  new ResponseEntity<>(apiError, status);
    }

    private List<FieldValidationError> getErrors(MethodArgumentNotValidException exception) {
        List<FieldValidationError> fieldValidationErrors;
        return fieldValidationErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldValidationError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

    }
}
