package com.example.practice.project.utilities;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.dto.CompanyFileDto;
import com.example.practice.project.dto.CompanyTypeDto;
import com.example.practice.project.dto.FileDto;
import com.example.practice.project.dto.PackageDto;
import com.example.practice.project.dto.PaymentDto;
import com.example.practice.project.dto.ShareholderDto;
import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.Company;
import com.example.practice.project.entity.CompanyFiles;
import com.example.practice.project.entity.CompanyType;
import com.example.practice.project.entity.File;
import com.example.practice.project.entity.Package;
import com.example.practice.project.entity.Payment;
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
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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

    public static PaymentDto convertToDto(Payment payment) {
        return modelMapper.map(payment, PaymentDto.class);
    }

    public static CompanyDto convertToDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());

        companyDto.setFirstName(company.getFirstName());
        companyDto.setSecondName(company.getSecondName());
        companyDto.setThirdName(company.getThirdName());
        companyDto.setCompanyType(company.getCompanyType());
        companyDto.setStreetAddress(company.getStreetAddress());
        companyDto.setDetailAddress(company.getDetailAddress());
        companyDto.setCity(company.getCity());
        companyDto.setState(company.getState());
        companyDto.setCode(company.getCode());

        companyDto.setCompanyStatus(company.getCompanyStatus());

        if (company.getAPackage() != null) {
            companyDto.setPackageId(company.getAPackage().getId());
        }

        if (company.getCreatedBy() != null) {
            companyDto.setCreatedBy(company.getCreatedBy().getId());
        }

        return companyDto;
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
        if (companyAddRequest == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setFirstName(companyAddRequest.getFirstName());
        companyDto.setSecondName(companyAddRequest.getSecondName());
        companyDto.setThirdName(companyAddRequest.getThirdName());
        companyDto.setStreetAddress(companyAddRequest.getStreetAddress());
        companyDto.setDetailAddress(companyAddRequest.getDetailAddress());
        companyDto.setCity(companyAddRequest.getCity());
        companyDto.setState(companyAddRequest.getState());
        companyDto.setCode(companyAddRequest.getCode());
        companyDto.setPackageId(companyAddRequest.getPackageId());
        companyDto.setCreatedBy(companyAddRequest.getCreatedBy());
        companyDto.setCompanyType(companyAddRequest.getCompanyType());
        return companyDto;
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

    public static List<ShareholderDto> convertToList(List<Shareholder> shareholders) {
        return Arrays.asList(modelMapper.map(shareholders, ShareholderDto[].class));
    }

    public static List<PackageDto> convertToPackageDtosList(List<Package> packages) {
        return Arrays.asList(modelMapper.map(packages, PackageDto[].class));
    }

    public static List<CompanyDto> convertToCompanyDtosList(List<Company> companies) {
        return companies.stream()
            .map(ModelConverter::convertToDto)  // Assuming convertToDto is a static method in CompanyMapper
            .collect(Collectors.toList());
        //return Arrays.asList(modelMapper.map(companies, CompanyDto[].class));
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
        List<CompanyDto> companyDtoList = companies.getContent().stream()
            .map(company -> convertToDto(company))
            .collect(Collectors.toList());

        return new PageImpl<>(companyDtoList, companies.getPageable(), companies.getTotalElements());

        //return companies.map(x -> modelMapper.map(x, CompanyDto.class));
    }

    public static User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static Payment convertToEntity(PaymentDto paymentDto) {
        return modelMapper.map(paymentDto, Payment.class);
    }

    public static UserAddRequest convertToRequest(UserDto userDto) {
        return modelMapper.map(userDto, UserAddRequest.class);
    }

    public static CompanyAddRequest convertToRequest(CompanyDto companyDto) {
        return modelMapper.map(companyDto, CompanyAddRequest.class);
    }

    public static Shareholder convertToEntity(ShareholderDto shareholderDto) {
        if (shareholderDto == null) {
            return null;
        }

        Shareholder shareholder = new Shareholder();

        shareholder.setId(null != shareholderDto.getId() ? shareholder.getId() : null);

        shareholder.setFirstName(shareholderDto.getFirstName());
        shareholder.setLastName(shareholderDto.getLastName());
        shareholder.setNationality(shareholderDto.getNationality());
        shareholder.setPassportNumber(shareholderDto.getPassportNumber());
        shareholder.setOccupation(shareholderDto.getOccupation());
        shareholder.setMaritalStatus(shareholderDto.getMaritalStatus());
        shareholder.setHomeAddress(shareholderDto.getHomeAddress());
        shareholder.setSharePercentage(shareholderDto.getSharePercentage());
        shareholder.setShareHolder(shareholderDto.isShareHolder());
        shareholder.setLegalRepresentative(shareholderDto.isLegalRepresentative());
        shareholder.setManager(shareholderDto.isManager());

        if (shareholderDto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(shareholderDto.getCompanyId());
            shareholder.setCompany(company);
        }

        return shareholder;
    }

    public static Company convertToEntity(CompanyDto companyDto) {

        if (companyDto == null) {
            return null;
        }

        Company company = new Company();

        company.setId(null != companyDto.getId() ? companyDto.getId() : null);
        company.setFirstName(companyDto.getFirstName());
        company.setSecondName(companyDto.getSecondName());
        company.setThirdName(companyDto.getThirdName());
        company.setCompanyType(companyDto.getCompanyType());
        company.setStreetAddress(companyDto.getStreetAddress());
        company.setDetailAddress(companyDto.getDetailAddress());
        company.setCity(companyDto.getCity());
        company.setState(companyDto.getState());
        company.setCode(companyDto.getCode());
        company.setCompanyStatus(companyDto.getCompanyStatus());

        if (companyDto.getPackageId() != null) {
            Package aPackage = new Package();
            aPackage.setId(companyDto.getPackageId());
            company.setAPackage(aPackage);
        }

        if (companyDto.getCreatedBy() != null) {
            User createdBy = new User();
            createdBy.setId(companyDto.getCreatedBy());
            company.setCreatedBy(createdBy);
        }

        return company;
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
