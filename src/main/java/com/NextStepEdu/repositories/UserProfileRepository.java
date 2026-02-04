package com.NextStepEdu.repositories;

import com.NextStepEdu.models.UserModel;
import com.NextStepEdu.models.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository  extends JpaRepository<UserProfileModel , Integer> {
    boolean existsByUser(UserModel user);
}
