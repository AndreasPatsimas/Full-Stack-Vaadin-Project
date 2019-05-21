package com.company.company.model.service;

import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;

public interface TokenService {

	public int getEmployeeIdOfToken(String uuid);
	
	public void touchToken(String uuid);
	
	public void createToken(Employee employee);
	
	public void deleteToken(String uuid);
	
	public Token getTokenByEmployeeId(int emplId);
}
