package com.cruse.controller.system.user;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cruse.domain.system.User;
import com.cruse.service.SystemService;


@Controller
@RequestMapping("/login.htm")
public class LoginController {

	@Autowired
	private SystemService systemService;

	public LoginController() {
//		this.setFormView("common/home");
//		this.setSuccessView("Welcome.htm");
//		this.setCommandName("newUser");
	}	

	@ModelAttribute("newUser")
	public Object formBackingObject(HttpServletRequest request)
			throws Exception {
		return new User();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmitInternal(HttpServletRequest request,
			HttpServletResponse response, @Validated @ModelAttribute("newUser") User user, BindingResult errors)
			throws Exception {

		// if user or password have not been entered then complain.
		if (StringUtils.isEmpty(user.getUniqueIdentifier())|| StringUtils.isEmpty(user.getPassword()))
		{
			errors.reject("error.empty.username.password");
			return "common/home";
		}
		
		User dbUser = getSystemService().getUser(user.getUniqueIdentifier());
		if (dbUser== null||!dbUser.getPassword().equalsIgnoreCase(user.getPassword())){
			errors.reject("error.invalid.username.password");
			return "common/home";
		}
		
		request.getSession().setAttribute("user", dbUser);
		Collection savedSearches = getSystemService().getSavedSearches(dbUser.getUniqueIdentifier());
		request.getSession().setAttribute("savedSearches", savedSearches);

		return "redirect:Welcome.htm";
	}
	
	
	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}

