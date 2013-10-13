//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   RunSavedSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:48:30  $
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
import com.butter.tag.table.TableUtil;
import com.cruse.controller.CruseAbstractController;
import com.cruse.service.SystemService;


/**
*  Controller used to run a saved search
*/
@Controller
@RequestMapping("/runSavedSearch.htm")
public class RunSavedSearchController extends CruseAbstractController{

	@Autowired
	private SystemService systemService;

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchId = request.getParameter("searchId");
		
		ButterSearchView searchViewDto = systemService.loadSearch(searchId);
		
		if (searchViewDto!=null){
			// load columns
	      if ( searchViewDto.getColumns() != null){
	          loadView(searchViewDto.getTableId(), request, searchViewDto.getColumns());
	      }

	      String searchController = "Welcome.htm";
	      
	      // For each saved search set the criteria and send to the search controller.
	      // Load the search for the table id	      
	      if (searchViewDto.getTableId().equals("users")){
	    	request.getSession().setAttribute("userCriteria",searchViewDto.getCriteria());
	    	searchController = "userSearch.htm";
		  }
	      else if (searchViewDto.getTableId().equals("ethnics")){
	    	  request.getSession().setAttribute("ethnicCriteria",searchViewDto.getCriteria());
		      searchController = "ethnicSearch.htm";
		  }    
	      else if (searchViewDto.getTableId().equals("ethnicSummary")){
	    	  request.getSession().setAttribute("ethnicCriteria",searchViewDto.getCriteria());
		      searchController = "ethnicSummary.htm";
		  }    
	      else if (searchViewDto.getTableId().equals("referrals")){
	    	  request.getSession().setAttribute("referralCriteria",searchViewDto.getCriteria());
		      searchController = "referralSearch.htm";
		  }    
	      else if (searchViewDto.getTableId().equals("referralSummary")){
	    	  request.getSession().setAttribute("referralCriteria",searchViewDto.getCriteria());
		      searchController = "referralSummary.htm";
		  } 
	      
	      return new ModelAndView(new RedirectView(searchController));
		}
		return null;		
	}
	
	/**
	 * This method sets the columns and tableId.viewLoaded=true to the session
	 * @param tableId
	 * @param request
	 * @param columns
	 */
	private void loadView(String tableId, HttpServletRequest request, Collection<String> columns){
		
		request.getSession().setAttribute(TableUtil.getColumnsKey(tableId), columns );
		request.getSession().setAttribute(TableUtil.getViewLoadedKey(tableId), "true");
	}
	
	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
