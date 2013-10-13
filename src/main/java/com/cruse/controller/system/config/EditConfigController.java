//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   EditConfigController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:36  $
//*
//******************************************************************************

package com.cruse.controller.system.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.butter.config.ButterConfig;
import com.butter.news.ButterNews;
import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.service.SystemService;

/**
 * Controller used to edit a configuration item. The controller would be used when 
 * going into the form page, and when the form page is submitted. The type of request
 * (ie get or post) determine the flow within the configuration.
 * 
 * The ViewConfigController is extended to benefit from the functionality to retrieve a 
 * config item based upon a parameter passed in.
 *
 */
@Controller
@RequestMapping({"/editConfig.htm","/addConfig.htm"})
public class EditConfigController extends CruseFormController {
	
	@Autowired
	private Config config;
	
	@Autowired
	private SystemService systemService;
	
	public EditConfigController() {	
	}
	
	@ModelAttribute("config")
	private ButterConfig getEntity(@RequestParam("id") String id){
		if (id != null) {
			return getSystemService().getConfig(id);
		}
		ButterConfig config = new ButterConfig();
		return config;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "system/config";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("config") ButterConfig config,
			@RequestParam("id") String id,
			BindingResult errors)
			throws Exception {
		
		try {
			if (id == null) { // insert
				getSystemService().insertConfig(config,
						getUser(request).getUserId());
				this.addInsertMessage(request, "Configuration");				
			} else {
				getSystemService().updateConfig(config,
						getUser(request).getUserId());
				this.addUpdateMessage(request, "Configuration");
			}
		} catch (DataIntegrityViolationException dao){
			errors.reject("error.test",new Object[]{"test"},"Coundn't find it"); 
			errors.reject("error.test2",new Object[]{"test"},"Coundn't find it2");
			return "system/config";
		}

		this.getConfig().refresh();
		return "redirect:viewConfig.htm?id"+config.getConfigParam();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
