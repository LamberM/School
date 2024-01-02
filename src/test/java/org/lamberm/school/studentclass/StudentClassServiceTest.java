package org.lamberm.school.studentclass;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.lamberm.school.error.handler.StudentClassExistException;
import org.lamberm.school.error.handler.StudentClassNotExistException;
import org.lamberm.school.error.handler.TeacherLastNameNotExistException;
import org.lamberm.school.teacher.Teacher;
import org.lamberm.school.teacher.TeacherMapper;
import org.lamberm.school.teacher.TeacherRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentClassServiceTest implements UnitTest {

    @InjectMocks
    StudentClassService systemUnderTest;

    @Mock
    StudentClassMapper studentClassMapperMock;

    @Mock
    StudentClassRepository studentClassRepositoryMock;

    @Mock
    StudentClassDto studentClassDtoMock;

    @Mock
    StudentClass studentClassMock;

    @Mock
    TeacherMapper teacherMapperMock;

    @Mock
    TeacherRepository teacherRepositoryMock;

    @Mock
    Teacher teacherMock;

    Long id = 1L;

    @Nested
    class AddStudentClassTest {
        @Test
        void shouldAddStudentClass() {
            when(!studentClassRepositoryMock.existsByName(studentClassDtoMock.getName())).thenReturn(false);
            when(!studentClassRepositoryMock.existsByYear(studentClassDtoMock.getYear())).thenReturn(false);
            when(!studentClassRepositoryMock.existsByMentor(studentClassDtoMock.getMentor())).thenReturn(false);
            when(studentClassMapperMock.map(studentClassDtoMock)).thenReturn(studentClassMock);

            systemUnderTest.addStudentClass(studentClassDtoMock);

            verify(studentClassMapperMock).map(studentClassDtoMock);
            verify(studentClassRepositoryMock).save(studentClassMock);
        }

        @Test
        void shouldNotAddStudentClass() {
            when(!studentClassRepositoryMock.existsByName(studentClassDtoMock.getName())).thenReturn(true);

            assertThatThrownBy(() -> systemUnderTest.addStudentClass(studentClassDtoMock))
                    .isInstanceOf(StudentClassExistException.class)
                    .hasMessage("Student class exist");
        }
    }

    @Nested
    class DeleteStudentClassTest {
        @Test
        void shouldDeleteStudentClass() {
            when(studentClassRepositoryMock.existsById(id)).thenReturn(true);

            systemUnderTest.deleteStudentClass(id);

            verify(studentClassRepositoryMock).deleteById(id);
        }

        @Test
        void shouldNotDeleteStudentClass() {
            when(studentClassRepositoryMock.existsById(id)).thenReturn(false);

            assertThatThrownBy(() -> systemUnderTest.deleteStudentClass(id))
                    .isInstanceOf(StudentClassNotExistException.class)
                    .hasMessage("Student class not exist");

        }
    }

    @Nested
    class EditStudentClassTest {
        @Test
        void shouldEditStudentClass() {
            when(!studentClassRepositoryMock.existsByName(studentClassDtoMock.getName())).thenReturn(false);
            when(!studentClassRepositoryMock.existsByYear(studentClassDtoMock.getYear())).thenReturn(false);
            when(!studentClassRepositoryMock.existsByMentor(studentClassDtoMock.getMentor())).thenReturn(false);
            when(studentClassRepositoryMock.findStudentClassByMentor(studentClassDtoMock.getMentor())).thenReturn(studentClassMock);

            systemUnderTest.editStudentClass(studentClassDtoMock);

            verify(studentClassMapperMock).updateStudentClassFromDto(studentClassDtoMock, studentClassMock);
            verify(studentClassRepositoryMock).save(studentClassMock);
        }

        @Test
        void shouldNotEditStudentClass() {
            when(!studentClassRepositoryMock.existsByName(studentClassDtoMock.getName())).thenReturn(true);

            assertThatThrownBy(() -> systemUnderTest.editStudentClass(studentClassDtoMock))
                    .isInstanceOf(StudentClassNotExistException.class)
                    .hasMessage("Student class not exist");
        }
    }

    @Nested
    class FindAllStudentClassTest {
        @Test
        void shouldFindAllStudentClass() {
            systemUnderTest.findAllStudentClass();

            verify(studentClassRepositoryMock).findAll();
        }
    }

    @Nested
    class FindStudentClassByYearTest {
        @Test
        void shouldFindStudentClassByYear() {
            systemUnderTest.findStudentClassByYear("1");

            verify(studentClassRepositoryMock).findStudentClassByYear("1");
        }
    }

    @Nested
    class FindStudentClassByNameTest {
        @Test
        void shouldFindStudentClassByName() {
            systemUnderTest.findStudentClassByName("A");

            verify(studentClassRepositoryMock).findStudentClassByName("A");
        }
    }

    @Nested
    class FindStudentClassByMentorTest {

        String mentorName = "test";

        @Test
        void shouldFindStudentClassByMentor() {
            when(teacherRepositoryMock.existsByLastName(mentorName)).thenReturn(true);
            when(teacherRepositoryMock.findTeacherByLastName(mentorName)).thenReturn(teacherMock);
            when(studentClassRepositoryMock.findStudentClassByMentor(teacherMapperMock.map(teacherMock))).thenReturn(studentClassMock);

            systemUnderTest.findStudentClassByMentor(mentorName);

            verify(teacherRepositoryMock).existsByLastName(mentorName);
            verify(teacherRepositoryMock).findTeacherByLastName(mentorName);
            verify(studentClassRepositoryMock).findStudentClassByMentor(teacherMapperMock.map(teacherMock));
        }

        @Test
        void shouldNotFindStudentClassByMentor() {
            when(teacherRepositoryMock.existsByLastName(mentorName)).thenReturn(false);

            assertThatThrownBy(() -> systemUnderTest.findStudentClassByMentor(mentorName))
                    .isInstanceOf(TeacherLastNameNotExistException.class)
                    .hasMessage("Teacher last name not exist");
        }
    }
}