package com.example.practice.project.service;

import com.example.practice.project.dto.CompanyDto;
import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();


    CompanyDto getById(Long id);

    CompanyDto add(CompanyDto companyDto, boolean isSendNotification);

    CompanyDto update(CompanyDto companyDto);

    Boolean deleteById(Long id);

}
