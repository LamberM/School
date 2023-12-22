package org.lamberm.school.teacher;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.lamberm.school.util.SchoolSubject;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Validated
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Save teacher into database", description = "Teacher not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher saved"),
            @ApiResponse(responseCode = "400", description = "Invalid teacher data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity saveTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Delete teacher by id", description = "Teacher exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher deleted"),
            @ApiResponse(responseCode = "404", description = "Id not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Edit teacher by id", description = "Teacher exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid teacher data"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public ResponseEntity editTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        teacherService.editTeacher(teacherDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get all teachers from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of teachers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<TeacherDto>> provideTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @Operation(summary = "Get teachers by school subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of teachers by subject"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{schoolSubject}")
    public ResponseEntity<List<TeacherDto>> provideTeachersBySchoolSubject(@Valid @PathVariable("schoolSubject") SchoolSubject schoolSubject) {
        return ResponseEntity.ok(teacherService.findTeachersBySchoolSubject(schoolSubject));
    }
}
