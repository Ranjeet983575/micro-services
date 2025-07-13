package com.arg.user.user.services.impl;

import com.arg.user.user.dto.StudentDto;
import com.arg.user.user.entities.Course;
import com.arg.user.user.entities.Student;
import com.arg.user.user.respositories.CourseRepository;
import com.arg.user.user.respositories.StudentRepository;
import com.arg.user.user.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;


    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = studentRepository.findByName(studentDto.getName());
        if (student == null) {
            student = new Student();
            student.setName(studentDto.getName());
        }
        Set<Course> courses = new HashSet<>();
        for (String course : studentDto.getCourses()) {
            Course c = new Course();
            c.setTitle(course);
            Optional<Course> byTitle = courseRepository.findByTitle(course);
            if (byTitle.isPresent()) {
                courses.add(byTitle.get());
            } else {
                Course save = courseRepository.save(c);
                courses.add(save);
            }
        }
        student.setCourses(courses);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return null;
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

//    @Override
//    public Flux<Student> findAllStudentStream() {
//        return Flux.fromIterable(studentRepository.findAll())
//                .concatMap(student ->
//                        Mono.fromCallable(() -> {
//                            processCustomer(student.getId());
//                            student.setName(student.getName().toUpperCase());
//                            return student;
//                        }).subscribeOn(Schedulers.boundedElastic())
//                );
//    }

    @Override
    public Flux<Student> findAllStudentStream() {
        return Flux.fromIterable(studentRepository.findAll()) // Step 1: Get all students (blocking)
                .concatMap(student ->                         // Step 2: Process each student in sequence
                        Mono.fromCallable(() -> {
                                    // Simulate a failure for a specific student (for example purposes)
                                    if (student.getId() == 3) {
                                        throw new RuntimeException("Failed to process student with ID: " + student.getId());
                                    }

                                    processCustomer(student.getId()); // Simulate long processing
                                    student.setName(student.getName().toUpperCase());
                                    return student;
                                })
                                .subscribeOn(Schedulers.boundedElastic()) // Offload to avoid blocking Netty
                                .onErrorResume(e -> {
                                    // Handle error: log and skip this student
                                    System.err.println("⚠️ Error processing student " + student.getId() + ": " + e.getMessage());
                                    return Mono.empty(); // Skip this student
                                })
                );
    }


    private static void processCustomer(Long id) {
        try {
            System.out.println("Processing customer " + id);
            Thread.sleep(500); // Simulated blocking delay
            System.out.println("Finished customer " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
