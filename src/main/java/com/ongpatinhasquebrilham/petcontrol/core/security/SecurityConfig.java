package com.ongpatinhasquebrilham.petcontrol.core.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pets").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/pets").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pets/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/pets/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/pets/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/pets/{id}/available").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/pets/{id}/available").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pets/unavailable").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}