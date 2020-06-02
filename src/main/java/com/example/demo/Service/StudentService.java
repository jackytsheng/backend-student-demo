package com.example.demo.Service;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Dto.StudentPostDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.Exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class StudentService {

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
}
