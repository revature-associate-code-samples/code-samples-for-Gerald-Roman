package com.app.pojo;

public class User {
	private int userId;
	private String Username;
	private String Password;
	private String FirstName;
	private String LastName;
	private String Email;
	private int roleId;

	public User() {
	}

	public User(String firstname, String lastname, String Username, String Password) {
		super();
		this.FirstName = firstname;
		this.LastName = lastname;
		this.Username = Username;
		this.Password = Password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [ userId " + userId + ", Username " + Username + ", Password " + Password + ", FirstName "
				+ FirstName + ", Lastname " + LastName + ", Email " + Email + ", roleId " + roleId + "]";
	}

}
