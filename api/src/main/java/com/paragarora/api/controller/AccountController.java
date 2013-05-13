package com.paragarora.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paragarora.appdata.model.Account;
import com.paragarora.appdata.model.User;
import com.paragarora.appdata.service.AccountService;
import com.paragarora.appdata.util.CurrentThread;
import com.paragarora.appdata.util.ResponseMap;
import com.paragarora.utils.entity.ResponseStatus;
import com.paragarora.utils.entity.UserUtils;
import com.paragarora.utils.entity.ValidationUtils;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	Map register(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullName) {
		ResponseMap map = new ResponseMap();

		ValidationUtils.validateUser(map, email, fullName, password);

		if (Integer.parseInt(map.get("status").toString()) != ResponseStatus.SUCCESS) {
			return map.parse();
		}

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullName(fullName);

		if (userService.findByEmail(email) != null) {
			map.put("status", ResponseStatus.GENERIC_ERROR);
			map.put("message", "$email.unique.false");
			return map.parse();
		}

		try {
			userService.register(user);
		} catch (NoSuchAlgorithmException e) {
			return reportAndGetExceptionMap(e);
		}

		map.put("message", "$user.register.success");
		map.put("user", user);
		return map.parse();
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	Map login(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		Date now = new Date();
		ResponseMap map = new ResponseMap();
		User user = userService.findByEmail(email);

		if (user != null) {
			String passToMatch;
			try {
				passToMatch = UserUtils.generateHashedPassword(user.getSalt(),
						password);

				if (passToMatch.equals(user.getPassword())) {
					user.setLastLogin(now);
					user.setSessionid(UserUtils.getSalt());
					userService.update(user);
					map.put("message", "$user.logged.in");
					map.put("user", user);
					return map.parse();
				} else {
					map.put("status", ResponseStatus.GENERIC_ERROR);
					map.put("message", "$user.password.failure");
					return map.parse();
				}
			} catch (NoSuchAlgorithmException e) {
				return reportAndGetExceptionMap(e);
			}
		}
		map.put("status", ResponseStatus.GENERIC_ERROR);
		map.put("message", "$user.email.not.found");
		return map.parse();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/a/add-account", method = RequestMethod.POST)
	public @ResponseBody
	Map addAccount(@RequestParam("sessionid") String sessionid,
			@RequestParam("name") String name,
			@RequestParam("website") String website,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone) {
		ResponseMap map = new ResponseMap();

		ValidationUtils.validateAccount(map, name, website, address, phone);
		if (Integer.parseInt(map.get("status").toString()) != ResponseStatus.SUCCESS) {
			return map.parse();
		}

		Account account = new Account();
		account.setAddress(address);
		account.setName(name);
		account.setPhone(phone);
		account.setWebsite(website);

		try {
			accountService.addAccount(account, CurrentThread.getUser());
		} catch (NoSuchAlgorithmException e) {
			return reportAndGetExceptionMap(e);
		}

		map.put("message", "$account.add.success");
		map.put("account", account);

		return map.parse();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/a/update-account", method = RequestMethod.POST)
	public @ResponseBody
	Map updateAccount(@RequestParam("sessionid") String sessionid,
			@RequestParam("account-unique") String accountUnique,
			@RequestParam("name") String name,
			@RequestParam("website") String website,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone) {
		ResponseMap map = new ResponseMap();

		Account account = accountService.findByAccountUnique(accountUnique);
		User user = CurrentThread.getUser();

		if (!user.getUserUnique().equals(account.getAdmin().getUserUnique())) {
			map.put("status", ResponseStatus.NOT_ACCESSIBLE);
			map.put("status", "$account.admin.invalid");
			return map.parse();
		}

		ValidationUtils.validateAccount(map, name, website, address, phone);
		if (Integer.parseInt(map.get("status").toString()) != ResponseStatus.SUCCESS) {
			return map.parse();
		}

		account.setAddress(address);
		account.setName(name);
		account.setPhone(phone);
		account.setWebsite(website);
		accountService.update(account);

		map.put("message", "$account.update.success");
		map.put("account", account);
		return map.parse();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/a/delete-account", method = RequestMethod.POST)
	public @ResponseBody
	Map deleteAccount(@RequestParam("sessionid") String sessionid,
			@RequestParam("account-unique") String accountUnique) {
		ResponseMap map = new ResponseMap();

		Account account = accountService.findByAccountUnique(accountUnique);
		User user = CurrentThread.getUser();

		if (!user.getUserUnique().equals(account.getAdmin().getUserUnique())) {
			map.put("status", ResponseStatus.NOT_ACCESSIBLE);
			map.put("status", "$account.admin.invalid");
			return map.parse();
		}
		accountService.delete(account);

		map.put("message", "$account.delete.success");
		return map.parse();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/a/link-account", method = RequestMethod.POST)
	public @ResponseBody
	Map linkAccount(@RequestParam("sessionid") String sessionid,
			@RequestParam("account-unique") String accountUnique,
			@RequestParam("email") String email) {
		ResponseMap map = new ResponseMap();

		Account account = accountService.findByAccountUnique(accountUnique);
		User user = CurrentThread.getUser();

		if (!user.getUserUnique().equals(account.getAdmin().getUserUnique())) {
			map.put("status", ResponseStatus.NOT_ACCESSIBLE);
			map.put("status", "$account.admin.invalid");
			return map.parse();
		}

		User u = userService.findByEmail(email);
		
		if(u==null) {
			map.put("status", ResponseStatus.INVALID_USER);
			map.put("message", "$user.email.not.found");
			return map.parse();
		}
		if(u.getAccount()!=null) {
			map.put("status", ResponseStatus.INVALID_USER);
			map.put("message", "$user.account.associated");
			return map.parse();
		}
			
		u.setAccount(account);
		userService.update(u);
		
		//TODO: SET NOTIFICATION TO THIS u that account is updated
		
		return map.parse();
	}
}
