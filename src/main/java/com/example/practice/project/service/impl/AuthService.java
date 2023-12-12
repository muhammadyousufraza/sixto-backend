package com.example.practice.project.service.impl;


import static com.example.practice.project.utilities.ApiEndPointConstants.INVALID_PASSWORD;
import static com.example.practice.project.utilities.ApiEndPointConstants.NEW_AND_OLD_PASSWORD_SAME_ERROR;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.dto.ResponseDto;
import com.example.practice.project.dto.UserDto;
import com.example.practice.project.model.request.ChangePasswordRequest;
import com.example.practice.project.service.IAuthService;
import com.example.practice.project.utilities.ModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthService implements IAuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDto changePassword(ChangePasswordRequest changePasswordRequest) {
        UserDto userDto = userService.getUserByEmail(changePasswordRequest.getEmail());

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), userDto.getPassword())) {
            log.error("Entered Password is Invalid For Email: ", changePasswordRequest.getEmail());
            throw new BusinessException(INVALID_PASSWORD);
        }
        if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getOldPassword())) {
            log.error("New and Old Password could not be same ");
            throw new BusinessException(NEW_AND_OLD_PASSWORD_SAME_ERROR);
        }

        userService.updatePassword(ModelConverter.convertToEntity(userDto), changePasswordRequest.getNewPassword());


        log.info("Password Updated Successfully With Parameter Email {} :  ", changePasswordRequest.getEmail());
        return ResponseDto.builder().message("Password Updated Successfully").status(true).build();
    }
}
