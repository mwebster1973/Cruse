package com.cruse.controller.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.controller.CruseSearchController;
import com.cruse.service.AdminService;


/**
 * Controller used to perform a search for gps.
 */
@Controller
@RequestMapping("/maintainGp.htm")
public class GPSearchController extends CruseSearchController{	

	@Autowired
	private AdminService adminService;
	/**
	 * Perform a search. Moves to the search results page.
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection col = adminService.getAllGP();
		
		// forward to the search results page.
    	ModelAndView mv= new ModelAndView("admin/gpSearchResults");
    	mv.addObject("gps", col);
    	mv.addObject("screenName", "Maintain GPs");
		return mv;
	}	
}