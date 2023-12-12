package com.example.practice.project.service.impl;

import static com.example.practice.project.utilities.ApiEndPointConstants.USER_NOT_FOUND;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.User;
import com.example.practice.project.repository.UserRepository;
import com.example.practice.project.service.IUserService;
import com.example.practice.project.utilities.ModelConverter;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return null;
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
        return null;
    }

    @Override
    public UserDto add(UserDto userDto, boolean isSendNotification) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

    @Override
    public boolean updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }
}
