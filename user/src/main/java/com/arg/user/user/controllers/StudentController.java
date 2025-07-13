package com.arg.user.user.controllers;

import com.arg.user.user.dto.StudentDto;
import com.arg.user.user.entities.Student;
import com.arg.user.user.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.util.List;

@RestController
@RequestMapping("/user/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping()
    @PreAuthorize("hasRole('TESTER')")
    public Student createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping()
    public List<Student> getAllStudent() {
        return studentService.findAllStudent();
    }

    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getProducts() {
        return studentService.findAllStudentStream();
    }


}
