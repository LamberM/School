package org.lamberm.school.rest.service;

import lombok.RequiredArgsConstructor;
import org.lamberm.school.model.Student;
import org.lamberm.school.repository.StudentRepository;
import org.lamberm.school.rest.error.handler.ServiceBadRequestToDbException;
import org.lamberm.school.rest.error.handler.ServiceNotFoundInDbException;
import org.lamberm.school.rest.mapper.StudentMapper;
import org.lamberm.school.rest.model.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private static final String LIST_IS_EMPTY = "List is empty";
    private static final String ID_NOT_EXIST = "ID doesn't exist";
    private static final String PESEL_EXIST = "PESEL exist";
    private static final String PESEL_NOT_EXIST = "PESEL doesn't exist";
    private static final String STUDENT_NOT_EXIST = "Student doesn't exist";

    @Transactional
    public void addStudent(StudentDTO studentDTO) {
        Student student = studentMapper.map(studentDTO);
        if (isPESELexist(student.getPesel())) {
            throw new ServiceBadRequestToDbException(PESEL_EXIST);
        } else {
            studentRepository.save(student);
        }
    }

    @Transactional
    public void deleteStudentById(Long id) {
        if (isListEmpty()) {
            throw new ServiceNotFoundInDbException(LIST_IS_EMPTY);
        } else {
            if (isIdExist(id)) {
                studentRepository.deleteById(id);
            } else {
                throw new ServiceNotFoundInDbException(ID_NOT_EXIST);
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<List<StudentDTO>> findAllStudents() {
        if (isListEmpty()) {
            throw new ServiceNotFoundInDbException(LIST_IS_EMPTY);
        } else {
            List<Student> studentList = studentRepository.findAll();
            return Optional.of(studentList.stream().map(this::convertToDTO).toList());
        }
    }

    @Transactional(readOnly = true)
    public Optional<StudentDTO> findStudentById(Long id) {
        if (isListEmpty()) {
            throw new ServiceNotFoundInDbException(LIST_IS_EMPTY);
        } else {
            if (isIdExist(id)) {
                Optional<Student> student = studentRepository.findById(id);
                return student.map(this::convertToDTO);
            } else {
                throw new ServiceNotFoundInDbException(ID_NOT_EXIST);
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<List<StudentDTO>> findStudentsByLastName(String lastName) {
        if (isListEmpty()) {
            throw new ServiceNotFoundInDbException(LIST_IS_EMPTY);
        } else {
            if (Boolean.TRUE.equals(studentRepository.isLastNameExist(lastName))) {
                List<Student> studentList = studentRepository.findStudentsByLastName(lastName);
                return Optional.of(studentList.stream().map(this::convertToDTO).toList());
            } else {
                throw new ServiceNotFoundInDbException(STUDENT_NOT_EXIST);
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<List<StudentDTO>> findStudentsByFirstName(String firstName) {
        if (isListEmpty()) {
            throw new ServiceNotFoundInDbException(LIST_IS_EMPTY);
        } else {
            if (Boolean.TRUE.equals(studentRepository.isFirstNameExist(firstName))) {
                List<Student> studentList = studentRepository.findStudentsByFirstName(firstName);
                return Optional.of(studentList.stream().map(this::convertToDTO).toList());
            } else {
                throw new ServiceNotFoundInDbException(STUDENT_NOT_EXIST);
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<StudentDTO> findStudentByPESEL(String pesel) {
        if (isListEmpty()) {
            throw new ServiceNotFoundInDbException(LIST_IS_EMPTY);
        } else {
            if (isPESELexist(pesel)) {
                Optional<Student> student = studentRepository.findStudentByPESEL(pesel);
                return student.map(this::convertToDTO);
            } else {
                throw new ServiceNotFoundInDbException(PESEL_NOT_EXIST);
            }
        }
    }


    private StudentDTO convertToDTO(Student student) {
        return studentMapper.map(student);
    }

    private boolean isListEmpty() {
        return studentRepository.findAll().isEmpty();
    }

    private boolean isIdExist(Long id) {
        return studentRepository.findById(id).isPresent();
    }

    private boolean isPESELexist(String pesel) {
        return studentRepository.findStudentByPESEL(pesel).isPresent();
    }
}
