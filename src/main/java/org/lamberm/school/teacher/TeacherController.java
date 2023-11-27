package org.lamberm.school.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.lamberm.school.util.SchoolSubject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Validated
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/teachers")
    public ResponseEntity saveTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/teachers")
    public ResponseEntity editTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        teacherService.editTeacher(teacherDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> provideTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @GetMapping("/teachers/{schoolSubject}")
    public ResponseEntity<List<TeacherDto>> provideTeachersBySchoolSubject(@Valid @PathVariable("schoolSubject") SchoolSubject schoolSubject) {
        return ResponseEntity.ok(teacherService.findTeachersBySchoolSubject(schoolSubject));
    }
}
