package com.NextStepEdu.mappers;

import com.NextStepEdu.dto.responses.AuthResponse;
import com.NextStepEdu.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    AuthResponse toAuthResponse(UserModel user);

}
