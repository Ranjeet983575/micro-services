package com.arg.user.user.services;

import com.arg.user.user.dto.StudentDto;
import com.arg.user.user.entities.Student;

public interface StudentService {

    public Student createStudent(StudentDto studentDto);

    public Student findStudentById(Long id);

}
