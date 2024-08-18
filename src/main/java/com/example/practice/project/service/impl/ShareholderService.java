package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.Constants.SHAREHOLDER_NOT_FOUND;

import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.dto.ShareholderDto;
import com.example.practice.project.entity.Shareholder;
import com.example.practice.project.repository.ShareholderRepository;
import com.example.practice.project.service.IShareholderService;
import com.example.practice.project.utilities.ModelConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShareholderService implements IShareholderService {

    @Autowired
    private ShareholderRepository shareholderRepository;

    @Override
    public List<ShareholderDto> getAllShareholders() {
        log.info("Getting all shareholders...");
        List<Shareholder> shareholders = shareholderRepository.findAll();
        if (shareholders.isEmpty()) {
            log.error("shareholders not found : {}", shareholders);
            return new ArrayList<>();
        }
        return ModelConverter.convertToShareholderDtosList(shareholders);
    }

    @Override
    public List<ShareholderDto> getAllShareholdersByCompanyId(Long companyId) {
        log.info("Getting all shareholders by company id: {}", companyId);
        return ModelConverter.convertToList(shareholderRepository.findByCompany(companyId));
    }

    @Override
    public ShareholderDto getById(Long id) {
        log.info("Getting shareholder by id: {}", id);
        Optional<Shareholder> shareholderOptional = shareholderRepository.findById(id);
        if (shareholderOptional.isPresent()) {
            return ModelConverter.convertToDto(shareholderOptional.get());
        } else {
            log.error(SHAREHOLDER_NOT_FOUND + " with parameter : {}", id);
            throw new NotFoundException(SHAREHOLDER_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public ShareholderDto add(ShareholderDto shareholderDto, boolean isSendNotification) {
        log.info("Adding a new shareholder..");
        Shareholder shareholder = ModelConverter.convertToEntity(shareholderDto);
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholder = shareholderRepository.save(shareholder);

        shareholderDto = ModelConverter.convertToDto(shareholder);
        return shareholderDto;
    }

    @Override
    public ShareholderDto update(ShareholderDto shareholderDto) {
        log.info("Updating shareholder with ID: {}", shareholderDto.getId());
        Optional<Shareholder> shareholderOptional = shareholderRepository.findById(shareholderDto.getId());

        if (!shareholderOptional.isPresent()) {
            log.error(SHAREHOLDER_NOT_FOUND + " while updating with parameter : {}", shareholderDto.getId());
            throw new NotFoundException(SHAREHOLDER_NOT_FOUND);
        }


        Shareholder shareholder = ModelConverter.convertToEntity(shareholderDto);
        shareholder.setCreatedDate(shareholderOptional.get().getCreatedDate());
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholder = shareholderRepository.save(shareholder);
        ShareholderDto updatedShareholderDto = ModelConverter.convertToDto(shareholder);

        return updatedShareholderDto;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Deleting Shareholder with ID: {}", id);
        Optional<Shareholder> shareholderOptional = shareholderRepository.findById(id);
        if (shareholderOptional.isEmpty()) {
            log.error(SHAREHOLDER_NOT_FOUND + " while deletion with parameter : {}", id);
            throw new NotFoundException(SHAREHOLDER_NOT_FOUND);
        }
        shareholderRepository.deleteById(id);
        return true;
    }


}
