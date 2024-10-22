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
public class ShareholderAddRequest implements Serializable {

    private String firstName;
    private String lastName;
    private String nationality;
    private String passportNumber;
    private String occupation;
    private String maritalStatus;
    private String homeAddress;
    private Double sharePercentage;
    private boolean isShareHolder;
    private boolean isLegalRepresentative;
    private boolean isManager;

    private Long companyId;
}
