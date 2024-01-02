package org.lamberm.school.studentclass;

import lombok.RequiredArgsConstructor;
import org.lamberm.school.error.handler.StudentClassExistException;
import org.lamberm.school.error.handler.StudentClassNotExistException;
import org.lamberm.school.error.handler.TeacherLastNameNotExistException;
import org.lamberm.school.teacher.TeacherMapper;
import org.lamberm.school.teacher.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClassService {

    private final StudentClassMapper studentClassMapper;

    private final StudentClassRepository studentClassRepository;

    private final TeacherMapper teacherMapper;

    private final TeacherRepository teacherRepository;

    @Transactional
    public void addStudentClass(StudentClassDto studentClassDto) {
        if (!studentClassRepository.existsByName(studentClassDto.getName()) && !studentClassRepository.existsByYear(studentClassDto.getYear()) && !studentClassRepository.existsByMentor(studentClassDto.getMentor())) {
            var studentClass = studentClassMapper.map(studentClassDto);
            studentClassRepository.save(studentClass);
        } else {
            throw new StudentClassExistException();
        }
    }

    @Transactional
    public void deleteStudentClass(Long id) {
        if (studentClassRepository.existsById(id)) {
            studentClassRepository.deleteById(id);
        } else {
            throw new StudentClassNotExistException();
        }
    }

    @Transactional
    public void editStudentClass(StudentClassDto studentClassDto) {
        if (!studentClassRepository.existsByName(studentClassDto.getName()) && !studentClassRepository.existsByYear(studentClassDto.getYear()) && !studentClassRepository.existsByMentor(studentClassDto.getMentor())) {
            var studentClass = studentClassRepository.findStudentClassByMentor(studentClassDto.getMentor());
            studentClassMapper.updateStudentClassFromDto(studentClassDto, studentClass);
            studentClassRepository.save(studentClass);
        } else {
            throw new StudentClassNotExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<StudentClassDto> findAllStudentClass() {
        var studentClassList = studentClassRepository.findAll();
        return studentClassList.stream().map(studentClassMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public List<StudentClassDto> findStudentClassByYear(String year) {
        var studentClassList = studentClassRepository.findStudentClassByYear(year);
        return studentClassList.stream().map(studentClassMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public List<StudentClassDto> findStudentClassByName(String name) {
        var studentClassList = studentClassRepository.findStudentClassByName(name);
        return studentClassList.stream().map(studentClassMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public StudentClassDto findStudentClassByMentor(String mentorName) {
        if (teacherRepository.existsByLastName(mentorName)) {
            var mentor = teacherRepository.findTeacherByLastName(mentorName);
            var studentClass = studentClassRepository.findStudentClassByMentor(teacherMapper.map(mentor));
            return studentClassMapper.map(studentClass);
        } else {
            throw new TeacherLastNameNotExistException();
        }
    }
}
