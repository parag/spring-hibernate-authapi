package com.paragarora.utils.entity;

/**
 * The Class ResponseStatus.
 */
public class ResponseStatus {
	public static final int SUCCESS = 0;
	public static final int GENERIC_ERROR = 1;
	public static final int INVALID_USER = 2;
	public static final int LOGIN_REQUIRED = 3;
	public static final int VERIFY_PASSWORD_REQUIRED = 4;
	public static final int ACCOUNT_DEACTIVATED = 5;
	public static final int MANDATORY_UPDATE_REQUIRED = 6;
	public static final int OPTIONAL_UPDATE_AVAILABLE = 7;

	public static final int NOT_ACCESSIBLE = 14;

	public static final int PAYMENT_PROCESSING_ERROR = 15;

	public static final int EXCEPTION_OCCURRED = 1000;
}
