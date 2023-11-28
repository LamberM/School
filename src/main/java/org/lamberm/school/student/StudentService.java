package org.lamberm.school.student;

import lombok.RequiredArgsConstructor;
import org.lamberm.school.error.handler.IdNotExistException;
import org.lamberm.school.error.handler.PeselExistException;
import org.lamberm.school.error.handler.PeselNotExistException;
import org.lamberm.school.error.handler.StudentNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public void addStudent(StudentDto studentDto) {
        if (studentRepository.existsByPesel(studentDto.getPesel())) {
            throw new PeselExistException();
        } else {
            var student = studentMapper.map(studentDto);
            studentRepository.save(student);
        }
    }

    @Transactional
    public void deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findAllStudents() {
        var studentList = studentRepository.findAll();
        return studentList.stream().map(studentMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public StudentDto findStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            var student = studentRepository.findById(id).get();
            return studentMapper.map(student);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findStudentsByLastName(String lastName) {
        if (studentRepository.existsByLastName(lastName)) {
            var studentList = studentRepository.findStudentByLastName(lastName);
            return studentList.stream().map(studentMapper::map).toList();
        } else {
            throw new StudentNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findStudentsByFirstName(String firstName) {
        if (studentRepository.existsByFirstName(firstName)) {
            var studentList = studentRepository.findStudentByFirstName(firstName);
            return studentList.stream().map(studentMapper::map).toList();
        } else {
            throw new StudentNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public StudentDto findStudentByPESEL(String pesel) {
        if (studentRepository.existsByPesel(pesel)) {
            var student = studentRepository.findStudentByPesel(pesel);
            return studentMapper.map(student);
        } else {
            throw new PeselNotExistException();
        }
    }

}
