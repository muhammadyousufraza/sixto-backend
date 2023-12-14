package com.example.practice.project.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageUpdateRequest implements Serializable {


    private Long id;
    private String packageTitle;
    private Double packageTotalPrice;
    private Double packageFee;
    private Double stateFee;
    private String packageDetail;

}