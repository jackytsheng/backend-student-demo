package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.dto.UserGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AdminController {

  @Autowired
  private UserService userService;

  @GetMapping("adminAccess")
  ResponseEntity<List<StudentGetDto>> adminGetAll(){
    System.out.println("In Admin Access");
    List<StudentGetDto> studentGetDtoList = userService.getAll();
    return new ResponseEntity<>(studentGetDtoList , HttpStatus.OK);
  }

  @PostMapping("createStudent")
  ResponseEntity<UserGetDto> createStudent(@RequestBody StudentPostDto studentDto){
    UserGetDto userGetDto = userService.addStudentJWT(studentDto);
    return new ResponseEntity<>(userGetDto , HttpStatus.OK);
  }

  @PostMapping("createAdmin")
  ResponseEntity<UserGetDto> createAdmin(@RequestBody StudentPostDto studentDto){
    UserGetDto userGetDto = userService.addAdminJWT(studentDto);
    return new ResponseEntity<>(userGetDto , HttpStatus.OK);
  }

}

