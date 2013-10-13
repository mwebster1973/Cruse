package com.cruse.controller.admin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Pct;
import com.cruse.handler.editor.AdminEditor;
import com.cruse.service.AdminService;


/**
* Controller used to edit a user.
*/
@Controller
@RequestMapping({"editAdmin.htm","addAdmin.htm"})
public class AdminEditController extends CruseFormController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired private Config config;

	public AdminEditController() {
		this.setFormView("admin/admin");
		this.setSuccessView("viewAdmin.htm");
		this.setCommandName("admin");
	}	
	
	@InitBinder
	 private void initBinder(WebDataBinder binder) {
	     binder.registerCustomEditor(Pct.class, new AdminEditor(config, Admin.ENTITY_NAME_PCT));
	 }
	
	@ModelAttribute("pctList")
	protected Collection<Admin> getPctList(){
		return config.getAdminList(Admin.ENTITY_NAME_PCT);
	}
	
	@ModelAttribute("screenName")
	public String getScreenName(@RequestParam("domain") String domain){
		return Admin.getEntitySingular(domain);
	}
	
	@ModelAttribute("admin")
	public Admin getEntity(@RequestParam(value="id", required=false) String code,
			@RequestParam("domain") String entityName){
		if (code != null) {			
			return adminService.get(code, entityName);
		}
		return Admin.createAdmin(entityName);		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "admin/admin";
	}

	
	@RequestMapping(method=RequestMethod.POST)
	protected String save(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("admin") Admin admin, BindingResult errors)
			throws Exception {


		// If the user is not a current user then we are creating a new user so
		// we need to check to
		// see if the userid has been resolved.
		String code = request.getParameter("id");

		try{
			// Perform an update of an existing user or add a new user
			if (StringUtils.isEmpty(code)) {
				adminService.insert(admin, getUser(request).getUserId());
				this.addInsertMessage(request, Admin.getEntitySingular(admin.getEntityName()));
			} else {
				adminService.update(admin, getUser(request).getUserId());
				this.addUpdateMessage(request,  Admin.getEntitySingular(admin.getEntityName()));
			}
	
			if (StringUtils.isNotEmpty(request.getParameter("addAnother"))){
				return "redirect:addAdmin.htm?domain="+admin.getEntityName();
			} else{
				return "redirect:viewAdmin.htm?domain="+admin.getEntityName()+"&id="+admin.getCode();
			}
		}catch (DataIntegrityViolationException de){
			errors.reject("error.duplicate.code", new Object[]{Admin.getEntitySingular(admin.getEntityName())},
			"Unknown Error");
			return getView();
		}
	}


}
