package com.example.demo.service;


import com.example.demo.dto.StudentGetDto;
import com.example.demo.dto.StudentPostDto;
import com.example.demo.dto.StudentPutDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  RabbitMessageService rabbitMessageService;


  public List<StudentGetDto> getAll(){
    List<StudentGetDto> students = new ArrayList<>();
    studentRepository.findAll().forEach( student -> {
        StudentGetDto studentGetDto = new StudentGetDto();
        studentGetDto.setEmail(student.getEmail());
        studentGetDto.setName(student.getName());
        studentGetDto.setId(student.getStudentID());
        students.add(studentGetDto);
    }
    );
    return students;
  }

  public StudentGetDto getOneByID(Long id){
   StudentGetDto studentGetDto = new StudentGetDto();

   Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

   studentGetDto.setId(student.getStudentID());
   studentGetDto.setName(student.getName());
   studentGetDto.setEmail(student.getEmail());
   return studentGetDto;
  }

  public StudentGetDto addStudent(StudentPostDto studentDto) throws Exception {
    Student student = new Student();
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setRole("student");
    Student s1 = studentRepository.save(student);
    StudentGetDto studentGetDto = new StudentGetDto();
    studentGetDto.setEmail(s1.getEmail());
    studentGetDto.setName(s1.getName());
    studentGetDto.setId(s1.getStudentID());
    rabbitMessageService.sendMessage("Student Creation",student.getName()+
      " with email: "+ student.getEmail() + " is created.");
    return studentGetDto;
  }

  public StudentGetDto edit(Long id, StudentPutDto studentPutDto){
    Student student;
    student = studentRepository.findById(id).orElseThrow( () -> {
      log.error("no such id found");
      throw new StudentNotFoundException(id);
    }
    );
    student.setName(studentPutDto.getName());
    student.setEmail(studentPutDto.getEmail());
    studentRepository.save(student);
    StudentGetDto studentGetDto = new StudentGetDto();
    studentGetDto.setEmail(student.getEmail());
    studentGetDto.setName(student.getName());
    studentGetDto.setId(student.getStudentID());
    return studentGetDto;
  }
  public String delete(Long id){
    studentRepository.findById(id).orElseThrow( () -> {
          log.error("no such id found");
          throw new StudentNotFoundException(id);
        }
    );
    studentRepository.deleteById(id);
    return "Student with id " + id + " is deleted";
  }
}
