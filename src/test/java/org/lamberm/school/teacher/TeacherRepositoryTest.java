package org.lamberm.school.teacher;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.school.util.SchoolSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository systemUnderTest;

    String pesel = "12345678910";

    @AfterEach
    void tearDown() {
        systemUnderTest.deleteAll();
    }

    @Nested
    class findTeacherBySchoolSubjectTest {
        @Test
        void shouldFindTeacherBySchoolSubject() {
            var teacher = new Teacher(1L, "12345678910", "test", "test", "test", SchoolSubject.POLISH);
            systemUnderTest.saveAndFlush(teacher);

            var isEmpty = systemUnderTest.findTeacherBySchoolSubject(SchoolSubject.POLISH).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotFindTeacherBySchoolSubjectListIsEmpty() {
            var isEmpty = systemUnderTest.findTeacherBySchoolSubject(SchoolSubject.POLISH).isEmpty();

            Assertions.assertTrue(isEmpty);
        }
    }

    @Nested
    class findTeacherByPeselTest {
        @Test
        void shouldFindTeacherBySchoolSubject() {
            var teacher = new Teacher(3L, pesel, "test", "test", "test", SchoolSubject.POLISH);
            systemUnderTest.save(teacher);

            var result = systemUnderTest.findTeacherByPesel(pesel);

            Assertions.assertEquals(teacher, result);
        }

        @Test
        void shouldNotFindTeacherBySchoolSubjectListIsEmpty() {
            var result = systemUnderTest.findTeacherByPesel(pesel);

            Assertions.assertNull(result);
        }
    }

    @Nested
    class existsByPeselTest {
        @Test
        void shouldGetTrue() {
            var teacher = new Teacher(1L, pesel, "test", "test", "test", SchoolSubject.POLISH);
            systemUnderTest.save(teacher);

            var exist = systemUnderTest.existsByPesel(pesel);

            Assertions.assertTrue(exist);
        }

        @Test
        void shouldGetFalse() {
            var exist = systemUnderTest.existsByPesel(pesel);

            Assertions.assertFalse(exist);
        }
    }

    @Nested
    class existsByIdTest {

        Long id = 1L;

        @Test
        void shouldGetTrue() {
            var teacher = new Teacher(id, pesel, "test", "test", "test", SchoolSubject.POLISH);
            systemUnderTest.save(teacher);

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