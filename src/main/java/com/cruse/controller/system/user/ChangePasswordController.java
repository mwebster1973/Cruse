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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.butter.validation.ButterMessage;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.system.NewPasswordRequest;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;


/**
 * Controller used to edit a user.
 */
@Controller
@RequestMapping("/changePassword.htm")
public class ChangePasswordController extends CruseFormController {
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("passwordRequest")
	public NewPasswordRequest getEntity(@RequestParam("id") String id){
		return new NewPasswordRequest(id);		
	}

	@ModelAttribute("currentUser")
	protected User getCurrentUser(@ModelAttribute("passwordRequest") NewPasswordRequest req ){
		return getSystemService().getUser(req.getUserId());
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "system/changePassword";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected String save(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id", required=false) String id,
			@ModelAttribute("passwordRequest") NewPasswordRequest password, BindingResult errors)
			throws Exception {

		if (password.getNewPassword().toUpperCase().equals(password.getVerifyNewPassword().toUpperCase())){
		
			this.getSystemService().changePasssword(password, (User)super.getUser(request));
			
			this.addConfirmationMessage(request, new ButterMessage("message.password.changed"));
			
		} else{
			password.setNewPassword(null);
			password.setVerifyNewPassword(null);
			
			errors.addError(new ObjectError("all", "New password and the verified password do not match. Please re-enter."));
			return "system/changePassword";
			
		}
		return "redirect:viewUser.htm?id="+id;		
	}
	
	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
