package com.example.practice.project.model.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetAccessTokenRequest implements Serializable {
    private String refreshToken;
    private String username;
}
