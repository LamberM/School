package org.lamberm.school.studentclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.school.teacher.Teacher;
import org.lamberm.school.teacher.TeacherDto;
import org.lamberm.school.util.SchoolSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

@DataJpaTest
class StudentClassRepositoryTest {

    @Autowired
    StudentClassRepository systemUnderTest;

    @AfterEach
    void tearDown() {
        systemUnderTest.deleteAll();
    }

    @Nested
    class FindStudentClassByMentorTest {
        TeacherDto mentor = new TeacherDto("12345678910", "test", "", "test", SchoolSubject.POLISH);

        @Test
        void shouldFindStudentClassByMentor() {
            var studentClass = new StudentClass(1L, "2", "A", new Teacher(), new ArrayList<>());
            systemUnderTest.save(studentClass);

            var result = systemUnderTest.findStudentClassByMentor(mentor);

            Assertions.assertEquals(studentClass, result);
        }

        @Test
        void shouldNotFindStudentClassByMentor() {
            var result = systemUnderTest.findStudentClassByMentor(mentor);

            Assertions.assertNull(result);
        }
    }

    @Nested
    class FindStudentClassByYearTest {
        String year = "2";

        @Test
        void shouldFindStudentClassByYear() {
            var studentClass = new StudentClass(1L, year, "A", new Teacher(), new ArrayList<>());
            systemUnderTest.save(studentClass);

            var result = systemUnderTest.findStudentClassByYear(year);

            Assertions.assertEquals(new ArrayList<>(), result);
        }

        @Test
        void shouldFindEmptyList() {
            var result = systemUnderTest.findStudentClassByYear(year);

            Assertions.assertNull(result);
        }
    }

    @Nested
    class FindStudentClassByNameTest {
        String name = "A";

        @Test
        void shouldFindStudentClassByName() {
            var studentClass = new StudentClass(1L, "2", name, new Teacher(), new ArrayList<>());
            systemUnderTest.save(studentClass);

            var result = systemUnderTest.findStudentClassByName(name);

            Assertions.assertEquals(new ArrayList<>(), result);
        }

        @Test
        void shouldFindEmptyList() {
            var result = systemUnderTest.findStudentClassByName(name);

            Assertions.assertNull(result);
        }
    }

    @Nested
    class ExistsByNameTest {
        String name = "A";

        @Test
        void shouldGetTrue() {
            var studentClass = new StudentClass(1L, "2", name, new Teacher(), new ArrayList<>());
            systemUnderTest.save(studentClass);

            var exist = systemUnderTest.existsByName(name);

            Assertions.assertTrue(exist);
        }

        @Test
        void shouldGetFalse() {
            var exist = systemUnderTest.existsByName(name);

            Assertions.assertFalse(exist);

        }
    }

    @Nested
    class ExistsByYearTest {
        String year = "2";

        @Test
        void shouldGetTrue() {
            var studentClass = new StudentClass(1L, year, "A", new Teacher(), new ArrayList<>());
            systemUnderTest.save(studentClass);

            var exist = systemUnderTest.existsByYear(year);

            Assertions.assertTrue(exist);
        }

        @Test
        void shouldGetFalse() {
            var exist = systemUnderTest.existsByYear(year);

            Assertions.assertFalse(exist);
        }
    }

    @Nested
    class ExistsByMentorTest {
        TeacherDto mentor = new TeacherDto("12345678910", "test", "", "test", SchoolSubject.POLISH);

        @Test
        void shouldGetTrue() {
            var studentClass = new StudentClass(1L, "2", "A", new Teacher(), new ArrayList<>());
            systemUnderTest.save(studentClass);

            var exist = systemUnderTest.existsByMentor(mentor);

            Assertions.assertTrue(exist);
        }

        @Test
        void shouldGetFalse() {
            var exist = systemUnderTest.existsByMentor(mentor);

            Assertions.assertFalse(exist);
        }
    }

    @Nested
    class ExistsByIdTest {
        Long id = 1L;

        @Test
        void shouldGetTrue() {
            var exist = systemUnderTest.existsById(id);

            Assertions.assertTrue(exist);
        }

        @Test
        void shouldGetFalse() {
            var exist = systemUnderTest.existsById(id);

            Assertions.assertFalse(exist);
        }
    }
}