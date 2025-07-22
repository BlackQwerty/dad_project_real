// src/main/java/com/utm/lostfound/dto/LoginRequest.java
package com.utm.lostfound.dto;

public class LoginRequest {
    private String username;
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;

    // Getters and Setters
}
