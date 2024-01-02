package org.lamberm.school.studentclass;

import org.lamberm.school.teacher.TeacherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    StudentClass findStudentClassByMentor(@Param("mentor") TeacherDto mentor);

    List<StudentClass> findStudentClassByYear(@Param("year") String year);

    List<StudentClass> findStudentClassByName(@Param("name") String name);

    boolean existsByName(@Param("name") String name);

    boolean existsByYear(@Param("year") String year);

    boolean existsByMentor(@Param("mentor") TeacherDto mentor);

    boolean existsById(@Param("id") Long id);
}
