package com.example.demo.controller;


import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.service.StudentService;
import com.example.demo.dto.StudentPutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("students")
@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  public ResponseEntity<List<StudentGetDto>> all(){
    List<StudentGetDto> studentGetDtoList = studentService.getAll();
    return new ResponseEntity<>(studentGetDtoList , HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentGetDto> one(@PathVariable Long id){
    StudentGetDto studentGetDto = studentService.getOneByID(id);
    return new ResponseEntity<>(studentGetDto , HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<StudentGetDto> add(@RequestBody StudentPostDto studentPostDto){
    StudentGetDto studentGetDto = studentService.addStudent(studentPostDto);
    return new ResponseEntity<>(studentGetDto , HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentGetDto> edit(@PathVariable Long id, @RequestBody StudentPutDto studentPutDto){
    StudentGetDto studentGetDto = studentService.edit(id,studentPutDto);
    return new ResponseEntity<>(studentGetDto , HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id){
    String msg = studentService.delete(id);
    return new ResponseEntity<>(msg, HttpStatus.OK);
  }




}
