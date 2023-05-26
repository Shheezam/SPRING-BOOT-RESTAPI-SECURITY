package com.bootcamp.springrestapisecurity.service;



import com.bootcamp.springrestapisecurity.course.Course;
import com.bootcamp.springrestapisecurity.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    public Course addCourse (Course course){
        return courseRepository.save(course);
    }
    public List<Course> getAllCourse(){
        return  courseRepository.findAll();
    }
    public Course getCourseByName(String name) {
        return courseRepository.findCourseByName(name);
    }
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

}
