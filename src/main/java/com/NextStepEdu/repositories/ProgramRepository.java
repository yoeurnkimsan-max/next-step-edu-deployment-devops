package com.NextStepEdu.repositories;

import com.NextStepEdu.models.ProgramModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<ProgramModel, Integer> {
}
