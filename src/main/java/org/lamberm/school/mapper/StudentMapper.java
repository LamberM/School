package org.lamberm.school.mapper;


import org.lamberm.school.model.Student;
import org.lamberm.school.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StudentMapper {
    default Student map(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        } else {
            return Student
                    .builder()
                    .id(studentDTO.getId())
                    .pesel(studentDTO.getPesel())
                    .firstName(studentDTO.getFirstName())
                    .secondName(studentDTO.getSecondName())
                    .lastName(studentDTO.getLastName())
                    .build();
        }
    }

    default StudentDTO map(Student student) {
        if (student == null) {
            return null;
        } else {
            return StudentDTO
                    .builder()
                    .id(student.getId())
                    .pesel(student.getPesel())
                    .firstName(student.getFirstName())
                    .secondName(student.getSecondName())
                    .lastName(student.getLastName())
                    .build();
        }
    }
}
