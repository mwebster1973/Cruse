package com.cruse.controller.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cruse.controller.CruseAjaxController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Area;
import com.cruse.service.AdminService;

@Controller
@RequestMapping("/areaChangeAjax.htm")
public class AreaChangeAjaxController extends CruseAjaxController{

	@Autowired
	private AdminService adminService;
	
	protected void attachChoices(HttpServletRequest request,
			String changedComponent, String[] selectedValues,
			StringBuffer outBuffer) throws Exception {
		
	   String dropDown = request.getParameter("dropDown");
	
	   Area  ad = (Area)adminService.get(dropDown, Admin.ENTITY_NAME_AREA);
	   
	   // retrieve the area object for the area. 
		
		String strOut = "<PCT>" + ad.getPct().getCode()+"</PCT>";
		outBuffer.append(strOut);
		
		return;
	}
}