package org.lamberm.school.student;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    public static class RestValidationException extends RuntimeException {
        public RestValidationException(String message) {
            super(message);
        }
    }

    @Operation(summary = "Save student into database", description = "Student not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student saved"),
            @ApiResponse(responseCode = "400", description = "Invalid student data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity addStudentToDatabase(@Valid @RequestBody StudentDto studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Delete student by id", description = "Student exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Id not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentByIdFromDatabase(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get all students from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of students"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<StudentDto>> provideStudentsList() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @Operation(summary = "Get student by id", description = "Student exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student got"),
            @ApiResponse(responseCode = "404", description = "Id not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<StudentDto>> provideStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id.longValue()));
    }

    @Operation(summary = "Get student by last name", description = "Student exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student got"),
            @ApiResponse(responseCode = "400", description = "Invalid last name"),
            @ApiResponse(responseCode = "404", description = "Last name not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find/lastName/{lastName}")
    public ResponseEntity<List<StudentDto>> provideStudentsByLastName(@PathVariable("lastName") String lastName) {
        if (!isContainOnlyLetters(lastName)) {
            throw new RestValidationException("Last name must contain only letters");
        }
        return ResponseEntity.ok(studentService.findStudentsByLastName(lastName));
    }

    @Operation(summary = "Get student by first name", description = "Student exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student got"),
            @ApiResponse(responseCode = "400", description = "Invalid first name"),
            @ApiResponse(responseCode = "404", description = "First name not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find/firstName/{firstName}")
    public ResponseEntity<List<StudentDto>> provideStudentsByFirstName(@PathVariable("firstName") String firstName) {
        if (!isContainOnlyLetters(firstName)) {
            throw new RestValidationException("First name must contain only letters");
        }
        return ResponseEntity.ok(studentService.findStudentsByFirstName(firstName));
    }

    @Operation(summary = "Get student by PESEL", description = "Student exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student got"),
            @ApiResponse(responseCode = "400", description = "Invalid PESEL"),
            @ApiResponse(responseCode = "404", description = "PESEL not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find/PESEL/{PESEL}")
    public ResponseEntity<Optional<StudentDto>> provideStudentByPESEL(@PathVariable("PESEL") String pesel) {
        if (!isPeselDigitsCorrect(pesel)) {
            throw new RestValidationException("PESEL must be digits");
        }
        if (!isPeselLengthCorrect(pesel)) {
            throw new RestValidationException("PESEL length requires 11 characters");
        }
        return ResponseEntity.ok(studentService.findStudentByPESEL(pesel));
    }

    private boolean isPeselLengthCorrect(String pesel) {
        return pesel.length() == 11;
    }

    private boolean isPeselDigitsCorrect(String pesel) {
        return pesel.matches("^[0-9]+$");
    }

    private boolean isContainOnlyLetters(String text) {
        return text.matches("^[a-zA-Z]+$");
    }
}
