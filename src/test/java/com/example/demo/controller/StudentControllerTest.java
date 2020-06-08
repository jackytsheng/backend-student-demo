package com.example.demo.controller;


import com.example.demo.dto.StudentGetDto;
import com.example.demo.service.StudentService;
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
public class StudentControllerTest {

  private MockMvc mockMvc;


  @Mock
  private StudentService studentService;

  @InjectMocks
  private StudentController studentController;

  private String json1;

  @Autowired
  private ObjectMapper mapper;
  private List<StudentGetDto> students;
  private StudentGetDto s1;
  private StudentGetDto s2;

  @BeforeEach
  public void setUp() throws Exception{
    mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    s1 = new StudentGetDto();
    s2 = new StudentGetDto();
    s1.setName("Mike Chan");
    s1.setEmail("Miky@gmail.com");
    s1.setId(1L);
    s2.setName("Jacky Zheng");
    s2.setEmail("Jacky@gmail.com");
    s2.setId(2L);
    students = new ArrayList<>();
    students.add(s1);
    students.add(s2);
    json1 = mapper.writeValueAsString(s1);
  }

  @Test
  public void testGetAllStudents() throws Exception{
    when(studentService.getAll()).thenReturn(students);
    mockMvc.perform(get("/students").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",hasSize(2)))
        .andExpect(jsonPath("$[0].id",is(1)))
        .andExpect(jsonPath("$[0].email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$[0].name",is("Mike Chan")))
        .andExpect(jsonPath("$[1].id",is(2)))
        .andExpect(jsonPath("$[1].email",is("Jacky@gmail.com")))
        .andExpect(jsonPath("$[1].name",is("Jacky Zheng")));
    verify(studentService,times(1)).getAll();
  }

  @Test
  public void testGetStudentByID() throws Exception {
    when(studentService.getOneByID(any())).thenReturn(s1);
    mockMvc.perform(get("/students/{id}",1).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Mike Chan")));
    verify(studentService,times(1)).getOneByID(any());
  }
  @Test
  public void testPostStudent() throws Exception{
    when(studentService.addStudent(any())).thenReturn(s1);
    mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(json1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Mike Chan")));
    verify(studentService,times(1)).addStudent(any());
  }

  @Test
  public void testPutStudent() throws Exception{
    when(studentService.edit(any(),any())).thenReturn(s1);
    mockMvc.perform(put("/students/{id}",1).contentType(MediaType.APPLICATION_JSON).content(json1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.email",is("Miky@gmail.com")))
        .andExpect(jsonPath("$.name",is("Mike Chan")));
    verify(studentService,times(1)).edit(any(),any());
  }
  @Test
  public void testDeleteStudent() throws Exception{
    when(studentService.delete(any())).thenReturn("Deleted");
    mockMvc.perform(delete("/students/{id}",1))
        .andExpect(status().isOk())
        .andExpect(content().string("Deleted"));
    verify(studentService,times(1)).delete(any());
  }
}
