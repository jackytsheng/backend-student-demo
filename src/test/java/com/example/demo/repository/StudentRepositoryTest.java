package com.example.demo.repository;


import com.example.demo.entity.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void testStudentRepositoryAddingStudent(){
    Student student = new Student();
    student.setName("Jacky332");
    student.setEmail("jacky@gmail.com");
    student.setRole("student");
    student.setStudentID(10900L);
    studentRepository.save(student);
    Student s1 = studentRepository.findByName("Jacky332").orElseThrow();
    Assert.assertEquals(s1.getEmail(),student.getEmail());
    Assert.assertEquals(s1.getName(),student.getName());
    Assert.assertEquals(s1.getRole(),student.getRole());
  }
}
