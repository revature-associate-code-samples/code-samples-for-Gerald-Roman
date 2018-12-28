package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.ReimbursementDao;
import com.app.dao.UserDao;
import com.app.pojo.Reimbursement;
import com.app.pojo.User;

public class UserService {
	static UserDao userDao = new UserDao();
	static ReimbursementDao reimbursementDao = new ReimbursementDao();
	static ArrayList<User> users = new ArrayList<User>();

	public static User newUserAccount(String Firstname, String Lastname, String Username, String Password) {
		User u = new User(Firstname, Lastname, Username, Password);
		userDao.save(u);
		return u;
	}
	public static User getUser(int userId) {
		return userDao.findById(userId);
	}

	public Reimbursement makeReimbursement(Reimbursement r) {
		reimbursementDao.save(r);
		return r;
	}

	public List<User> findAllUsers() {
		return userDao.findAll();
	}
	public User getByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public User validateUser(String username, String password) {
		User u = getByUsername(username);
		if (u == null) {
			return null;
		} else if (u.getPassword().equals(password)) {
			return u;
		} else {
			return null;
		}
	}
}
