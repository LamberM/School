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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity saveStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id.longValue()));
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<StudentDTO>> getByLastName(@Valid @PathVariable("lastName") @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters") @Size(min = 3, max = 100, message = "Last name length must not be less than 3 and more than 100") @NotBlank(message = "Last name must not be blank") String lastName) {
        return ResponseEntity.ok(studentService.findStudentsByLastName(lastName));
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<List<StudentDTO>> getByFirstName(@Valid @PathVariable("firstName") @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters") @NotBlank(message = "First name must not be blank") @Size(min = 2, max = 20, message = "First name length must not be less than 2 and more than 20") String firstName) {
        return ResponseEntity.ok(studentService.findStudentsByFirstName(firstName));
    }

    @GetMapping("/{PESEL}")
    public ResponseEntity<StudentDTO> getByPESEL(@Valid @PathVariable("PESEL") @PESEL String pesel) {
        return ResponseEntity.ok(studentService.findStudentByPESEL(pesel));
    }
}
