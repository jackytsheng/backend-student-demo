package com.example.demo.Controller;

import com.example.demo.Dto.StudentPostDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import com.example.demo.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

  @Autowired
  private jwtUtil jwtUti;

  @Autowired
  private StudentService studentService;

  @PostMapping("students")
  ResponseEntity<String> add(@RequestBody StudentPostDTO studentDto){
    studentService.addStudent(studentDto);
    return new ResponseEntity<>("Student added", HttpStatus.OK);
  }
//
//  @PostMapping("validate")
//  ResponseEntity<String> validateUser(@RequestHeader("Authorization") String token){
//    if(jwtUti.validateUser(token)){
//    return ResponseEntity.ok("User Validated");}
//    else{return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid JWT token");}
//  }
}
