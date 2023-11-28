package org.lamberm.school.teacher;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TeacherMapper {
    Teacher map(TeacherDto teacherDto);

    TeacherDto map(Teacher teacher);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherDto teacherDto, @MappingTarget Teacher teacher);
}
