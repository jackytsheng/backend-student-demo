package com.example.demo.exception;

public class StudentNotFoundException extends RuntimeException {


  public StudentNotFoundException(Long id){
    super("No student found with ID number " + id);
  }

}
