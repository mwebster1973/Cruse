//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   UserSearchRequestController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:30  $
//*
//******************************************************************************

package com.cruse.controller.system.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.butter.util.StringUtil;
import com.cruse.controller.CruseSearchRequestController;
import com.cruse.domain.system.UserSearchCriteria;

@Controller
@RequestMapping("/userSearchRequest.htm")
public class UserSearchRequestController extends CruseSearchRequestController {

	public UserSearchRequestController() {
		this.setFormView("system/userSearch");
		this.setCommandName("userCriteria");
		this.setSuccessView("userSearch.htm");
	}

	/**
	 * Retrieve the saved searches for the user screen.
	 */
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("savedSearches", getSavedSearches(request, "users"));
		return map;
	}

	/**
	 * Method called on request and search. If reset = true, then create a new backing object, otherwise get the object from the session
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {			
		
		UserSearchCriteria criteria= (UserSearchCriteria)
			request.getSession().getAttribute("userCriteria");
		
		if (StringUtils.isNotEmpty(request.getParameter("reset"))){
			request.getSession().removeAttribute("userCriteria");
			criteria = new UserSearchCriteria();	
		}
		
		if (criteria == null){
			criteria = new UserSearchCriteria();			
		}
		return criteria;
	}

	/**
	 * Puts the criteria on the session and forwards to the controller to do the search.
	 */
	protected ModelAndView onSubmitInternal(
			HttpServletRequest request,	HttpServletResponse response, Object command,	BindException errors)
			throws Exception {
		
		UserSearchCriteria criteria = (UserSearchCriteria)command;
		criteria.setSelectedRoles(StringUtil.truncateArray(criteria.getSelectedRoles()));
		request.getSession().setAttribute("userCriteria", criteria);
		
		return forwardToSearch();
	}
}