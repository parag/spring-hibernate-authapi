package com.paragarora.appdata.service;

import java.security.NoSuchAlgorithmException;

import com.paragarora.appdata.model.User;

public interface UserService {
	void save(User user);
	void update(User user);
	void delete(User user);
	User findByEmail(String email);
	User findBySessionid(String sessionid);
	void register(User user) throws NoSuchAlgorithmException;
}
