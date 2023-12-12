package com.example.practice.project.model.request;

import static com.example.practice.project.utilities.Constants.EMAIL_FORMAT_INCORRECT;
import static com.example.practice.project.utilities.Constants.EMAIL_FORMAT_REGEXP;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest implements Serializable {

    private String username;

    @NotBlank(message = "email is empty")
    @Pattern(regexp = EMAIL_FORMAT_REGEXP, message = EMAIL_FORMAT_INCORRECT)
    private String email;

    @NotBlank(message = "password is empty")
    private String password;
    private String firstName;
    private String middleName;
    private String surname;
    private String address;
    private String contactNumber;
}
