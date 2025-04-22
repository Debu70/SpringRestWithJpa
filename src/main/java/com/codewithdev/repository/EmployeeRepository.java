package com.codewithdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codewithdev.model.Employee;

//controller must be a interface :: so that to perform
//         JPA works with help of respective APIs
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
//                                                      Employee is model and Long is datatype of ID


	

	
}
