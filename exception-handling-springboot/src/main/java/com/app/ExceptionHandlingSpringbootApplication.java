package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

@SpringBootApplication
public class ExceptionHandlingSpringbootApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandlingSpringbootApplication.class, args);
	}

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		Employee emp1 = Employee.builder()
				.name("Roshan")
				.email("roshan@gmail.com")
				.build();
		
		Employee emp2 = Employee.builder()
				.name("Sanket")
				.email("sanket@gmail.com")
				.build();
		
		Employee emp3 = Employee.builder()
				.name("Ramesh")
				.email("ramesh@gmail.com")
				.build();
		
		
		repo.save(emp1);
		repo.save(emp2);
		repo.save(emp3);
		
	}

}
