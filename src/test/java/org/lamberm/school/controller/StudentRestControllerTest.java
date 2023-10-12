//package org.lamberm.school.controller;
//
//import org.lamberm.school.rest.controller.StudentRestController;
//import org.lamberm.school.UnitTest;
//import org.lamberm.school.model.Student;
//import org.lamberm.school.repository.StudentRepository;
//import org.lamberm.school.rest.service.StudentService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.http.HttpStatus.*;
//
//class StudentRestControllerTest implements UnitTest {
//    @InjectMocks
//    StudentRestController systemUnderTest;
//    @Mock
//    StudentService studentServiceMock;
//
//    @Test
//    void givenEmptyStudentList_whenProvideStudentsList_thenReturnNotFound() {
//        //given
//
//        //when
//        ResponseEntity<Optional<List<Student>>> responseEntity = systemUnderTest.provideStudentsList();
//        //then
//        verify(studentServiceMock, times(2)).findAllStudents();
//        boolean isEmpty = studentServiceMock.findAllStudents().isEmpty();
//        Assertions.assertTrue(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findAllStudents(), responseEntity.getBody());
//        Assertions.assertEquals(NOT_FOUND, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenStudentList_whenProvideStudentsList_thenReturnOkWithStudentList() {
//        //given
//        Student studentMock = mock(Student.class);
//        StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//        studentRepositoryMock.save(studentMock);
//        //when
//        ResponseEntity<Optional<List<Student>>> responseEntity = systemUnderTest.provideStudentsList();
//        //then
//        verify(studentServiceMock, times(2)).findAllStudents();
//        boolean isEmpty = studentServiceMock.findAllStudents().isEmpty();
//        Assertions.assertFalse(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findAllStudents(), responseEntity.getBody());
//        Assertions.assertEquals(OK, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenNotExistStudent_whenProvideStudentsByLastName_thenReturnNotFound() {
//        //given
//        Student studentMock = mock(Student.class);
//        //when
//        ResponseEntity<Optional<Student>> responseEntity = systemUnderTest.provideStudentByLastName(studentMock.getLastName());
//        //then
//        verify(studentServiceMock, times(2)).findStudentByLastName(studentMock.getLastName());
//        boolean isEmpty = studentServiceMock.findStudentByLastName(studentMock.getLastName()).isEmpty();
//        Assertions.assertTrue(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findStudentByLastName(studentMock.getLastName()), responseEntity.getBody());
//        Assertions.assertEquals(NOT_FOUND, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenExistStudent_whenProvideStudentsByLastName_thenReturnOkWithStudent() {
//        //given
//        Student studentMock = mock(Student.class);
//        StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//        studentRepositoryMock.save(studentMock);
//        when(studentServiceMock.findStudentByLastName(studentMock.getLastName())).thenReturn(Optional.of(studentMock));
//        //when
//        ResponseEntity<Optional<Student>> responseEntity = systemUnderTest.provideStudentByLastName(studentMock.getLastName());
//        //then
//        verify(studentServiceMock, times(2)).findStudentByLastName(studentMock.getLastName());
//        boolean isEmpty = studentServiceMock.findStudentByLastName(studentMock.getLastName()).isEmpty();
//        Assertions.assertFalse(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findStudentByLastName(studentMock.getLastName()), responseEntity.getBody());
//        Assertions.assertEquals(OK, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenNotExistStudent_whenProvideStudentsByFirstName_thenReturnNotFound() {
//        //given
//        Student studentMock = mock(Student.class);
//        //when
//        ResponseEntity<Optional<Student>> responseEntity = systemUnderTest.provideStudentByLastName(studentMock.getFirstName());
//        //then
//        verify(studentServiceMock, times(2)).findStudentByFirstName(studentMock.getFirstName());
//        boolean isEmpty = studentServiceMock.findStudentByFirstName(studentMock.getFirstName()).isEmpty();
//        Assertions.assertTrue(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findStudentByFirstName(studentMock.getFirstName()), responseEntity.getBody());
//        Assertions.assertEquals(NOT_FOUND, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenExistStudent_whenProvideStudentsByFirstName_thenReturnOkWithStudent() {
//        //given
//        Student studentMock = mock(Student.class);
//        StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//        studentRepositoryMock.save(studentMock);
//        when(studentServiceMock.findStudentByFirstName(studentMock.getFirstName())).thenReturn(Optional.of(studentMock));
//        //when
//        ResponseEntity<Optional<Student>> responseEntity = systemUnderTest.provideStudentByFirstName(studentMock.getFirstName());
//        //then
//        verify(studentServiceMock, times(2)).findStudentByFirstName(studentMock.getFirstName());
//        boolean isEmpty = studentServiceMock.findStudentByFirstName(studentMock.getFirstName()).isEmpty();
//        Assertions.assertFalse(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findStudentByFirstName(studentMock.getFirstName()), responseEntity.getBody());
//        Assertions.assertEquals(OK, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenNotExistStudent_whenProvideStudentsById_thenReturnNotFound() {
//        //given
//        Student studentMock = mock(Student.class);
//        //when
//        ResponseEntity<Optional<Student>> responseEntity = systemUnderTest.provideStudentById(studentMock.getId());
//        //then
//        verify(studentServiceMock, times(2)).findStudentById(studentMock.getId());
//        boolean isEmpty = studentServiceMock.findStudentById(studentMock.getId()).isEmpty();
//        Assertions.assertTrue(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findStudentById(studentMock.getId()), responseEntity.getBody());
//        Assertions.assertEquals(NOT_FOUND, responseEntity.getStatusCode());
//    }
//
//    @Test
//    void givenExistStudent_whenProvideStudentsById_thenReturnOkWithStudent() {
//        //given
//        Student studentMock = mock(Student.class);
//        StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//        studentRepositoryMock.save(studentMock);
//        when(studentServiceMock.findStudentById(studentMock.getId())).thenReturn(Optional.of(studentMock));
//        //when
//        ResponseEntity<Optional<Student>> responseEntity = systemUnderTest.provideStudentById(studentMock.getId());
//        //then
//        verify(studentServiceMock, times(2)).findStudentById(studentMock.getId());
//        boolean isEmpty = studentServiceMock.findStudentById(studentMock.getId()).isEmpty();
//        Assertions.assertFalse(isEmpty);
//        Assertions.assertEquals(studentServiceMock.findStudentById(studentMock.getId()), responseEntity.getBody());
//        Assertions.assertEquals(OK, responseEntity.getStatusCode());
//    }
//
//    //repair
//    @Test
//    void givenExistingStudent_whenAddStudentToDatabase_thenReturnBadRequest() {
//        //given
//        Student studentMock = mock(Student.class);
//        StudentRepository studentRepositoryMock = mock(StudentRepository.class);
//        doAnswer(invocationOnMock -> {
//            Student student1 = invocationOnMock.getArgument(0);
//            studentRepositoryMock.save(student1);
//            return null;
//        }).when(studentServiceMock).addStudent(studentMock);
//        //when
//        systemUnderTest.addStudentToDatabase(studentMock);
//        //then
//        verify(studentServiceMock).addStudent(studentMock);
//        boolean isEmpty = studentServiceMock.findStudentByFirstName(studentMock.getFirstName()).isEmpty() && studentServiceMock.findStudentByFirstName(studentMock.getLastName()).isEmpty();
//        Assertions.assertFalse(isEmpty);
//        Assertions.assertNotNull(studentServiceMock.findStudentByFirstName(studentMock.getFirstName()));
//        Assertions.assertNotNull(studentServiceMock.findStudentByLastName(studentMock.getLastName()));
//        Assertions.assertEquals(BAD_REQUEST, systemUnderTest.addStudentToDatabase(studentMock).getStatusCode());
//    }
//
//    @Test
//    void givenStudent_whenAddStudentToDatabase_thenReturnOkWithAddedStudent() {
//        //given
//        Student studentMock = mock(Student.class);
//        //when
//        systemUnderTest.addStudentToDatabase(studentMock);
//        //then
//        verify(studentServiceMock).addStudent(studentMock);
//        boolean isEmpty = studentServiceMock.findStudentByFirstName(studentMock.getFirstName()).isEmpty() && studentServiceMock.findStudentByFirstName(studentMock.getLastName()).isEmpty();
//        Assertions.assertTrue(isEmpty);
//        Assertions.assertNotNull(studentServiceMock.findStudentByFirstName(studentMock.getFirstName()));
//        Assertions.assertNotNull(studentServiceMock.findStudentByLastName(studentMock.getLastName()));
//        Assertions.assertEquals(OK, systemUnderTest.addStudentToDatabase(studentMock).getStatusCode());
//    }
//}