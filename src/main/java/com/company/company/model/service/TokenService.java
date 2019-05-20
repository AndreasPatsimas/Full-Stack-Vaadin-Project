package com.company.company.model.service;

import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;

public interface TokenService {

	public Token getTokenByEmployeeId(int emplId);
	
	public void touchToken(String uuid);
	
	public void createToken(String uuid, Employee employee);
	
	public void deleteToken(String uuid);
}
