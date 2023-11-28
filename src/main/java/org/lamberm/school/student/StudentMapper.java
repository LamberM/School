package org.lamberm.school.student;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StudentMapper {
    Student map(StudentDto studentDTO);

    StudentDto map(Student student);
}
