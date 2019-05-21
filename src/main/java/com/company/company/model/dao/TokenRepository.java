package com.company.company.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

	public void createToken(String uuid, Employee employee);
	
	public void touchToken(String uuid);
	
	/*@Query(value = "SELECT * FROM TOKEN t WHERE t.employee.emplId = 1", 
			  nativeQuery = true)*/
	public int getEmployeeIdOfToken(String uuid);
	
	public Token getTokenByEmployeeId(int emplId);
}
