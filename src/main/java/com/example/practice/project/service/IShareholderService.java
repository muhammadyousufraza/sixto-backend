package com.example.practice.project.service;

import com.example.practice.project.dto.ShareholderDto;
import java.util.List;

public interface IShareholderService {

    List<ShareholderDto> getAllShareholders();

    List<ShareholderDto> getAllShareholdersByCompanyId(Long companyId);

    ShareholderDto getById(Long id);

    ShareholderDto add(ShareholderDto shareholderDto, boolean isSendNotification);

    ShareholderDto update(ShareholderDto shareholderDto);

    Boolean deleteById(Long id);

}
