package com.example.practice.project.service.impl;

import com.example.practice.project.dto.UserDto;
import com.example.practice.project.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.getUserByEmail(username);

        if (userDto != null) {

            return UserDetailsImpl.build(userDto);
        }
        throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
}
