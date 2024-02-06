package com.example.practice.project.service;

import com.example.practice.project.dto.PackageDto;
import java.util.List;

public interface IPackageService {

    List<PackageDto> getAllPackages();


    PackageDto getById(Long id);

    PackageDto add(PackageDto packageDto, boolean isSendNotification);

    PackageDto update(PackageDto packageDto);

    Boolean deleteById(Long id);

    List<PackageDto> getAllPackagesByCompanyType(Long companyTypeId);
}
