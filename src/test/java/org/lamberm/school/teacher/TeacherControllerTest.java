package org.lamberm.school.teacher;

import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.lamberm.school.util.SchoolSubject;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class TeacherControllerTest implements UnitTest {

    @InjectMocks
    TeacherController systemUnderTest;

    @Mock
    TeacherService teacherService;

    @Mock
    TeacherDto teacherDtoMock;

    @Test
    void shouldSaveTeacher() {
        systemUnderTest.saveTeacher(teacherDtoMock);

        verify(teacherService).addTeacher(teacherDtoMock);
    }

    @Test
    void shouldDeleteTeacher() {
        int id = 1;

        systemUnderTest.deleteTeacher(id);

        verify(teacherService).deleteTeacher((long) id);
    }

    @Test
    void shouldEditTeacher() {
        systemUnderTest.editTeacher(teacherDtoMock);

        verify(teacherService).editTeacher(teacherDtoMock);
    }

    @Test
    void shouldProvideTeachers() {
        systemUnderTest.provideTeachers();

        verify(teacherService).findAllTeachers();
    }

    @Test
    void shouldProvideTeachersBySchoolSubject() {
        SchoolSubject schoolSubject = SchoolSubject.POLISH;

        systemUnderTest.provideTeachersBySchoolSubject(schoolSubject);

        verify(teacherService).findTeachersBySchoolSubject(schoolSubject);
    }

}