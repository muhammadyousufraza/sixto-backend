package com.example.practice.project.controller;

import static com.example.practice.project.utilities.ApiEndPointConstants.API;
import static com.example.practice.project.utilities.ApiEndPointConstants.AUTH;
import static com.example.practice.project.utilities.ApiEndPointConstants.CHANGE_PASSWORD;
import static com.example.practice.project.utilities.ApiEndPointConstants.LOGIN;

import com.example.practice.project.dto.ResponseDto;
import com.example.practice.project.jwt.JwtUtils;
import com.example.practice.project.model.request.ChangePasswordRequest;
import com.example.practice.project.model.request.LoginRequest;
import com.example.practice.project.model.response.JwtResponse;
import com.example.practice.project.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping(API + AUTH)
public class AuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(LOGIN)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            authentication = authenticationManager.authenticate(authentication);

        } catch (AccountStatusException | BadCredentialsException ase) {
            return ResponseEntity
                .badRequest()
                .body(new JwtResponse("Error: Invalid Username or Password!", false));
        } // If the username/password are wrong the spec says we should send 400/invalid grant

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity
                .badRequest()
                .body(new JwtResponse("Error: Could not authenticate user: " + loginRequest.getEmail(), false));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse("Token Generated", jwt, true));
    }

    @PostMapping(CHANGE_PASSWORD)
    public ResponseEntity<ResponseDto> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(authService.changePassword(changePasswordRequest));
    }

}
