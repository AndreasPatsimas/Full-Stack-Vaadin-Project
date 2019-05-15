package com.company.company.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.company.model.entity.Employee;
import com.company.company.model.service.EmployeeService;

@RestController
public class EmployeeRestController {

	EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping(value = "employees")
	public List<Employee> getEmployees(){

		return employeeService.findAll();
	}
	
}
