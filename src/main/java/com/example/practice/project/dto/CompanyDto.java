package com.example.practice.project.dto;

import com.example.practice.project.enums.CompanyStatus;
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
public class CompanyDto implements Serializable {

    private Long id;
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
    private CompanyStatus companyStatus;
    private List<ShareholderDto> shareholderDtoList;

}
