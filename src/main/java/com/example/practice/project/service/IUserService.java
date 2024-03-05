package com.example.practice.project.service;

import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.User;
import com.example.practice.project.model.request.NotificationRequest;
import com.example.practice.project.model.request.UserCompanyShareholderRequest;
import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto getUserByEmail(String username);

    UserDto getById(Long id);

    UserDto add(UserDto userDto, boolean isSendNotification);

    UserDto addAdmin(UserDto userDto);

    UserCompanyShareholderRequest add(UserCompanyShareholderRequest userCompanyShareholderRequest);

    UserDto update(UserDto userDto);

    Boolean deleteById(Long id);

    boolean updatePassword(User user, String password);

    boolean sendPushNotificationToUser(NotificationRequest notificationRequest);

}