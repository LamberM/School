package org.lamberm.school.studentclass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
@Validated
public class StudentClassController {

    private final StudentClassService studentClassService;

    @Operation(summary = "Save student class into database", description = "Student class not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student class saved"),
            @ApiResponse(responseCode = "400", description = "Invalid Student class data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity saveStudentClass(@Valid @RequestBody StudentClassDto studentClassDto) {
        studentClassService.addStudentClass(studentClassDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Delete student class by id", description = "Student class exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student class deleted"),
            @ApiResponse(responseCode = "404", description = "Id not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentClass(@PathVariable("id") Long id) {
        studentClassService.deleteStudentClass(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Edit student class", description = "Student class exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student class deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid student class data"),
            @ApiResponse(responseCode = "404", description = "Student class not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public ResponseEntity editStudentClass(@Valid @RequestBody StudentClassDto studentClassDto) {
        studentClassService.editStudentClass(studentClassDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get all student classes from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of student classes"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<StudentClassDto>> getStudentClass() {
        return ResponseEntity.ok(studentClassService.findAllStudentClass());
    }

    @Operation(summary = "Get student classes by year from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of student classes by year"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{year}")
    public ResponseEntity<List<StudentClassDto>> getStudentClassByYear(@Valid @PathVariable("year") @Pattern(regexp = "[0-9]", message = "Year must contain only numbers")
                                                                       @NotBlank(message = "Year must not be blank") String year) {
        return ResponseEntity.ok(studentClassService.findStudentClassByYear(year));
    }

    @Operation(summary = "Get student classes by name from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of student classes by name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{name}")
    public ResponseEntity<List<StudentClassDto>> getStudentClassByName(@Valid @PathVariable("name") @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
                                                                       @NotBlank(message = "Name must not be blank") String name) {
        return ResponseEntity.ok(studentClassService.findStudentClassByName(name));
    }

    @Operation(summary = "Get student classes by mentor name from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of student classes by mentor name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{mentorName}")
    public ResponseEntity<StudentClassDto> getStudentClassByMentorName(@Valid @PathVariable("mentorName") @Pattern(regexp = "^[a-zA-Z]+$", message = "Mentor name must contain only letters")
                                                                       @NotBlank(message = "Mentor name must not be blank") String mentorName) {
        return ResponseEntity.ok(studentClassService.findStudentClassByMentor(mentorName));
    }
}
