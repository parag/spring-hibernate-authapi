package com.paragarora.appdata.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.paragarora.appdata.AbstractTestAppdata;
import com.paragarora.appdata.model.Account;
import com.paragarora.appdata.model.User;
import com.paragarora.appdata.service.AccountService;
import com.paragarora.appdata.service.UserService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDao extends AbstractTestAppdata {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;

	String email = "parag@parag.com";
	String accountUnique = "DUMMY";
	String userUnique = "UNIQUE";
	/**
	 * Rigourous Test. Clean test should be performed for consistency of unit test
	 */
	@Test
	public void testUserCrud() {
		User user = new User();
		user.setFullName("Test");
		user.setEmail(email);
		user.setUserUnique(userUnique);
		user.setPassword("somepass");
		user.setSalt("salt");
		user.setCreatedAt(new Date());
		user.setSessionid("sesison");
		user.setLastLogin(new Date());
		userService.save(user);

		User user2 = userService.findByEmail(email);
		if (user2.getId() != user.getId()) {
			assertEquals("findByEmail failed", user.getId(), user2.getId());
		}
		userService.delete(user);
		assertTrue(true);
	}
	
	@Test
	public void testAccountWithUserCrud() {
		User user = new User();
		user.setFullName("Test");
		user.setEmail(email);
		user.setUserUnique(userUnique);
		user.setPassword("somepass");
		user.setSalt("salt");
		user.setCreatedAt(new Date());
		user.setSessionid("sesison");
		user.setLastLogin(new Date());
		userService.save(user);

		User user2 = userService.findByEmail(email);
		if (user2.getId() != user.getId()) {
			assertEquals("findByEmail failed", user.getId(), user2.getId());
		}
		user2 = null;
		
		Account account = new Account();
		account.setAddress("some adrrsss");
		account.setCreatedAt(new Date());
		account.setAccountUnique(accountUnique);
		account.setName("NAME");
		account.setPhone("9999");
		account.setWebsite("website.com");
		account.setAdmin(user);
		accountService.save(account);
		
		Account account2 = accountService.findByAccountUnique(accountUnique);
		if(account2.getId() != account.getId()) {
			assertEquals("findByAccountId failed", account.getId(), account2.getId());
		}
		account2 = null;
		
		user.setAccount(account);
		userService.update(user);
		
		userService.delete(user);
		accountService.delete(account);
		assertTrue(true);
	}
	
	@Test
	public void cleanTests() {
		try {
			Account account = accountService.findByAccountUnique(accountUnique);
			if(account!=null)
				accountService.delete(account);
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			User user = userService.findByEmail(email);
			if(user!=null)
				userService.delete(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}
}
