package com.example.demo.Service.Exception;

public class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException(Long id){
    super("No student found with ID number " + id);
  }
}
