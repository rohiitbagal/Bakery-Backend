package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())

            // ✅ FIX CORS
            .cors(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth
                // ✅ allow login
                .requestMatchers("/api/admin/**").permitAll()

                // ✅ allow categories (IMPORTANT)
                .requestMatchers("/api/categories/**").permitAll()

                // ✅ allow products if needed
                .requestMatchers("/api/products/**").permitAll()

                // ❗ everything else secured
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
