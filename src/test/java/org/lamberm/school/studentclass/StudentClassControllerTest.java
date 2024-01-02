package org.lamberm.school.studentclass;

import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class StudentClassControllerTest implements UnitTest {

    @InjectMocks
    StudentClassController systemUnderTest;

    @Mock
    StudentClassService studentClassServiceMock;

    @Mock
    StudentClassDto studentClassDtoMock;

    @Test
    void shouldSaveStudentClass() {
        systemUnderTest.saveStudentClass(studentClassDtoMock);

        Mockito.verify(studentClassServiceMock).addStudentClass(studentClassDtoMock);
    }

    @Test
    void shouldDeleteStudentClass() {
        Long id = 1L;

        systemUnderTest.deleteStudentClass(id);

        Mockito.verify(studentClassServiceMock).deleteStudentClass(id);
    }

    @Test
    void shouldEditStudentClass() {
        systemUnderTest.editStudentClass(studentClassDtoMock);

        Mockito.verify(studentClassServiceMock).editStudentClass(studentClassDtoMock);
    }

    @Test
    void shouldGetStudentClass() {
        systemUnderTest.getStudentClass();

        Mockito.verify(studentClassServiceMock).findAllStudentClass();
    }

    @Test
    void shouldGetStudentClassByYear() {
        String year = "1";

        systemUnderTest.getStudentClassByYear(year);

        Mockito.verify(studentClassServiceMock).findStudentClassByYear(year);
    }

    @Test
    void shouldGetStudentClassByName() {
        String name = "TM";

        systemUnderTest.getStudentClassByName(name);

        Mockito.verify(studentClassServiceMock).findStudentClassByName(name);
    }

    @Test
    void shouldGetStudentClassByMentorName() {
        String mentorName = "Last name";

        systemUnderTest.getStudentClassByMentorName(mentorName);

        Mockito.verify(studentClassServiceMock).findStudentClassByMentor(mentorName);
    }
}