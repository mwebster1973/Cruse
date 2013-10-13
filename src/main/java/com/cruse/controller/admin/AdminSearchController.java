package com.cruse.controller.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.controller.CruseSearchController;
import com.cruse.domain.admin.Admin;
import com.cruse.service.AdminService;


/**
 * Controller used to perform a search for configuration items. The configuration page does
 * not have a search criteria.
 */
@Controller
public class AdminSearchController{	

	@Autowired
	private AdminService adminService;
	
	@ModelAttribute("admins")
	public Collection getAdmins(HttpServletRequest request){
		return adminService.getAll(request.getParameter("domain"));
	}
	
	@ModelAttribute("screenName")
	public String getScreenName(HttpServletRequest request){
		return this.getScreenName(request.getParameter("domain"));
	}
	/**
	 * Perform a search. Moves to the search results page.
	 */
	@RequestMapping("/maintainAdmin.htm")
	protected String getView() throws Exception {
		return "admin/adminSearchResults";
	}
	
	private String getScreenName(String domain){
		StringBuffer buffy = new StringBuffer("Maintain ");
		buffy.append(Admin.getEntityPlural(domain));
		return buffy.toString();
	}
}