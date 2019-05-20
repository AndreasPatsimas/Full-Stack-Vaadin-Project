package com.company.company.model.service;

import java.util.List;

import com.company.company.model.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public List<Employee> findByFirstNameStartsWithIgnoreCase(String firstName);
	
	public List<Employee> findByLastNameStartsWithIgnoreCase(String lastName);
	
	public Employee findById(int emplId);
	
	Employee findByEmail(String email);
	
	public void saveOrUpdateEmployee(Employee employee);
	
	public void deleteEmployee(int emplId);
}
