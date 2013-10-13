//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   CruseSearchRequestController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:14  $
//*
//******************************************************************************
package com.cruse.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.butter.controller.ButterSearchRequestController;
import com.cruse.persistence.DataManager;

/**
 * Controller to be used for the base for capturing the critieria for a search
 */
public class CruseSearchRequestController extends ButterSearchRequestController{
	
	@Autowired private DataManager dataManager;	
	

	
	public DataManager getDataManager() {
		return dataManager;
	}

}
