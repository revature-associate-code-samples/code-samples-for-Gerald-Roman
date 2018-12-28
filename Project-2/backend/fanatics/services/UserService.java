package com.fanatics.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanatics.models.User;
import com.fanatics.repository.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository repo;
	
	/**
	 * 
	 */
	public UserService() {
	}

	public List<User> getAll() {
		List<User> user = repo.findAll();

		return user;
	}

	public User getById(int id) {
		User user = repo.findOne(id);

		return user;
	}

	public User newUser(User u) {
		System.out.println(u);
		u.setIsAdmin(0);
		u.setIsVerified(0);
		u.setJoinDate(new Timestamp(System.currentTimeMillis()));
		System.out.println(u);
		User user = repo.save(u);
		System.out.println(user);
		return user;
	}
	
	public User getByUsername(String username) {
        User u = repo.findByUsernameLikeIgnoreCase(username);
        return u;
    }
    
    public User validateUser(User u, String password) {
        if (u.getPassword().equals(password)) {
            return u;
        }
        else {
            return null;
        }
    }

}
