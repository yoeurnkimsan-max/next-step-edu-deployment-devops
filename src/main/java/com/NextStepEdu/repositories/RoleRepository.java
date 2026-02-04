package com.NextStepEdu.repositories;

import com.NextStepEdu.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleModel ,Integer> {
    @Query(""" 
            SELECT r
            FROM RoleModel r
            WHERE r.name = 'USER'

         """)
    RoleModel findRoleUser();

    @Query("SELECT r FROM RoleModel  as r WHERE r.name = 'ADMIN'")
    RoleModel findRoleAdmin();


}
