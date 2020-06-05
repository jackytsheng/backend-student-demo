package com.example.demo.Controller;

import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Dto.UserGetDTO;
import com.example.demo.Service.StudentService;
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
public class PostControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  private List<StudentGetDTO> students;
  private StudentGetDTO s1;
  private StudentGetDTO s2;
  private UserGetDTO u1;
  private UserGetDTO u2;
  private String json1;
  private String json2;

  @Mock
  private StudentService studentService;

  @InjectMocks
  private PostController postController;

  @BeforeEach
  public void setUp() throws Exception{
    mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    s1 = new StudentGetDTO();
    s2 = new StudentGetDTO();
    u1 = new UserGetDTO();
    u2 = new UserGetDTO();
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
  public void testAddStudent() throws Exception{
    when(studentService.addStudent(any())).thenReturn(s1);
    mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(json1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Mike Chan")));
    verify(studentService,times(1)).addStudent(any());
  }
  @Test
  public void testCreateStudentsJWT() throws Exception{
    when(studentService.addStudentJWT(any())).thenReturn(u1);
    mockMvc.perform(post("/createStudent").contentType(MediaType.APPLICATION_JSON).content(json1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Mike Chan")))
        .andExpect(jsonPath("$.jws",is("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZSI6InN0dWRlbnQifQ.T7MempSggouXHg9RQ3EHFBMZvHObEw3IRJixwEo3tzE")));
    verify(studentService,times(1)).addStudentJWT(any());
  }
  @Test
  public void testCreateAdminJWT() throws Exception {
    when(studentService.addAdminJWT(any())).thenReturn(u2);
    mockMvc.perform(post("/createAdmin").contentType(MediaType.APPLICATION_JSON).content(json2))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(2)))
        .andExpect(jsonPath("$.email",is("Jacky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Jacky Zheng")))
        .andExpect(jsonPath("$.jws",is("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWNreSBaaGVuZyIsInJvbGUiOiJhZG1pbiJ9.LoKMaBGiA9noXSc9TTMisQNmU2czs5QiIIoW1y_6r4g")));
    verify(studentService,times(1)).addAdminJWT(any());
  }

}
