package com.example.practice.project.config;

import static com.example.practice.project.utilities.Constants.APACKAGE;
import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.AUTH;
import static com.example.practice.project.utilities.Constants.COMPANY;
import static com.example.practice.project.utilities.Constants.LOGIN;
import static com.example.practice.project.utilities.Constants.USER;

import com.example.practice.project.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurer {

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> {
                request.requestMatchers(HttpMethod.POST, API + AUTH + LOGIN).permitAll();
                request.requestMatchers("/users").hasAnyAuthority("USER", "ADMIN");
                request.requestMatchers(HttpMethod.GET, "/version").permitAll();
                request.requestMatchers(HttpMethod.GET, API + USER + "/**").permitAll();
                request.requestMatchers(HttpMethod.POST, API + USER + "/**").permitAll();
                request.requestMatchers(HttpMethod.PUT, API + USER + "/**").permitAll();
                request.requestMatchers(HttpMethod.DELETE, API + USER + "/**").permitAll();
                request.requestMatchers(HttpMethod.GET, API + APACKAGE + "/**").permitAll();
                request.requestMatchers(HttpMethod.POST, API + APACKAGE + "/**").permitAll();
                request.requestMatchers(HttpMethod.PUT, API + APACKAGE + "/**").permitAll();
                request.requestMatchers(HttpMethod.DELETE, API + APACKAGE + "/**").permitAll();
                request.requestMatchers(HttpMethod.GET, API + COMPANY + "/**").permitAll();
                request.requestMatchers(HttpMethod.POST, API + COMPANY + "/**").permitAll();
                request.requestMatchers(HttpMethod.PUT, API + COMPANY + "/**").permitAll();
                request.requestMatchers(HttpMethod.DELETE, API + COMPANY + "/**").permitAll();
            }).formLogin(Customizer.withDefaults()).build();

    }

}
