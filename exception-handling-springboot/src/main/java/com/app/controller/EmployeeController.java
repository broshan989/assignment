package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.customexception.InvalidEmpoyeeException;
import com.app.model.Employee;
import com.app.repository.EmployeeRepository;;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//rest end point to get all employees
	@GetMapping("/get-all") 
	public List<Employee> getEmployee() {
		log.info("inside get all employees");
		return repository.findAll();
	}
	
	@GetMapping("/get/{id}") //get employee by id
	public ResponseEntity<Employee> getById(@PathVariable int id){
		log.info("inside getById() with id: "+id);
		Employee employee = repository.findById(id).orElseThrow(() -> new InvalidEmpoyeeException("Employee not found"));
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteById(@PathVariable int id){
		log.info("inside deleteById() with id: "+id);
		Employee employee = repository.findById(id).orElseThrow(() -> new InvalidEmpoyeeException("Employee not found"));
		repository.delete(employee);
		return ResponseEntity.status(HttpStatus.OK).body("Employee has been deleted");
	}
	
	@PostMapping("/save")
	ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee){
		log.info("inside saveEmployee() emp: "+employee);
		Employee emp = repository.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}
	
}
