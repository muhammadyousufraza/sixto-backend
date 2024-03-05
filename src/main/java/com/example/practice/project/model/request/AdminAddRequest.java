package com.example.practice.project.model.request;

import static com.example.practice.project.utilities.Constants.EMAIL_FORMAT_INCORRECT;
import static com.example.practice.project.utilities.Constants.EMAIL_FORMAT_REGEXP;

import com.example.practice.project.dto.UserRoleDto;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminAddRequest implements Serializable {

    @NotBlank(message = "email is empty")
    @Pattern(regexp = EMAIL_FORMAT_REGEXP, message = EMAIL_FORMAT_INCORRECT)
    private String email;

    @NotBlank(message = "password is empty")
    private String password;
    private String firstName;
    private String middleName;
    private String surname;
    private String contactNumber;
    @NotNull(message = "role is empty")
    private List<UserRoleDto> userRoles;
}
