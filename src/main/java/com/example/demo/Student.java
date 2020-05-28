package com.example.demo;


import lombok.Data;

@Data
public class Student {
  private String name;
  private int studentID;
  private String role;

  public Student(String name, int studentID,String role) {
    this.name = name;
    this.studentID = studentID;
    this.role = role;
  }

}
