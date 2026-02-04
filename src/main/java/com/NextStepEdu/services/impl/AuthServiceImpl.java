package com.NextStepEdu.services.impl;

import com.NextStepEdu.dto.requests.LoginRequest;
import com.NextStepEdu.dto.requests.RegisterRequest;
import com.NextStepEdu.dto.responses.AuthResponse;
import com.NextStepEdu.models.RoleModel;
import com.NextStepEdu.mappers.UserMapper;
import com.NextStepEdu.models.UserModel;
import com.NextStepEdu.repositories.RoleRepository;
import com.NextStepEdu.repositories.UserRepository;
import com.NextStepEdu.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final UserMapper userMapper;
        private final PasswordEncoder passwordEncoder;

        private final JwtEncoder accessTokenJwtEncoder;
        private final JwtEncoder refreshTokenEncoder;

        private final AuthenticationManager authenticationManager;

        @Override
        public void register(RegisterRequest registerRequest) {
                boolean email = userRepository.existsAllByEmail(registerRequest.email());

                if (email) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
                }
                UserModel userModel = new UserModel();
                userModel.setEmail(registerRequest.email());
                userModel.setPassword(passwordEncoder.encode(registerRequest.password()));

                RoleModel roleModel = roleRepository.findRoleUser();
                if (roleModel == null) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                        "Role 'USER' not found in database. Please ensure roles are initialized.");
                }
                userModel.setRoles(List.of(roleModel));
                userRepository.save(userModel);

        }

        @Override
        public AuthResponse login(LoginRequest loginRequest) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                loginRequest.email(),
                                                loginRequest.password()));

                String scope = authentication.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .filter(role -> role.startsWith("ROLE_")) // ✅ Only include your custom roles
                                .collect(Collectors.joining(" "));

                Instant now = Instant.now();

                // Access Token
                JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                                .id(authentication.getName())
                                .subject(authentication.getName())
                                .issuer("taskflow-api")
                                .issuedAt(now)
                                .expiresAt(now.plus(10, ChronoUnit.MINUTES)) // ✅ Adjust expiration
                                .audience(List.of("NextJs", "Android", "IOS"))
                                .claim("role", scope)
                                .claim("scope", scope)
                                .build();

                // Refresh Token
                JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
                                .id(authentication.getName())
                                .subject(authentication.getName())
                                .issuer("taskflow-api")
                                .issuedAt(now)
                                .expiresAt(now.plus(7, ChronoUnit.DAYS))
                                .audience(List.of("NextJs", "Android", "IOS"))
                                .claim("role", scope)
                                .claim("scope", scope)
                                .build();

                String accessToken = accessTokenJwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet))
                                .getTokenValue();
                String refreshToken = refreshTokenEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaimsSet))
                                .getTokenValue();

                return AuthResponse.builder()
                                .tokenType("Bearer")
                                .accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build();
        }
}
