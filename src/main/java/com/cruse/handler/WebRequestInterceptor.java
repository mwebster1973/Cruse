//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   WebRequestInterceptor.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:50  $
//*
//******************************************************************************

package com.cruse.handler;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cruse.Config;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;

public class WebRequestInterceptor extends HandlerInterceptorAdapter{
	
	private SystemService systemService;
	
	private Config config;
	
	/**
	 * Retrieve the user deta
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Collection savedSearches = (Collection)session.getAttribute("savedSearches");
		
		
		//If the clearTrail is true we need to clear out everything from the session
		if (request.getParameter("clearTrail")!=null && request.getParameter("clearTrail").equals("true")){
			session.invalidate();
			session = request.getSession(true);
			session.setAttribute("user", user);
			session.setAttribute("savedSearches", savedSearches);	
		}		

		// if displaying the home page, make sure the user is reloaded.
		if (request.getRequestURI().endsWith("Welcome.htm") && user!= null) {
			user = getSystemService().getUser(user.getUserId());
			session.setAttribute("user", user);
		}
		
		request.setAttribute("Config", config);
		if (user == null && StringUtils.isEmpty(request.getParameter("userId"))) {
//			user = getSystemService().getUser("MWEBSTER");
//			session.setAttribute("user", user);
			request.setAttribute("newUser", new User());
			throw new UserNotRegisteredException();
		}
			
		request.setAttribute("user", user);	
		return true;
	}
	
	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
