package org.lamberm.school.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;
import org.lamberm.school.dto.StudentDTO;
import org.lamberm.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity saveStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudentById(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> provideStudentsList() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<StudentDTO>> provideStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id.longValue()));
    }

    @GetMapping("/students/{lastName}")
    public ResponseEntity<List<StudentDTO>> provideStudentsByLastName(@Valid @PathVariable("lastName") @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters") @Size(min = 3, max = 100, message = "Last name length must not be less than 3 and more than 100") @NotBlank(message = "Last name must not be blank") String lastName) {
        return ResponseEntity.ok(studentService.findStudentsByLastName(lastName));
    }

    @GetMapping("/students/{firstName}")
    public ResponseEntity<List<StudentDTO>> provideStudentsByFirstName(@Valid @PathVariable("firstName") @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters") @NotBlank(message = "First name must not be blank") @Size(min = 2, max = 20, message = "First name length must not be less than 2 and more than 20") String firstName) {
        return ResponseEntity.ok(studentService.findStudentsByFirstName(firstName));
    }

    @GetMapping("/students/{PESEL}")
    public ResponseEntity<Optional<StudentDTO>> provideStudentByPESEL(@Valid @PathVariable("PESEL") @PESEL String pesel) {
        return ResponseEntity.ok(studentService.findStudentByPESEL(pesel));
    }
}
