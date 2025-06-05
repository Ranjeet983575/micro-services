package com.arg.user.user.respositories;

import com.arg.user.user.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);
}
