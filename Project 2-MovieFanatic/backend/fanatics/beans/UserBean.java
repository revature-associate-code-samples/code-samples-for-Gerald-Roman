package com.fanatics.beans;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class UserBean {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int    id;
    private String bio;
    private String email;
    private Timestamp date;
    private int is_admin;
    
    
    
    public UserBean() {}



	public UserBean(String username, String password, String firstname, String lastname, int id, String bio,
			String email, Timestamp date, int is_admin) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.bio = bio;
		this.email = email;
		this.date = date;
		this.is_admin = is_admin;
	}



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



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}



	public int getIs_admin() {
		return is_admin;
	}



	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}



	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", id=" + id + ", bio=" + bio + ", email=" + email + ", date=" + date + ", is_admin="
				+ is_admin + "]";
	}

    

	
}
