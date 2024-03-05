package com.example.practice.project.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String surname;
    private String address;
    private String contactNumber;
    private List<UserRoleDto> userRoles;

}
