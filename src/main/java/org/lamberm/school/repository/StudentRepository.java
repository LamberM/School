package org.lamberm.school.repository;

import org.lamberm.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByFirstName(@Param("firstName") String firstName);

    boolean existsByFirstName(@Param("firstName") String firstName);

    List<Student> findStudentByLastName(@Param("lastName") String lastName);

    boolean existsByLastName(@Param("lastName") String lastName);

    Optional<Student> findStudentByPesel(@Param("pesel") String pesel);


}
