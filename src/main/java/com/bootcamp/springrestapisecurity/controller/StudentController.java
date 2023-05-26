package com.bootcamp.springrestapisecurity.controller;

import com.bootcamp.springrestapisecurity.course.Course;
import com.bootcamp.springrestapisecurity.service.StudentService;
import com.bootcamp.springrestapisecurity.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
        private final StudentService studentService;

        @Autowired
        public StudentController(StudentService studentService){
            this.studentService = studentService;
        }
        @PostMapping("/addStudent")
        public Student addStudent(@RequestBody Student student){
            return  studentService.addStudent(student);
        }
        @GetMapping("getAllStudents")
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }

        @GetMapping("getStudentById/{id}")
        public Student getStudentById(@PathVariable Long id) {
            return studentService.getStudentById(id);
        }

        @DeleteMapping("/{id}")
        public void deleteStudentById(@PathVariable Long id) {
            studentService.deleteStudentById(id);
        }

        @PutMapping("/{id}")
        public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
            student.setId(id);
            return studentService.updateStudent(student);
        }

        @PostMapping("/{id}/enroll")
        public void enrollCourse(@PathVariable Long id, @RequestBody Course course) {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                studentService.enrollCourse(student, course);
            }
        }

        @PostMapping("/{id}/drop")
        public void dropCourse(@PathVariable Long id, @RequestBody Course course) {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                studentService.dropCourse(student, course);
            }
        }


}
