package com.example.demo.service;


import com.example.demo.controller.AdminController;
import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.dto.StudentPutDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Mock
  private JwtUtil jwtUtil;

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private UserService userService;

  private StudentPostDto studentPostDto;
  private Student s1;
  private Student s2;
  private List<Student> students;

  @BeforeEach
  public void setUp() throws Exception{
    s1 = new Student();
    s2 = new Student();
    studentPostDto = new StudentPostDto();
    studentPostDto.setName("Mike Chan");
    studentPostDto.setEmail("Miky@gmail.com");
    s1.setName("Mike Chan");
    s1.setEmail("Miky@gmail.com");
    s1.setStudentID(1L);
    s2.setName("Jacky Zheng");
    s2.setEmail("Jacky@gmail.com");
    s2.setStudentID(2L);
    students = new ArrayList<>();
    students.add(s1);
    students.add(s2);
  }

  @Test
  public void testGetAll(){
    when(studentRepository.findAll()).thenReturn(students);
    List<StudentGetDto> returnedStudents = userService.getAll();
    Assert.assertEquals(returnedStudents.get(0).getEmail(),s1.getEmail());
    Assert.assertEquals(returnedStudents.get(0).getName(),s1.getName());
    Assert.assertEquals(returnedStudents.get(0).getId(),s1.getStudentID());
    Assert.assertEquals(returnedStudents.get(1).getEmail(),s2.getEmail());
    Assert.assertEquals(returnedStudents.get(1).getName(),s2.getName());
    Assert.assertEquals(returnedStudents.get(1).getId(),s2.getStudentID());
    verify(studentRepository,times(1)).findAll();
  }

  @Test
  public void testCreateStudentJWT(){

    when(studentRepository.save(any())).thenReturn(s1);
    when(jwtUtil.createUser(any())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJzdHVkZW50In0.4lM-W9WpVSncN4UDvSv2EnXqFSctSftg2-vRPCPuyz4");
    UserGetDto userGetDto = userService.addStudentJWT(studentPostDto);
    Assert.assertEquals(userGetDto.getEmail(),s1.getEmail());
    Assert.assertEquals(userGetDto.getName(),s1.getName());
    Assert.assertEquals(userGetDto.getJws(),"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJzdHVkZW50In0.4lM-W9WpVSncN4UDvSv2EnXqFSctSftg2-vRPCPuyz4");
    Assert.assertNotNull(userGetDto.getId());
    verify(studentRepository,times(1)).save(any());
    verify(jwtUtil,times(1)).createUser(any());
  }
  @Test
  public void testCreateAdminJWT(){
    when(studentRepository.save(any())).thenReturn(s1);
    when(jwtUtil.createUser(any())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJhZG1pbiJ9.TKykTf7e57kH0dMz6XAsnMFMoL0JF3paOEOAbDTsV5U");
    UserGetDto userGetDto = userService.addAdminJWT(studentPostDto);
    Assert.assertEquals(userGetDto.getEmail(),s1.getEmail());
    Assert.assertEquals(userGetDto.getName(),s1.getName());
    Assert.assertEquals(userGetDto.getJws(),"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJhZG1pbiJ9.TKykTf7e57kH0dMz6XAsnMFMoL0JF3paOEOAbDTsV5U");
    Assert.assertNotNull(userGetDto.getId());
    verify(studentRepository,times(1)).save(any());
    verify(jwtUtil,times(1)).createUser(any());
  }


}