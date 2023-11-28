package org.lamberm.school.teacher;

import org.lamberm.school.util.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findTeacherBySchoolSubject(@Param("schoolSubject") SchoolSubject schoolSubject);

    Teacher findTeacherByPesel(@Param("pesel") String pesel);

    boolean existsByPesel(@Param("pesel") String pesel);

    boolean existsById(@Param("id") Long id);
}
