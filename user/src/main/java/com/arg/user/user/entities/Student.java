package com.arg.user.user.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "student_course", // join table name
            joinColumns = @JoinColumn(name = "student_id"), // FK for Student
            inverseJoinColumns = @JoinColumn(name = "course_id") // FK for Course
    )
    @JsonManagedReference
    private Set<Course> courses = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
