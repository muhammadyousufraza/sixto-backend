package com.example.practice.project.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageDto implements Serializable {

    private Long id;
    private String packageTitle;
    private Double packageTotalPrice;
    private Double packageFee;
    private Double stateFee;
    private String packageDetail;
    private CompanyTypeDto companyType;
}
