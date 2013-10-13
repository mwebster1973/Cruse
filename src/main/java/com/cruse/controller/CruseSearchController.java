//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   CruseSearchController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:18  $
//*
//******************************************************************************
package com.cruse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.butter.controller.ButterSearchController;
import com.butter.search.ButterSearchView;
import com.butter.service.SystemService;
import com.butter.tag.table.TableUtil;

/**
 * Abstract search controller to be used when performing a search. This should be used for complex searches
 * where the table columns can be customised and there is a criteria.
 */
public abstract class CruseSearchController extends ButterSearchController{

	public void loadView(HttpServletRequest request,  String tableId){
		if (TableUtil.canLoadView(request, tableId)){
			String user = super.getUserId(request);
			ButterSearchView view = getSystemService().loadDefaultView(user, tableId);
			if (view!= null){
				TableUtil.displayView(request, tableId, view.getColumns());
			}
		}
	}
}
