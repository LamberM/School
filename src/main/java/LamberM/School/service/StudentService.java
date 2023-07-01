package LamberM.School.service;

import LamberM.School.model.Student;
import LamberM.School.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Transactional
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    @Transactional
    public void deleteStudentById(Integer id){
        studentRepository.deleteById(id);
    }
    public Optional<Student> findStudentByLastName(String lastName){
        return studentRepository.findByLastName(lastName);
    }
    public Optional<Student> findStudentByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }
    public Optional<Student> findStudentById(Integer id){
        return studentRepository.findById(id);
    }
    public List<Student> findAllStudents(){
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC,"number"));
    }

    public void updateNumberList(){
        List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.ASC,"lastName"));
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            student.setNumber(i + 1);
        }
        studentRepository.saveAll(students);
    }
}
