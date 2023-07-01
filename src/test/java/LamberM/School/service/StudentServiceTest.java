package LamberM.School.service;

import LamberM.School.UnitTest;
import LamberM.School.model.Student;
import LamberM.School.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class StudentServiceTest implements UnitTest {
    @InjectMocks
    StudentService systemUnderTest;
    @Mock
    StudentRepository studentRepositoryMock;

    @Test
    void givenStudentMock_whenAddStudent_thenInsertToDatabase(){
        //given
        Student studentMock = mock(Student.class);
        //when
        systemUnderTest.addStudent(studentMock);
        //then
        verify(studentRepositoryMock).save(studentMock);
        Assertions.assertNotNull(studentRepositoryMock.findById(studentMock.getId()));
    }
    @Test
    void givenStudentMock_whenDeleteStudent_thenDeleteFromDatabase(){
        //given
        Student studentMock = mock(Student.class);
        //when
        systemUnderTest.deleteStudentById(studentMock.getId());
        //then
        verify(studentRepositoryMock).deleteById(studentMock.getId());
        boolean isEmptyList= systemUnderTest.findStudentById(studentMock.getId()).isEmpty();
        Assertions.assertTrue(isEmptyList);
    }
    @Test
    void givenStudentMock_whenFindStudentByLastName_thenGetWantedStudent(){
        //given
        Student studentMock = mock(Student.class);
        studentRepositoryMock.save(studentMock);
        //when
        systemUnderTest.findStudentByLastName(studentMock.getLastName());
        //then
        verify(studentRepositoryMock).save(studentMock);
        verify(studentRepositoryMock).findByLastName(studentMock.getLastName());
        Assertions.assertNotNull(systemUnderTest.findStudentByLastName(studentMock.getLastName()));
    }
    @Test
    void givenStudentMockButNotSaveInDb_whenFindStudentByLastName_thenGetNothing(){
        //given
        Student studentMock = mock(Student.class);
        //when
        systemUnderTest.findStudentByLastName(studentMock.getLastName());
        //then
        verify(studentRepositoryMock).findByLastName(studentMock.getLastName());
        boolean isEmptyList= systemUnderTest.findStudentByLastName(studentMock.getLastName()).isEmpty();
        Assertions.assertTrue(isEmptyList);
    }
    @Test
    void givenStudentMock_whenFindStudentByFirstName_thenGetWantedStudent(){
        //given
        Student studentMock = mock(Student.class);
        studentRepositoryMock.save(studentMock);
        //when
        systemUnderTest.findStudentByFirstName(studentMock.getFirstName());
        //then
        verify(studentRepositoryMock).save(studentMock);
        verify(studentRepositoryMock).findByFirstName(studentMock.getFirstName());
        Assertions.assertNotNull(systemUnderTest.findStudentByFirstName(studentMock.getFirstName()));
    }
    @Test
    void givenStudentMockButNotSaveInDb_whenFindStudentByFirstName_thenGetNothing(){
        //given
        Student studentMock = mock(Student.class);
        //when
        systemUnderTest.findStudentByFirstName(studentMock.getFirstName());
        //then
        verify(studentRepositoryMock).findByFirstName(studentMock.getFirstName());
        boolean isEmptyList= systemUnderTest.findStudentByFirstName(studentMock.getFirstName()).isEmpty();
        Assertions.assertTrue(isEmptyList);
    }
    @Test
    void givenStudentMock_whenFindStudentById_thenGetWantedStudent(){
        //given
        Student studentMock = mock(Student.class);
        studentRepositoryMock.save(studentMock);
        //when
        systemUnderTest.findStudentById(studentMock.getId());
        //then
        verify(studentRepositoryMock).save(studentMock);
        verify(studentRepositoryMock).findById(studentMock.getId());
        Assertions.assertNotNull(systemUnderTest.findStudentById(studentMock.getId()));
    }
    @Test
    void givenStudentMockButNotSaveInDb_whenFindStudentById_thenGetNothing(){
        //given
        Student studentMock = mock(Student.class);
        //when
        systemUnderTest.findStudentById(studentMock.getId());
        //then
        verify(studentRepositoryMock).findById(studentMock.getId());
        boolean isEmptyList= systemUnderTest.findStudentById(studentMock.getId()).isEmpty();
        Assertions.assertTrue(isEmptyList);
    }
    @Test
    void givenStudentMock_whenFindAllStudents_thenGetStudents(){
        //given
        Student studentMock = mock(Student.class);
        studentRepositoryMock.save(studentMock);
        //when
        systemUnderTest.findAllStudents();
        //then
        verify(studentRepositoryMock).save(studentMock);
        verify(studentRepositoryMock).findAll(Sort.by(Sort.Direction.ASC,"number"));
        Assertions.assertNotNull(systemUnderTest.findAllStudents());
    }
    @Test
    void givenEmptyList_whenFindAllStudents_thenGetEmptyList(){
        //given
        //when
        systemUnderTest.findAllStudents();
        //then
        verify(studentRepositoryMock).findAll(Sort.by(Sort.Direction.ASC,"number"));
        boolean isEmptyList= systemUnderTest.findAllStudents().isEmpty();
        Assertions.assertTrue(isEmptyList);
    }
    @Test
    void givenList_whenUpdateNumberList_thenGetUpdateList(){
        //given
        //when
        systemUnderTest.updateNumberList();
        //then
        verify(studentRepositoryMock).findAll(Sort.by(Sort.Direction.ASC,"lastName"));
        verify(studentRepositoryMock).saveAll(studentRepositoryMock.findAll(Sort.by(Sort.Direction.ASC,"lastName")));
    }
}