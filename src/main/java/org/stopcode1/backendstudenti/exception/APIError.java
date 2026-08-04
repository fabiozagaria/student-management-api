package org.stopcode1.backendstudenti.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public record APIError(
        Instant timestamp,
        String errorName,
        String path,
        String details,
        HttpStatus status

) {
}