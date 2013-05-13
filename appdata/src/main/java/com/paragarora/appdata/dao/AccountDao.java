package com.paragarora.appdata.dao;

import com.paragarora.appdata.model.Account;

public interface AccountDao {
	void save(Account account);
	void update(Account account);
	void delete(Account account);
	Account findByAccountUnique(String accountUnique);
}
