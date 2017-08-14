package com.fantasy.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.UserDao;
import com.fantasy.springboot.domain.User;

/**
 * Created by fanjun on 2017/8/14.
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}

	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	public void delete(User user) {
		userDao.delete(user);
	}
	
	public void update(User user){
		userDao.update(user);
	}
	public List<User> findAll() {
		return userDao.findAll();
	}

}
