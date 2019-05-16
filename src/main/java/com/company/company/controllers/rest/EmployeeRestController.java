package com.company.company.controllers.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	
	@GetMapping(value = "employees/{word}")
	public List<Employee> getEmployeesFilteredByName(@PathVariable("word") String word){

		List<Employee> firstNameList = employeeService.findByFirstNameStartsWithIgnoreCase(word);
		List<Employee> lastNameList = employeeService.findByLastNameStartsWithIgnoreCase(word);
		try {
			if(!firstNameList.equals(Collections.EMPTY_LIST)
					|| !lastNameList.equals(Collections.EMPTY_LIST)) {
				List<Employee> unionList = new  ArrayList<>(firstNameList.size() + lastNameList.size());
				unionList.addAll(firstNameList);
				unionList.addAll(lastNameList);
				
				Set<Employee> unionSet = new HashSet<>(unionList);
				unionList.clear();
				unionList.addAll(unionSet);
				
				return unionList;
			}
			else {
				return employeeService.findByLastNameStartsWithIgnoreCase(word);
			}
		}
		catch (Exception ex) {
			return employeeService.findByFirstNameStartsWithIgnoreCase(word);
		}
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
