package org.lamberm.school.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository systemUnderTest;

    @AfterEach
    void tearDown() {
        systemUnderTest.deleteAll();
    }

    String firstName = "test";
    String lastName = "test";
    String pesel = "12345678910";
    Long id = 1L;

    @Nested
    class findStudentsByFirstNameTest {
        @Test
        void shouldGetStudents() {
            var student = new Student(1L, "12345678910", firstName, "second", "last", "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.getStudentsByFirstName(firstName).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotGetStudentsWhenStudentsNotExist() {
            var isEmpty = systemUnderTest.getStudentsByFirstName(firstName).isEmpty();

            Assertions.assertTrue(isEmpty);
        }
    }

    @Nested
    class isFirstNameExistTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(1L, "12345678910", firstName, "second", "last", "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.isFirstNameExist(firstName);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.isFirstNameExist(firstName);

            Assertions.assertFalse(isEmpty);
        }
    }

    @Nested
    class findStudentsByLastNameTest {
        @Test
        void shouldGetStudents() {
            var student = new Student(1L, "12345678910", "first", "second", lastName, "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.getStudentsByLastName(lastName).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotGetStudentsWhenStudentsNotExist() {
            var isEmpty = systemUnderTest.getStudentsByLastName(lastName).isEmpty();

            Assertions.assertTrue(isEmpty);
        }
    }

    @Nested
    class isLastNameExistTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(1L, "12345678910", "first", "second", lastName, "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.isLastNameExist(lastName);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.isLastNameExist(lastName);

            Assertions.assertFalse(isEmpty);
        }
    }

    @Nested
    class findStudentByPeselTest {
        @Test
        void shouldGetStudent() {
            var student = new Student(1L, pesel, "first", "second", "lastName", "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.findStudentByPESEL(pesel).isPresent();

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetNothingWhenStudentNotExist() {
            var isEmpty = systemUnderTest.findStudentByPESEL(pesel).isPresent();

            Assertions.assertFalse(isEmpty);
        }
    }

    @Nested
    class isPeselExistTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(1L, pesel, "first", "second", lastName, "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.isPeselExist(pesel);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.isPeselExist(pesel);

            Assertions.assertFalse(isEmpty);
        }
    }

    @Nested
    class isIdExistTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(id, "12345678910", "first", "second", "lastName", "");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.isIdExist(id);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.isIdExist(id);

            Assertions.assertFalse(isEmpty);
        }
    }
}