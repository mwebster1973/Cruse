//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   EditUserController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:32  $
//*
//******************************************************************************

package com.cruse.controller.system.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cruse.controller.CruseFormController;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;


/**
 * Controller used to edit a user.
 */
@Controller
@RequestMapping({"/editUser.htm","/addUser.htm"})
public class EditUserController extends CruseFormController {
	
	@Autowired
	private SystemService systemService;

	public EditUserController() {
	}	
	
	@ModelAttribute("currentUser")
	public User getEntity(@RequestParam("id") String cdsId){
		if (cdsId != null) {			
			return getSystemService().getUser(cdsId);
		}
		return new User();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "system/user";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String save(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("currentUser") User user,
			@RequestParam(value="id", required=false) String id,
			BindingResult errors)
			throws Exception {
		
		// Perform an update of an existing user or add a new user
		if (StringUtils.isEmpty(id)) {
			getSystemService()
					.insertUser(user, getUser(request).getUserId());
			this.addInsertMessage(request, "User");
		} else {
			getSystemService()
					.updateUser(user, getUser(request).getUserId());
			this.addUpdateMessage(request, "User");
		}

		if (StringUtils.isNotEmpty(request.getParameter("addAnother"))){
			return "redirect:addUser.htm";
		} 
			
		return "viewUser.htm?id="+user.getUserId();
		
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
