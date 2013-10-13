//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   RemoveSavedSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:48:28  $
//*
//******************************************************************************

package com.cruse.controller.system.search;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.butter.search.ButterSearchView;
import com.butter.validation.ButterMessage;
import com.cruse.controller.CruseController;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;

/**
 * Controller called to remove a saved search from the system. The controller is
 * configured with a series of views that the controller will return to after
 * performing the deltion.
 * 
 */
@Controller
@RequestMapping("/removeSavedSearch.htm")
public class RemoveSavedSearchController extends CruseController{

	/**
	 * System service used to remove the saved search.
	 */
	@Autowired
	private SystemService systemService;

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchId = request.getParameter("searchId");

		ButterSearchView search = null;
		try {
			search = systemService.loadSearch(searchId);
		} catch (ClassCastException ce) {
			// that could be a really old one. Let the deltion occur.
		}
		systemService.removeSearch(searchId);

		User user = (User)super.getUser(request);

		Collection savedSearches = getSystemService().getSavedSearches(user
				.getUserId().toUpperCase());
		// remove the saved search
		request.getSession().setAttribute("savedSearches", savedSearches);

		// if home parameter is passed then go back to home page.
		String homeParam = request.getParameter("home");
		String returningView = "";
		if (search == null
				|| (homeParam != null && homeParam.equalsIgnoreCase("true"))) {
			returningView = "Welcome.htm";
		// / FOR EACH Saved search add line to redirect to the search request
		// page.
		} else if (search.getTableId().equals("users")){
			returningView = "userSearchRequest.htm";
		}else if (search.getTableId().equals("ethnics")){
			returningView = "ethnicSearchRequest.htm";
		}else if (search.getTableId().equals("ethnicSummary")){
			returningView = "ethnicSearchRequest.htm";
		}else if (search.getTableId().equals("referrals")){
			returningView = "referralSearchRequest.htm";
		}else if (search.getTableId().equals("referralSummary")){
			returningView = "referralSearchRequest.htm";
		}		
		
		ButterMessage message = new ButterMessage("message.search.removed");
		this.addConfirmationMessage(request, message);
		return new ModelAndView(new RedirectView(returningView));

	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

}