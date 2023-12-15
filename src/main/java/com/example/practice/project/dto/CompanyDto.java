package com.example.practice.project.dto;

import com.example.practice.project.entity.Package;
import com.example.practice.project.entity.User;
import com.example.practice.project.enums.CompanyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
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
    private CompanyType companyType;
}
