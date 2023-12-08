package com.example.practice.project.model.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class JwtResponse implements Serializable {

    private String token;
    private String type = "Bearer";
    private boolean status;
    private String message;

    public JwtResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    /**
     * JwtResponse.
     * @param message String
     * @param accessToken String
     * @param status boolean
     */
    public JwtResponse(String message, String accessToken, boolean status) {
        this.message = message;
        this.token = accessToken;
        this.status = status;
    }
}