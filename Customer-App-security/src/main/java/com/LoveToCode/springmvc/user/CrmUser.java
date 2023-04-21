package com.LoveToCode.springmvc.user;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

public class CrmUser {

	@NotNull(message = "Is required")
	@Size(min = 2, message = "Must be more than two chars.")
	private String userName;

	@NotNull(message = "Is required")
	@Size(min = 2, message = "Must be more than two chars.")
	private String password;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
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

	public CrmUser(
			@NotNull(message = "Is required") @Size(min = 2, message = "Must be more than two chars.") String userName,
			@NotNull(message = "Is required") @Size(min = 2, message = "Must be more than two chars.") String password,
			List<GrantedAuthority> authorities) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public CrmUser() {

	}

}
