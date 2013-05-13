package com.paragarora.appdata.util;

import java.util.HashMap;
import java.util.Map;

import com.paragarora.utils.entity.ResponseStatus;

public class ResponseMap extends HashMap<String, Object> {
	private static final long serialVersionUID = -8189016850590458593L;

	public ResponseMap() {
		this.put("status", ResponseStatus.SUCCESS);
	}
	
	public void setToAuthenticate() {
		this.put("status", ResponseStatus.LOGIN_REQUIRED);
		this.put("message", "user.login.required");
	}
	
	@SuppressWarnings("rawtypes")
	public Map parse() {
		if(!this.containsKey("message")) {
			this.put("status", AppProperties.getProperty("app.unknown.message"));
		}
		String message = this.get("message").toString();
		
		if(message.startsWith("$")) {
			message = message.substring(1);
			message = AppProperties.getProperty(message);
			this.put("message", message);
		}
		return this;
	}
}
