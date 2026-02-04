package com.NextStepEdu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")

public class UserProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    private String image;

    @NotBlank(message = "Phone number is required")

    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserModel user;
}
