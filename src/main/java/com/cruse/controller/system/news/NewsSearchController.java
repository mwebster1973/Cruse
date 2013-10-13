//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   NewsSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:36  $
//*
//******************************************************************************

package com.cruse.controller.system.news;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.controller.CruseSearchController;

/**
 * Controller used to perform a search on news.
 * @author mwebst28
 *
 */
@Controller
@RequestMapping("/newsSearch.htm")
public class NewsSearchController extends CruseSearchController{
	
	/**
	 * Perform a search. If calling from the menu
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection col = 
			getSystemService().getAllNews();
		
		// forward to the search results page.
    	ModelAndView mv= new ModelAndView("system/newsSearchResults");
    	mv.addObject("allNews", col);
		return mv;
	}
}