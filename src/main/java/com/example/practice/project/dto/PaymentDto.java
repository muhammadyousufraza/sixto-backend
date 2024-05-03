package com.example.practice.project.dto;

import com.example.practice.project.enums.PaymentStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto implements Serializable {

    private Long id;
    private Double amount;
    private PaymentStatus status;
    private Long userId;
    private Long companyId;

}
