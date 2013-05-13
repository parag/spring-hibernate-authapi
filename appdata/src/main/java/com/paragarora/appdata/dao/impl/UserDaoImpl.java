package com.paragarora.appdata.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.paragarora.appdata.dao.UserDao;
import com.paragarora.appdata.model.User;
import com.paragarora.appdata.util.CustomHibernateDaoSupport;

@Repository("userDao")
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao {

	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}
	
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public User findByEmail(String email) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from User where email=?", email);
		if(list.size() > 0)
			return (User)list.get(0);
		else
			return null;
	}

	public User findBySessionid(String sessionid) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from User where sessionid=?", sessionid);
		if(list.size() > 0)
			return (User)list.get(0);
		else
			return null;
	}

	public User findByUserUnique(String userUnique) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from User where userUnique=?", userUnique);
		if(list.size() > 0)
			return (User)list.get(0);
		else
			return null;
	}

}
