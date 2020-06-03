package com.example.demo.Dto;

import lombok.Data;

@Data
public class UserGetDTO {
  Long id;
  String name;
  String email;
  String jws;
}
