package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.requests.RequestUserProfile;
import com.NextStepEdu.dto.responses.ResponseUserProfile;
import com.NextStepEdu.services.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseUserProfile> create(
            @RequestPart("name") String name,
            @RequestPart("phone") String phone,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userProfileService.create(name, phone, image));
    }

    @GetMapping("/findAll")
    public List<ResponseUserProfile> findAll() {
        return userProfileService.findAll();
    }
}
