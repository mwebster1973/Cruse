//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   CruseFormController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:14  $
//*
//******************************************************************************
package com.cruse.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.butter.controller.ButterFormController;
import com.cruse.persistence.DataManager;

public abstract class CruseFormController extends ButterFormController {
	
	@Autowired 
	private DataManager dataManager;

	
	public DataManager getDataManager() {
		return dataManager;
	}
	
	 
	 public abstract String getView();
	 
	 protected final ModelAndView onSubmitInternal(HttpServletRequest request,
				HttpServletResponse response, Object command, BindException errors)
				throws Exception{
		 return null;
	 }

}
