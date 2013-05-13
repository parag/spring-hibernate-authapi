package com.paragarora.utils.entity;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UserUtils {


	public static String getSalt() throws NoSuchAlgorithmException {
		String uuid = UUID.randomUUID().toString();
		return HashUtils.MD5String(uuid);
	}

	public static String generateHashedPassword(String salt, String password)
			throws NoSuchAlgorithmException {
		String passHash = HashUtils.MD5String(password);
		String combString = salt + passHash;
		return HashUtils.MD5String(combString);
	}

	public static boolean verifyPassword(String salt, String password,
			String hashedPassword) throws NoSuchAlgorithmException {
		String passHash = generateHashedPassword(salt, password);
		if (passHash.equals(hashedPassword))
			return true;
		return false;
	}
}
