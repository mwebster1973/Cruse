//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   NewsViewController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:38  $
//*
//******************************************************************************

package com.cruse.controller.system.news;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.butter.news.ButterNews;
import com.butter.tag.navigator.EntityKeyList;
import com.butter.util.StringUtil;
import com.cruse.controller.CruseViewController;



@Controller
@RequestMapping("/viewNews.htm")
public class NewsViewController extends CruseViewController {


	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = request.getParameter("newsId");		
		if( ! StringUtil.isPopulated( param )){
			throw new InvalidParameterException("No newsId in NewsViewController");
		}
		
		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("allNews");
		if (col!= null){
			col.setSelectedRow(param);
		}
		
        ModelAndView mav = new ModelAndView("system/newsView");
        ButterNews dto= getSystemService().getNews(param);
        mav.addObject("news", dto);        
        super.addAuditHistory(mav, dto);
        
        return mav; 
	}
}