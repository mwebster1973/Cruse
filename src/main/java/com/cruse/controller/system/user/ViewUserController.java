//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   ViewUserController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:34  $
//*
//******************************************************************************

package com.cruse.controller.system.user;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.butter.tag.navigator.EntityKeyList;
import com.butter.util.StringUtil;
import com.cruse.controller.CruseViewController;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;

@Controller
@RequestMapping("/viewUser.htm")
public class ViewUserController extends CruseViewController {
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = request.getParameter("id");		
		if( ! StringUtil.isPopulated( param )){
			throw new InvalidParameterException("No id in ViewUserController");
		}
		
		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("users");
		if (col!= null){
			col.setSelectedRow(param);
		}
		
        ModelAndView mav = new ModelAndView("system/userView");
        User dto= ((SystemService) getSystemService()).getUser(param);
        mav.addObject("currentUser", dto);        
        super.addAuditHistory(mav, dto);
        
        return mav; 
	}

}