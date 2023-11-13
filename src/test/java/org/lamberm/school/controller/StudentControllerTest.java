package org.lamberm.school.controller;

import org.junit.jupiter.api.Assertions;
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
    void givenStudent_whenAddStudentToDatabase_thenAddStudent() {
        //given
        StudentDTO studentDTO = new StudentDTO(1L, "12345678910", "first", "second", "last");
        //when
        systemUnderTest.addStudentToDatabase(studentDTO);
        //then
        verify(studentServiceMock).addStudent(studentDTO);
    }

    @Test
    void givenStudentWithWrongFirstName_whenAddStudentToDatabase_thenRestErrorResponse() {
        //given
        StudentDTO studentDTO = new StudentDTO(1L, "12345678910", "", "second", "last");
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.addStudentToDatabase(studentDTO));
    }

    @Test
    void givenStudentWithWrongSecondName_whenAddStudentToDatabase_thenRestErrorResponse() {
        //given
        StudentDTO studentDTO = new StudentDTO(1L, "12345678910", "first", "second name length than twenty", "last");
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.addStudentToDatabase(studentDTO));
    }

    @Test
    void givenStudentWithWrongLastName_whenAddStudentToDatabase_thenRestErrorResponse() {
        //given
        StudentDTO studentDTO = new StudentDTO(1L, "12345678910", "first", "second", "");
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.addStudentToDatabase(studentDTO));
    }

    @Test
    void givenStudentWithWrongPesel_whenAddStudentToDatabase_thenRestErrorResponse() {
        //given
        StudentDTO studentDTO = new StudentDTO(1L, "1234567890", "first", "second", "last");
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.addStudentToDatabase(studentDTO));

    }

    @Test
    void givenId_whenDeleteStudentByIdFromDatabase_thenDeleteStudent() {
        //given
        Long id = 1L;
        //when
        systemUnderTest.deleteStudentByIdFromDatabase(id);
        //then
        verify(studentServiceMock).deleteStudentById(id);
    }

    @Test
    void whenProvideStudentsList_thenGetStudents() {
        //given
        //when
        systemUnderTest.provideStudentsList();
        //then
        verify(studentServiceMock).findAllStudents();
    }

    @Test
    void givenId_whenProvideStudentById_thenGetStudent() {
        //given
        Long id = 1L;
        //when
        systemUnderTest.provideStudentById(id);
        //then
        verify(studentServiceMock).findStudentById(id);
    }

    @Test
    void givenLastName_whenProvideStudentsByLastName_thenGetStudents() {
        //given
        String lastName = "test";
        //when
        systemUnderTest.provideStudentsByLastName(lastName);
        //then
        verify(studentServiceMock).findStudentsByLastName(lastName);
    }

    @Test
    void givenLastName_whenProvideStudentsByLastName_thenRestErrorHandler() {
        //given
        String lastName = "";
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.provideStudentsByLastName(lastName));
    }
    @Test
    void givenFirstName_whenProvideStudentsByFirstName_thenGetStudents() {
        //given
        String firstName = "test";
        //when
        systemUnderTest.provideStudentsByFirstName(firstName);
        //then
        verify(studentServiceMock).findStudentsByFirstName(firstName);
    }

    @Test
    void givenFirstName_whenProvideStudentsByFirstName_thenRestErrorHandler() {
        //given
        String firstName = "";
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.provideStudentsByFirstName(firstName));
    }
    @Test
    void givenPesel_whenProvideStudentByPESEL_thenGetStudent() {
        //given
        String pesel = "12345678910";
        //when
        systemUnderTest.provideStudentByPESEL(pesel);
        //then
        verify(studentServiceMock).findStudentByPESEL(pesel);
    }

    @Test
    void givenNotDigitsPesel_whenProvideStudentByPESEL_thenRestErrorHandler() {
        //given
        String pesel = "testtesttes";
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.provideStudentByPESEL(pesel));
    }
    @Test
    void givenTooShortPesel_whenProvideStudentByPESEL_thenRestErrorHandler() {
        //given
        String pesel = "123";
        //when
        //then
        Assertions.assertThrows(StudentController.RestValidationException.class, () -> systemUnderTest.provideStudentByPESEL(pesel));
    }
}