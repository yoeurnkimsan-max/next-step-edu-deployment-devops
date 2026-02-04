package com.NextStepEdu.services.impl;

import com.NextStepEdu.dto.requests.RequestUserProfile;
import com.NextStepEdu.dto.responses.ResponseUserProfile;
import com.NextStepEdu.mappers.UserProfileMapper;
import com.NextStepEdu.models.UserModel;
import com.NextStepEdu.models.UserProfileModel;
import com.NextStepEdu.repositories.UserProfileRepository;
import com.NextStepEdu.repositories.UserRepository;
import com.NextStepEdu.services.CloudinaryImageService;
import com.NextStepEdu.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private  final UserRepository userRepository;
    private  final CloudinaryImageService  cloudinaryImageService;
    private final UserProfileMapper userProfileMapper;
    @Override
    public ResponseUserProfile create(String name , String phone , MultipartFile image) {

        // 1️⃣ Get logged-in user email
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        // 2️⃣ Upload image (optional)
        String imageUrl = null;
        try {
            if (image != null && !image.isEmpty()) {
                Map<String, Object> upload = cloudinaryImageService.upload(image);
                imageUrl = (String) upload.get("secure_url");
            }
        } catch (Exception e) {
            throw new RuntimeException("Upload image failed", e);
        }

        // 3️⃣ Create profile
        UserProfileModel profile = new UserProfileModel();
        profile.setName(name);
        profile.setPhone(phone);
        profile.setImage(imageUrl);

        // 🔴 THIS LINE FIXES YOUR ERROR
        profile.setUser(user);

        UserProfileModel saved = userProfileRepository.save(profile);

        return userProfileMapper.toResponse(saved);
    }

    @Override
    public List<ResponseUserProfile> findAll() {
        return userProfileMapper.toResponseList(
                userProfileRepository.findAll()
        );
    }

}
