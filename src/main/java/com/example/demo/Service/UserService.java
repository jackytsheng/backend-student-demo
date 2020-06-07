package com.example.demo.Service;


import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

  private final jwtUtil jwtUtil;
  private final StudentRepository studentRepository;

  public List<StudentGetDto> getAll(){
    List<StudentGetDto> students = new ArrayList<>();
    studentRepository.findAll().forEach( student -> {
          StudentGetDto studentGetDto = new StudentGetDto();
          studentGetDto.setEmail(student.getEmail());
          studentGetDto.setName(student.getName());
          studentGetDto.setId(student.getStudentID());
          students.add(studentGetDto);
        }
    );
    return students;
  }

  public UserGetDto addAdminJWT(StudentPostDto studentDto){
    Student student = new Student();
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setRole("admin");
    Student s1 = studentRepository.save(student);
    UserGetDto userGetDto = new UserGetDto();
    userGetDto.setEmail(s1.getEmail());
    userGetDto.setName(s1.getName());
    userGetDto.setId(s1.getStudentID());
    userGetDto.setJws(jwtUtil.createUser(student));
    return userGetDto;
  }
  public UserGetDto addStudentJWT(StudentPostDto studentDto){
    Student student = new Student();
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setRole("student");
    Student s1 = studentRepository.save(student);
    UserGetDto userGetDto = new UserGetDto();
    userGetDto.setEmail(s1.getEmail());
    userGetDto.setName(s1.getName());
    userGetDto.setId(s1.getStudentID());
    userGetDto.setJws(jwtUtil.createUser(student));
    return userGetDto;
  }
}
