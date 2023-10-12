package org.lamberm.school.repository;

import org.lamberm.school.model.Student;
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

    @Query("SELECT CASE WHEN COUNT(student) > 0 THEN TRUE ELSE FALSE END FROM Student student  WHERE student.firstName = :firstName")
    Boolean isFirstNameExist(@Param("firstName") String firstName);

    @Query("SELECT student FROM Student student  WHERE student.lastName LIKE %:lastName%")
    List<Student> findStudentsByLastName(@Param("lastName") String lastName);

    @Query("SELECT CASE WHEN COUNT(student) > 0 THEN TRUE ELSE FALSE END  FROM Student student WHERE student.lastName = :lastName")
    Boolean isLastNameExist(@Param("lastName") String lastName);

    @Query("SELECT student FROM Student student WHERE student.pesel LIKE %:pesel%")
    Optional<Student> findStudentByPESEL(@Param("pesel") String pesel);


}
