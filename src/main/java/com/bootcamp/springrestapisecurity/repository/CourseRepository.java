package com.bootcamp.springrestapisecurity.repository;


import com.bootcamp.springrestapisecurity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.name = :name")
    Course findCourseByName(@Param("name") String name);
}
