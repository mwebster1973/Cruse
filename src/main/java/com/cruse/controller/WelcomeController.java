//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   WelcomeController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:16  $
//*
//******************************************************************************

package com.cruse.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.butter.news.ButterNews;
import com.cruse.service.SystemService;
import com.cruse.controller.CruseAbstractController;

/**
 * Controller used by the front page. This is a basic controller that just
 * retrieves the news.
 * 
 */
@Controller
public class WelcomeController extends CruseAbstractController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/Welcome.htm")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("common/home");

		Collection<ButterNews> news = systemService.getCurrentNews();
		request.setAttribute("news", news);
		return mv;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
