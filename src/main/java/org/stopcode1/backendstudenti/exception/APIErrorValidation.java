package org.stopcode1.backendstudenti.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record APIErrorValidation(
        Instant timestamp,
        String errorName,
        String path,
        String details,
        HttpStatus status,
        List<FieldValidationError> errors
) {
}
