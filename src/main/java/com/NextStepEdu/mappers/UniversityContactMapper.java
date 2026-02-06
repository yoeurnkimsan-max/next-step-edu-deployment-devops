package com.NextStepEdu.mappers;

import com.NextStepEdu.dto.requests.UniversityContactRequest;
import com.NextStepEdu.dto.responses.UniversityContactResponse;
import com.NextStepEdu.models.UniversityContactModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UniversityContactMapper {

    @Mapping(source = "university.id", target = "universityId")
    UniversityContactResponse toResponse(UniversityContactModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "university", ignore = true)
    UniversityContactModel toModel(UniversityContactRequest request);
}
