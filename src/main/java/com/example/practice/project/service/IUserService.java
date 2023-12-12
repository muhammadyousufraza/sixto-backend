package com.example.practice.project.service;

import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.User;
import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto getUserByEmail(String username);

    UserDto getById(Long id);

    UserDto add(UserDto userDto, boolean isSendNotification);

    UserDto update(UserDto userDto);

    Boolean deleteById(Long id);

    boolean updatePassword(User user, String password);
}
