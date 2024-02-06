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
public class CompanyFileDto implements Serializable {

    private Long id;
    private Long fileId;
    private String fileType;
    private CompanyDto company;
}
