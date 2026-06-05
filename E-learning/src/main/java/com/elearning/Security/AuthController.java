package com.elearning.Security;

import com.elearning.Security.DTO.LoginRequest;
import com.elearning.Security.DTO.LoginResponse;
import com.elearning.Security.DTO.SignupRequest;
import com.elearning.Security.services.AuthenticationService;
import com.elearning.entities.users.UserRoles;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Validated
@AllArgsConstructor
public class AuthController{

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<LoginResponse>login(@RequestBody @Valid LoginRequest request)
    {
        try {
            String jwtToken = authenticationService.login(request.email(),request.password());
            var response = new LoginResponse("Login success",jwtToken);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    ResponseEntity<LoginResponse>signup(@RequestBody @Valid SignupRequest request)
    {
        if(request.role() != UserRoles.Student  && request.role() != UserRoles.Instructor)
            return ResponseEntity.badRequest().build();
        var usr = authenticationService.signup(request.name(),request.email(),request.password(),request.role());
        return ResponseEntity.ok().build();
    }

}
