package com.arg.user.user.controllers;

import com.arg.user.user.entities.Course;
import com.arg.user.user.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/user/course")
public class CourseController {


    @Autowired
    CourseService courseService;

    @GetMapping()
    public List<Course> getAllStudent() {
        return courseService.findAllCourse();
    }

    @PostMapping()
    public Course saveCourse(@RequestBody Course course) {
        for (int i = 1; i <= 1000; i++) {
            Course c = new Course();
            c.setTitle("Java-" + i);
            c.setStudents(null);
            courseService.save(c);
        }
        return null;
    }
}
