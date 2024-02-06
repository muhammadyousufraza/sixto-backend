package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.LOOKUP;

import com.example.practice.project.dto.CompanyTypeDto;
import com.example.practice.project.service.ILookupService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping(API + LOOKUP)
public class LookupController {

    @Autowired
    ILookupService lookupService;

    @GetMapping("/company-types")
    public ResponseEntity<List<CompanyTypeDto>> getCompanyType() {
        log.info("Get All Company Type");
        List<CompanyTypeDto> allCompanies = lookupService.getAllCompanyType();
        if (allCompanies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(allCompanies);
        }
        return ResponseEntity.ok(allCompanies);
    }

}
