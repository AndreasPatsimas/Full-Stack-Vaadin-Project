package com.company.company.model.service_impl;

import java.util.List;

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
		
		return null;
	}

	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int emplId) {
		
		employeeRepository.deleteById(emplId);
	}

}
