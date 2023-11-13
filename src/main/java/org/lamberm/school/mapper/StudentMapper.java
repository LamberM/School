package org.lamberm.school.mapper;


import org.lamberm.school.model.Student;
import org.lamberm.school.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StudentMapper {
    Student map(StudentDTO studentDTO);
    StudentDTO map(Student student);
}
