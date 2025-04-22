package com.codewithdev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdev.model.Employee;
import com.codewithdev.repository.EmployeeRepository;



@RestController

//add a annotaon as RequestMapping for starting page
@RequestMapping("/api")
public class EmployeeController {


	@Autowired      //by "@AutoWired" we can not create objs manually , spring will create ..
	EmployeeRepository employeeRepository;


	//create a api endpoint for create a new Employee as POST
	@PostMapping("/employees")
	public String createNewEmplyee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "New Employee Created Successfully !";
	}


	//for get all employees in DB
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	//for fetch employee by employeeId
	@GetMapping("/employees/{emp_id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable long emp_id){
		Optional<Employee> empByID = employeeRepository.findById(emp_id);
		if (empByID.isPresent()) {
			return new ResponseEntity<>(empByID.get(), HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/employees/{emp_id}")
	public String updateEmployeeByID(@PathVariable long emp_id, @RequestBody Employee employee){
		Optional<Employee> emp =  employeeRepository.findById(emp_id);
		if (emp.isPresent()) {
			Employee existEmployee = emp.get();
			existEmployee.setEmp_name( employee.getEmp_name());
			existEmployee.setEmp_age(employee.getEmp_age());
			existEmployee.setEmp_salary(employee.getEmp_salary());
			existEmployee.setEmp_department(employee.getEmp_department());
			existEmployee.setEmp_city(employee.getEmp_city());

			employeeRepository.save(existEmployee);

			return "Employee Details Are Updated Successfully! against Employee-ID on "+emp_id;
		}else {
			return "Employee Details Are Not Found against Employee-ID on "+emp_id;
		}
	}

	@DeleteMapping("/employees/{emp_id}")
	public String deleteEmployeeByID(@PathVariable long emp_id) {
		employeeRepository.deleteById(emp_id);
		return "Employee Details Deleted Successfully! aginst Employee-ID "+emp_id;
	}

	@DeleteMapping("/employees")
	public String deleteAllEMployee() {
		employeeRepository.deleteAll();
		return "All employee Deleted Successfully!";
	}




	 








}
