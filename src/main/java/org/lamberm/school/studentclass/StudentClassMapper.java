package org.lamberm.school.studentclass;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StudentClassMapper {

    StudentClass map(StudentClassDto studentClassDto);

    StudentClassDto map(StudentClass studentClass);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentClassFromDto(StudentClassDto studentClassDto, @MappingTarget StudentClass studentClass);
}
