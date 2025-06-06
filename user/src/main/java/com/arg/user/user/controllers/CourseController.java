package com.arg.user.user.controllers;

import com.arg.user.user.entities.Course;
import com.arg.user.user.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/course")
public class CourseController {


    @Autowired
    CourseService courseService;

    @GetMapping()
    public List<Course> getAllStudent() {
        return courseService.findAllCourse();
    }
}
