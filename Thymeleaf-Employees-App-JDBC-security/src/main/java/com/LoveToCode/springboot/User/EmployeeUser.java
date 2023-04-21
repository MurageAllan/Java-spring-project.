package com.LoveToCode.springboot.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeUser {

	@NotNull(message = "Is required")
	@Size(min = 2, message = "Must be more than two characters")
	private String userName;

	@NotNull(message = "Is required")
	@Size(min = 2, message = "Must be more than two characters")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

	public EmployeeUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public EmployeeUser() {

	}

}
