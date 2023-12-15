package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.Company;

import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.model.request.CompanyAddRequest;
import com.example.practice.project.model.request.CompanyUpdateRequest;
import com.example.practice.project.service.ICompanyService;
import com.example.practice.project.utilities.ModelConverter;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(API + Company)
public class companyController {

    @Autowired
    ICompanyService iCompanyService;


    @PostMapping()
    public ResponseEntity<CompanyDto> save(@Valid @RequestBody CompanyAddRequest companyAddRequest) {
        log.info(" Company save  Request: {}", companyAddRequest.getCompanyFirstName());
        CompanyDto companyDto = iCompanyService.add(ModelConverter.convertToDto(companyAddRequest), true);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDto);
    }

    @PutMapping()
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyUpdateRequest companyUpdateRequest) {
        log.info("Update Company By Id Request: {}", companyUpdateRequest.getId());
        CompanyDto companyDto = iCompanyService.update(ModelConverter.convertToDto(companyUpdateRequest));
        return ResponseEntity.ok(companyDto);
    }


    @GetMapping()
    public ResponseEntity<List<CompanyDto>> getCompanies() {
        log.debug("Get All Companies");
        List<CompanyDto> allCompanies = iCompanyService.getAllCompanies();
        if (allCompanies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(allCompanies);
        }
        return ResponseEntity.ok(allCompanies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> get(@PathVariable Long id) {
        log.debug("Get Company By Id Request: {}", id);
        CompanyDto companyDto = iCompanyService.getById(id);
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete company By Id Request: {}", id);
        iCompanyService.deleteById(id);
        log.info("Deleted Successfully : {}", id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
