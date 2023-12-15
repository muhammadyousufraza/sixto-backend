package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.APACKAGE;
import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.AUTH;

import com.example.practice.project.dto.PackageDto;
import com.example.practice.project.model.request.PackageAddRequest;
import com.example.practice.project.model.request.PackageUpdateRequest;
import com.example.practice.project.service.IPackageService;
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
@RequestMapping(API + APACKAGE)
public class packageController {

    @Autowired
    IPackageService iPackageService;


    @PostMapping()
    public ResponseEntity<PackageDto> save(@Valid @RequestBody PackageAddRequest packageAddRequest) {
        log.info(" Package save  Request: {}", packageAddRequest.getPackageTitle());
        PackageDto packageDto = iPackageService.add(ModelConverter.convertToDto(packageAddRequest), true);
        return ResponseEntity.status(HttpStatus.CREATED).body(packageDto);
    }

    @PutMapping()
    public ResponseEntity<PackageDto> update(@RequestBody PackageUpdateRequest packageUpdateRequest) {
        log.info("Update Package By Id Request: {}", packageUpdateRequest.getId());
        PackageDto packageDto = iPackageService.update(ModelConverter.convertToDto(packageUpdateRequest));
        return ResponseEntity.ok(packageDto);
    }


    @GetMapping()
    public ResponseEntity<List<PackageDto>> getPackages() {
        log.debug("Get All Packages");
        List<PackageDto> allPackages = iPackageService.getAllPackages();
        if (allPackages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(allPackages);
        }
        return ResponseEntity.ok(allPackages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageDto> get(@PathVariable Long id) {
        log.debug("Get Packages By Id Request: {}", id);
        PackageDto packageDto = iPackageService.getById(id);
        return ResponseEntity.ok(packageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete Package By Id Request: {}", id);
        iPackageService.deleteById(id);
        log.info("Deleted Successfully : {}", id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
