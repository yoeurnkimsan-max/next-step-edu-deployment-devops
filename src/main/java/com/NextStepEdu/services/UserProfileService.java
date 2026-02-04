package com.NextStepEdu.services;

import com.NextStepEdu.dto.requests.RequestUserProfile;
import com.NextStepEdu.dto.responses.ResponseUserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserProfileService {

    ResponseUserProfile create(String name , String phone , MultipartFile image);

    List<ResponseUserProfile > findAll();
}
