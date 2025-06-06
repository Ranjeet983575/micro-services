package com.arg.user.user.services.impl;

import com.arg.user.user.dto.StudentDto;
import com.arg.user.user.entities.Course;
import com.arg.user.user.entities.Student;
import com.arg.user.user.respositories.CourseRepository;
import com.arg.user.user.respositories.StudentRepository;
import com.arg.user.user.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
