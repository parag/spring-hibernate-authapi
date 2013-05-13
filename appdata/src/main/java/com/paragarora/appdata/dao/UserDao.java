package com.paragarora.appdata.dao;

import com.paragarora.appdata.model.User;

public interface UserDao {
	void save(User user);
	void update(User user);
	void delete(User user);
	User findByEmail(String email);
	User findBySessionid(String sessionid);
	User findByUserUnique(String userUnique);
}
