package com.NextStepEdu.dto.requests;


import jakarta.validation.constraints.NotBlank;

public record RequestUserProfile(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Phone number is required")
        String phone,

        // Cloudinary secure_url
        String image


) {}