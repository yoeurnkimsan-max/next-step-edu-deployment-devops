package com.NextStepEdu.services;

import com.NextStepEdu.dto.requests.UniversityRequest;
import com.NextStepEdu.dto.responses.UniversityResponse;

import java.util.List;

public interface UniversityService {
    UniversityResponse createUniversity(UniversityRequest request);

    UniversityResponse getUniversityById(Integer id);

    UniversityResponse getUniversityBySlug(String slug);

    List<UniversityResponse> getAllUniversities();

    List<UniversityResponse> searchUniversities(String keyword);

    UniversityResponse updateUniversity(Integer id, UniversityRequest request);

    void deleteUniversity(Integer id);
}
