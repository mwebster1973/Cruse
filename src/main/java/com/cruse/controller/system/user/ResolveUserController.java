//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   ResolveUserController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:32  $
//*
//******************************************************************************

package com.cruse.controller.system.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cruse.controller.CruseFormController;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;

/**
 * Controller used to resolve the user. This is called when the user has typed the cdsid and then selects
 * the resolve button. This controller is only ever called as a post.
 */
@Controller
@RequestMapping("/resolveUser.htm")
public class ResolveUserController extends CruseFormController {

	@Autowired
	private SystemService systemService;

	public ResolveUserController() {
	}
	
	@ModelAttribute("currentUser")
	public User getEntity(){
		return new User();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "system/user";
	}

	@RequestMapping(method=RequestMethod.POST)
	protected String onSubmitInternal(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("currentUser") User user, 
			BindingResult errors)
			throws Exception {

		User dbUser = getSystemService().getUser(user.getUserId());

		// Check that an inserted cds id does not already exist in the CID
		// system...
		if (dbUser != null && dbUser.getUserId() != null) {
			errors.reject("error.duplicate.user");
			return "system/user";
		}

		return "viewUser.htm?currentUser="+user;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
