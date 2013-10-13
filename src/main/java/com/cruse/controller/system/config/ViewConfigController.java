//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   ViewConfigController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:36  $
//*
//******************************************************************************

package com.cruse.controller.system.config;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.butter.config.ButterConfig;
import com.butter.tag.navigator.EntityKeyList;
import com.butter.util.StringUtil;
import com.cruse.controller.CruseViewController;

/**
 * Controller used to view a configuration item.
 */
@Controller
@RequestMapping("/viewConfig.htm")
public class ViewConfigController extends CruseViewController {
		
	/**
	 * Perform a view
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = request.getParameter("id");		
		if( ! StringUtil.isPopulated( param )){
			throw new InvalidParameterException("No id in ViewConfigController");
		}
		
		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("configs");
		if (col!= null){
			col.setSelectedRow(param);
		}
		
        ModelAndView mav = new ModelAndView("system/configView");
        ButterConfig config = getSystemService().getConfig(param);
        mav.addObject("config", config);
        
        super.addAuditHistory(mav, config);
        
        return mav; 
	}

}