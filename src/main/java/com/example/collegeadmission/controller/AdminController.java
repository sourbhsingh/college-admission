package com.example.collegeadmission.controller;

import com.example.collegeadmission.model.Student;
import com.example.collegeadmission.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    CourseService courseService;
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard";
    }

    @GetMapping("/students/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getAllCourses());
        return "student_register";
    }

}
