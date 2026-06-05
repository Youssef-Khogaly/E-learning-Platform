package com.elearning.Security.filters;


import com.elearning.ApplicationConstants;
import com.elearning.Security.CurrentUserDetails;
import com.elearning.Security.services.JwtService;
import com.elearning.entities.users.UserRoles;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final List<PathPatternRequestMatcher> skipValidationMatchersList;
    private final JwtService jwtService;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public JwtAuthenticationFilter(@Qualifier("publicApiMatchers") List<PathPatternRequestMatcher> skipValidationMatchersList, JwtService jwtService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.skipValidationMatchersList = skipValidationMatchersList;
        this.jwtService = jwtService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    private void createSuccessfulAuthenticationObject(long id , String authorities){
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authorities);
        var role = UserRoles.from(grantedAuthority);
        var authoritySet = new HashSet<>(Collections.singleton(grantedAuthority));

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(new CurrentUserDetails(id,role),null,authoritySet);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(ApplicationConstants.JWT_HEADER_NAME);
        Claims claims;
        try{
            claims = jwtService.verify(token);
        }catch (BadCredentialsException | CredentialsExpiredException e ){
            authenticationEntryPoint.commence(request,response,e);
            return;
        }

        long id = Long.parseLong(claims.get("id",String.class));

        String authorities = claims.get("authorities",String.class);
        createSuccessfulAuthenticationObject(id,authorities);


        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {

        for(PathPatternRequestMatcher matcher : skipValidationMatchersList){
            if(matcher.matches(request))
                return true;
        }

        return false;
    }

}
