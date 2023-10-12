package org.lamberm.school.rest.controller;


import lombok.RequiredArgsConstructor;
import org.lamberm.school.rest.model.StudentDTO;
import org.lamberm.school.rest.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentRestController {
    private final StudentService studentService;
    private static final String FIRST_NAME_LENGTH = "First name length can't be less than 2 and more than 20";
    private static final String SECOND_NAME_LENGTH = "Second name length can't be more than 20";
    private static final String LAST_NAME_LENGTH = "Last name length can't be less than 3 and more than 100";
    private static final String PESEL_LENGTH = "PESEL length requires 11 characters";

    public static class RestValidationException extends RuntimeException {
        public RestValidationException(String message) {
            super(message);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addStudentToDatabase(@RequestBody StudentDTO studentDTO) {
        if (!isFirstNameValid(studentDTO.getFirstName())) {
            throw new RestValidationException(FIRST_NAME_LENGTH);
        }
        if (!isSecondNameValid(studentDTO.getSecondName())) {
            throw new RestValidationException(SECOND_NAME_LENGTH);
        }
        if (!isLastNameValid(studentDTO.getLastName())) {
            throw new RestValidationException(LAST_NAME_LENGTH);
        }
        studentService.addStudent(studentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentByIdFromDatabase(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Optional<List<StudentDTO>>> provideStudentsList() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<StudentDTO>> provideStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping("/find/lastName/{lastName}")
    public ResponseEntity<Optional<List<StudentDTO>>> provideStudentByLastName(@PathVariable("lastName") String lastName) {
        if (!isLastNameValid(lastName)) {
            throw new RestValidationException(LAST_NAME_LENGTH);
        }
        return ResponseEntity.ok(studentService.findStudentsByLastName(lastName));
    }

    @GetMapping("/find/firstName/{firstName}")
    public ResponseEntity<Optional<List<StudentDTO>>> provideStudentByFirstName(@PathVariable("firstName") String firstName) {
        if (!isFirstNameValid(firstName)) {
            throw new RestValidationException(FIRST_NAME_LENGTH);
        }
        return ResponseEntity.ok(studentService.findStudentsByFirstName(firstName));
    }

    @GetMapping("/find/PESEL/{PESEL}")
    public ResponseEntity<Optional<StudentDTO>> provideStudentByPESEL(@Validated @PathVariable("PESEL") String pesel) {
        if (!isPeselValid(pesel)){
            throw new RestValidationException(PESEL_LENGTH);
        }
        return ResponseEntity.ok(studentService.findStudentByPESEL(pesel));
    }
// przyda się później
//    @PutMapping("/updateNumbers")
//    public ResponseEntity updateNumbersInDatabase() {
//        studentService.updateNumberList();
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    private boolean isFirstNameValid(String firstName) {
        return firstName.length() >= 2 && firstName.length() <= 20;
    }

    private boolean isSecondNameValid(String secondName) {
        return secondName == null || secondName.length() <= 20;
    }

    private boolean isLastNameValid(String lastName) {
        return lastName.length() >= 3 && lastName.length() <= 100;
    }
    private boolean isPeselValid(String pesel){
        return pesel.length() == 11;
    }
}
