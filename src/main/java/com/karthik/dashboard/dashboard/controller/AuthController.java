package com.karthik.dashboard.dashboard.controller;

import com.karthik.dashboard.dashboard.dto.ApiResponse;
import com.karthik.dashboard.dashboard.dto.auth.AuthResponse;
import com.karthik.dashboard.dashboard.dto.auth.LoginRequest;
import com.karthik.dashboard.dashboard.dto.auth.RegisterRequest;
import com.karthik.dashboard.dashboard.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @Valid @RequestBody RegisterRequest request) {

        String result = authService.register(request);

        return ResponseEntity.ok(
                new ApiResponse<>("success", result)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(
                new ApiResponse<>("success", response)
        );
    }
}