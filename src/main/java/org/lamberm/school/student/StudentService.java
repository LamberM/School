package org.lamberm.school.student;

import lombok.RequiredArgsConstructor;
import org.lamberm.school.error.handler.IdNotExistException;
import org.lamberm.school.error.handler.PeselExistException;
import org.lamberm.school.error.handler.PeselNotExistException;
import org.lamberm.school.error.handler.StudentNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public void addStudent(StudentDto studentDTO) {
        Student student = studentMapper.map(studentDTO);
        if (isPESELexist(student.getPesel())) {
            throw new PeselExistException();
        } else {
            studentRepository.save(student);
        }
    }

    @Transactional
    public void deleteStudentById(Long id) {
        if (isIdExist(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(studentMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public Optional<StudentDto> findStudentById(Long id) {
        if (isIdExist(id)) {
            Optional<Student> student = studentRepository.findById(id);
            return student.map(studentMapper::map);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findStudentsByLastName(String lastName) {
        if (studentRepository.existsByLastName(lastName)) {
            List<Student> studentList = studentRepository.findStudentByLastName(lastName);
            return studentList.stream().map(studentMapper::map).toList();
        } else {
            throw new StudentNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findStudentsByFirstName(String firstName) {
        if (studentRepository.existsByFirstName(firstName)) {
            List<Student> studentList = studentRepository.findStudentByFirstName(firstName);
            return studentList.stream().map(studentMapper::map).toList();
        } else {
            throw new StudentNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public Optional<StudentDto> findStudentByPESEL(String pesel) {
        if (isPESELexist(pesel)) {
            Optional<Student> student = studentRepository.findStudentByPesel(pesel);
            return student.map(studentMapper::map);
        } else {
            throw new PeselNotExistException();
        }
    }

    private boolean isIdExist(Long id) {
        return studentRepository.findById(id).isPresent();
    }

    private boolean isPESELexist(String pesel) {
        return studentRepository.findStudentByPesel(pesel).isPresent();
    }
}
