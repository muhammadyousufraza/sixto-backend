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
public class UserUpdateRequest implements Serializable {


    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String middleName;
    private String surname;
    private String address;
    private String contactNumber;
}
