//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   SaveDefaultViewInterceptor.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:48  $
//*
//******************************************************************************

package com.cruse.handler;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.butter.search.ButterSearchView;
import com.butter.tag.table.TableUtil;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;

public class SaveDefaultViewInterceptor extends HandlerInterceptorAdapter {

	private SystemService systemService;

	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (StringUtils
				.isNotEmpty(request.getParameter("TABLE_ACTION_RESTORE"))) {
			String tableId = request.getParameter("TABLE_ACTION_RESTORE");
			request.getSession().removeAttribute(
					TableUtil.getColumnsKey(tableId));
			request.getSession().setAttribute(
					TableUtil.getViewLoadedKey(tableId), "false");
		}
		// if saving
		if (StringUtils.isNotEmpty(request.getParameter("TABLE_ACTION_SAVE"))) {
			String tableId = request.getParameter("TABLE_ACTION_SAVE");
			Collection<String> columns = (Collection<String>) request.getSession()
					.getAttribute(TableUtil.getColumnsKey(tableId));
			if (columns != null) {
				save(request, request.getParameter("TABLE_ACTION_SAVE"),
						columns);
			} else {
				request.getSession().removeAttribute(
						TableUtil.getColumnsKey(tableId));
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public void save(HttpServletRequest request, String tableId,
			Collection columns) throws Exception {

		User user = (User) request.getSession()
				.getAttribute("user");

		// populate the Dto
		ButterSearchView userView = new ButterSearchView();
		userView.setTableId(tableId);
		userView.setCdsid(user.getUserId());
		userView.setColumns(new HashSet<String>(columns));

		// run the method in the service layer
		getSystemService().saveDefaultView(user.getUserId(), userView);
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

}
