package com.paragarora.appdata.service.impl;


import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paragarora.appdata.dao.UserDao;
import com.paragarora.appdata.model.User;
import com.paragarora.appdata.service.UserService;
import com.paragarora.utils.entity.UserUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}
	
	public void delete(User user) {
		userDao.delete(user);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public void register(User user) throws NoSuchAlgorithmException {
		Date now = new Date();
		
		user.setSalt(UserUtils.getSalt());
		String hashedPass = UserUtils.generateHashedPassword(user.getSalt(), user.getPassword());
		user.setPassword(hashedPass);
		user.setSessionid(UserUtils.getSalt());
		user.setUserUnique(UserUtils.getSalt());
		user.setLastLogin(now);
		user.setCreatedAt(now);
		save(user);
	}

	public User findBySessionid(String sessionid) {
		return userDao.findBySessionid(sessionid);
	}

}
