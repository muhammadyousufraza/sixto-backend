package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.Constants.USER_NOT_FOUND;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.customexception.NotFoundException;
import com.example.practice.project.dto.CompanyDto;
import com.example.practice.project.dto.ShareholderDto;
import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.User;
import com.example.practice.project.model.request.NotificationRequest;
import com.example.practice.project.model.request.ShareholderAddRequest;
import com.example.practice.project.model.request.UserCompanyShareholderRequest;
import com.example.practice.project.repository.UserRepository;
import com.example.practice.project.service.ICompanyService;
import com.example.practice.project.service.IPackageService;
import com.example.practice.project.service.IShareholderService;
import com.example.practice.project.service.IUserService;
import com.example.practice.project.service.WebSocketService;
import com.example.practice.project.utilities.ModelConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IPackageService packageService;

    @Autowired
    private IShareholderService shareholderService;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users...");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.error("User not found : {}", users);
            return new ArrayList<>();
        }
        users.stream().forEach(c -> c.setPassword(null));
        return ModelConverter.convertToUserDtosList(users);
    }

    @Override
    public UserDto getUserByEmail(String username) {
        log.info("Starting getUserByUsername()");
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = ModelConverter.convertToDto(user);
            return userDto;
        }
        log.error("User not found with parameter: {}", username);
        log.info("Ending getUserByUsername()");
        throw new BusinessException(USER_NOT_FOUND);
    }

    @Override
    public UserDto getById(Long id) {
        log.info("Getting user by id: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userOptional.get().setPassword(null);
            return ModelConverter.convertToDto(userOptional.get());
        } else {
            log.error(USER_NOT_FOUND + " with parameter : {}", id);
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public UserDto add(UserDto userDto, boolean isSendNotification) {
        log.info("Adding a new user..");
        String checkDuplicate = checkDuplicateUser(userDto);
        if (StringUtils.hasLength(checkDuplicate)) {
            log.error("Duplicate User: {}", userDto);
            throw new BusinessException(checkDuplicate);
        }
        User user = ModelConverter.convertToEntity(userDto);

        user.setPassword(passwordEncoder.encode((userDto.getPassword())));
        user.setUsername(userDto.getEmail());
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user = userRepository.save(user);
        userDto = ModelConverter.convertToDto(user);
        userDto.setPassword(null);
        return userDto;
    }

    @Override
    public UserCompanyShareholderRequest add(UserCompanyShareholderRequest userCompanyShareholderRequest) {
        UserDto userDto = add(ModelConverter.convertToDto(userCompanyShareholderRequest.getUser()), false);
        log.info("User added successfully. User ID: {}", userDto.getId());
        userCompanyShareholderRequest.getCompany().setCreatedBy(userDto.getId());
        CompanyDto companyDto = ModelConverter.convertToDto(userCompanyShareholderRequest.getCompany());
        companyDto.setCompanyType(packageService.getById(companyDto.getPackageId()).getCompanyType().getName());
        companyDto = companyService.add(companyDto, false);
        log.info("Company saved successfully. Company ID: {}", companyDto.getId());
        for (ShareholderAddRequest shareholder : userCompanyShareholderRequest.getShareholder()) {
            shareholder.setCompanyId(companyDto.getId());
            ShareholderDto shareholderDto = shareholderService.add(ModelConverter.convertToDto(shareholder), false);
            log.info("ShareHolder  saved successfully. ShareHolder ID: {}", shareholderDto.getId());
        }
        userDto.setPassword(null);
        return UserCompanyShareholderRequest.builder().user(ModelConverter.convertToRequest(userDto)).build();
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.info("Updating user with ID: {}", userDto.getId());
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            log.error(USER_NOT_FOUND + " while updating with parameter : {}", userDto.getId());
            throw new NotFoundException(USER_NOT_FOUND);
        }

        String checkDuplicate = checkDuplicateUser(userDto);
        if (StringUtils.hasLength(checkDuplicate)) {
            log.error("Duplicate User : {}", userDto);
            throw new BusinessException(checkDuplicate);
        }
        User savedUser = ModelConverter.convertToEntity(userDto);
        savedUser.setPassword(user.getPassword());
        savedUser.setCreatedDate(user.getCreatedDate());
        savedUser.setUpdatedDate(LocalDateTime.now());
        savedUser.setEmail(user.getEmail());
        savedUser.setUsername(user.getUsername());
        savedUser = userRepository.save(savedUser);
        UserDto updatedUserDto = ModelConverter.convertToDto(savedUser);
        updatedUserDto.setPassword(null);

        return updatedUserDto;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Deleting user with ID: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            log.error(USER_NOT_FOUND + " while deletion with parameter : {}", id);
            throw new NotFoundException(USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }

    private List<User> getDuplicateList(UserDto userDto) {
        List<User> duplicateUsers = userRepository.findAllByEmail(userDto.getEmail());
        if (userDto.getId() != null) {
            duplicateUsers = duplicateUsers.stream().filter(x -> !x.getId().equals(userDto.getId())).toList();
        }
        return duplicateUsers;
    }

    private String checkDuplicateUser(UserDto userDto) {
        String response = "";
        List<User> duplicateUsers = getDuplicateList(userDto);
        boolean isEmailDuplicateExists = false;

        for (User user : duplicateUsers) {
            isEmailDuplicateExists = isEmailDuplicateExists(userDto, isEmailDuplicateExists, user);
        }

        if (isEmailDuplicateExists) {
            response = "Email already exists";
        }

        return response;
    }

    private boolean isEmailDuplicateExists(UserDto userDto, boolean isEmailDuplicateExists, User user) {
        if (user.getEmail() != null && userDto.getEmail() != null && user.getEmail().equals(userDto.getEmail())) {
            isEmailDuplicateExists = true;
        }
        return isEmailDuplicateExists;
    }

    public boolean sendPushNotificationToUser(NotificationRequest notificationRequest) {
        try {
            webSocketService.notifyUser(notificationRequest.getEmail(), notificationRequest.getMessage());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
