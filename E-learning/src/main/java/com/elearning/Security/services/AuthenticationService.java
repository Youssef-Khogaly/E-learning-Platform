package com.elearning.Security.services;

import com.elearning.Exceptions.BadRequestException;
import com.elearning.Security.CurrentUserDetails;
import com.elearning.Users.UserDto;
import com.elearning.Users.UserJpaRepo;
import com.elearning.entities.users.User;
import com.elearning.entities.users.UserRoles;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserJpaRepo userJpaRepo;
    private final JwtService jwtService;

    public String login(String email , String password) throws BadCredentialsException {
        var user = userJpaRepo.findByEmail(email).orElseThrow(() -> new BadCredentialsException("wrong email or password"));
        if(!encoder.matches(password,user.getPassword()))
         throw  new BadCredentialsException("wrong email or password");

        // logic successful , return jwt token
        Map<String,String> claims = new HashMap<>(2);
        claims.put("id",user.getId().toString());
        claims.put("authorities","ROLE_"+user.getRole().toString());

        return jwtService.generateNewToken(claims, Duration.ofHours(1L));
    }

    public User signup(String name , String email , String password , UserRoles role)
    {
        if(userJpaRepo.existsByEmail(email))
            throw new BadRequestException("email is already registered");
        if(userJpaRepo.existsByName(name))
            throw new BadRequestException("name is already used");

        var usr = new User();
        usr.setName(name);
        usr.setEmail(email);
        usr.setPassword(encoder.encode(password));
        usr.setRole(role);
        return userJpaRepo.save(usr);
    }
    public static Optional<CurrentUserDetails> getCurrentUser()
    {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null)
            return Optional.empty();

        return Optional.ofNullable((CurrentUserDetails) auth.getPrincipal());
    }
}
