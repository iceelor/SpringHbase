package com.sunnyvale.springhbase.model;

public class User {

	private String name;
	private String email;
	private String password;
	private String mode;
	
	public User() {
		name = "";
		email = "";
		password = "";
		mode = "O";
	}
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mode = "O";
	}
		
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password="
				+ password + ", mode=" + mode + "]";
	}
	
	
}
