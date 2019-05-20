package com.company.company.model.service_impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.company.model.dao.EmployeeRepository;
import com.company.company.model.entity.Employee;
import com.company.company.model.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
		
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int emplId) {
		
		Optional<Employee> result = employeeRepository.findById(emplId);
		
		Employee employee = null;
		
		if(result.isPresent()) {
			employee = result.get();
		}
		else {
			throw new RuntimeException("There is not a product with id - "+emplId);
		}
		
		return employee;
		
	}

	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int emplId) {
		
		employeeRepository.deleteById(emplId);
	}

	@Override
	public List<Employee> findByLastNameStartsWithIgnoreCase(String lastName) {
		
		return employeeRepository.findByLastNameStartsWithIgnoreCase(lastName);
	}

	@Override
	public List<Employee> findByFirstNameStartsWithIgnoreCase(String firstName) {
		
		return employeeRepository.findByFirstNameStartsWithIgnoreCase(firstName);
	}

	@Override
	public Employee findByEmail(String email) {
		
		return employeeRepository.findByEmail(email);
	}

}
