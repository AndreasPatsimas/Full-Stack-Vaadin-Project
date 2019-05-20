package com.company.company.model.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "token")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uuid")
	private String uuId;
	
	@NotNull
	@Column(name = "last_access_time")
	private Timestamp lastAccessTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Token() {
		this.lastAccessTime = new Timestamp(System.currentTimeMillis());
	}

	public Token(String uuId, @NotNull Timestamp lastAccessTime, Employee employee) {
		super();
		this.uuId = uuId;
		this.lastAccessTime = new Timestamp(System.currentTimeMillis());
		this.employee = employee;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public Timestamp getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Token [uuId=" + uuId + ", lastAccessTime=" + lastAccessTime + ", employee=" + employee + "]";
	}
	
	
}
