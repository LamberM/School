package org.lamberm.school.student;

import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class StudentControllerTest implements UnitTest {
    @InjectMocks
    StudentController systemUnderTest;
    @Mock
    StudentService studentServiceMock;

    @Test
    void shouldSaveStudent() {
        StudentDto studentDto = new StudentDto("12345678910", "first", "second", "last");

        systemUnderTest.saveStudent(studentDto);

        verify(studentServiceMock).addStudent(studentDto);
    }

    @Test
    void shouldDeleteStudent() {
        Integer id = 1;

        systemUnderTest.deleteById(id);

        verify(studentServiceMock).deleteStudentById(id.longValue());
    }

    @Test
    void shouldProvideStudentsList() {
        systemUnderTest.getAll();

        verify(studentServiceMock).findAllStudents();
    }

    @Test
    void shouldProvideStudentById() {
        Integer id = 1;

        systemUnderTest.getById(id);

        verify(studentServiceMock).findStudentById(id.longValue());
    }

    @Test
    void shouldProvideStudentsByLastName() {
        String lastName = "test";

        systemUnderTest.getByLastName(lastName);

        verify(studentServiceMock).findStudentsByLastName(lastName);
    }

    @Test
    void shouldProvideStudentsByFirstName() {
        String firstName = "test";

        systemUnderTest.getByFirstName(firstName);

        verify(studentServiceMock).findStudentsByFirstName(firstName);
    }

    @Test
    void shouldProvideStudentByPESEL() {
        String pesel = "12345678910";

        systemUnderTest.getByPESEL(pesel);

        verify(studentServiceMock).findStudentByPESEL(pesel);
    }
}