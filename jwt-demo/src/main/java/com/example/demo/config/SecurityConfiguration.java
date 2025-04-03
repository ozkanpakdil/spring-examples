package com.example.demo.config;

import com.example.demo.jwt.JwtAccessDeniedHandler;
import com.example.demo.jwt.JwtAuthFilter;
import com.example.demo.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.jwt.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserAuthProvider userAuthProvider;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                 UserAuthProvider userAuthProvider,
                                 @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userAuthProvider = userAuthProvider;
        this.resolver = resolver;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthFilter(userAuthProvider, resolver), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception) -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/api-docs.yaml").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/configuration/security").permitAll()
                        .requestMatchers("/webjars/**").permitAll()

                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/profiles/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/profiles/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/profiles/{id}").authenticated()

                        .requestMatchers("/subscriptions/**").authenticated()

                        .requestMatchers("/photo/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/news/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/news/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/news/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/news/**").authenticated()

                        .requestMatchers("/accounts/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/languages").permitAll()
                        .requestMatchers(HttpMethod.GET, "/towns").permitAll()

                        .requestMatchers(HttpMethod.POST, "/languages").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/towns").hasAnyRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().denyAll()
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }
}
