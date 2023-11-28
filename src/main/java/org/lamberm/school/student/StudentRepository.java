package org.lamberm.school.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByFirstName(@Param("firstName") String firstName);

    boolean existsByFirstName(@Param("firstName") String firstName);

    List<Student> findStudentByLastName(@Param("lastName") String lastName);

    boolean existsByLastName(@Param("lastName") String lastName);

    Student findStudentByPesel(@Param("pesel") String pesel);

    boolean existsByPesel(@Param("pesel") String pesel);
}
