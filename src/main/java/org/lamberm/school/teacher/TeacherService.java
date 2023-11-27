package org.lamberm.school.teacher;


import lombok.RequiredArgsConstructor;
import org.lamberm.school.error.handler.IdNotExistException;
import org.lamberm.school.error.handler.PeselExistException;
import org.lamberm.school.error.handler.PeselNotExistException;
import org.lamberm.school.util.SchoolSubject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @Transactional
    public void addTeacher(TeacherDto teacherDto) {
        if (!teacherRepository.existsByPesel(teacherDto.getPesel())) {
            var teacher = teacherMapper.map(teacherDto);
            teacherRepository.save(teacher);
        } else {
            throw new PeselExistException();
        }
    }

    @Transactional
    public void deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new IdNotExistException();
        }
    }

    @Transactional
    public void editTeacher(TeacherDto teacherDto) {
        if (teacherRepository.existsByPesel(teacherDto.getPesel())) {
            var teacher = teacherRepository.findTeacherByPesel(teacherDto.getPesel());
            teacherMapper.updateTeacherFromDto(teacherDto, teacher);
            teacherRepository.save(teacher);
        } else {
            throw new PeselNotExistException();
        }
    }

    List<TeacherDto> findAllTeachers() {
        var teacherList = teacherRepository.findAll();
        return teacherList.stream().map(teacherMapper::map).toList();
    }

    List<TeacherDto> findTeachersBySchoolSubject(SchoolSubject schoolSubject) {
        var teacherList = teacherRepository.findTeacherBySchoolSubject(schoolSubject);
        return teacherList.stream().map(teacherMapper::map).toList();
    }
}
