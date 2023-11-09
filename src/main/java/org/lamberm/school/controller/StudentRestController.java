package org.lamberm.school.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.lamberm.school.dto.StudentDTO;
import org.lamberm.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentRestController {
    private final StudentService studentService;

    public static class RestValidationException extends RuntimeException {
        public RestValidationException(String message) {
            super(message);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addStudentToDatabase(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentByIdFromDatabase(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<StudentDTO>> provideStudentsList() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<StudentDTO>> provideStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id.longValue()));
    }

    @GetMapping("/find/lastName/{lastName}")
    public ResponseEntity<List<StudentDTO>> provideStudentsByLastName(@PathVariable("lastName") String lastName) {
        if(!isContainOnlyLetters(lastName)){
            throw new RestValidationException("Last name must contain only letters");
        }
        return ResponseEntity.ok(studentService.findStudentsByLastName(lastName));
    }

    @GetMapping("/find/firstName/{firstName}")
    public ResponseEntity<List<StudentDTO>> provideStudentsByFirstName(@PathVariable("firstName") String firstName) {
        if(!isContainOnlyLetters(firstName)){
            throw new RestValidationException("First name must contain only letters");
        }
        return ResponseEntity.ok(studentService.findStudentsByFirstName(firstName));
    }

    @GetMapping("/find/PESEL/{PESEL}")
    public ResponseEntity<Optional<StudentDTO>> provideStudentByPESEL(@PathVariable("PESEL") String pesel) {
        if(!isPeselDigitsCorrect(pesel)){
            throw new RestValidationException("PESEL must be digits");
        }
        if(!isPeselLengthCorrect(pesel)){
            throw new RestValidationException("PESEL length requires 11 characters");
        }
        return ResponseEntity.ok(studentService.findStudentByPESEL(pesel));
    }
    private boolean isPeselLengthCorrect(String pesel){
        return pesel.length() == 11;
    }
    private boolean isPeselDigitsCorrect(String pesel){
        return pesel.matches("^[0-9]+$");
    }
    private boolean isContainOnlyLetters(String text){
        return text.matches("^[a-zA-Z]+$");
    }
}
