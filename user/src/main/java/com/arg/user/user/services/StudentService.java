package com.arg.user.user.services;

import com.arg.user.user.dto.StudentDto;
import com.arg.user.user.entities.Student;
import reactor.core.publisher.Flux;

import java.util.List;

public interface StudentService {

    public Student createStudent(StudentDto studentDto);

    public Student findStudentById(Long id);

    public List<Student> findAllStudent();


}
