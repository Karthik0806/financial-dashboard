package com.karthik.dashboard.dashboard.service;

import com.karthik.dashboard.dashboard.dto.auth.AuthResponse;
import com.karthik.dashboard.dashboard.dto.auth.LoginRequest;
import com.karthik.dashboard.dashboard.dto.auth.RegisterRequest;
import com.karthik.dashboard.dashboard.exception.BadRequestException;
import com.karthik.dashboard.dashboard.exception.ResourceNotFoundException;
import com.karthik.dashboard.dashboard.model.*;
import com.karthik.dashboard.dashboard.repo.RoleRepo;
import com.karthik.dashboard.dashboard.repo.UserRepo;
import com.karthik.dashboard.dashboard.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final RoleRepo roleRepo;

    public String register(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        Role role = roleRepo.findByName(RoleName.ROLE_ANALYST)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .status(Status.ACTIVE)
                .roles(Set.of(role))
                .build();

        userRepo.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}