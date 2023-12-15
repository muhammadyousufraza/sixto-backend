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
public class CompanyUpdateRequest implements Serializable {


    private Long id;
    private String companyFirstName;
    private String companySecondName;
    private String companyThirdName;
    private Long packageId;
    private Long userId;

}