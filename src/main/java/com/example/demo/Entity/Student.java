package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {

  @Id
  @GeneratedValue
  private Long studentID;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String role;


}
