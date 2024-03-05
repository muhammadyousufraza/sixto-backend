package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.API;
import static com.example.practice.project.utilities.Constants.USER;

import com.example.practice.project.dto.UserDto;
import com.example.practice.project.model.request.AdminAddRequest;
import com.example.practice.project.model.request.NotificationRequest;
import com.example.practice.project.model.request.UserAddRequest;
import com.example.practice.project.model.request.UserCompanyShareholderRequest;
import com.example.practice.project.model.request.UserUpdateRequest;
import com.example.practice.project.service.IUserService;
import com.example.practice.project.utilities.ModelConverter;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping(API + USER)
public class UserController {

    @Autowired
    IUserService userService;


    @PostMapping()
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserAddRequest userAddRequest) {
        log.info(" Users Signup Request: {}", userAddRequest.getEmail());
        UserDto userDto = userService.add(ModelConverter.convertToDto(userAddRequest), true);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/admin")
    public ResponseEntity<UserDto> saveAdmin(@Valid @RequestBody AdminAddRequest adminAddRequest) {
        log.info(" Admin Signup Request: {}", adminAddRequest.getEmail());
        UserDto userDto = userService.addAdmin(ModelConverter.convertToDto(adminAddRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/save-user-company-shareholder")
    public ResponseEntity<UserCompanyShareholderRequest> saveUserAndCompany(@Valid @RequestBody UserCompanyShareholderRequest userCompanyShareholderRequest) {
        log.info("Save User and company and shareholder {}", userCompanyShareholderRequest);
        UserCompanyShareholderRequest userDto = userService.add(userCompanyShareholderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping()
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateRequest userDto) {
        log.info("Update Users By Id Request: {}", userDto.getId());
        UserDto updatedUser = userService.update(ModelConverter.convertToDto(userDto));
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        log.debug("Get All Users");
        List<UserDto> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        log.debug("Get Users By Id Request: {}", id);
        UserDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete Users By Id Request: {}", id);
        userService.deleteById(id);
        log.info("Deleted Successfully : {}", id);
        return ResponseEntity.ok("Deleted Successfully");
    }


    @PostMapping("/notify")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("Sending notification to : {}", notificationRequest.getEmail());
        userService.sendPushNotificationToUser(notificationRequest);
        return ResponseEntity.ok("Send Successfully");
    }


}
