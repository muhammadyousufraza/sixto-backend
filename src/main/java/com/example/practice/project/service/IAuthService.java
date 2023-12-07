package com.example.practice.project.service;


import com.example.practice.project.dto.ResponseDto;
import com.example.practice.project.model.request.ChangePasswordRequest;

public interface IAuthService {

    ResponseDto changePassword(ChangePasswordRequest changePasswordRequest);
}
