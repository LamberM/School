package org.lamberm.school.controller;

import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.lamberm.school.dto.StudentDTO;
import org.lamberm.school.service.StudentService;
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
        StudentDTO studentDTO = new StudentDTO("12345678910", "first", "second", "last");

        systemUnderTest.saveStudent(studentDTO);

        verify(studentServiceMock).addStudent(studentDTO);
    }

    @Test
    void shouldDeleteStudent() {
        Integer id = 1;

        systemUnderTest.deleteStudentById(id);

        verify(studentServiceMock).deleteStudentById(id.longValue());
    }

    @Test
    void shouldProvideStudentsList() {
        systemUnderTest.provideStudentsList();

        verify(studentServiceMock).findAllStudents();
    }

    @Test
    void shouldProvideStudentById() {
        Integer id = 1;

        systemUnderTest.provideStudentById(id);

        verify(studentServiceMock).findStudentById(id.longValue());
    }

    @Test
    void shouldProvideStudentsByLastName() {
        String lastName = "test";

        systemUnderTest.provideStudentsByLastName(lastName);

        verify(studentServiceMock).findStudentsByLastName(lastName);
    }

    @Test
    void shouldProvideStudentsByFirstName() {
        String firstName = "test";

        systemUnderTest.provideStudentsByFirstName(firstName);

        verify(studentServiceMock).findStudentsByFirstName(firstName);
    }

    @Test
    void shouldProvideStudentByPESEL() {
        String pesel = "12345678910";

        systemUnderTest.provideStudentByPESEL(pesel);

        verify(studentServiceMock).findStudentByPESEL(pesel);
    }
}