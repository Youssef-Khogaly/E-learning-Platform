package com.elearning.Security.config;

import com.elearning.Security.AuthEntryPointCustom;
import com.elearning.Security.filters.JwtAuthenticationFilter;
import com.elearning.entities.users.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@Configuration
@Profile("test")
public class SecurityConfigTest {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http  ,
                                                   List<PathPatternRequestMatcher> publicApiMatchers
                                                , JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {
        http
                .authorizeHttpRequests(
                        (requests) ->
                                requests
                                        // courses config
                                        .requestMatchers(HttpMethod.POST,"/courses/**").hasRole(UserRoles.Instructor.toString())
                                        .requestMatchers(HttpMethod.PUT,"/courses/**").hasRole(UserRoles.Instructor.toString())
                                        .requestMatchers(HttpMethod.DELETE,"/courses/**").hasRole(UserRoles.Instructor.toString())
                                        // instructor courses
                                        .requestMatchers(HttpMethod.GET,"/courses/mine/**").hasRole(UserRoles.Instructor.toString())
                                        .requestMatchers(HttpMethod.GET,"courses/enrolled/*").authenticated()

                                        // lesson content
                                        .requestMatchers(HttpMethod.GET,"/courses/*/sections/*/lessons/*/content").authenticated()
                                        // payments
                                        .requestMatchers("/payment/**").authenticated()

                                        // videos , only instructor allowed to access or upload videos, students should use get content endpoint
                                        .requestMatchers("/videos/**").hasRole(UserRoles.Instructor.toString())
                                        .requestMatchers(publicApiMatchers.toArray(new RequestMatcher[0])).permitAll()
                );
        // disable session creation
        http.sessionManagement(s ->{
            s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        // disable form login and http basic authentication
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        // cors protection.
        http.cors(AbstractHttpConfigurer::disable);
        // csrf protection.
        http.csrf(AbstractHttpConfigurer::disable);
        // logout
        http.logout(c-> {
            c.deleteCookies("XSRF-TOKEN");
            c.clearAuthentication(true);
        });

        // jwt validation
         http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // exception handling
        http.exceptionHandling(
                c -> c.authenticationEntryPoint(new AuthEntryPointCustom())
        );
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // for jwt validation filter to skip validation
    @Bean
    List<PathPatternRequestMatcher> publicApiMatchers(){
        var course = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,"/courses/*");
        var section = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,"/courses/*/sections/*");
        var lesson = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,"/courses/*/sections/*/lessons");
        var login = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST,"/api/auth/login");
        var reg = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST,"/api/auth/signup");
        var webhook = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST,"/api/webhooks/*");

        return List.of(course,section,lesson,reg,login,webhook);
    }

}