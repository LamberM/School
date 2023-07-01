package LamberM.School.repository;

import LamberM.School.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT student FROM Student student WHERE student.lastName LIKE %:lastName%")
    Optional<Student> findByLastName(@Param("lastName") String lastName);
    @Query("SELECT student FROM Student student WHERE student.firstName LIKE %:firstName%")
    Optional<Student> findByFirstName(@Param("firstName") String firstName);
}
