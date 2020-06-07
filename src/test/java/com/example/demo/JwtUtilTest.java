package com.example.demo;

import com.example.demo.Entity.Student;
import com.example.demo.Util.jwtUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUtilTest {

  @Autowired
  jwtUtil jwtUtil;

  @Test
  public void testCreateUser(){
    Student user1 = new Student();
    user1.setName("Jacky Zheng");
    user1.setRole("admin");

    Student user2 = new Student();
    user2.setName("Mike");
    user2.setRole("student");

    Assert.assertEquals(jwtUtil.createUser(user1),"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSBaaGVuZyIsInJvbGUiOiJhZG1pbiJ9.LoKMaBGiA9noXSc9TTMisQNmU2czs5QiIIoW1y_6r4g");
    Assert.assertEquals(jwtUtil.createUser(user2),"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZSI6InN0dWRlbnQifQ.T7MempSggouXHg9RQ3EHFBMZvHObEw3IRJixwEo3tzE");
  }


  @Test
  public void testIsAdmin(){
    Assert.assertTrue(jwtUtil.isAdmin("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSBaaGVuZyIsInJvbGUiOiJhZG1pbiJ9.LoKMaBGiA9noXSc9TTMisQNmU2czs5QiIIoW1y_6r4g"));
  }

}
