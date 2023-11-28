package org.lamberm.school.student;

import org.junit.jupiter.api.Assertions;
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
    void givenStudent_whenAddStudentToDatabase_thenAddStudent() {
        //given
        StudentDto studentDTO = new StudentDto("12345678910", "first", "second", "last");
        //when
        systemUnderTest.addStudentToDatabase(studentDTO);
        //then
        verify(studentServiceMock).addStudent(studentDTO);
    }

    @Test
    void givenId_whenDeleteStudentByIdFromDatabase_thenDeleteStudent() {
        //given
        Integer id = 1;
        //when
        systemUnderTest.deleteStudentByIdFromDatabase(id);
        //then
        verify(studentServiceMock).deleteStudentById(id.longValue());
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
        Integer id = 1;
        //when
        systemUnderTest.provideStudentById(id);
        //then
        verify(studentServiceMock).findStudentById(id.longValue());
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