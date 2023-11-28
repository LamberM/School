package org.lamberm.school.teacher;

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

    @PostMapping
    public ResponseEntity saveTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id.longValue());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity editTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        teacherService.editTeacher(teacherDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> provideTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @GetMapping("/{schoolSubject}")
    public ResponseEntity<List<TeacherDto>> provideTeachersBySchoolSubject(@Valid @PathVariable("schoolSubject") SchoolSubject schoolSubject) {
        return ResponseEntity.ok(teacherService.findTeachersBySchoolSubject(schoolSubject));
    }
}
