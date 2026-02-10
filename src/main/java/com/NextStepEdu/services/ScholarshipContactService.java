package com.NextStepEdu.services;

import com.NextStepEdu.dto.requests.ScholarshipContactRequest;
import com.NextStepEdu.models.ScholarshipContactModel;

import java.util.List;

public interface ScholarshipContactService {

    List<ScholarshipContactModel> findAll();

    List<ScholarshipContactModel> findByScholarshipId(Integer scholarshipId);

    ScholarshipContactModel findById(Integer id);

    ScholarshipContactModel create(ScholarshipContactRequest request);

    ScholarshipContactModel update(Integer id, ScholarshipContactRequest request);

    void delete(Integer id);
}
