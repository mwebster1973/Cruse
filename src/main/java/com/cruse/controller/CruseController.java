package com.cruse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.butter.controller.ButterController;
import com.butter.domain.ButterUser;
import com.butter.validation.ButterMessage;

public class CruseController extends ButterController {


	/**
	 * Retrieve the id of the logged in user
	 * @param req	The request object.
	 * @return		The id of the user
	 */
	public final String getUserId(HttpServletRequest req){
		return this.getUser(req).getUserId();
	}
	
	/**
	 * Display a confirmation message to the user. 
	 * @param request
	 * @param message
	 */
	protected void addConfirmationMessage(HttpServletRequest request,ButterMessage message){
		request.getSession().setAttribute("confirmation", message);
	}
	
	/**
	 * Add an error message.
	 */
	protected void addErrorMessage(HttpServletRequest request,ButterMessage message){
		request.getSession().setAttribute("errorMessage", message);
	}
}
