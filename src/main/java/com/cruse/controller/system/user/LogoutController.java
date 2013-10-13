package com.cruse.controller.system.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LogoutController{

	@RequestMapping(value="/logout.htm", method = RequestMethod.GET)
	protected String logout(HttpServletRequest request) throws Exception {

		request.getSession().removeAttribute("savedSearches");
		request.getSession().removeAttribute("user");
		return "redirect:Welcome.htm";
	}	
}

