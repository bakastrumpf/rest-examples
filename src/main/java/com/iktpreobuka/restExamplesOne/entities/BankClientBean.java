package com.iktpreobuka.restExamplesOne.entities;

public class BankClientBean {
	protected Integer id;
	protected String name;
	protected String surname;
	protected String email;
	protected String dob;
	protected String bonitet;
	
	public BankClientBean() {
		super();
	}

	public BankClientBean(Integer id, String name, String surname, String email, String dob, String bonitet) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dob = dob;
		this.bonitet = bonitet;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBonitet() {
		return bonitet;
	}

	public void setBonitet(String bonitet) {
		this.bonitet = bonitet;
	}
	
	
	
}