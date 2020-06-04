package com.example.demo.Controller;


import com.example.demo.Dto.StudentGetDTO;
import com.example.demo.Service.StudentService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private StudentService studentService;

  @InjectMocks
  private GetController getController;

  @Test
  public void testGetAllStudents() throws Exception{

    List<StudentGetDTO> students = new ArrayList<>();
    StudentGetDTO s1 = new StudentGetDTO();
    StudentGetDTO s2 = new StudentGetDTO();
    s1.setName("Mike Chan");
    s1.setEmail("Miky@gmail.com");
    s1.setId(1L);
    s2.setName("Jacky Zheng");
    s2.setEmail("Jacky@gmail.com");
    s2.setId(2L);
    students.add(s1);
    students.add(s2);

    when(studentService.getAll()).thenReturn(students);
    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$",hasSize(1)));
    verify(studentService).getAll();
  }

}
