package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.Constants.PACKAGE_NOT_FOUND;

import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.dto.PackageDto;
import com.example.practice.project.entity.Package;
import com.example.practice.project.repository.PackageRepository;
import com.example.practice.project.service.IPackageService;
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
public class PackageService implements IPackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public List<PackageDto> getAllPackages() {
        log.info("Getting all packages...");
        List<Package> packages = packageRepository.findAll();
        if (packages.isEmpty()) {
            log.error("package not found : {}", packages);
            return new ArrayList<>();
        }
        return ModelConverter.convertToPacakgeDtosList(packages);
    }

    @Override
    public PackageDto getById(Long id) {
        log.info("Getting package by id: {}", id);
        Optional<Package> optionalPackage = packageRepository.findById(id);
        if (optionalPackage.isPresent()) {
            return ModelConverter.convertToDto(optionalPackage.get());
        } else {
            log.error(PACKAGE_NOT_FOUND + " with parameter : {}", id);
            throw new NotFoundException(PACKAGE_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public PackageDto add(PackageDto packageDto, boolean isSendNotification) {
        log.info("Adding a new Package..");

        Package aPackage = ModelConverter.convertToEntity(packageDto);
        aPackage.setCreatedDate(LocalDateTime.now());
        aPackage.setUpdatedDate(LocalDateTime.now());

        aPackage = packageRepository.save(aPackage);

        packageDto = ModelConverter.convertToDto(aPackage);
        return packageDto;
    }

    @Override
    public PackageDto update(PackageDto packageDto) {
        log.info("Updating package with ID: {}", packageDto.getId());
        Optional<Package> optionalPackage = packageRepository.findById(packageDto.getId());

        if (!optionalPackage.isPresent()) {
            log.error(PACKAGE_NOT_FOUND + " while updating with parameter : {}", packageDto.getId());
            throw new NotFoundException(PACKAGE_NOT_FOUND);
        }


        Package savedPackage = ModelConverter.convertToEntity(packageDto);
//        savedPackage.setPackageTitle(packageDto.getPackageTitle());
//        savedPackage.setPackageFee(packageDto.getPackageFee());
//        savedPackage.setPackageDetail(packageDto.getPackageDetail());
//        savedPackage.setPackageTotalPrice(packageDto.getPackageTotalPrice());
//        savedPackage.setStateFee(packageDto.getStateFee());

        savedPackage.setCreatedDate(optionalPackage.get().getCreatedDate());
        savedPackage.setUpdatedDate(LocalDateTime.now());

        savedPackage = packageRepository.save(savedPackage);
        PackageDto updatedPackageDto = ModelConverter.convertToDto(savedPackage);

        return updatedPackageDto;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Deleting package with ID: {}", id);
        Optional<Package> optionalPackage = packageRepository.findById(id);
        if (optionalPackage.isEmpty()) {
            log.error(PACKAGE_NOT_FOUND + " while deletion with parameter : {}", id);
            throw new NotFoundException(PACKAGE_NOT_FOUND);
        }
        packageRepository.deleteById(id);
        return true;
    }


}
