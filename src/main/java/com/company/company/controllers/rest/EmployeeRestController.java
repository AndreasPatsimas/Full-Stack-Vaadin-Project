package com.company.company.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping(value = "employee/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") int emplId) {
		
		Employee employee = employeeService.findById(emplId);
		
		return employee;
	}
	
	@PostMapping(value = "employee")
	public Employee saveEmployee (@RequestBody Employee employee) {
		
		employee.setEmplId(0);
		
		employeeService.saveOrUpdateEmployee(employee);
		
		return employee;
	}
	
	@PutMapping(value = "employee")
	public Employee editEmployee (@RequestBody Employee employee) {
		
		employeeService.saveOrUpdateEmployee(employee);
		
		return employee;
	}
	
	@DeleteMapping(value = "employee/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") int emplId) {
		
		employeeService.deleteEmployee(emplId);
		
		return "Deleted Employee with id -"+emplId;

	}
}
