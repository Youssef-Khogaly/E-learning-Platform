package com.elearning;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Builder
public record ErrorResponse(
        HttpStatus status,
        List<String> message,
        String path,
        Instant timeStamp

) {
}