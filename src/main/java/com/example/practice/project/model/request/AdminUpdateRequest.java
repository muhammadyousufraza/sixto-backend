package com.example.practice.project.model.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateRequest implements Serializable {

    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String middleName;
    private String surname;
    private String contactNumber;
}
