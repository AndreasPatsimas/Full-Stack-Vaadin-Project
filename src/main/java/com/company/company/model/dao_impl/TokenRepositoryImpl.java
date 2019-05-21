package com.company.company.model.dao_impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.company.company.model.dao.TokenRepository;
import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Token;

public class TokenRepositoryImpl implements TokenRepository {

	@Autowired
	private TokenRepository tokenRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	

	@Override
	@Transactional
	public void createToken(String uuid, Employee employee) {
		
		entityManager.createNativeQuery("INSERT INTO token (employee_id, uuid, last_access_time) VALUES (?,?,now())")
	      .setParameter(1, employee.getEmplId())
	      .setParameter(2, uuid)
	      .executeUpdate();
	}

	@Override
	@Transactional
	public void touchToken(String uuid) {
		
		entityManager.createNativeQuery("UPDATE token SET last_access_time = now() WHERE uuid = ?")
	      .setParameter(1, uuid)
	      .executeUpdate();
	}
	
	@Override
	public int getEmployeeIdOfToken(String uuid) {
		
		String sql = "SELECT employee_id FROM token WHERE uuid = ?";
		
		Query query = entityManager.createNativeQuery(sql, Token.class);
		
		query.setParameter(1, uuid);
		
		int employeeId = (Integer) query.getSingleResult();
		
		return employeeId;
	}
	
	@Override
	public Token getTokenByEmployeeId(int emplId) {
		
		String sql = "SELECT * FROM token WHERE employee_id = ?";
		
		Query query = entityManager.createNativeQuery(sql, Token.class);
		
		query.setParameter(1, emplId);
		
		Token token = (Token) query.getSingleResult();
		
		return token;
	}

	/*@Override
	@Transactional
	public Token getTokenByEmployeeId(int emplId) {
		
		//Token token = entityManager.find(Token.class, emplId);
		
		entityManager.createNativeQuery("SELECT * FROM token WHERE employee_id = ?")
	      .setParameter(1, emplId)
	      .getSingleResult();
		
		return null;
	}*/

	@Override
	public List<Token> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Token> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Token> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Token> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Token> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Token> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Token> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Token> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Token> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Token> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Token> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Token entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Token> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Token> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Token> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Token> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Token> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}


}
