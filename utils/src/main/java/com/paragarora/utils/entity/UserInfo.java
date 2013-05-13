package com.paragarora.utils.entity;

public class UserInfo {
	private String userUnique;
	private boolean loggedIn = true;

	public String getUserUnique() {
		return userUnique;
	}

	public void setUserUnique(String string) {
		this.userUnique = string;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
