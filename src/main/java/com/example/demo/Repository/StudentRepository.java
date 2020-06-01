package com.example.demo.Repository;


import com.example.demo.Entity.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepository extends CrudRepository<Student,Long> {
  //CRUD..
  //GetAllstudent
  //Get Student By Id
  //Get Student By name
  //GetStudent
  //UpdateStudent
  //deleteStudent
}
