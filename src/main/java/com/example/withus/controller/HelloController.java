package com.example.withus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from public endpoint!";
    }

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "Hello from secure endpoint! You are authenticated.";
    }
}

