package org.lamberm.school.service;

import lombok.RequiredArgsConstructor;
import org.lamberm.school.dto.StudentDTO;
import org.lamberm.school.error.handler.IdNotExistException;
import org.lamberm.school.error.handler.PeselExistException;
import org.lamberm.school.error.handler.PeselNotExistException;
import org.lamberm.school.error.handler.StudentNotExistException;
import org.lamberm.school.mapper.StudentMapper;
import org.lamberm.school.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public void addStudent(StudentDTO studentDTO) {
        if (studentRepository.isPeselExist(studentDTO.getPesel())) {
            throw new PeselExistException();
        } else {
            var student = studentMapper.map(studentDTO);
            studentRepository.save(student);
        }
    }

    @Transactional
    public void deleteStudentById(Long id) {
        if (studentRepository.isIdExist(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findAllStudents() {
        var studentList = studentRepository.findAll();
        return studentList.stream().map(studentMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public StudentDTO findStudentById(Long id) {
        if (studentRepository.isIdExist(id)) {
            var student = studentRepository.findById(id).get();
            return studentMapper.map(student);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findStudentsByLastName(String lastName) {
        if (studentRepository.isLastNameExist(lastName)) {
            var studentList = studentRepository.getStudentsByLastName(lastName);
            return studentList.stream().map(studentMapper::map).toList();
        } else {
            throw new StudentNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findStudentsByFirstName(String firstName) {
        if (studentRepository.isFirstNameExist(firstName)) {
            var studentList = studentRepository.getStudentsByFirstName(firstName);
            return studentList.stream().map(studentMapper::map).toList();
        } else {
            throw new StudentNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public StudentDTO findStudentByPESEL(String pesel) {
        if (studentRepository.isPeselExist(pesel)) {
            var student = studentRepository.findStudentByPESEL(pesel);
            return studentMapper.map(student);
        } else {
            throw new PeselNotExistException();
        }
    }

}
