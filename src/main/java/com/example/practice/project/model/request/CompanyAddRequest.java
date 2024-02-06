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
public class CompanyAddRequest implements Serializable {

    private String firstName;
    private String secondName;
    private String thirdName;
    private String streetAddress;
    private String detailAddress;
    private String city;
    private String state;
    private String code;
    private Long packageId;
    private Long createdBy;
    private String companyType;
}
