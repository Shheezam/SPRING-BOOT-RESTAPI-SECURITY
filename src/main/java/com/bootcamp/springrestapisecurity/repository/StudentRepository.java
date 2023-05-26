package com.bootcamp.springrestapisecurity.repository;


import com.bootcamp.springrestapisecurity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
