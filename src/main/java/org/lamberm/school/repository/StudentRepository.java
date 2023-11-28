package org.lamberm.school.repository;

import org.lamberm.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT student FROM Student student  WHERE student.firstName LIKE %:firstName%")
    List<Student> getStudentsByFirstName(@Param("firstName") String firstName);

    @Query("SELECT COUNT(student) > 0 FROM Student student WHERE student.firstName = :firstName")
    boolean isFirstNameExist(@Param("firstName") String firstName);

    @Query("SELECT student FROM Student student  WHERE student.lastName LIKE %:lastName%")
    List<Student> getStudentsByLastName(@Param("lastName") String lastName);

    @Query("SELECT COUNT(student) > 0 FROM Student student WHERE student.lastName = :lastName")
    boolean isLastNameExist(@Param("lastName") String lastName);

    @Query("SELECT student FROM Student student WHERE student.pesel LIKE %:pesel%")
    Student findStudentByPESEL(@Param("pesel") String pesel);

    @Query("SELECT COUNT(student) > 0 FROM Student student WHERE student.pesel = :pesel")
    boolean isPeselExist(@Param("pesel") String pesel);

    @Query("SELECT COUNT(student) > 0 FROM Student student WHERE student.id = :id")
    boolean isIdExist(@Param("id") Long id);
}
