package com.example.practice.project.model.request;

import com.example.practice.project.dto.CompanyTypeDto;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageAddRequest implements Serializable {

    @NotBlank(message = "Title empty")
    private String packageTitle;

    @NotNull(message = "Total Price empty")
    @Min(value = 0, message = "Total Price must be greater than or equal to 0")
    private Double packageTotalPrice;

    @NotNull(message = "Package Fee empty")
    @Min(value = 0, message = "Package Fee must be greater than or equal to 0")
    private Double packageFee;

    @NotNull(message = "State Fee empty")
    @Min(value = 0, message = "State Fee must be greater than or equal to 0")
    private Double stateFee;

    @NotBlank(message = "Package Detail empty")
    private String packageDetail;

    @NotNull(message = "Company Type empty")
    private CompanyTypeDto companyType;

    private String type;

}
