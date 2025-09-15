package com.gspann.product_service.config;

import static org.springframework.security.config.Customizer.withDefaults; // ✅ This is required

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ProductSecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter(jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/product/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/products/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/product/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/product/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/product/**").authenticated()
                .anyRequest().authenticated()
            )
         
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
