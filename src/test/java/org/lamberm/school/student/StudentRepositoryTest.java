package org.lamberm.school.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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

    @Nested
    class findStudentByFirstNameTest {
        @Test
        void shouldGetStudents() {
            var student = new Student(1L, "12345678910", firstName, "second", "last");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.findStudentByFirstName(firstName).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotGetStudentsWhenStudentsNotExist() {
            var isEmpty = systemUnderTest.findStudentByFirstName(firstName).isEmpty();

            Assertions.assertTrue(isEmpty);
        }
    }

    @Nested
    class existsByFirstNameTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(1L, "12345678910", firstName, "second", "last");
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.existsByPesel(firstName);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.existsByPesel(firstName);

            Assertions.assertFalse(isEmpty);
        }
    }

    @Nested
    class findStudentByLastNameTest {
        @Test
        void shouldGetStudents() {
            var student = new Student(1L, "12345678910", "first", "second", lastName);
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.findStudentByLastName(lastName).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotGetStudentsWhenStudentsNotExist() {
            var isEmpty = systemUnderTest.findStudentByLastName(lastName).isEmpty();

            Assertions.assertTrue(isEmpty);
        }
    }

    @Nested
    class existsByLastNameTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(1L, "12345678910", "first", "second", lastName);
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.existsByLastName(lastName);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.existsByLastName(lastName);

            Assertions.assertFalse(isEmpty);
        }
    }

    @Nested
    class findStudentByPeselTest {
        @Test
        void shouldGetStudent() {
            var student = new Student(1L, pesel, "first", "second", "lastName");
            systemUnderTest.save(student);

            var result = systemUnderTest.findStudentByPesel(pesel);

            Assertions.assertEquals(student, result);
        }

        @Test
        void shouldGetNothingWhenStudentNotExist() {
            var result = systemUnderTest.findStudentByPesel(pesel);

            Assertions.assertNull(result);
        }
    }

    @Nested
    class existsByPeselTest {
        @Test
        void shouldGetTrue() {
            var student = new Student(1L, pesel, "first", "second", lastName);
            systemUnderTest.save(student);

            var isEmpty = systemUnderTest.existsByPesel(pesel);

            Assertions.assertTrue(isEmpty);
        }

        @Test
        void shouldGetFalse() {
            var isEmpty = systemUnderTest.existsByPesel(pesel);

            Assertions.assertFalse(isEmpty);
        }
    }

}