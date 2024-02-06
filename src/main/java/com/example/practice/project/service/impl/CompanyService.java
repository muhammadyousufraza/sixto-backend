package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.Constants.COMPANY_NOT_FOUND;

import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.dto.CompanyFileDto;
import com.example.practice.project.entity.Company;
import com.example.practice.project.enums.CompanyStatus;
import com.example.practice.project.repository.CompanyFileRepository;
import com.example.practice.project.repository.CompanyRepository;
import com.example.practice.project.service.ICompanyService;
import com.example.practice.project.utilities.ModelConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyFileRepository companyFileRepository;

    @Override
    public List<CompanyDto> getAllCompanies() {
        log.info("Getting all company...");
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty()) {
            log.error("companies not found : {}", companies);
            return new ArrayList<>();
        }
        return ModelConverter.convertToCompanyDtosList(companies);
    }

    @Override
    public CompanyDto getById(Long id) {
        log.info("Getting company by id: {}", id);
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            return ModelConverter.convertToDto(companyOptional.get());
        } else {
            log.error(COMPANY_NOT_FOUND + " with parameter : {}", id);
            throw new NotFoundException(COMPANY_NOT_FOUND);
        }
    }

    @Override
    public Page<CompanyDto> getAllCompaniesByUserId(Long userId, List<CompanyStatus> companyStatus, Pageable pageable) {
        log.info("Getting all company by user id...");
        Page<Company> companies = companyRepository.findAllByCreatedBy_IdAndCompanyStatusIn(userId, companyStatus, pageable);
        if (companies.isEmpty()) {
            log.error("companies not found : {}", companies);
            return new PageImpl<>(new ArrayList<>());
        }
        return ModelConverter.convertToCompanyBookingPageDto(companies);
    }


    @Transactional
    @Override
    public CompanyDto add(CompanyDto companyDto, boolean isSendNotification) {
        log.info("Adding a new company..");
        Company company = ModelConverter.convertToEntity(companyDto);
        company.setCreatedDate(LocalDateTime.now());
        company.setUpdatedDate(LocalDateTime.now());

        company = companyRepository.save(company);

        companyDto = ModelConverter.convertToDto(company);
        return companyDto;
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        log.info("Updating company with ID: {}", companyDto.getId());
        Optional<Company> companyOptional = companyRepository.findById(companyDto.getId());

        if (!companyOptional.isPresent()) {
            log.error(COMPANY_NOT_FOUND + " while updating with parameter : {}", companyDto.getId());
            throw new NotFoundException(COMPANY_NOT_FOUND);
        }


        Company savedComapny = ModelConverter.convertToEntity(companyDto);
        savedComapny.setCreatedDate(companyOptional.get().getCreatedDate());
        savedComapny.setUpdatedDate(LocalDateTime.now());

        savedComapny = companyRepository.save(savedComapny);
        CompanyDto updatedCompanyDto = ModelConverter.convertToDto(savedComapny);

        return updatedCompanyDto;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Deleting company with ID: {}", id);
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            log.error(COMPANY_NOT_FOUND + " while deletion with parameter : {}", id);
            throw new NotFoundException(COMPANY_NOT_FOUND);
        }
        companyRepository.deleteById(id);
        return true;
    }

    @Override
    public CompanyFileDto saveCompanyFiles(CompanyFileDto companyFile) {
        log.info("Saving company file: {}", companyFile);
        return ModelConverter.convertToDto(companyFileRepository.save(ModelConverter.convertToEntity(companyFile)));
    }

    @Override
    public List<CompanyFileDto> getCompanyFiles(Long companyId) {
        log.info("Get all company files of company: {}", companyId);
        return ModelConverter.convertToCompanyFileDtosList(companyFileRepository.findByCompanyId(companyId));
    }

}
