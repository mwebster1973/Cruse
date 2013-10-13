//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   ConfigSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:36  $
//*
//******************************************************************************

package com.cruse.controller.system.config;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.controller.CruseSearchController;

/**
 * Controller used to perform a search for configuration items. The configuration page does
 * not have a search criteria.
 */
@Controller
@RequestMapping("/configSearch.htm")
public class ConfigSearchController extends CruseSearchController{	

	/**
	 * Perform a search. Moves to the search results page.
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection configs = getSystemService().getConfigs();
		
		
		// forward to the search results page.
    	ModelAndView mv= new ModelAndView("system/configSearchResults");
    	mv.addObject("configs", configs);
		return mv;
	}
}