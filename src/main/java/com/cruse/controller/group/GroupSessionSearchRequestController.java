package com.cruse.controller.group;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cruse.Config;
import com.cruse.controller.CruseSearchRequestController;
import com.cruse.domain.group.GroupSessionSearchCriteria;


@Controller
@RequestMapping("/sessionSearchRequest.htm")
public class GroupSessionSearchRequestController extends CruseSearchRequestController {
	
	@Autowired Config config;

	public GroupSessionSearchRequestController() {
		this.setFormView("group/sessionSearch");
		this.setCommandName("sessionCriteria");
		this.setSuccessView("sessionSearch.htm");
	}
	
	 protected Map referenceData(HttpServletRequest request,
             Object command,
             Errors errors)
      throws Exception{
		 HashMap map = new HashMap();
		 Collection col1 = getSavedSearches(request, "sessions");
		 map.put("savedSearches", col1);
		 return map;
	 }

	 protected void initBinder(HttpServletRequest request,
	         ServletRequestDataBinder binder) throws Exception
	   {
	      String dateFormat = "dd/MM/yyyy";
	      SimpleDateFormat df = new SimpleDateFormat(dateFormat);
	      df.setLenient(true);
	      CustomDateEditor editor = new CustomDateEditor(
		            df, true){
	    	public String getAsText(){
	    		String res = super.getAsText();
	    		if (res== null || res.length()==0){
	    			return "dd/mm/yyyy";
	    		}
	    		return res;
	    	}
	    	
	    	public void setAsText(String text){
	    		if (text.equalsIgnoreCase("dd/mm/yyyy")){
	    			super.setAsText("");
	    			return;
	    		}
	    		super.setAsText(text);
	    	}
	    	  
	      };
	      
	      binder.registerCustomEditor(java.util.Date.class,editor);
	   }



	/**
	 * Method called on request and search. If reset = true, then create a new backing object, otherwise get the object from the session
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {			
		

		
		GroupSessionSearchCriteria criteria= (GroupSessionSearchCriteria)
			request.getSession().getAttribute("sessionCriteria");
		
		if (StringUtils.isNotEmpty(request.getParameter("reset"))){
			request.getSession().removeAttribute("sessionCriteria");
			criteria = new GroupSessionSearchCriteria();	
		}
		
		if (criteria == null){
			criteria = new GroupSessionSearchCriteria();			
		}
		return criteria;
	}
	
	protected ModelAndView onSubmitInternal(
			HttpServletRequest request,	HttpServletResponse response, Object command,	BindException errors)
			throws Exception {
		
		GroupSessionSearchCriteria criteria = (GroupSessionSearchCriteria)command;
		request.getSession().setAttribute("sessionCriteria", criteria);
		
		return forwardToSearch();
	}
}