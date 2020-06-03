package com.example.demo.Service;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Dto.StudentPostDTO;
import com.example.demo.Dto.UserGetDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.Exception.StudentNotFoundException;
import com.example.demo.Util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

  private final jwtUtil jwtUtil;
  private final StudentRepository studentRepository;

  public List<StudentGetDTO> getAll(){
    List<StudentGetDTO> students = new ArrayList<>();
    studentRepository.findAll().forEach( student -> {
        StudentGetDTO studentGetDto = new StudentGetDTO();
        studentGetDto.setEmail(student.getEmail());
        studentGetDto.setName(student.getName());
        studentGetDto.setId(student.getStudentID());
        students.add(studentGetDto);
    }
    );
    return students;
  }

  public StudentGetDTO getOneByID(Long id){
   StudentGetDTO studentGetDto = new StudentGetDTO();
   Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
   studentGetDto.setId(student.getStudentID());
   studentGetDto.setName(student.getName());
   studentGetDto.setEmail(student.getEmail());
   return studentGetDto;
  }

  public StudentGetDTO addStudent(StudentPostDTO studentDto){
    Student student = new Student();
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setRole("student");
    Student s1 = studentRepository.save(student);
    StudentGetDTO studentGetDto = new StudentGetDTO();
    studentGetDto.setEmail(s1.getEmail());
    studentGetDto.setName(s1.getName());
    studentGetDto.setId(s1.getStudentID());
    return studentGetDto;
  }

  public UserGetDTO addAdminJWT(StudentPostDTO studentDto){
      Student student = new Student();
      student.setName(studentDto.getName());
      student.setEmail(studentDto.getEmail());
      student.setRole("admin");
      Student s1 = studentRepository.save(student);
      UserGetDTO userGetDto = new UserGetDTO();
      userGetDto.setEmail(s1.getEmail());
      userGetDto.setName(s1.getName());
      userGetDto.setId(s1.getStudentID());
      userGetDto.setJws(jwtUtil.createUser(student));
      return userGetDto;
    }
  public UserGetDTO addStudentJWT(StudentPostDTO studentDto){
    Student student = new Student();
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setRole("student");
    Student s1 = studentRepository.save(student);
    UserGetDTO userGetDto = new UserGetDTO();
    userGetDto.setEmail(s1.getEmail());
    userGetDto.setName(s1.getName());
    userGetDto.setId(s1.getStudentID());
    userGetDto.setJws(jwtUtil.createUser(student));
    return userGetDto;
  }
}
