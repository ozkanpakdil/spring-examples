package dev.demo.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Build Authentication from the mTLS client certificate (X.509)
            .x509(x -> x
                // Extract username from Subject DN's CN component (matches our demo cert CN=demo-client)
                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/hello").hasRole("CLIENT")
                .anyRequest().denyAll()
            )
            // For simple API demo; adjust as needed if you add browser forms
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // Map the extracted principal (e.g., "demo-client") to application roles.
    // In production, back this with your identity source (DB/LDAP/etc.).
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails demoClient = User
            .withUsername("demo-client")
            .password("{noop}n/a") // password not used for X.509
            .roles("CLIENT")
            .build();
        return new InMemoryUserDetailsManager(demoClient);
    }
}
