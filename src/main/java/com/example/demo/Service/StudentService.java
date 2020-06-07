package com.example.demo.Service;


import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.dto.StudentPutDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.Exception.StudentNotFoundException;
import com.example.demo.Util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

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

  public StudentGetDto getOneByID(Long id){
   StudentGetDto studentGetDto = new StudentGetDto();
   Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
   studentGetDto.setId(student.getStudentID());
   studentGetDto.setName(student.getName());
   studentGetDto.setEmail(student.getEmail());
   return studentGetDto;
  }

  public StudentGetDto addStudent(StudentPostDto studentDto){
    Student student = new Student();
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setRole("student");
    Student s1 = studentRepository.save(student);
    StudentGetDto studentGetDto = new StudentGetDto();
    studentGetDto.setEmail(s1.getEmail());
    studentGetDto.setName(s1.getName());
    studentGetDto.setId(s1.getStudentID());
    return studentGetDto;
  }

  public StudentGetDto edit(Long id, StudentPutDto studentPutDto){
    Student student;
    student = studentRepository.findById(id).orElseThrow();
    student.setName(studentPutDto.getName());
    student.setEmail(studentPutDto.getEmail());
    studentRepository.save(student);
    StudentGetDto studentGetDto = new StudentGetDto();
    studentGetDto.setEmail(student.getEmail());
    studentGetDto.setName(student.getName());
    studentGetDto.setId(student.getStudentID());
    return studentGetDto;
  }
  public String delete(Long id){
    studentRepository.deleteById(id);
    return "Student with id " + id + " is deleted";
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
