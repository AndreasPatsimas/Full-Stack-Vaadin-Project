package com.company.company.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByLastNameStartsWithIgnoreCase(String lastName);
}
