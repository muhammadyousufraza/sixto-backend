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
public class ShareholderUpdateRequest implements Serializable {


    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String passportNumber;
    private String occupation;
    private String maritalStatus;
    private String homeAddress;
    private Double sharePercentage;

    private Long companyId;

}