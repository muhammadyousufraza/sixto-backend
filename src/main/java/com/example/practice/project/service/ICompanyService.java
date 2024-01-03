package com.example.practice.project.service;

import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.enums.CompanyStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICompanyService {

    List<CompanyDto> getAllCompanies();

    CompanyDto getById(Long id);

    Page<CompanyDto> getAllCompaniesByUserId(Long userId, List<CompanyStatus> companyStatus, Pageable pageable);

    CompanyDto add(CompanyDto companyDto, boolean isSendNotification);

    CompanyDto update(CompanyDto companyDto);

    Boolean deleteById(Long id);

}
