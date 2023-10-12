package org.lamberm.school.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lamberm.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository systemUnderTest;

    @AfterEach
    void tearDown() {
        systemUnderTest.deleteAll();
    }

    @Test
    void givenStudents_whenFindStudentsByFirstName_thenGetStudents() {
        //given
        String firstName = "test";
        Student student1 = new Student(1L, "12345678910", firstName, "second", "last", "");
        Student student2 = new Student(2L, "12345678911", firstName, "", "last", "");
        systemUnderTest.save(student1);
        systemUnderTest.save(student2);
        //when
        boolean isEmpty = systemUnderTest.findStudentsByFirstName(firstName).isEmpty();
        //then
        Assertions.assertFalse(isEmpty);
    }
    @Test
    void givenFirstName_whenFindStudentsByFirstName_thenGetNothing() {
        //given
        String firstName = "test";
        //when
        boolean isEmpty = systemUnderTest.findStudentsByFirstName(firstName).isEmpty();
        //then
        Assertions.assertTrue(isEmpty);
    }

    @Test
    void givenStudent_whenIsFirstNameExist_thenGetTrue() {
        //given
        String firstName = "test";
        Student student1 = new Student(1L, "12345678910", firstName, "second", "last", "");
        systemUnderTest.save(student1);
        //when
        boolean actual = systemUnderTest.isFirstNameExist(firstName);
        //then
        Assertions.assertTrue(actual);
    }
    @Test
    void givenFirstName_whenIsFirstNameExist_thenGetFalse() {
        //given
        String firstName = "test";
        //when
        boolean actual = systemUnderTest.isFirstNameExist(firstName);
        //then
        Assertions.assertFalse(actual);
    }
    @Test
    void givenStudents_whenFindStudentsByLastName_thenGetStudents() {
        //given
        String lastName = "test";
        Student student1 = new Student(1L, "12345678910", "first", "second", lastName, "");
        Student student2 = new Student(2L, "12345678911", "second", "", lastName, "");
        systemUnderTest.save(student1);
        systemUnderTest.save(student2);
        //when
        boolean isEmpty = systemUnderTest.findStudentsByLastName(lastName).isEmpty();
        //then
        Assertions.assertFalse(isEmpty);
    }
    @Test
    void givenFirstName_whenFindStudentsByLastName_thenGetNothing() {
        //given
        String lastName = "test";
        //when
        boolean isEmpty = systemUnderTest.findStudentsByLastName(lastName).isEmpty();
        //then
        Assertions.assertTrue(isEmpty);
    }
    @Test
    void givenStudent_whenIsLastNameExist_thenGetTrue() {
        //given
        String lastName = "test";
        Student student1 = new Student(1L, "12345678910", "first", "second", lastName, "");
        systemUnderTest.save(student1);
        //when
        boolean actual = systemUnderTest.isLastNameExist(lastName);
        //then
        Assertions.assertTrue(actual);
    }
    @Test
    void givenFirstName_whenIsLastNameExist_thenGetFalse() {
        //given
        String lastName = "test";
        //when
        boolean actual = systemUnderTest.isLastNameExist(lastName);
        //then
        Assertions.assertFalse(actual);
    }
    @Test
    void givenStudent_whenFindStudentByPESEL_thenGetStudent() {
        //given
        String pesel = "12345678910";
        Student student1 = new Student(1L,pesel , "first", "second", "lastName", "");
        systemUnderTest.save(student1);
        //when
        boolean actual = systemUnderTest.findStudentByPESEL(pesel).isPresent();
        //then
        Assertions.assertTrue(actual);
    }
    @Test
    void _whenFindStudentByPESEL_thenGetNothing() {
        //given
        String pesel = "12345678910";
        //when
        boolean actual = systemUnderTest.findStudentByPESEL(pesel).isPresent();
        //then
        Assertions.assertFalse(actual);
    }
}