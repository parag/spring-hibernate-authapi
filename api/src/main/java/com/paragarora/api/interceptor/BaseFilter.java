package com.paragarora.api.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paragarora.appdata.model.User;
import com.paragarora.appdata.service.UserService;
import com.paragarora.appdata.util.CurrentThread;

@Component("baseFilter")
public class BaseFilter implements Filter {

	@Autowired
	private UserService userService;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		CurrentThread.initialize();

		CurrentThread.getUserInfo().setLoggedIn(false);
		String sessionid = request.getParameter("sessionid");
		if (sessionid != null) {
			CurrentThread.getSessionInfo().setAuthSessionId(sessionid);
		}
		
		final String pathInfo = httpRequest.getPathInfo();
		if (pathInfo.contains("/a/") && !isLoggedIn()) {
			CurrentThread.getUserInfo().setLoggedIn(false);
			// this request requires authentication
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		try {
			// set current thread variables here
			chain.doFilter(request, response);
		} finally {
			CurrentThread.clean();
		}
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	protected boolean isLoggedIn() {
		String sessionid = CurrentThread.getSessionInfo().getAuthSessionId();
		if (sessionid != null) {
			User user = userService.findBySessionid(sessionid);
			//TODO set all current thread parameters here only
			if (user != null) {
				CurrentThread.getUserInfo().setLoggedIn(true);
				CurrentThread.getUserInfo().setUserUnique(user.getUserUnique());
				CurrentThread.getUser().cloneUser(user);
				return true;
			}
		}
		return false;
	}

}
