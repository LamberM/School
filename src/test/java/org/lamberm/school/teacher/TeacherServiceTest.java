package org.lamberm.school.teacher;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.lamberm.school.error.handler.IdNotExistException;
import org.lamberm.school.error.handler.PeselExistException;
import org.lamberm.school.error.handler.PeselNotExistException;
import org.lamberm.school.util.SchoolSubject;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TeacherServiceTest implements UnitTest {
    @InjectMocks
    TeacherService systemUnderTest;

    @Mock
    TeacherRepository teacherRepositoryMock;

    @Mock
    TeacherMapper teacherMapperMock;

    @Mock
    TeacherDto teacherDTOMock;

    @Mock
    Teacher teacherMock;

    Long id = 1L;

    @Nested
    class addTeacherTest {

        @Test
        void shouldAddTeacher() {
            when(teacherRepositoryMock.existsByPesel(teacherDTOMock.getPesel())).thenReturn(false);
            when(teacherMapperMock.map(teacherDTOMock)).thenReturn(teacherMock);

            systemUnderTest.addTeacher(teacherDTOMock);

            verify(teacherMapperMock).map(teacherDTOMock);
            verify(teacherRepositoryMock).save(teacherMock);
        }

        @Test
        void shouldNotAddTeacher_TeacherExist() {
            when(teacherRepositoryMock.existsByPesel(teacherDTOMock.getPesel())).thenReturn(true);

            assertThatThrownBy(() -> systemUnderTest.addTeacher(teacherDTOMock))
                    .isInstanceOf(PeselExistException.class)
                    .hasMessage("PESEL exist");
        }
    }

    @Nested
    class deleteTeacherTest {

        @Test
        void shouldDeleteTeacher() {
            when(teacherRepositoryMock.existsById(id)).thenReturn(true);

            systemUnderTest.deleteTeacher(id);

            verify(teacherRepositoryMock).deleteById(id);
        }

        @Test
        void shouldNotDeleteTeacherIdNotExist() {
            when(teacherRepositoryMock.existsById(id)).thenReturn(false);

            assertThatThrownBy(() -> systemUnderTest.deleteTeacher(id))
                    .isInstanceOf(IdNotExistException.class)
                    .hasMessage("ID doesn't exist");
        }
    }

    @Nested
    class editTeacherTest {

        @Test
        void shouldEditTeacher() {
            when(teacherRepositoryMock.existsByPesel(teacherDTOMock.getPesel())).thenReturn(true);
            when(teacherRepositoryMock.findTeacherByPesel(teacherDTOMock.getPesel())).thenReturn(teacherMock);

            systemUnderTest.editTeacher(teacherDTOMock);

            verify(teacherMapperMock).updateTeacherFromDto(teacherDTOMock, teacherMock);
            verify(teacherRepositoryMock).save(teacherMock);
        }

        @Test
        void shouldNotEditTeacherPeselNotExist() {
            when(teacherRepositoryMock.existsByPesel(teacherDTOMock.getPesel())).thenReturn(false);

            assertThatThrownBy(() -> systemUnderTest.editTeacher(teacherDTOMock))
                    .isInstanceOf(PeselNotExistException.class)
                    .hasMessage("PESEL doesn't exist");
        }
    }

    @Nested
    class findAllTeachersTest {

        @Test
        void shouldFindAllTeachers() {
            systemUnderTest.findAllTeachers();

            verify(teacherRepositoryMock).findAll();
        }
    }

    @Nested
    class findTeachersBySchoolSubjectTest {

        @Test
        void shouldFindTeachersBySchoolSubject() {
            systemUnderTest.findTeachersBySchoolSubject(SchoolSubject.POLISH);

            verify(teacherRepositoryMock).findTeacherBySchoolSubject(SchoolSubject.POLISH);
        }
    }
}