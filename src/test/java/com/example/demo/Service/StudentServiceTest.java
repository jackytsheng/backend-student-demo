package com.example.demo.Service;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Dto.StudentPostDTO;
import com.example.demo.Dto.UserGetDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Util.jwtUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentServiceTest {


  @Mock
  private StudentRepository studentRepository;

  @Mock
  private jwtUtil jwtUtil;


  @InjectMocks
  private StudentService studentService;


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
    verify(studentRepository).save(any());
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
    verify(studentRepository).findById(any());
  }

  @Test
  public void testGetAll(){
    Student s1 = new Student();
    s1.setName("Jacky");
    s1.setEmail("jacky@gmail.com");
    s1.setStudentID(1000L);

    Student s2 = new Student();
    s2.setName("Jun");
    s2.setEmail("jun@gmail.com");
    s2.setStudentID(1001L);

    List<Student> students = new ArrayList<>();
    students.add(s1);
    students.add(s2);

    when(studentRepository.findAll()).thenReturn(students);

    List<StudentGetDTO> returnedStudents = studentService.getAll();
    Assert.assertEquals(returnedStudents.get(0).getEmail(),s1.getEmail());
    Assert.assertEquals(returnedStudents.get(0).getName(),s1.getName());
    Assert.assertEquals(returnedStudents.get(0).getId(),s1.getStudentID());
    Assert.assertEquals(returnedStudents.get(1).getEmail(),s2.getEmail());
    Assert.assertEquals(returnedStudents.get(1).getName(),s2.getName());
    Assert.assertEquals(returnedStudents.get(1).getId(),s2.getStudentID());
  }
  @Test
  public void testCreateStudentJWT(){
    StudentPostDTO studentPostDto = new StudentPostDTO();
    studentPostDto.setName("Jacky");
    studentPostDto.setEmail("jacky@gmail.com");

    Student s1 = new Student();
    s1.setName("Jacky");
    s1.setEmail("jacky@gmail.com");
    s1.setStudentID(50000L);
    s1.setRole("student");
    when(studentRepository.save(any())).thenReturn(s1);
    when(jwtUtil.createUser(any())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJzdHVkZW50In0.4lM-W9WpVSncN4UDvSv2EnXqFSctSftg2-vRPCPuyz4");

    UserGetDTO userGetDto = studentService.addStudentJWT(studentPostDto);
    Assert.assertEquals(userGetDto.getEmail(),studentPostDto.getEmail());
    Assert.assertEquals(userGetDto.getName(),studentPostDto.getName());
    Assert.assertEquals(userGetDto.getJws(),"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJzdHVkZW50In0.4lM-W9WpVSncN4UDvSv2EnXqFSctSftg2-vRPCPuyz4");
    Assert.assertNotNull(userGetDto.getId());
    verify(studentRepository).save(any());
    verify(jwtUtil).createUser(any());
  }
  @Test
  public void testCreateAdminJWT(){
    StudentPostDTO studentPostDto = new StudentPostDTO();
    studentPostDto.setName("Jacky");
    studentPostDto.setEmail("jacky@gmail.com");

    Student s1 = new Student();
    s1.setName("Jacky");
    s1.setEmail("jacky@gmail.com");
    s1.setStudentID(50000L);
    s1.setRole("admin");
    when(studentRepository.save(any())).thenReturn(s1);
    when(jwtUtil.createUser(any())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJhZG1pbiJ9.TKykTf7e57kH0dMz6XAsnMFMoL0JF3paOEOAbDTsV5U");

    UserGetDTO userGetDto = studentService.addStudentJWT(studentPostDto);
    Assert.assertEquals(userGetDto.getEmail(),studentPostDto.getEmail());
    Assert.assertEquals(userGetDto.getName(),studentPostDto.getName());
    Assert.assertEquals(userGetDto.getJws(),"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSIsInJvbGUiOiJhZG1pbiJ9.TKykTf7e57kH0dMz6XAsnMFMoL0JF3paOEOAbDTsV5U");
    Assert.assertNotNull(userGetDto.getId());
    verify(studentRepository).save(any());
    verify(jwtUtil).createUser(any());
  }


}
