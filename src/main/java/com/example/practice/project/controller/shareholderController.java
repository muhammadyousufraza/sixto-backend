package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.SHAREHOLDER;

import com.example.practice.project.dto.ShareholderDto;
import com.example.practice.project.model.request.ShareholderAddRequest;
import com.example.practice.project.model.request.ShareholderUpdateRequest;
import com.example.practice.project.service.IShareholderService;
import com.example.practice.project.utilities.ModelConverter;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping(API + SHAREHOLDER)
public class shareholderController {

    @Autowired
    IShareholderService iShareholderService;


    @PostMapping()
    public ResponseEntity<ShareholderDto> save(@Valid @RequestBody ShareholderAddRequest shareholderAddRequest) {
        log.info(" shareholder save  Request: {}", shareholderAddRequest.getFirstName());
        ShareholderDto shareholderDto = iShareholderService.add(ModelConverter.convertToDto(shareholderAddRequest), true);
        return ResponseEntity.status(HttpStatus.CREATED).body(shareholderDto);
    }

    @PutMapping()
    public ResponseEntity<ShareholderDto> update(@RequestBody ShareholderUpdateRequest shareholderUpdateRequest) {
        log.info("Update shareholder By Id Request: {}", shareholderUpdateRequest.getId());
        ShareholderDto shareholderDto = iShareholderService.update(ModelConverter.convertToDto(shareholderUpdateRequest));
        return ResponseEntity.ok(shareholderDto);
    }


    @GetMapping()
    public ResponseEntity<List<ShareholderDto>> getShareholders() {
        log.debug("Get All Shareholders");
        List<ShareholderDto> shareholderDtoList = iShareholderService.getAllShareholders();
        if (shareholderDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(shareholderDtoList);
        }
        return ResponseEntity.ok(shareholderDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareholderDto> get(@PathVariable Long id) {
        log.debug("Get Shareholder By Id Request: {}", id);
        ShareholderDto shareholderDto = iShareholderService.getById(id);
        return ResponseEntity.ok(shareholderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete Shareholder By Id Request: {}", id);
        iShareholderService.deleteById(id);
        log.info("Deleted Successfully : {}", id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
