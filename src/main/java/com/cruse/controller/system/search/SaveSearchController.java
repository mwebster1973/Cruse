//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   SaveSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:48:28  $
//*
//******************************************************************************

package com.cruse.controller.system.search;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.butter.search.ButterCriteria;
import com.butter.search.ButterSearchView;
import com.butter.tag.table.TableUtil;
import com.butter.validation.ButterMessage;
import com.cruse.controller.CruseController;
import com.cruse.service.SystemService;

/**
 * This action is used to save a search criteria. It uses the
 * saveSearch method in the UserService
 */
@Controller
@RequestMapping("/saveSearch.htm")
public class SaveSearchController extends CruseController {

	
	@Autowired
	private SystemService systemService;

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String criteriaName = request.getParameter("criteriaName");
		ButterCriteria criteria = (ButterCriteria) request.getSession()
				.getAttribute(criteriaName);

		String searchName = request.getParameter("searchName");
		String tableId = request.getParameter("tableId");
		String viewName = request.getParameter("viewName");

		Collection<String> columns = getView(tableId, request);

		ButterSearchView searchViewDto = new ButterSearchView();
		searchViewDto.setCdsid(getUser(request).getUserId());
		searchViewDto.setTableId(tableId);
		if (columns!= null){
			searchViewDto.setColumns(new HashSet<String>(columns));
		}
		searchViewDto.setSearchName(searchName);
		searchViewDto.setCriteria(criteria);

		systemService.saveSearch(searchViewDto);
		
		ButterMessage message = new ButterMessage("message.search.saved");
		this.addConfirmationMessage(request, message);

		// reload the save searches
		Collection savedSearches = systemService.getSavedSearches(getUser(
				request).getUserId());
		request.getSession().setAttribute("savedSearches", savedSearches);

		RedirectView redirectView = new RedirectView(viewName);
		return new ModelAndView(redirectView);
	}

	/**
	 * Gets a collection of column names for the specified table id from the
	 * session
	 * 
	 * @param tableId
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Collection<String> getView(String tableId,
			HttpServletRequest request) {
		return (Collection<String>) request.getSession().getAttribute(
				TableUtil.getColumnsKey(tableId));
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

}
