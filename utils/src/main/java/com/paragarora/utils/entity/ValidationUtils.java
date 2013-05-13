package com.paragarora.utils.entity;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;

public class ValidationUtils {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void validateUser(Map map, String email, String fullName,
			String password) {
		if (!validateEmail(email)) {
			map.put("status", ResponseStatus.GENERIC_ERROR);
			map.put("message", "$user.email.invalid");
		}

		if (!isValid(fullName, 4, 50, false)) {
			map.put("status", ResponseStatus.GENERIC_ERROR);
			map.put("message", "$user.fullname.invalid");
		}

		if (!isValid(password, 4, 18, false)) {
			map.put("status", ResponseStatus.GENERIC_ERROR);
			map.put("message", "$user.password.invalid");
		}
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validateEmail(final String hex) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}
	
	public static boolean isValidUrl(final String url) {
		String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
		UrlValidator urlValidator = new UrlValidator(schemes);
		return urlValidator.isValid(url);
	}

	public static boolean isValid(String value, int minSize, int maxSize,
			boolean nullable) {
		if (value == null) {
			return nullable;
		}
		if (value.length() < minSize) {
			return false;
		}
		if (maxSize > 0 && value.length() > maxSize) {
			return false;
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void validateAccount(Map map, String name, String website,
			String address, String phone) {
		if (!isValid(name, 4, 50, false)) {
			map.put("status", ResponseStatus.GENERIC_ERROR);
			map.put("message", "$account.name.invalid");
		}
		if (!isValidUrl(website)) {
			map.put("status", ResponseStatus.GENERIC_ERROR);
			map.put("message", "$account.url.invalid");
		}
	}
}
