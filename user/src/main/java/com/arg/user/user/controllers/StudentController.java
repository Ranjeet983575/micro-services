package com.arg.user.user.controllers;

import com.arg.user.user.dto.StudentDto;
import com.arg.user.user.entities.Student;
import com.arg.user.user.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping()
    public Student createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }
}
