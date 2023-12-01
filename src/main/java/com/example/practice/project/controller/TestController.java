package com.example.practice.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/get")
    public String getMethod() {
        return "Hi";
    }

    @GetMapping("/")
    public String index() {
        return "Welcome";
    }
}
