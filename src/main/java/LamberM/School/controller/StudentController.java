package LamberM.School.controller;


import LamberM.School.model.Student;
import LamberM.School.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Student>> provideStudentsList(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }
    @GetMapping("/find/{lastName}")
    public ResponseEntity<Optional<Student>>provideStudent(@PathVariable("lastName") String lastName){
        return ResponseEntity.ok(studentService.findStudentByLastName(lastName));
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestBody Student student){
        studentService.deleteStudent(student);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("/updateNumbers")
    public ResponseEntity updateNumbers(){
        studentService.updateNumberList();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
