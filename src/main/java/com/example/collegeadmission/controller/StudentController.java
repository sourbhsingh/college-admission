package com.example.collegeadmission.controller;

import com.example.collegeadmission.helper.Status;
import com.example.collegeadmission.model.Student;
import com.example.collegeadmission.repository.StudentRepository;
import com.example.collegeadmission.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping({"/","/list"})
    public String listStudents(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "admission_status";
    }

    @PostMapping("/register")
    public String registerStudnet(Model model){
        Student student= new Student();
        model.addAttribute("student",student);
        return "student_register";
    }

    @PostMapping("/allot")
    public String allotSeat( Model model){
        List<Student> students =studentService.alotSeats();
        model.addAttribute("students", students);
        return "admission_status";
    }
    @GetMapping("/meritlist")
    public String showMeritList(Model model){
        List<Student> students =studentService.getMeritAdmittedStudents(Status.ACCEPTED);
        model.addAttribute("students",students);
        return "merit_list";
    }

}
