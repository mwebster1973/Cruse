//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   UserSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:32  $
//*
//******************************************************************************

package com.cruse.controller.system.user;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.controller.CruseSearchController;
import com.cruse.domain.system.UserSearchCriteria;
import com.cruse.service.SystemService;

@Controller
@RequestMapping("/userSearch.htm")
public class UserSearchController extends CruseSearchController {

	/**
	 * Perform a search. If calling from the menu
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserSearchCriteria criteria = (UserSearchCriteria) request
				.getSession().getAttribute("userCriteria");

		if (StringUtils.isNotEmpty(request.getParameter("reset"))) {
			criteria = new UserSearchCriteria();
			request.getSession().setAttribute("userCriteria", criteria);
		}

		if (criteria == null) { // if no criteria then do the search again
			RedirectView view = new RedirectView("userSearchRequest.htm");
			return new ModelAndView(view);
		}

		Collection users = ((SystemService)getSystemService()).userSearch(criteria);

		// forward to the search results page.
		ModelAndView mv = new ModelAndView("system/userSearchResults");
		mv.addObject("users", users);
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		return mv;
	}
}