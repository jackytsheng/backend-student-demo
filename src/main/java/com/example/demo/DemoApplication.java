package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository){
		return args->{
			Student s1 = new Student();
			s1.setRole("student");
			s1.setName("Mike Chan");
			s1.setEmail("Mike@qq.com");
			studentRepository.save(s1);
		};
	}


}
