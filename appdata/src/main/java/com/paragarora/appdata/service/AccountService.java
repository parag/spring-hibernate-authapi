package com.paragarora.appdata.service;

import java.security.NoSuchAlgorithmException;

import com.paragarora.appdata.model.Account;
import com.paragarora.appdata.model.User;

public interface AccountService {
	void save(Account account);
	void update(Account account);
	void delete(Account account);
	Account findByAccountUnique(String accountUnique);
	void addAccount(Account account, User user) throws NoSuchAlgorithmException;
}
