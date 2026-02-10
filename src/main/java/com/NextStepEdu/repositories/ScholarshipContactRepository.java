package com.NextStepEdu.repositories;

import com.NextStepEdu.models.ScholarshipContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScholarshipContactRepository extends JpaRepository<ScholarshipContactModel, Integer> {

    List<ScholarshipContactModel> findByScholarshipId(Integer scholarshipId);
}
