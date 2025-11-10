package com.example.oauth.controller;

import com.example.oauth.config.JwtUtils;
import org.springframework.web.bind.annotation.*;

record LoginRequest(String username, String password) {}
record TokenResponse(String token) {}

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {

        if (req.username().equals("admin") && req.password().equals("adminPass")) {
            return new TokenResponse(JwtUtils.generateToken("admin", "ADMIN"));
        }
        if (req.username().equals("user") && req.password().equals("userPass")) {
            return new TokenResponse(JwtUtils.generateToken("user", "USER"));
        }

        throw new RuntimeException("Error login or password");
    }
}
