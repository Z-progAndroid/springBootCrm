package com.inmozara.crm.login.controller;

import com.inmozara.crm.login.model.AuthResponse;
import com.inmozara.crm.login.model.ForgotPasswordRequest;
import com.inmozara.crm.login.model.LoginRequest;
import com.inmozara.crm.login.model.RegisterRequest;
import com.inmozara.crm.login.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api_v1/crm/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    @PostMapping("/forgotPassword")
    public AuthResponse forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return authService.forgotPassword(request);
    }
}