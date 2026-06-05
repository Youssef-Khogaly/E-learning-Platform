package com.elearning.Exceptions;

import com.elearning.ErrorResponse;
import com.elearning.External.VideoExternalService.Exceptions.ApiVideoRateLimiterException;
import com.elearning.External.VideoExternalService.Exceptions.ApiVideoUnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandling {



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundEntityHandler(NotFoundException exception , HttpServletRequest req)
    {
        var err  = ErrorResponse.builder().status(HttpStatus.NOT_FOUND)
                .message(Collections.singletonList(exception.getMessage()))
                .path(req.getPathInfo()).timeStamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(ApiVideoUnavailableException.class)
    public ResponseEntity<?> ApiVideoUnavailable(ApiVideoUnavailableException exception , HttpServletRequest req)
    {
        var err  = ErrorResponse.builder().status(HttpStatus.SERVICE_UNAVAILABLE)
                .message(Collections.singletonList(exception.getMessage()))
                .path(req.getPathInfo()).timeStamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
    }
    @ExceptionHandler(ApiVideoRateLimiterException.class)
    public ResponseEntity<?> ApiVideoUnavailable(ApiVideoRateLimiterException exception , HttpServletRequest req)
    {
        var err  = ErrorResponse.builder().status(HttpStatus.TOO_MANY_REQUESTS)
                .message(Collections.singletonList(exception.getMessage()))
                .path(req.getPathInfo()).timeStamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).header("Retry-After", String.valueOf(exception.getRetryAfterInSeconds())).body(err);
    }

    @ExceptionHandler(exception = {UnAllowedStateTransitionException.class, BadRequestException.class, NotAllowedOperation.class , MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequest(Exception exception , HttpServletRequest req)
    {
        var err  = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST)
                .message(Collections.singletonList(exception.getMessage()))
                .path(req.getPathInfo()).timeStamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(exception = {UnAuthorizedException.class})
    public ResponseEntity<?> unAuthorized(Exception exception , HttpServletRequest req)
    {
        var err  = ErrorResponse.builder().status(HttpStatus.FORBIDDEN)
                .message(Collections.singletonList(exception.getMessage()))
                .path(req.getPathInfo()).timeStamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
}
