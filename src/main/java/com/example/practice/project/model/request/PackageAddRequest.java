package com.example.practice.project.model.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageAddRequest implements Serializable {

    @NotBlank(message = "Title is empty")
    private String packageTitle;
    @NotBlank(message = "Total Price is empty")
    private Double packageTotalPrice;
    @NotBlank(message = "package Fee is empty")
    private Double packageFee;
    @NotBlank(message = "state Fee empty")
    private Double stateFee;
    @NotBlank(message = "package Detail empty")
    private String packageDetail;
}
