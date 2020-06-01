package com.example.demo.Controller;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class GetController {

  @Autowired
  private StudentService studentService;

  @GetMapping("students")
  public ResponseEntity<List<StudentGetDTO>> all(){
    List<StudentGetDTO> studentGetDtoList = studentService.getAll();
    return new ResponseEntity<>(studentGetDtoList , HttpStatus.OK);
  }

  @GetMapping("students/{id}")
  public ResponseEntity<StudentGetDTO> one(@PathVariable Long id){
    StudentGetDTO studentGetDto = studentService.getOneByID(id);
    return new ResponseEntity<>(studentGetDto , HttpStatus.OK);
  }

  @GetMapping("unauthorised")
  ResponseEntity<String> unauthorised(){
    return new ResponseEntity<> ("Unauthorised User", HttpStatus.FORBIDDEN);
  }

  @GetMapping("notLogin")
  ResponseEntity<String> notLogin(){
    return new ResponseEntity<> ("you need to login first", HttpStatus.FORBIDDEN);
  }

}
