package com.programmeren4.turnahead.shared.dto;

import java.io.Serializable;

/**
 * Login
 * 
 */
public class LoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private Long ID;

	public LoginDTO() {
		super();
	}

	public LoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEMail() {
		return email;
	}

	public void setEMail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDTO object = (LoginDTO) obj;
		if (!this.getEMail().equals(object.getEMail()))
			return false;
		if (!this.getPassword().equals(object.getPassword()))
			return false;
		return true;
	}

}
