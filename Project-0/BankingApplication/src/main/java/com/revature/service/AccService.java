package com.revature.service;

import java.util.List;

import com.revature.dao.TypeDao;
import com.revature.dao.AccDao;
import com.revature.dao.DAO;
import com.revature.dao.userDao;
import com.revature.pojos.Account;
import com.revature.pojos.User;

public class AccService {
	static userDao userDao = new userDao();
	static AccDao accDao = new AccDao();

	public static User newUserAccount(String Firstname, String Lastname, String Username, String Password) {
		User u = new User(Firstname, Lastname, Username, Password);
		userDao.save(u);
		return u;
	}

	public Account makeAccount(Account a) {
		accDao.save(a);
		return a;
	}

	public List<User> findAllUsers() {
		return userDao.findAll();
	}
}
