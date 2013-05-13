package com.paragarora.appdata.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paragarora.appdata.dao.AccountDao;
import com.paragarora.appdata.model.Account;
import com.paragarora.appdata.model.User;
import com.paragarora.appdata.service.AccountService;
import com.paragarora.appdata.service.UserService;
import com.paragarora.utils.entity.UserUtils;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private UserService userService;

	public void save(Account account) {
		accountDao.save(account);
	}

	public void update(Account account) {
		accountDao.update(account);
	}

	public void delete(Account account) {
		accountDao.delete(account);
	}

	public Account findByAccountUnique(String accountUnique) {
		return accountDao.findByAccountUnique(accountUnique);
	}

	public void addAccount(Account account, User user) throws NoSuchAlgorithmException {
		Date now = new Date();
		account.setAccountUnique(UserUtils.getSalt());
		account.setAdmin(user);
		account.setCreatedAt(now);
		save(account);
		
		user.setAccount(account);
		userService.update(user);
	}

}
