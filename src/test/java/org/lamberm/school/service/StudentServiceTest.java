package org.lamberm.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lamberm.school.UnitTest;
import org.lamberm.school.model.Student;
import org.lamberm.school.repository.StudentRepository;
import org.lamberm.school.rest.error.handler.ServiceBadRequestToDbException;
import org.lamberm.school.rest.error.handler.ServiceNotFoundInDbException;
import org.lamberm.school.rest.mapper.StudentMapper;
import org.lamberm.school.rest.model.StudentDTO;
import org.lamberm.school.rest.service.StudentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class StudentServiceTest implements UnitTest {
    @InjectMocks
    StudentService systemUnderTest;
    @Mock
    StudentRepository studentRepositoryMock;
    @Mock
    StudentMapper studentMapperMock;

    @Test
    void validationTest() {
        //given
        Long id = 1L;
        String pesel = "12345678910";
        String name = "test";
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findAllStudents())
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("List is empty");
        assertThatThrownBy(() -> systemUnderTest.findStudentById(id))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("List is empty");
        assertThatThrownBy(() -> systemUnderTest.findStudentByPESEL(pesel))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("List is empty");
        assertThatThrownBy(() -> systemUnderTest.findStudentsByFirstName(name))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("List is empty");
        assertThatThrownBy(() -> systemUnderTest.findStudentsByLastName(name))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("List is empty");
    }

    @Test
    void givenStudentMock_whenAddStudent_thenInsertToDatabase() {
        //given
        StudentDTO studentDTOMock = mock(StudentDTO.class);
        Student studentMock = mock(Student.class);
        when(studentMapperMock.map(studentDTOMock)).thenReturn(studentMock);
        //when
        systemUnderTest.addStudent(studentDTOMock);
        //then
        verify(studentMapperMock).map(studentDTOMock);
        verify(studentRepositoryMock).save(studentMock);
        Assertions.assertNotNull(studentRepositoryMock.findById(studentMock.getId()));
    }

    @Test
    void givenStudents_whenAddStudent_thenWillThrowException() {
        //given
        Student student1 = new Student(1L, "12345678910", "first", "second", "last", "");
        studentRepositoryMock.save(student1);
        StudentDTO studentDTO = new StudentDTO(2L, "12345678910", "test", "test", "test");
        Student student = mock(Student.class);
        when(studentMapperMock.map(studentDTO)).thenReturn(student);
        when(studentRepositoryMock.findStudentByPESEL(student.getPesel())).thenReturn(Optional.of(student1));
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.addStudent(studentDTO))
                .isInstanceOf(ServiceBadRequestToDbException.class)
                .hasMessage("PESEL exist");
    }

    @Test
    void givenExistingIdWithStudentsList_whenDeleteStudent_thenStudentIsDeleted() {
        //given
        Long id = 1L;
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        given(studentRepositoryMock.findAll()).willReturn(studentList);
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
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.deleteStudentById(id))
                .isInstanceOf(ServiceNotFoundInDbException.class)
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
        StudentDTO studentDTOMock = mock(StudentDTO.class);
        studentRepositoryMock.save(studentMock);
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(studentMock);
        given(studentRepositoryMock.findAll()).willReturn(studentsList);
        when(studentRepositoryMock.findById(studentMock.getId())).thenReturn(Optional.of(studentMock));
        when(studentMapperMock.map(studentMock)).thenReturn(studentDTOMock);
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
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentById(id))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("ID doesn't exist");
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByLastName_thenGetStudents() {
        //given
        Student student1 = new Student(1L, "12345678910", "first", "second", "last", "");
        Student student2 = new Student(2L, "12345678910", "first", "", "last", "");
        studentRepositoryMock.save(student1);
        studentRepositoryMock.save(student2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        given(studentRepositoryMock.isLastNameExist(student1.getLastName())).willReturn(Boolean.TRUE);
        //when
        boolean isPresent = systemUnderTest.findStudentsByLastName(student1.getLastName()).isPresent();
        //then
        Assertions.assertTrue(isPresent);
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByLastName_thenWillThrowException() {
        //given
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentsByLastName(studentMock.getLastName()))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("Student doesn't exist");
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByFirstName_thenGetStudents() {
        //given
        Student student1 = new Student(1L, "12345678910", "first", "second", "last", "");
        Student student2 = new Student(2L, "12345678910", "first", "", "last", "");
        studentRepositoryMock.save(student1);
        studentRepositoryMock.save(student2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        given(studentRepositoryMock.isFirstNameExist(student1.getFirstName())).willReturn(Boolean.TRUE);
        //when
        boolean isPresent = systemUnderTest.findStudentsByFirstName(student1.getFirstName()).isPresent();
        //then
        Assertions.assertTrue(isPresent);
    }

    @Test
    void givenStudentsListWithStudents_whenFindStudentsByFirstName_thenWillThrowException() {
        //given
        Student studentMock = mock(Student.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentMock);
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentsByFirstName(studentMock.getFirstName()))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("Student doesn't exist");
    }

    @Test
    void givenStudentMock_whenFindStudentByPesel_thenGetStudent() {
        //given
        String pesel = "012345678910";
        Student studentMock = mock(Student.class);
        StudentDTO studentDTOMock = mock(StudentDTO.class);
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(studentMock);
        given(studentRepositoryMock.findAll()).willReturn(studentsList);
        when(studentRepositoryMock.findStudentByPESEL(pesel)).thenReturn(Optional.ofNullable(studentMock));
        when(studentMapperMock.map(studentMock)).thenReturn(studentDTOMock);
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
        given(studentRepositoryMock.findAll()).willReturn(studentList);
        //when
        //then
        assertThatThrownBy(() -> systemUnderTest.findStudentByPESEL(pesel))
                .isInstanceOf(ServiceNotFoundInDbException.class)
                .hasMessage("PESEL doesn't exist");
    }
}