//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   NewsEditController.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:38  $
//*
//******************************************************************************

package com.cruse.controller.system.news;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.butter.domain.ButterReference;
import com.butter.news.ButterNews;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.referral.ReferralGroup;
import com.cruse.domain.system.User;
import com.cruse.service.SystemService;


@Controller
@RequestMapping({"/editNews.htm", "/addNews.htm"})
public class NewsEditController extends CruseFormController {
	
	@Autowired
	private SystemService systemService;
	
	public NewsEditController(){
		this.setCommandName("news");
		this.setSuccessView("viewNews.htm");
		this.setFormView("system/news");
	}
	
	@ModelAttribute("times")
	protected Collection<ButterReference> getTimesList(){
		return getTimes();
	}
	
	@ModelAttribute("news")
	public ButterNews getEntity(@RequestParam("newsId") String id,
			@RequestParam("clone") String clone){
		if (id != null) {
			ButterNews news = getSystemService().getNews(id);
			if (StringUtils.isNotEmpty(clone)) {
				news.setEndDate(null);
				news.setStartDate(null);
				news.setId(null);
			}
			return news;
		}
		return new ButterNews();
	}
	
	/**
	 * Retrieve a collection of times
	 */
	public Collection<ButterReference> getTimes() {
		ArrayList<ButterReference> arrayList = new ArrayList<ButterReference>();
		for (int hours = 0; hours < 24; hours++) {
			for (int minutes = 0; minutes < 4; minutes++) {

				NumberFormat form = NumberFormat.getInstance();
				form.setMinimumIntegerDigits(2);

				String time = form.format(hours) + ":"
						+ form.format(minutes * 15);
				arrayList.add(new ButterReference(time, time));
			}
		}
		return arrayList;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "system/news";
	}

	/**
	 * Perform a submit when editiing config
	 */
	@RequestMapping(method=RequestMethod.POST)
	protected String onSubmitInternal(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("news") ButterNews news, BindingResult errors)
			throws Exception {
		
		news.deriveEndDateTime();
		news.deriveStartDateTime();
		User user = (User) request.getSession().getAttribute("user");


		if (news.getId() != null) { // udpate
			getSystemService().updateNews(user.getUserId(), news);
			this.addUpdateMessage(request, "News");
			
		} else {
			getSystemService().insertNews(user.getUserId(), news);
			this.addInsertMessage(request, "News");
		}

		return "viewNews.htm?newsId="+news.getId();
		
	}


	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

}