package org.lamberm.school.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.lamberm.school.error.handler.IdNotExistException;
import org.lamberm.school.error.handler.PeselExistException;
import org.lamberm.school.error.handler.PeselNotExistException;
import org.lamberm.school.error.handler.StudentNotExistException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServiceTest implements UnitTest {
    @InjectMocks
    StudentService systemUnderTest;
    @Mock
    StudentRepository studentRepositoryMock;
    @Mock
    StudentMapper studentMapperMock;

    @Nested
    class addStudentTest {
        StudentDto studentDtoMock = mock(StudentDto.class);
        Student studentMock = mock(Student.class);

        @Test
        void shouldAddStudent() {
            when(studentMapperMock.map(studentDtoMock)).thenReturn(studentMock);

            systemUnderTest.addStudent(studentDtoMock);

            verify(studentMapperMock).map(studentDtoMock);
            verify(studentRepositoryMock).save(studentMock);
            Assertions.assertNotNull(studentRepositoryMock.findById(studentMock.getId()));
        }

        @Test
        void shouldNotAddStudentStudentExist() {
            when(studentRepositoryMock.isPeselExist(studentDtoMock.getPesel())).thenReturn(true);

            assertThatThrownBy(() -> systemUnderTest.addStudent(studentDTOMock))
                    .isInstanceOf(PeselExistException.class)
                    .hasMessage("PESEL exist");
        }
    }

    @Nested
    class deleteStudentByIdTest {
        Long id = 1L;

        @Test
        void shouldDeleteStudent() {
            when(studentRepositoryMock.isIdExist(id)).thenReturn(true);

            systemUnderTest.deleteStudentById(id);
            verify(studentRepositoryMock).deleteById(id);
        }

        @Test
        void shouldNotDeleteStudentIdNotExist() {
            when(studentRepositoryMock.isIdExist(id)).thenReturn(false);

            assertThatThrownBy(() -> systemUnderTest.deleteStudentById(id))
                    .isInstanceOf(IdNotExistException.class)
                    .hasMessage("ID doesn't exist");
        }
    }

    @Nested
    class findAllStudentsTest {
        @Test
        void shouldFindAllStudents() {
            systemUnderTest.findAllStudents();

            verify(studentRepositoryMock).findAll();
        }
    }

    @Nested
    class findStudentById {
        Long id = 1L;

        @Test
        void shouldFindStudentById() {
            var studentMock = mock(Student.class);
            var studentDtoMock = mock(StudentDto.class);
            when(studentRepositoryMock.isIdExist(id)).thenReturn(true);
            when(studentRepositoryMock.findById(id)).thenReturn(Optional.of(studentMock));
            when(studentMapperMock.map(studentMock)).thenReturn(studentDtoMock);

            var result = systemUnderTest.findStudentById(id);

            Assertions.assertEquals(studentDtoMock, result);
        }

        @Test
        void shouldNotFindStudentIdNotExist() {
            assertThatThrownBy(() -> systemUnderTest.findStudentById(id))
                    .isInstanceOf(IdNotExistException.class)
                    .hasMessage("ID doesn't exist");
        }
    }

    @Nested
    class findStudentsByLastName {
        String lastName = "test";

        @Test
        void shouldFindStudents() {
            var studentMock = mock(Student.class);
            List<Student> studentsList = new ArrayList<>();
            studentsList.add(studentMock);
            when(studentRepositoryMock.isLastNameExist(lastName)).thenReturn(true);
            when(studentRepositoryMock.getStudentsByLastName(lastName)).thenReturn(studentsList);

            var isEmpty = systemUnderTest.findStudentsByLastName(lastName).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotFindStudentsLastNameNotExist() {
            assertThatThrownBy(() -> systemUnderTest.findStudentsByLastName(lastName))
                    .isInstanceOf(StudentNotExistException.class)
                    .hasMessage("Student doesn't exist");
        }
    }

    @Nested
    class findStudentsByFirstName {
        String firstName = "test";

        @Test
        void shouldFindStudents() {
            var studentMock = mock(Student.class);
            List<Student> studentsList = new ArrayList<>();
            studentsList.add(studentMock);
            when(studentRepositoryMock.isFirstNameExist(firstName)).thenReturn(true);
            when(studentRepositoryMock.getStudentsByFirstName(firstName)).thenReturn(studentsList);

            var isEmpty = systemUnderTest.findStudentsByFirstName(firstName).isEmpty();

            Assertions.assertFalse(isEmpty);
        }

        @Test
        void shouldNotFindStudentsFirstNameNotExist() {
            assertThatThrownBy(() -> systemUnderTest.findStudentsByFirstName(firstName))
                    .isInstanceOf(StudentNotExistException.class)
                    .hasMessage("Student doesn't exist");
        }
    }

    @Nested
    class findStudentByPesel {
        String pesel = "012345678910";

        @Test
        void shouldFindStudent() {
            var studentMock = mock(Student.class);
            var studentDtoMock = mock(StudentDto.class);
            when(studentRepositoryMock.isPeselExist(pesel)).thenReturn(true);
            when(studentRepositoryMock.findStudentByPESEL(pesel)).thenReturn(studentMock);
            when(studentMapperMock.map(studentMock)).thenReturn(studentDtoMock);

            var result = systemUnderTest.findStudentByPESEL(pesel);

            Assertions.assertEquals(studentDtoMock, result);
        }

        @Test
        void shouldNotFindStudentPeselNotExist() {
            assertThatThrownBy(() -> systemUnderTest.findStudentByPESEL(pesel))
                    .isInstanceOf(PeselNotExistException.class)
                    .hasMessage("PESEL doesn't exist");
        }
    }
}