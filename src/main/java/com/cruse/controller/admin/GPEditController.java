package com.cruse.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.GP;
import com.cruse.service.AdminService;

/**
* Controller used to edit a user.
*/
@Controller
@RequestMapping({"/editGp.htm","/addGp.htm"})
public class GPEditController extends CruseFormController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired private Config config;

	public GPEditController() {
		this.setFormView("admin/gp");
		this.setSuccessView("viewGp.htm");
		this.setCommandName("gp");
	}	
	
	@ModelAttribute("screenName")
	protected String getScreenName(){
		return "GP";
	}
	
	@ModelAttribute("gp")
	protected GP getEntity(@RequestParam(value="id", required=false) String id){
		if (id != null) {			
			return adminService.getGP(id);
		}
		return new GP();	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "admin/gp";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id",required=false) String id,
			@ModelAttribute("gp") GP gp, BindingResult errors){
		
		try{
			// Perform an update of an existing user or add a new user
			if (StringUtils.isEmpty(id)) {
				adminService.insertGP(gp, getUser(request).getUserId());
				this.addInsertMessage(request, "GP");
			} else {
				adminService.updateGP(gp, getUser(request).getUserId());
				this.addUpdateMessage(request,  "GP");
			}
	
			if (StringUtils.isNotEmpty(request.getParameter("addAnother"))){
				return "redirect:addGp.htm";
			} else{
				return "redirect:addGp.htm?id="+gp.getId()+"";
			}
		}catch (DataIntegrityViolationException de){
			ModelAndView view = this.getModelAndView(this.getFormView());
			errors.reject("error.duplicate.code", new Object[]{"Gp"},
			"Unknown Error");
			return "admin/gp";
		}
	}


}
