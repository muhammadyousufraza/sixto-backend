package com.example.practice.project.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest implements Serializable {

    @Email
    private String email;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
