package com.NextStepEdu.mappers;

import com.NextStepEdu.dto.requests.RequestUserProfile;
import com.NextStepEdu.dto.responses.ResponseUserProfile;
import com.NextStepEdu.models.UserModel;
import com.NextStepEdu.models.UserProfileModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {

    @Mapping(source = "user.id", target = "userId")
    ResponseUserProfile toResponse(UserProfileModel profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    UserProfileModel toEntity(RequestUserProfile request, UserModel user);

    List<ResponseUserProfile> toResponseList(List<UserProfileModel> models);
}
