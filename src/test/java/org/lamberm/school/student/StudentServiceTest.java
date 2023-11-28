package org.lamberm.school.student;

import org.junit.jupiter.api.Assertions;
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
import static org.mockito.BDDMockito.given;
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

    @Test
    void givenStudentMock_whenAddStudent_thenInsertToDatabase() {
        //given
        StudentDto studentDtoMock = mock(StudentDto.class);
        Student studentMock = mock(Student.class);
        when(studentMapperMock.map(studentDtoMock)).thenReturn(studentMock);
        //when
        systemUnderTest.addStudent(studentDtoMock);
        //then
        verify(studentMapperMock).map(studentDtoMock);
        verify(studentRepositoryMock).save(studentMock);
        Assertions.assertNotNull(studentRepositoryMock.findById(studentMock.getId()));
    }

    @Test
    void givenStudents_whenAddStudent_thenWillThrowException() {
        //given
        Student student1 = new Student(1L, "12345678910", "first", "second", "last");
        studentRepositoryMock.save(student1);
        StudentDto studentDTO = new StudentDto("12345678910", "test", "test", "test");
        Student student = mock(Student.class);
        when(studentMapperMock.map(studentDTO)).thenReturn(student);
        when(studentRepositoryMock.findStudentByPesel(student.getPesel())).thenReturn(Optional.of(student1));
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.addStudent(studentDTO))
                .isInstanceOf(PeselExistException.class)
                .hasMessage("PESEL exist");
    }

    @Test
    void givenExistingIdWithStudentsList_whenDeleteStudent_thenStudentIsDeleted() {
        //given
        Long id = 1L;
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        given(studentRepositoryMock.findById(id)).willReturn(Optional.of(studentMock));
        //when
        systemUnderTest.deleteStudentById(id);
        //then
        verify(studentRepositoryMock).deleteById(id);
        boolean isEmptyList = systemUnderTest.findStudentById(id).isEmpty();
        Assertions.assertTrue(isEmptyList);
    }


    @Test
    void givenNotExistingId_whenDeleteStudent_thenWillThrowException() {
        //given
        Long id = 2L;
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.deleteStudentById(id))
                .isInstanceOf(IdNotExistException.class)
                .hasMessage("ID doesn't exist");
    }

    @Test
    void givenStudentsList_whenFindAllStudents_thenGetStudents() {
        //given
        Student studentMock = mock(Student.class);
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(studentMock);
        given(studentRepositoryMock.findAll()).willReturn(studentsList);
        //when
        systemUnderTest.findAllStudents();
        //then
        boolean isEmptyList = systemUnderTest.findAllStudents().isEmpty();
        Assertions.assertFalse(isEmptyList);
    }

    @Test
    void givenStudentMock_whenFindStudentById_thenGetStudent() {
        //given
        Student studentMock = mock(Student.class);
        StudentDto studentDtoMock = mock(StudentDto.class);
        studentRepositoryMock.save(studentMock);
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(studentMock);
        when(studentRepositoryMock.findById(studentMock.getId())).thenReturn(Optional.of(studentMock));
        when(studentMapperMock.map(studentMock)).thenReturn(studentDtoMock);
        //when
        boolean isPresent = systemUnderTest.findStudentById(studentMock.getId()).isPresent();
        //then
        Assertions.assertTrue(isPresent);
    }

    @Test
    void givenNotExistingId_whenFindById_thenWillThrowException() {
        //given
        Long id = 2L;
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentById(id))
                .isInstanceOf(IdNotExistException.class)
                .hasMessage("ID doesn't exist");
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByLastName_thenGetStudents() {
        //given
        Student student1 = new Student(1L, "12345678910", "first", "second", "last");
        Student student2 = new Student(2L, "12345678910", "first", "", "last");
        studentRepositoryMock.save(student1);
        studentRepositoryMock.save(student2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        given(studentRepositoryMock.existsByLastName(student1.getLastName())).willReturn(Boolean.TRUE);
        given(studentRepositoryMock.findStudentByLastName("last")).willReturn(studentList);
        //when
        boolean isEmpty = systemUnderTest.findStudentsByLastName(student1.getLastName()).isEmpty();
        //then
        Assertions.assertFalse(isEmpty);
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByLastName_thenWillThrowException() {
        //given
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentsByLastName(studentMock.getLastName()))
                .isInstanceOf(StudentNotExistException.class)
                .hasMessage("Student doesn't exist");
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByFirstName_thenGetStudents() {
        //given
        Student student1 = new Student(1L, "12345678910", "first", "second", "last");
        Student student2 = new Student(2L, "12345678910", "first", "", "last");
        studentRepositoryMock.save(student1);
        studentRepositoryMock.save(student2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        given(studentRepositoryMock.existsByFirstName(student1.getFirstName())).willReturn(Boolean.TRUE);
        given(studentRepositoryMock.findStudentByFirstName("first")).willReturn(studentList);
        //when
        boolean isEmpty = systemUnderTest.findStudentsByFirstName(student1.getFirstName()).isEmpty();
        //then
        Assertions.assertFalse(isEmpty);
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByFirstName_thenWillThrowException() {
        //given
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentsByFirstName(studentMock.getFirstName()))
                .isInstanceOf(StudentNotExistException.class)
                .hasMessage("Student doesn't exist");
    }

    @Test
    void givenStudentMock_whenFindStudentByPesel_thenGetStudent() {
        //given
        String pesel = "012345678910";
        Student studentMock = mock(Student.class);
        StudentDto studentDtoMock = mock(StudentDto.class);
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(studentMock);
        when(studentRepositoryMock.findStudentByPesel(pesel)).thenReturn(Optional.ofNullable(studentMock));
        when(studentMapperMock.map(studentMock)).thenReturn(studentDtoMock);
        //when
        boolean isPresent = systemUnderTest.findStudentByPESEL(pesel).isPresent();
        //then
        Assertions.assertTrue(isPresent);
    }

    @Test
    void givenNotExistingPesel_whenFindStudentByPesel_thenWillThrowException() {
        //given
        String pesel = "012345678910";
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentByPESEL(pesel))
                .isInstanceOf(PeselNotExistException.class)
                .hasMessage("PESEL doesn't exist");
    }
}