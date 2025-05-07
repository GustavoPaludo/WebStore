package com.server.core.user.model;

public class UserRegisterModel extends UserFormModel {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private Long fedidentification;

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

	public Long getFedidentification() {
		return fedidentification;
	}

	public void setFedidentification(Long fedidentification) {
		this.fedidentification = fedidentification;
	}
}
