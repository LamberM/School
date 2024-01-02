package org.lamberm.school.studentclass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lamberm.school.student.Student;
import org.lamberm.school.teacher.Teacher;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;

    private String name;

    private Teacher mentor;

    private List<Student> studentList;
}
