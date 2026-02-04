package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.requests.LoginRequest;
import com.NextStepEdu.dto.requests.RegisterRequest;
import com.NextStepEdu.dto.responses.AuthResponse;
import com.NextStepEdu.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    void register(@Valid @RequestBody RegisterRequest registerRequest) {
         authService.register(registerRequest);
    }
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
