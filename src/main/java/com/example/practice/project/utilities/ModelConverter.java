package com.example.practice.project.utilities;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.dto.CompanyFileDto;
import com.example.practice.project.dto.CompanyTypeDto;
import com.example.practice.project.dto.FileDto;
import com.example.practice.project.dto.PackageDto;
import com.example.practice.project.dto.ShareholderDto;
import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.Company;
import com.example.practice.project.entity.CompanyFiles;
import com.example.practice.project.entity.CompanyType;
import com.example.practice.project.entity.File;
import com.example.practice.project.entity.Package;
import com.example.practice.project.entity.Shareholder;
import com.example.practice.project.entity.User;
import com.example.practice.project.model.request.AdminAddRequest;
import com.example.practice.project.model.request.AdminUpdateRequest;
import com.example.practice.project.model.request.CompanyAddRequest;
import com.example.practice.project.model.request.CompanyUpdateRequest;
import com.example.practice.project.model.request.PackageAddRequest;
import com.example.practice.project.model.request.PackageUpdateRequest;
import com.example.practice.project.model.request.ShareholderAddRequest;
import com.example.practice.project.model.request.ShareholderUpdateRequest;
import com.example.practice.project.model.request.UserAddRequest;
import com.example.practice.project.model.request.UserUpdateRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ModelConverter {


    private ModelConverter() {
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public static UserDto convertToDto(AdminAddRequest adminAddRequest) {
        return modelMapper.map(adminAddRequest, UserDto.class);
    }
    public static UserDto convertToDto(AdminUpdateRequest adminUpdateRequest) {
        return modelMapper.map(adminUpdateRequest, UserDto.class);
    }

    public static ShareholderDto convertToDto(Shareholder shareholder) {
        return modelMapper.map(shareholder, ShareholderDto.class);
    }

    public static PackageDto convertToDto(Package packages) {
        return modelMapper.map(packages, PackageDto.class);
    }

    public static CompanyDto convertToDto(Company company) {
        return modelMapper.map(company, CompanyDto.class);
    }

    public static UserDto convertToDto(UserAddRequest userAddRequest) {
        return modelMapper.map(userAddRequest, UserDto.class);
    }

    public static CompanyTypeDto convertToDto(CompanyType companyType) {
        return modelMapper.map(companyType, CompanyTypeDto.class);
    }

    public static ShareholderDto convertToDto(ShareholderAddRequest shareholderAddRequest) {
        return modelMapper.map(shareholderAddRequest, ShareholderDto.class);
    }

    public static CompanyDto convertToDto(CompanyAddRequest companyAddRequest) {
        return modelMapper.map(companyAddRequest, CompanyDto.class);
    }

    public static PackageDto convertToDto(PackageAddRequest packages) {
        return modelMapper.map(packages, PackageDto.class);
    }

    public static UserDto convertToDto(UserUpdateRequest userUpdateRequest) {
        return modelMapper.map(userUpdateRequest, UserDto.class);
    }

    public static PackageDto convertToDto(PackageUpdateRequest packages) {
        return modelMapper.map(packages, PackageDto.class);
    }

    public static CompanyDto convertToDto(CompanyUpdateRequest companyUpdateRequest) {
        return modelMapper.map(companyUpdateRequest, CompanyDto.class);
    }

    public static ShareholderDto convertToDto(ShareholderUpdateRequest shareholderUpdateRequest) {
        return modelMapper.map(shareholderUpdateRequest, ShareholderDto.class);
    }

    public static FileDto convertToDto(File file) {
        return modelMapper.map(file, FileDto.class);
    }

    public static CompanyFileDto convertToDto(CompanyFiles companyFiles) {
        return modelMapper.map(companyFiles, CompanyFileDto.class);
    }


    public static List<UserDto> convertToUserDtosList(List<User> users) {
        return Arrays.asList(modelMapper.map(users, UserDto[].class));
    }

    public static List<PackageDto> convertToPackageDtosList(List<Package> packages) {
        return Arrays.asList(modelMapper.map(packages, PackageDto[].class));
    }

    public static List<CompanyDto> convertToCompanyDtosList(List<Company> companies) {
        return Arrays.asList(modelMapper.map(companies, CompanyDto[].class));
    }

    public static List<ShareholderDto> convertToShareholderDtosList(List<Shareholder> shareholders) {
        return Arrays.asList(modelMapper.map(shareholders, ShareholderDto[].class));
    }

    public static List<FileDto> convertToFileDtosList(List<File> files) {
        return Arrays.asList(modelMapper.map(files, FileDto[].class));
    }

    public static List<CompanyFileDto> convertToCompanyFileDtosList(List<CompanyFiles> companyFiles) {
        return Arrays.asList(modelMapper.map(companyFiles, CompanyFileDto[].class));
    }

    public static List<CompanyTypeDto> convertToCompantTypeDtosList(List<CompanyType> companyTypes) {
        return Arrays.asList(modelMapper.map(companyTypes, CompanyTypeDto[].class));
    }

    public static Page<CompanyDto> convertToCompanyBookingPageDto(Page<Company> companies) {
        return companies.map(x -> modelMapper.map(x, CompanyDto.class));
    }

    public static User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserAddRequest convertToRequest(UserDto userDto) {
        return modelMapper.map(userDto, UserAddRequest.class);
    }

    public static Shareholder convertToEntity(ShareholderDto shareholderDto) {
        return modelMapper.map(shareholderDto, Shareholder.class);
    }

    public static Company convertToEntity(CompanyDto companyDto) {
        return modelMapper.map(companyDto, Company.class);
    }

    public static Package convertToEntity(PackageDto packageDto) {
        return modelMapper.map(packageDto, Package.class);
    }

    public static File convertToEntity(FileDto fileDto) {
        return modelMapper.map(fileDto, File.class);
    }

    public static CompanyFiles convertToEntity(CompanyFileDto companyFileDto) {
        return modelMapper.map(companyFileDto, CompanyFiles.class);
    }


    /**
     * convertToMap.
     *
     * @param obj obj
     * @return Map
     */
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
                throw new BusinessException(e.getLocalizedMessage());
            }
        }
        return map;
    }
}
