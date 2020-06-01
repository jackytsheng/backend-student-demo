package com.example.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Student {

  private String name;

  @Id @GeneratedValue
  private Long studentID;


  private String role;

  public Student(){}
  public Student(String name,String role){
    this.name=name;
    this.role=role;
  }

}
