package com.example.practice.project.config;

import static com.example.practice.project.utilities.ApiEndPointConstants.REFRESH_TOKEN;

import com.example.practice.project.jwt.AuthEntryPointJwt;
import com.example.practice.project.jwt.AuthTokenFilter;
import com.example.practice.project.utilities.ApiEndPointConstants;
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

    /**
     * filterChain.
     *
     * @param http              HttpSecurity
     * @param authEntryPointJwt AuthEntryPointJwt
     * @return http .
     * @throws Exception Exception
     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, AuthEntryPointJwt authEntryPointJwt) throws Exception {
//        http.cors().and().csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources",
//            "/configuration/security", "/webjars/**", "/swagger-resources/configuration/ui",
//            "/swagger-resources/configuration/security", "/swagger-ui/**").permitAll().and()
//            .authorizeRequests().antMatchers(HttpMethod.POST, ApiEndPointConstants.API + ApiEndPointConstants.AUTH + ApiEndPointConstants.LOGIN).permitAll().and()
//            .authorizeRequests().antMatchers(HttpMethod.GET, ApiEndPointConstants.API + ApiEndPointConstants.AUTH + REFRESH_TOKEN).authenticated()
//            .anyRequest().authenticated();
//
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
}
