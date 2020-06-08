package com.example.demo.service;


import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.dto.StudentPutDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.JwtUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentServiceTest {


  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentService studentService;

  private StudentPostDto studentPostDto;
  private StudentPutDto studentPutDto;
  private Student s1;
  private Student s2;
  private List<Student> students;

  @BeforeEach
  public void setUp() throws Exception{
    studentPostDto = new StudentPostDto();
    studentPutDto = new StudentPutDto();
    studentPostDto.setName("Jacky");
    studentPostDto.setEmail("jacky@gmail.com");
    studentPutDto.setEmail("jacky2@gmail.com");
    studentPutDto.setName("Jacky2");
    s1 = new Student();
    s1.setName("Jacky");
    s1.setEmail("jacky@gmail.com");
    s1.setStudentID(1L);
    s2 = new Student();
    s2.setName("Mick");
    s2.setEmail("mick@gmail.com");
    s2.setStudentID(2L);
    students = new ArrayList<>();
    students.add(s1);
    students.add(s2);
  }
  @Test
  public void testGetAll(){
    when(studentRepository.findAll()).thenReturn(students);
    List<StudentGetDto> returnedStudents = studentService.getAll();
    Assert.assertEquals(returnedStudents.get(0).getEmail(),s1.getEmail());
    Assert.assertEquals(returnedStudents.get(0).getName(),s1.getName());
    Assert.assertEquals(returnedStudents.get(0).getId(),s1.getStudentID());
    Assert.assertEquals(returnedStudents.get(1).getEmail(),s2.getEmail());
    Assert.assertEquals(returnedStudents.get(1).getName(),s2.getName());
    Assert.assertEquals(returnedStudents.get(1).getId(),s2.getStudentID());
    verify(studentRepository,times(1)).findAll();
  }

  @Test
  public void testAddStudent(){
    when(studentRepository.save(any())).thenReturn(s1);
    StudentGetDto returnedGetDto = studentService.addStudent(studentPostDto);
    Assert.assertEquals(returnedGetDto.getEmail(),s1.getEmail());
    Assert.assertEquals(returnedGetDto.getName(),s1.getName());
    Assert.assertEquals(returnedGetDto.getId(),s1.getStudentID());
    verify(studentRepository,times(1)).save(any());
  }

  @Test
  public void testGetOneByID(){
    when(studentRepository.findById(any())).thenReturn(Optional.of(s1));
    StudentGetDto returnedGetDto = studentService.getOneByID(1L);
    Assert.assertEquals(returnedGetDto.getEmail(),s1.getEmail());
    Assert.assertEquals(returnedGetDto.getName(),s1.getName());
    Assert.assertEquals(returnedGetDto.getId(),s1.getStudentID());
    verify(studentRepository).findById(any());
  }
  @Test
  public void testEdit(){
    Student editedStudent = new Student();
    editedStudent.setName(studentPostDto.getName());
    editedStudent.setEmail(studentPostDto.getEmail());
    editedStudent.setStudentID(1L);
    when(studentRepository.findById(any())).thenReturn(Optional.of(editedStudent));
    when(studentRepository.save(any())).thenReturn(editedStudent);
    StudentGetDto returnedGetDto = studentService.edit(1L,studentPutDto);
    Assert.assertEquals(returnedGetDto.getEmail(),editedStudent.getEmail());
    Assert.assertEquals(returnedGetDto.getName(),editedStudent.getName());
    Assert.assertEquals(returnedGetDto.getId(),editedStudent.getStudentID());
    verify(studentRepository,times(1)).save(any());
    verify(studentRepository,times(1)).findById(any());
  }
  @Test
  public void testDelete(){
    when(studentRepository.findById(any())).thenReturn(Optional.of(s1));
    Long id = 1L;
    String testingStr = studentService.delete(id);
    verify(studentRepository,times(1)).deleteById(any());
    verify(studentRepository,times(1)).findById(any());
    Assert.assertEquals(testingStr ,"Student with id " + id + " is deleted");
  }

}
