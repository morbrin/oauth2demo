package com.example.oauth.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrance")
public class UserController {
    @GetMapping("/hello/user")
    public String userPass() {
        return "Привет, USER";
    }

    @GetMapping("/hello/admin")
    public String adminPass() {
        return "Привет, ADMIN";
    }
}
