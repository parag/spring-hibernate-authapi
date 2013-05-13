package com.paragarora.api.controller;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.paragarora.api.AbstractTestApi;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAccountController extends AbstractTestApi {

	@Autowired
	AccountController accountController;
	
	String email = "parag.arora@gmail.com";
	String password = "wordpass";
	String fullName = "Parag Arora";
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@Test
	public void testUserRegister() throws NoSuchAlgorithmException {
		Map map = accountController.register(email, password, fullName);
		assertTrue(true);
	}
}
