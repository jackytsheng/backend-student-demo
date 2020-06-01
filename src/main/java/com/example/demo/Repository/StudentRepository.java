package com.example.demo.Repository;


import com.example.demo.Entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
  //CRUD..
  //GetAllTopics
  //GetStudent
  //UpdateStudent
  //deleteStudent
}
