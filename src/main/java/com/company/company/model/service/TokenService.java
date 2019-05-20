package com.company.company.model.service;

import com.company.company.model.entity.Employee;

public interface TokenService {

	public Employee getEmployeeByToken(String uuid);
	
	public void touchToken(String uuid);
	
	public void createToken(String uuid, Employee employee);
	
	public void deleteToken(String uuid);
}
