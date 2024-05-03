package com.example.practice.project.config;

import static com.example.practice.project.utilities.Constants.APACKAGE;
import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.AUTH;
import static com.example.practice.project.utilities.Constants.CHANGE_PASSWORD;
import static com.example.practice.project.utilities.Constants.COMPANY;
import static com.example.practice.project.utilities.Constants.FILE;
import static com.example.practice.project.utilities.Constants.LOGIN;
import static com.example.practice.project.utilities.Constants.LOOKUP;
import static com.example.practice.project.utilities.Constants.PAYMENT;
import static com.example.practice.project.utilities.Constants.USER;

import com.example.practice.project.jwt.AuthEntryPointJwt;
import com.example.practice.project.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    public SecurityFilterChain filterChain(HttpSecurity http, AuthEntryPointJwt authEntryPointJwt) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().antMatchers(HttpMethod.POST, API + AUTH + LOGIN).permitAll()
            .antMatchers(HttpMethod.POST, API).permitAll()
            //.antMatchers(HttpMethod.POST, API + AUTH + LOGIN).permitAll()
            //.antMatchers("/users").hasAnyAuthority("USER", "ADMIN")
            .antMatchers(HttpMethod.GET, "/version").permitAll()
            .antMatchers(HttpMethod.POST, API + AUTH + CHANGE_PASSWORD).permitAll()
            .antMatchers(HttpMethod.GET, API + USER + "/**").permitAll()
            //.antMatchers(HttpMethod.GET, API + USER + "/**").hasAnyAuthority("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, API + USER + "/**").permitAll()
            .antMatchers(HttpMethod.PUT, API + USER + "/**").permitAll()
            .antMatchers(HttpMethod.DELETE, API + USER + "/**").permitAll()
            .antMatchers(HttpMethod.GET, API + APACKAGE + "/**").permitAll()
            .antMatchers(HttpMethod.POST, API + APACKAGE + "/**").permitAll()
            .antMatchers(HttpMethod.PUT, API + APACKAGE + "/**").permitAll()
            .antMatchers(HttpMethod.DELETE, API + APACKAGE + "/**").permitAll()
            .antMatchers(HttpMethod.GET, API + COMPANY + "/**").permitAll()
            .antMatchers(HttpMethod.POST, API + COMPANY + "/**").permitAll()
            .antMatchers(HttpMethod.PUT, API + COMPANY + "/**").permitAll()
            .antMatchers(HttpMethod.DELETE, API + COMPANY + "/**").permitAll()
            .antMatchers(HttpMethod.GET, API + PAYMENT + "/**").permitAll()
            .antMatchers(HttpMethod.POST, API + PAYMENT + "/**").permitAll()
            .antMatchers(HttpMethod.PUT, API + PAYMENT + "/**").permitAll()
            .antMatchers(HttpMethod.GET, API + LOOKUP + "/**").permitAll()
            .antMatchers(HttpMethod.DELETE, API + COMPANY + "/**").permitAll()
            .antMatchers(HttpMethod.POST, API + FILE + "/**").permitAll()
            .antMatchers(HttpMethod.GET, API + FILE + "/**").permitAll()
            .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//            .cors(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(request -> {
//                request.requestMatchers(HttpMethod.POST, API + AUTH + LOGIN).permitAll();
//                request.requestMatchers("/users").hasAnyAuthority("USER", "ADMIN");
//                request.requestMatchers(HttpMethod.GET, "/version").permitAll();
//                request.requestMatchers(HttpMethod.GET, API + USER + "/**").permitAll();
//                request.requestMatchers(HttpMethod.POST, API + USER + "/**").permitAll();
//                request.requestMatchers(HttpMethod.PUT, API + USER + "/**").permitAll();
//                request.requestMatchers(HttpMethod.DELETE, API + USER + "/**").permitAll();
//                request.requestMatchers(HttpMethod.GET, API + APACKAGE + "/**").permitAll();
//                request.requestMatchers(HttpMethod.POST, API + APACKAGE + "/**").permitAll();
//                request.requestMatchers(HttpMethod.PUT, API + APACKAGE + "/**").permitAll();
//                request.requestMatchers(HttpMethod.DELETE, API + APACKAGE + "/**").permitAll();
//                request.requestMatchers(HttpMethod.GET, API + COMPANY + "/**").permitAll();
//                request.requestMatchers(HttpMethod.POST, API + COMPANY + "/**").permitAll();
//                request.requestMatchers(HttpMethod.PUT, API + COMPANY + "/**").permitAll();
//                request.requestMatchers(HttpMethod.DELETE, API + COMPANY + "/**").permitAll();
//            }).formLogin(Customizer.withDefaults()).build();
//
//    }

}
