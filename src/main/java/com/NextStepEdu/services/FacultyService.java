package com.NextStepEdu.services;

import com.NextStepEdu.dto.requests.FacultyRequest;
import com.NextStepEdu.dto.responses.FacultyResponse;

import java.util.List;
import java.util.UUID;

public interface FacultyService {
    FacultyResponse create(FacultyRequest request);
    FacultyResponse getById(UUID id);
    List<FacultyResponse> getAll(UUID universityId);
    FacultyResponse update(UUID id, FacultyRequest request);
    void delete(UUID id);
}
