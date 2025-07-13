package com.arg.user.user.controllers;

import com.arg.user.user.entities.Course;
import com.arg.user.user.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
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

    @GetMapping(value = "list", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getProducts() {
        return Flux.just("Java", "C", "Cpp", "Oracle", "Python")
                .map(String::toUpperCase)
                .delayElements(Duration.ofMillis(500));
    }
}
