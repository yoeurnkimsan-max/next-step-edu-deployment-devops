package com.NextStepEdu.init;


import com.NextStepEdu.models.RoleModel;
import com.NextStepEdu.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final RoleRepository roleRepository;

    @PostConstruct
    void init() {
        if (roleRepository.count() == 0) {
            RoleModel user = new RoleModel();
            user.setName("USER");

            RoleModel admin = new RoleModel();
            admin.setName("ADMIN");



            roleRepository.saveAll(List.of(user, admin));

            System.out.println("✅ Roles initialized: USER, ADMIN, STAFF");
        } else {
            System.out.println("ℹ️ Roles already exist in database");
        }
    }
}