package com.cruse.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.butter.tag.navigator.EntityKeyList;
import com.cruse.controller.CruseViewController;
import com.cruse.domain.admin.Admin;
import com.cruse.service.AdminService;

@Controller
@RequestMapping("/viewAdmin.htm")
public class AdminViewController extends CruseViewController {
	
	@Autowired
	private AdminService adminService;
	
	@ModelAttribute("admin")
	public Admin getDto(@RequestParam("id") String id, 
			@RequestParam("domain") String domain){
        return adminService.get(id, domain);
	}
	
	@ModelAttribute("entitySingular")
	public String getEntitySinglular(@RequestParam("id") String id, 
			@RequestParam("domain") String domain){
		return Admin.getEntitySingular(domain);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(@ModelAttribute("admin") Admin admin,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("admins");
		if (col!= null){
			col.setSelectedRow(admin.getUniqueIdentifier());
		}
		
        ModelAndView mav = new ModelAndView("admin/adminView");
        super.addAuditHistory(mav, admin);        
        return mav; 
	}
}