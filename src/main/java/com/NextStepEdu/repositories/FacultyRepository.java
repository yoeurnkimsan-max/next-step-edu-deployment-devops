package com.NextStepEdu.repositories;

import com.NextStepEdu.models.FacultyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacultyRepository extends JpaRepository<FacultyModel, UUID> {
    Optional<FacultyModel> findByName(String name);
    List<FacultyModel> findByUniversity_Id(UUID universityId);
    boolean existsByUniversity_IdAndNameIgnoreCase(UUID universityId, String name);
}
