package com.paragarora.appdata.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.paragarora.appdata.dao.AccountDao;
import com.paragarora.appdata.model.Account;
import com.paragarora.appdata.util.CustomHibernateDaoSupport;

@Repository("accountDao")
public class AccountDaoImpl extends CustomHibernateDaoSupport implements AccountDao {

	public void save(Account account) {
		getHibernateTemplate().save(account);
	}

	public void update(Account account) {
		getHibernateTemplate().update(account);
	}

	public void delete(Account account) {
		getHibernateTemplate().delete(account);
	}

	public Account findByAccountUnique(String accountUnique) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from Account where accountUnique=?", accountUnique);
		if(list.size() > 0)
			return (Account)list.get(0);
		else
			return null;
	}

}
