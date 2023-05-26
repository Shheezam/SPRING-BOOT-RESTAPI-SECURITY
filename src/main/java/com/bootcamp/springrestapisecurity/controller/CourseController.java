package com.bootcamp.springrestapisecurity.controller;



import com.bootcamp.springrestapisecurity.course.Course;
import com.bootcamp.springrestapisecurity.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
        private final CourseService courseService;

        @Autowired
        public CourseController(CourseService courseService) {
            this.courseService = courseService;
        }

        @PostMapping("/addCourse")
        public Course addCourse(@RequestBody Course course) {
            return courseService.addCourse(course);
        }

        @GetMapping("getAllCourses")
        public List<Course> getAllCourses() {
            return courseService.getAllCourse();
        }

        @GetMapping("getCourseById/{id}")
        public Course getCourseById(@PathVariable Long id) {
            return courseService.getCourseById(id);
        }

        @DeleteMapping("/{id}")
        public void deleteCourseById(@PathVariable Long id) {
            courseService.deleteCourseById(id);
        }

        @PutMapping("/{id}")
        public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
            course.setId(id);
            return courseService.updateCourse(course);
        }
}
