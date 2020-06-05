package com.example.demo.Controller;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
public class GetControllerTest {

  private MockMvc mockMvc;

  private List<StudentGetDTO> students;
  private StudentGetDTO s1;
  private StudentGetDTO s2;

  @Mock
  private StudentService studentService;

  @InjectMocks
  private GetController getController;

  @BeforeEach
  public void setUp() throws Exception{
    mockMvc = MockMvcBuilders.standaloneSetup(getController).build();

    s1 = new StudentGetDTO();
    s2 = new StudentGetDTO();
    s1.setName("Mike Chan");
    s1.setEmail("Miky@gmail.com");
    s1.setId(1L);
    s2.setName("Jacky Zheng");
    s2.setEmail("Jacky@gmail.com");
    s2.setId(2L);
    students = new ArrayList<>();
    students.add(s1);
    students.add(s2);
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
  public void testAdminGetAllStudents() throws Exception{
    when(studentService.getAll()).thenReturn(students);
    mockMvc.perform(get("/adminAccess").accept(MediaType.APPLICATION_JSON))
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

}
