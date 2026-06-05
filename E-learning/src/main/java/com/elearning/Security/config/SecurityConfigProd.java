package com.elearning.Security.config;

import com.elearning.ApplicationConstants;
import com.elearning.Security.AuthEntryPointCustom;
import com.elearning.Security.filters.JwtAuthenticationFilter;
import com.elearning.entities.users.UserRoles;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.List;

@Configuration
@Profile("prod")
public class SecurityConfigProd {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http ,
                                                   List<PathPatternRequestMatcher> publicApiMatchers,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter ) throws Exception {

        // csrf config
        http.csrf(
                csrf -> {

                    CookieCsrfTokenRepository repo = CookieCsrfTokenRepository.withHttpOnlyFalse();
                    repo.setCookieCustomizer(cu -> {
                        cu.sameSite("lax");
                        cu.maxAge(Duration.ofHours(12));
                    });
                    csrf.csrfTokenRepository(repo);
                    csrf.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
                    csrf.ignoringRequestMatchers(publicApiMatchers.toArray(new RequestMatcher[0]));
                }
        );
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
                                        .requestMatchers(HttpMethod.PUT,"/courses/*/sections/*/lessons/*/content").hasRole(UserRoles.Instructor.toString())
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

    @Bean
    List<String>corsAllowedOrigins(){
        return List.of();
    }
     //cors configration
    @Bean
    public CorsConfigurationSource corsConfig(@Qualifier("corsAllowedOrigins") List<String>corsAllowedOrigins ){
        var source = new UrlBasedCorsConfigurationSource();
        var general = new CorsConfiguration();

        // allowed origins
        general.setAllowedOrigins(corsAllowedOrigins);
        general.setAllowedHeaders(List.of(ApplicationConstants.JWT_HEADER_NAME,ApplicationConstants.CSRF_HEADER_NAME));
        general.setAllowCredentials(false);
        general.setMaxAge(Duration.ofHours(12));
        general.setExposedHeaders(List.of(ApplicationConstants.JWT_HEADER_NAME,ApplicationConstants.CSRF_HEADER_NAME));
        source.registerCorsConfiguration("/**",general);

        return source;
    }
}