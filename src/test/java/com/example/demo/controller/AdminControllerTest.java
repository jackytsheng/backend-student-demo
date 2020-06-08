package com.example.demo.controller;

import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class AdminControllerTest {

  @Mock
  private UserService userService;

  private MockMvc mockMvc;
  private String json1;
  private String json2;

  @Autowired
  private ObjectMapper mapper;

  private List<StudentGetDto> students;
  private StudentGetDto s1;
  private StudentGetDto s2;
  private UserGetDto u1;
  private UserGetDto u2;

  @InjectMocks
  private AdminController adminController;

  @BeforeEach
  public void setUp() throws Exception{
    mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    s1 = new StudentGetDto();
    s2 = new StudentGetDto();
    u1 = new UserGetDto();
    u2 = new UserGetDto();
    s1.setName("Mike Chan");
    s1.setEmail("Miky@gmail.com");
    s1.setId(1L);
    s2.setName("Jacky Zheng");
    s2.setEmail("Jacky@gmail.com");
    s2.setId(2L);
    u1.setName("Mike Chan");
    u1.setEmail("Miky@gmail.com");
    u1.setId(1L);
    u1.setJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZSI6InN0dWRlbnQifQ.T7MempSggouXHg9RQ3EHFBMZvHObEw3IRJixwEo3tzE");
    u2.setJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSBaaGVuZyIsInJvbGUiOiJhZG1pbiJ9.LoKMaBGiA9noXSc9TTMisQNmU2czs5QiIIoW1y_6r4g");
    u2.setName("Jacky Zheng");
    u2.setEmail("Jacky@gmail.com");
    u2.setId(2L);
    students = new ArrayList<>();
    students.add(s1);
    students.add(s2);
    json1 = mapper.writeValueAsString(s1);
    json2 = mapper.writeValueAsString(s2);
  }

  @Test
  public void testAdminGetAllStudents() throws Exception{
    when(userService.getAll()).thenReturn(students);
    mockMvc.perform(get("/adminAccess").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",hasSize(2)))
        .andExpect(jsonPath("$[0].id",is(1)))
        .andExpect(jsonPath("$[0].email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$[0].name",is("Mike Chan")))
        .andExpect(jsonPath("$[1].id",is(2)))
        .andExpect(jsonPath("$[1].email",is("Jacky@gmail.com")))
        .andExpect(jsonPath("$[1].name",is("Jacky Zheng")));
    verify(userService,times(1)).getAll();
  }


  @Test
  public void testCreateStudentsJWT() throws Exception{
    when(userService.addStudentJWT(any())).thenReturn(u1);
    mockMvc.perform(post("/createStudent").contentType(MediaType.APPLICATION_JSON).content(json1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Mike Chan")))
        .andExpect(jsonPath("$.jws",is("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZSI6InN0dWRlbnQifQ.T7MempSggouXHg9RQ3EHFBMZvHObEw3IRJixwEo3tzE")));
    verify(userService,times(1)).addStudentJWT(any());
  }
  @Test
  public void testCreateAdminJWT() throws Exception {
    when(userService.addAdminJWT(any())).thenReturn(u2);
    mockMvc.perform(post("/createAdmin").contentType(MediaType.APPLICATION_JSON).content(json2))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(2)))
        .andExpect(jsonPath("$.email",is("Jacky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Jacky Zheng")))
        .andExpect(jsonPath("$.jws",is("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSBaaGVuZyIsInJvbGUiOiJhZG1pbiJ9.LoKMaBGiA9noXSc9TTMisQNmU2czs5QiIIoW1y_6r4g")));
    verify(userService,times(1)).addAdminJWT(any());
  }
}
