package com.company.company.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.company.encryption.CryptoConverter;
import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;
import com.company.company.model.service.EmployeeService;
import com.company.company.model.service.TokenService;

@RestController
public class TokenRestController {

	TokenService tokenService;
	
	EmployeeService employeeService;
	
	@Autowired
	public TokenRestController(TokenService tokenService, EmployeeService employeeService) {
		this.tokenService = tokenService;
		this.employeeService = employeeService;
	}

	@GetMapping(value = "token/{employeeId}")
	public Token getTokenByEmployeeId(@PathVariable("employeeId") int emplId) {
		
		Token token = tokenService.getTokenByEmployeeId(emplId);
		
		return token;
	}
	
	@GetMapping(value = "tokenForEmployeeId/{uuid}")
	public int getEmployeeIdOfToken(@PathVariable("uuid") String uuid) {
		
		int employeeId = tokenService.getEmployeeIdOfToken(uuid);
		
		return employeeId;
	}
	
	@GetMapping(value = "login/{email}/{password}")
	public Token checkCredentials(@PathVariable("email") String email, @PathVariable("password") String password) {
		
		Employee employee = employeeService.findByEmail(email);
		
		try {
			if(employee.getEmail().equals(email) && 
				CryptoConverter.decrypt(employee.getPassword()).equals(password)) {
				
				Token token = null;
				
				try {
					token = tokenService.getTokenByEmployeeId(employee.getEmplId());
					tokenService.touchToken(token.getUuId());
				}
				catch(Exception ex) {

					tokenService.createToken(employee);
					token = tokenService.getTokenByEmployeeId(employee.getEmplId());
				}

				return token;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			return null;
		}

	}
	
	@PutMapping(value = "touchToken/{uuid}")
	public String touchToken(@PathVariable("uuid") String uuid) {
		
		tokenService.touchToken(uuid);
		
		return "New Login";
	}
	
	@PostMapping(value = "token")
	public Token saveToken (@RequestBody Employee employee) {
		
		tokenService.createToken(employee);
		
		Token token = tokenService.getTokenByEmployeeId(employee.getEmplId());
		
		return token;
	}
	
	@DeleteMapping(value = "logout/{uuid}")
	public String logout(@PathVariable("uuid") String uuid) {
		
		tokenService.deleteToken(uuid);
		
		return "Token was destroyed!";
	}
}
