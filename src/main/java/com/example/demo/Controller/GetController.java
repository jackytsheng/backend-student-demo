package com.example.demo.Controller;


import com.example.demo.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class GetController {

  @GetMapping("students")
  ResponseEntity<ArrayList<Student>> all(){
    Student Student1 = new Student("Jacky",1,"Student");
    Student Student2 = new Student("Jacky2",2,"Student");
    ArrayList<Student> StudentList = new ArrayList<>();
    StudentList.add(Student1);
    StudentList.add(Student2);

    return new ResponseEntity<>(StudentList, HttpStatus.OK);
  }

}
