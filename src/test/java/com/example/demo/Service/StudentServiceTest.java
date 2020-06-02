package com.example.demo.Service;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Dto.StudentPostDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.Exception.StudentNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentServiceTest {

  @Autowired
  @InjectMocks
  private StudentService studentService;

  @Mock
  private StudentRepository studentRepository;

  @Test
  public void testAddStudent(){
    StudentPostDTO studentPostDto = new StudentPostDTO();
    studentPostDto.setName("Jacky");
    studentPostDto.setEmail("jacky@gmail.com");

    Student s1 = new Student();
    s1.setName("Jacky");
    s1.setEmail("jacky@gmail.com");
    s1.setStudentID(50000L);

    when(studentRepository.save(any())).thenReturn(s1);
    StudentGetDTO studentGetDto = studentService.addStudent(studentPostDto);
    Assert.assertEquals(studentGetDto.getEmail(),studentPostDto.getEmail());
    Assert.assertEquals(studentGetDto.getName(),studentPostDto.getName());
    Assert.assertNotNull(studentGetDto.getId());
  }

  @Test
  public void testGetOneByID(){
    Student s1 = new Student();
    s1.setName("Jacky");
    s1.setEmail("jacky@gmail.com");
    s1.setRole("student");
    s1.setStudentID(1L);

    when(studentRepository.findById(any())).thenReturn(Optional.of(s1));
    StudentGetDTO studentGetDto = studentService.getOneByID(1L);
    Assert.assertEquals(studentGetDto.getEmail(),s1.getEmail());
    Assert.assertEquals(studentGetDto.getName(),s1.getName());
    Assert.assertEquals(studentGetDto.getId(),s1.getStudentID());
  }

//  @Test
//  public void testGetAll(){
//    StudentPostDTO studentPostDto1 = new StudentPostDTO();
//    studentPostDto1.setName("Jacky");
//    studentPostDto1.setEmail("jacky@gmail.com");
//
//    Student s1 = new Student();
//    s1.setName("Jacky");
//    s1.setEmail("jacky@gmail.com");
//    s1.setStudentID(1L);
//
//    when(studentRepository.save(studentPostDto1)).thenReturn(s1);
//
//    when(studentRepository.findById(any()).orElseThrow()).thenReturn(s1);
//    StudentGetDTO studentGetDto = studentService.getOneByID(3L);
//    Assert.assertEquals(studentGetDto.getEmail(),s1.getEmail());
//    Assert.assertEquals(studentGetDto.getName(),s1.getName());
//    Assert.assertEquals(studentGetDto.getId(),s1.getStudentID());
//  }


}
