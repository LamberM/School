package org.lamberm.school.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT student FROM Student student  WHERE student.firstName LIKE %:firstName%")
    List<Student> findStudentsByFirstName(@Param("firstName") String firstName);

    @Query("SELECT COUNT(student) > 0 FROM Student student WHERE student.firstName = :firstName")
    boolean isFirstNameExist(@Param("firstName") String firstName);

    @Query("SELECT student FROM Student student  WHERE student.lastName LIKE %:lastName%")
    List<Student> findStudentsByLastName(@Param("lastName") String lastName);

    @Query("SELECT COUNT(student) > 0 FROM Student student WHERE student.lastName = :lastName")
    boolean isLastNameExist(@Param("lastName") String lastName);

    @Query("SELECT student FROM Student student WHERE student.pesel LIKE %:pesel%")
    Optional<Student> findStudentByPESEL(@Param("pesel") String pesel);


}
