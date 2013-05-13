package com.paragarora.appdata.util;

import com.paragarora.appdata.model.User;
import com.paragarora.utils.entity.DeviceType;
import com.paragarora.utils.entity.RequestInfo;
import com.paragarora.utils.entity.SessionInfo;
import com.paragarora.utils.entity.UserInfo;

public class CurrentThread {
	private static ThreadLocal<UserInfo> userInfos = new ThreadLocal<UserInfo>();
	private static ThreadLocal<RequestInfo> requestInfos = new ThreadLocal<RequestInfo>();
	private static ThreadLocal<SessionInfo> sessionInfos = new ThreadLocal<SessionInfo>();
	private static ThreadLocal<User> users = new ThreadLocal<User>();

	public static class ThreadContext {
		private final UserInfo userInfo;
		private final RequestInfo requestInfo;
		private final SessionInfo sessionInfo;
		private final User user;

		public ThreadContext(UserInfo userInfo, RequestInfo requestInfo,
				SessionInfo sessionInfo, User user) {
			super();
			this.userInfo = userInfo;
			this.requestInfo = requestInfo;
			this.sessionInfo = sessionInfo;
			this.user = user;
		}

		public UserInfo getUserInfo() {
			return userInfo;
		}

		public RequestInfo getRequestInfo() {
			return requestInfo;
		}

		public SessionInfo getSessionInfo() {
			return sessionInfo;
		}

		public User getUser() {
			return user;
		}
	}

	public static UserInfo getUserInfo() {
		UserInfo userInfo = userInfos.get();
		if (userInfo == null) {
			userInfo = new UserInfo();
			userInfos.set(userInfo);
		}
		return userInfo;
	}

	public static RequestInfo getRequestInfo() {
		RequestInfo requestInfo = requestInfos.get();
		if (requestInfo == null) {
			requestInfo = new RequestInfo();
			requestInfos.set(requestInfo);
		}
		return requestInfo;
	}

	public static SessionInfo getSessionInfo() {
		SessionInfo sessionInfo = sessionInfos.get();
		if (sessionInfo == null) {
			sessionInfo = new SessionInfo();
			sessionInfos.set(sessionInfo);
		}
		return sessionInfo;
	}

	public static User getUser() {
		User user = users.get();
		if (user == null) {
			user = new User();
			users.set(user);
		}
		return user;
	}

	public static void initialize() {
		userInfos.set(new UserInfo());
		requestInfos.set(new RequestInfo());
		sessionInfos.set(new SessionInfo());
		users.set(new User());
	}

	public static void clean() {
		userInfos.remove();
		requestInfos.remove();
		sessionInfos.remove();
		users.remove();
	}

	public static String getRegion() {
		String region = getRequestInfo().getRegion();
		return (region != null) ? region : "IN";
	}

	public static DeviceType getDeviceType() {
		String deviceType = getRequestInfo().getDeviceType();
		return DeviceType.fromValue(deviceType);
	}

	public static String getDeviceId() {
		return getRequestInfo().getDeviceId();
	}

	public static boolean isAuthenticated() {
		return getUserInfo().getUserUnique() != null;
	}

	public static ThreadContext takeSnapshot() {
		return new ThreadContext(getUserInfo(), getRequestInfo(),
				getSessionInfo(), getUser());
	}

	public static void restoreSnapshot(ThreadContext threadContext) {
		userInfos.set(threadContext.getUserInfo());
		requestInfos.set(threadContext.getRequestInfo());
		sessionInfos.set(threadContext.getSessionInfo());
		users.set(threadContext.getUser());
	}

}
