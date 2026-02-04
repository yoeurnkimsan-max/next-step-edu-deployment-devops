package com.NextStepEdu.services;

import com.NextStepEdu.dto.requests.LoginRequest;
import com.NextStepEdu.dto.requests.RegisterRequest;
import com.NextStepEdu.dto.responses.AuthResponse;

public interface AuthService {

    void register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
