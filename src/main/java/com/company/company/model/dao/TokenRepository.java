package com.company.company.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

	public void createToken(String uuid, Employee employee);
	
	public void touchToken(String uuid);
}
