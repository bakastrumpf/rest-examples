package com.iktpreobuka.restExamplesOne.entities;

public class BankClientBean {
	protected Integer id;
	protected String name;
	protected String surname;
	protected String email;
	
	public BankClientBean() {
		super();
	}
	
	public BankClientBean(Integer id, String name, String surname, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}