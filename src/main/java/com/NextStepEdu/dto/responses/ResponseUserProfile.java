package com.NextStepEdu.dto.responses;


public record ResponseUserProfile(

        Integer id,
        String name,
        String phone,
        String image,
        Integer userId

) {}