package org.lamberm.school.teacher;

import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TeacherMapper {
    Teacher map(TeacherDto teacherDto);

    TeacherDto map(Teacher teacher);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherDto teacherDto, @MappingTarget Teacher teacher);
}
