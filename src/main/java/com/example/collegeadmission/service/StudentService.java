package com.example.collegeadmission.service;

import com.example.collegeadmission.helper.Status;
import com.example.collegeadmission.model.Course;
import com.example.collegeadmission.model.Student;
import com.example.collegeadmission.repository.CourseRepository;
import com.example.collegeadmission.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    public void saveStudent(Student std){
        studentRepository.save(std);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
      public List<Student> alotSeats(){
        List<Course> courses = courseRepository.findAll();

        for(Course course : courses){
           int avlSeats =course.getAvailableSeats();
           int i = 0 ;
           List<Student> stdList= course.getStudents();
           stdList.sort(((o1, o2) -> Double.compare(o2.getTotalMarks(),o1.getTotalMarks())));
           while (avlSeats>0)
           {
               stdList.get(i).setStatus(Status.ACCEPTED);
               studentRepository.save(stdList.get(i));
               i++;
               avlSeats--;
               course.setAvailableSeats(avlSeats);
               courseRepository.save(course);

           }
            while (i < stdList.size()) {
                stdList.get(i).setStatus(Status.REJECTED);
                studentRepository.save(stdList.get(i));
                i++;
            }
        }
        return new ArrayList<Student>() ;
      }
//    public List<Student> alotSeats(Student std){
//        Course course = courseRepository.findById(courseID).orElse(null);
//
//        List<Student> studentList= studentRepository.findAllByCourse_id(courseID);
//        studentList.sort((o1, o2) -> Double.compare(o2.getTotalMarks(),o1.getTotalMarks()));
//        if(course!=null) {
//            int seatsAvailable = course.getAvailableSeats();
//            int i = 0 ;
//            while (seatsAvailable>0) {
//                studentList.get(i).setStatus(Status.ACCEPTED);
//                studentRepository.save(studentList.get(i));
//                seatsAvailable--;
//                course.setAvailableSeats(seatsAvailable);
//                courseRepository.save(course);
//                i++;
//            }
//            while (i < studentList.size()) {
//                studentList.get(i).setStatus(Status.REJECTED);
//                studentRepository.save(studentList.get(i));
//                i++;
//            }
//        }
//         return studentList;
//    }


    public List<Student> getMeritAdmittedStudents(Status status){
        return studentRepository.findAllByStatus(status);
    }
}
