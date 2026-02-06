package com.NextStepEdu.mappers;

import com.NextStepEdu.dto.responses.UserResponse;
import com.NextStepEdu.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "profile.firstname", target = "firstname")
    @Mapping(source = "profile.lastname", target = "lastname")
    @Mapping(source = "profile.phone", target = "phone")
    UserResponse toUserResponse(UserModel user);

}
