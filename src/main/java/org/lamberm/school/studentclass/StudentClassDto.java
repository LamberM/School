package org.lamberm.school.studentclass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.lamberm.school.student.Student;
import org.lamberm.school.teacher.TeacherDto;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentClassDto {
    @Pattern(regexp = "[0-9]", message = "Year must contain only numbers")
    @NotBlank(message = "Year must not be blank")
    private String year;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank
    private TeacherDto mentor;

    @NotBlank
    private List<Student> studentList;
}
