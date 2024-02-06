package com.example.practice.project.service.impl;

import com.example.practice.project.dto.CompanyTypeDto;
import com.example.practice.project.repository.CompanyTypeRepository;
import com.example.practice.project.service.ILookupService;
import com.example.practice.project.utilities.ModelConverter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LookupService implements ILookupService {

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @Override
    public List<CompanyTypeDto> getAllCompanyType() {
        return ModelConverter.convertToCompantTypeDtosList(companyTypeRepository.findAll());
    }
}
