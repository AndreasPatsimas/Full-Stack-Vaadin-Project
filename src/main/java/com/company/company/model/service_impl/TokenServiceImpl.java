package com.company.company.model.service_impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.company.model.dao.EmployeeRepository;
import com.company.company.model.dao.TokenRepository;
import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;
import com.company.company.model.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	
	private TokenRepository tokenRepository;
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public TokenServiceImpl(TokenRepository tokenRepository, EmployeeRepository employeeRepository) {
		this.tokenRepository = tokenRepository;
		this.employeeRepository = employeeRepository;
	}

	/*@Override
	public Employee getEmployeeByToken(String uuid) {
		
		Optional<Token> tokenResult = tokenRepository.findById(uuid);
		
		Token token = null;
		
		if(tokenResult.isPresent()) {
			token = tokenResult.get();
		}
		else {
			throw new RuntimeException("There is not such token!");
		}
		
		return token.getEmployee();
	}*/

	@Override
	public void touchToken(String uuid) {
		tokenRepository.touchToken(uuid);
	}

	@Override
	public void createToken(Employee employee) {
		
		String uuid = UUID.randomUUID().toString();
		
		tokenRepository.createToken(uuid, employee);
	}

	@Override
	public void deleteToken(String uuid) {
		
		tokenRepository.deleteById(uuid);
	}

	@Override
	public int getEmployeeIdOfToken(String uuid) {
		
		return tokenRepository.getEmployeeIdOfToken(uuid);
	}
	
	@Override
	public Token getTokenByEmployeeId(int emplId) {
		
		return tokenRepository.getTokenByEmployeeId(emplId);
	}

}
