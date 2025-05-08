package com.server.core.user.dao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
@NamedQueries({
	@NamedQuery(name = User.FIND_BY_ID, query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User {

	public static final String FIND_BY_ID = "User.FIND_BY_ID";
	public static final String FIND_BY_EMAIL = "User.FIND_BY_EMAIL";

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "SURNAME", nullable = true, length = 100)
	private String surname;

	@Column(name = "FEDERAL_IDENTIFICATION", nullable = false, length = 255)
	private Long federalIdentification;

	@Column(name = "LAST_UPDATED", nullable = false)
	private Date lastUpdated;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getFederalIdentification() {
		return federalIdentification;
	}

	public void setFederalIdentification(Long federalIdentification) {
		this.federalIdentification = federalIdentification;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
