//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   RefreshConfigController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:38  $
//*
//******************************************************************************

package com.cruse.controller.system.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.Config;
import com.cruse.controller.CruseAbstractController;

/**
 * Refresh the configuraiton object.
 */
@Controller
@RequestMapping("/refreshConfig.htm")
public class RefreshConfigController extends CruseAbstractController{
	
	@Autowired
	private Config config;

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	ModelAndView mv= new ModelAndView(new RedirectView("Welcome.htm"));
		config.refresh();
		return mv;
	}
	
	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}