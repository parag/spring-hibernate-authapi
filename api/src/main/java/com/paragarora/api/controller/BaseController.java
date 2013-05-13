package com.paragarora.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paragarora.appdata.service.UserService;
import com.paragarora.appdata.util.AppProperties;
import com.paragarora.appdata.util.ResponseMap;
import com.paragarora.utils.entity.ResponseStatus;

@Component
public class BaseController {
	@Autowired
	protected UserService userService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map reportAndGetExceptionMap(Exception e) {
		//TODO: report exception
		e.printStackTrace();
		
		Map map = new ResponseMap();
		map.put("status", ResponseStatus.EXCEPTION_OCCURRED);
		map.put("message", AppProperties.getProperty("app.unknown.exception"));
		return map;
	}
	
}
