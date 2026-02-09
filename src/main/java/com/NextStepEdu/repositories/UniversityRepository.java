package com.NextStepEdu.repositories;

import com.NextStepEdu.models.UniversityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UniversityRepository extends JpaRepository<UniversityModel, UUID> {

    Optional<UniversityModel> findBySlug(String slug);
}
