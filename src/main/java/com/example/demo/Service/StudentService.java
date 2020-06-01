package com.example.demo.Service;


import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> getAll(){
    List<Student> students = new ArrayList<>();
    studentRepository.findAll().forEach(student-> students.add(student));
    return students;
  }

  public void addStudent(Student student){
    studentRepository.save(student);
  }

}
