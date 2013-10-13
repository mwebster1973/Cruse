//****************************************************************
//* Copyright (c) 2004 Ford Motor Company. All Rights Reserved.
//*
//* $Workfile:   CruseAjaxController.java  $
//* $Revision:   1.0  $
//* $Author:   mwebst28  $
//* $Date:   Oct 29 2009 14:50:16  $
//*
//*****************************************************************

package com.cruse.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.butter.controller.ButterAjaxController;
import com.cruse.persistence.DataManager;

/**
* Base class to be used for actions that implement Ajax based functionality.
* Ajax is the mechanism for asynchronous calls from a web page. The mechanism
* is used to provide dynamic data driven content to a normally static page. See
* <a href="http://en.wikipedia.org/wiki/AJAX"> wikipedia</a> for more
* information. <br>
* This base ajax action is to be used for populating dependent lists.
*/ 
public abstract class CruseAjaxController extends ButterAjaxController{
	
	@Autowired private DataManager dataManager;

	protected DataManager getDataManager() {
		return dataManager;
	}

	protected void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	

	
	
}
