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
        if (listIsEmpty()) return ResponseEntity.status(404).body(studentService.findAllStudents());
        else return ResponseEntity.ok(studentService.findAllStudents());
    }
    @GetMapping("/find/lastName/{lastName}")
    public ResponseEntity<Optional<Student>>provideStudentFromLastName(@PathVariable("lastName") String lastName){
        if (!lastNameExist(lastName)) return ResponseEntity.status(404).body(studentService.findStudentByLastName(lastName));
        else return ResponseEntity.ok(studentService.findStudentByLastName(lastName));
    }
    @GetMapping("/find/firstName/{firstName}")
    public ResponseEntity<Optional<Student>>provideStudentFromFirstName(@PathVariable("firstName") String firstName){
        if (!firstNameExist(firstName)) return ResponseEntity.status(404).body(studentService.findStudentByFirstName(firstName));
        else return ResponseEntity.ok(studentService.findStudentByFirstName(firstName));
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        if (studentExist(student.getFirstName(),student.getLastName())) return ResponseEntity.status(404).body("student exist");
        else {
            studentService.addStudent(student);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentById(@PathVariable("id") Integer id){
        if (!idExist(id)) return ResponseEntity.status(404).body("student does not exist");
        else {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
    @PutMapping("/updateNumbers")
    public ResponseEntity updateNumbers(){
        if (listIsEmpty()) return ResponseEntity.status(404).body("Student list is empty. Can't update numbers in list");
        else {
            studentService.updateNumberList();
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
    private boolean listIsEmpty(){
        return studentService.findAllStudents().isEmpty();
    }
    private boolean firstNameExist(String firstName){
       return !studentService.findStudentByFirstName(firstName).isEmpty();
    }
    private boolean lastNameExist(String lastName){
        return !studentService.findStudentByLastName(lastName).isEmpty();
    }
    private boolean studentExist(String firstName,String lastName){
        return firstNameExist(firstName) && lastNameExist(lastName);
    }
    private boolean idExist(Integer id){
        return !studentService.findStudentById(id).isEmpty();
    }
}
