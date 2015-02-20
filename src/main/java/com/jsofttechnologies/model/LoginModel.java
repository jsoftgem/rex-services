package com.jsofttechnologies.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jerico on 7/13/2014.
 */
@XmlRootElement
public class LoginModel {
	private String username;
	private String password;
	private Boolean remember;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRemember(Boolean remember) {
		this.remember = remember;
	}

	public Boolean getRemember() {
		return remember;
	}
}
