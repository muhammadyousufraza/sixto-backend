package com.example.practice.project.controller;

import static com.example.practice.project.utilities.Constants.VERSION;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/version")
    public String getVersion() {
        return "VERSION: " + VERSION;
    }

    @GetMapping("/")
    public String index() {
        return "Welcome";
    }
}
