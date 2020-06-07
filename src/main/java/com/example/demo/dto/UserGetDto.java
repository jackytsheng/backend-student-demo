package com.example.demo.dto;

import lombok.Data;

@Data
public class UserGetDto {
  Long id;
  String name;
  String email;
  String jws;
}
