package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.COMPANY;

import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.enums.CompanyStatus;
import com.example.practice.project.model.request.CompanyAddRequest;
import com.example.practice.project.model.request.CompanyShareholderRequest;
import com.example.practice.project.model.request.CompanyUpdateRequest;
import com.example.practice.project.service.ICompanyService;
import com.example.practice.project.utilities.ModelConverter;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping(API + COMPANY)
public class CompanyController {

    @Autowired
    ICompanyService iCompanyService;

    @PostMapping()
    public ResponseEntity<CompanyDto> save(@Valid @RequestBody CompanyAddRequest companyAddRequest) {
        log.info(" Company save  Request: {}", companyAddRequest.getFirstName());
        CompanyDto companyDto = iCompanyService.add(ModelConverter.convertToDto(companyAddRequest), true);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDto);
    }

    @PostMapping("/save-company-shareholder")
    public ResponseEntity<CompanyShareholderRequest> saveUserAndCompany(@Valid @RequestBody CompanyShareholderRequest companyShareholderRequest) {
        log.info("Save company and shareholder {}", companyShareholderRequest);
        CompanyShareholderRequest userDto = iCompanyService.add(companyShareholderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping()
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyUpdateRequest companyUpdateRequest) {
        log.info("Update Company By Id Request: {}", companyUpdateRequest.getId());
        CompanyDto companyDto = iCompanyService.update(ModelConverter.convertToDto(companyUpdateRequest));
        return ResponseEntity.ok(companyDto);
    }


    @GetMapping()
    public ResponseEntity<List<CompanyDto>> getCompanies() {
        log.info("Get All Companies");
        List<CompanyDto> allCompanies = iCompanyService.getAllCompanies();
        if (allCompanies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(allCompanies);
        }
        return ResponseEntity.ok(allCompanies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> get(@PathVariable Long id) {
        log.info("Get Company By Id Request: {}", id);
        CompanyDto companyDto = iCompanyService.getById(id);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping("/by-user/{id}/{statuses}")
    public ResponseEntity<Page<CompanyDto>> getAllCompaniesByUserIdAndStatus(@PathVariable Long id, @PathVariable(required = false) List<CompanyStatus> statuses,
                                                                             @PageableDefault(size = 10) Pageable pageable) {
        log.info("Get All Companies By User Id and Status Request: {}", id);
        Page<CompanyDto> companyDto = iCompanyService.getAllCompaniesByUserId(id, statuses, pageable);
        if (!companyDto.hasContent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(companyDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyDto);
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<Page<CompanyDto>> getAllCompaniesByUserId(@PathVariable Long id,
                                                                    @PageableDefault(size = 10) Pageable pageable) {
        log.info("Get All Companies By User Id Request: {}", id);
        Page<CompanyDto> companyDto = iCompanyService.getAllCompaniesByUserId(id, null, pageable);
        if (!companyDto.hasContent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(companyDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete company By Id Request: {}", id);
        iCompanyService.deleteById(id);
        log.info("Deleted Successfully : {}", id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
