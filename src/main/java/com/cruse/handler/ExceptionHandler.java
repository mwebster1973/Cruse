//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   ExceptionHandler.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:50  $
//*
//******************************************************************************

package com.cruse.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.butter.mail.SystemErrorMail;
import com.cruse.Config;


/**
 * Exception handler to be called from the request processor and from the reset method. 
 */
public class ExceptionHandler extends  SimpleMappingExceptionResolver {   
	
	private Config config;
	
	protected ModelAndView doResolveException(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView view = super.doResolveException(request, response, handler, ex);
		ex.printStackTrace();
		if (canSendEmail(ex,request)){
			sendEmail(ex,request);	
		}
		return view;
	}

	private boolean canSendEmail(Exception ex, HttpServletRequest request){		
		if (!config.isLocal()) {
			if (ex instanceof UserNotRegisteredException){
				return false;
			}
			return true;
		}
		return false;
	}

	public void sendEmail(Exception ex, HttpServletRequest request){
		try{
			SystemErrorMail.sendEmail(
					config, 
					request,
					config.getDatabase(), 
					ex);
			
		}catch (Exception e){
			ex.printStackTrace();
		}
	}
	
	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
}
