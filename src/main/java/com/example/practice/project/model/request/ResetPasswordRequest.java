package com.example.practice.project.model.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest implements Serializable {

    private String realm;
    private String email;
    private String newPassword;
    private Boolean isFirstTimeLogin;
}
