package com.elearning.Security;

import com.elearning.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
@Component
public class AuthEntryPointCustom implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        var error = new ErrorResponse(HttpStatus.valueOf(response.getStatus()), List.of(authException.getMessage()),request.getRequestURI(), Instant.now());

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),error);
        response.getWriter().flush();
        response.flushBuffer();
    }
}
