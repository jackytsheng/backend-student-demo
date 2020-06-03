package com.example.demo.Controller;

import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Dto.StudentPostDTO;
import com.example.demo.Dto.UserGetDTO;
import com.example.demo.Service.StudentService;
import com.example.demo.Util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

  @Autowired
  private jwtUtil jwtUti;

  @Autowired
  private StudentService studentService;

  @PostMapping("students")
  ResponseEntity<StudentGetDTO> add(@RequestBody StudentPostDTO studentDto){
    StudentGetDTO studentGetDto = studentService.addStudent(studentDto);
    return new ResponseEntity<>(studentGetDto , HttpStatus.OK);
  }

  @PostMapping("createStudent")
  ResponseEntity<UserGetDTO> createStudent(@RequestBody StudentPostDTO studentDto){
    UserGetDTO userGetDto = studentService.addStudentJWT(studentDto);
    return new ResponseEntity<>(userGetDto , HttpStatus.OK);
  }

  @PostMapping("createAdmin")
  ResponseEntity<UserGetDTO> createAdmin(@RequestBody StudentPostDTO studentDto){
    UserGetDTO userGetDto = studentService.addAdminJWT(studentDto);
    return new ResponseEntity<>(userGetDto , HttpStatus.OK);
  }

}
