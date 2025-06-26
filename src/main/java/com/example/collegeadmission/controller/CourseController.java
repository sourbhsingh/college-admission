package com.example.collegeadmission.controller;


import com.example.collegeadmission.model.Course;
import com.example.collegeadmission.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;
    //list
     @GetMapping({"", "/list"})
     public String getAllCourses(Model model) {
         List<Course> courses= courseService.getAllCourses();
            model.addAttribute("courses", courses);
            return "courses";
     }
    //show add course form
     @GetMapping("/add")
     public String addCourse(Model model ) {
            Course course = new Course();
            model.addAttribute("course", course);
         return "add_course";
     }

     //save course
        @PostMapping("/add")
        public String saveCourse(@ModelAttribute("course") Course course) {
            courseService.addCourse(course);
            return "redirect:/courses";
        }
//show updateform
        @GetMapping("/edit/{id}")
        public String editCourse(@PathVariable("id") Integer id, Model model) {
            Course course = courseService.getAllCourses().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (course != null) {
                model.addAttribute("course", course);
                return "add_course";
            }
            return "redirect:/courses/list";

     }
     //show update form and save course
     @PostMapping("/edit/{id}")
        public String updateCourse(@PathVariable("id") Integer id, @ModelAttribute("course") Course course) {
            Course existingCourse = courseService.getAllCourses().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (existingCourse != null) {
                existingCourse.setCourseName(course.getCourseName());
                existingCourse.setAvailableSeats(course.getAvailableSeats());
                courseService.addCourse(existingCourse);
            }
            return "redirect:/courses/list";
        }
//delete course
        @GetMapping("/delete/{id}")
        public String deleteCourse(@PathVariable("id") Integer id) {
            courseService.getAllCourses().removeIf(c -> c.getId().equals(id));
            return "redirect:/courses/list";
        }

}
