package com.example.demo.Repository;


import com.example.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Long> {
  //CRUD..
  //GetAllstudent
  //Get Student By Id
  //Get Student By name
  //GetStudent
  //UpdateStudent
  //deleteStudent
  Optional<Student> findByName(String name);
}
