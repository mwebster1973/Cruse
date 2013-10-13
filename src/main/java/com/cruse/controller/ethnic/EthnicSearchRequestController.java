package com.cruse.controller.ethnic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.butter.domain.ButterReference;
import com.butter.util.StringUtil;
import com.cruse.Config;
import com.cruse.controller.CruseSearchRequestController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.ethnic.EthnicSearchCriteria;

@Controller
@RequestMapping("/ethnicSearchRequest.htm")
public class EthnicSearchRequestController extends CruseSearchRequestController {
	
	@Autowired Config config;

	public EthnicSearchRequestController() {
		this.setFormView("ethnic/ethnicSearch");
		this.setCommandName("ethnicCriteria");
		this.setSuccessView("ethnicSearch.htm");
	}
	
	@ModelAttribute("attributeList")
	public ArrayList<ButterReference> getAttributes(){
		 ArrayList<ButterReference> attributeList=  new ArrayList<ButterReference>();
		 attributeList.add(new ButterReference(Admin.ENTITY_NAME_AREA, Admin.getEntitySingular(Admin.ENTITY_NAME_AREA)));
		 attributeList.add(new ButterReference(Admin.ENTITY_NAME_AGE, Admin.getEntitySingular(Admin.ENTITY_NAME_AGE)));
		 attributeList.add(new ButterReference("carer", "Carer"));
		 attributeList.add(new ButterReference("considerDisabled", "Consider themself Disabled"));
		 attributeList.add(new ButterReference(Admin.ENTITY_NAME_ETHNIC_BACK, Admin.getEntitySingular(Admin.ENTITY_NAME_ETHNIC_BACK))); 
		 attributeList.add(new ButterReference(Admin.ENTITY_NAME_GENDER, Admin.getEntitySingular(Admin.ENTITY_NAME_GENDER)));
		 attributeList.add(new ButterReference(Admin.ENTITY_NAME_PCT, Admin.getEntitySingular(Admin.ENTITY_NAME_PCT))); 
		 attributeList.add(new ButterReference(Admin.ENTITY_NAME_RELIGEON, Admin.getEntitySingular(Admin.ENTITY_NAME_RELIGEON)));
		 attributeList.add(new ButterReference("registeredDisabled", "Registerd Disabled"));
		 return attributeList;
	}
	
	@ModelAttribute("savedSearches")
	public Collection getSavedSearches(HttpServletRequest request){
		 Collection col1 = getSavedSearches(request, "ethnics");
		 Collection col2 = getSavedSearches(request, "ethnicSummary");
		 col1.addAll(col2);
		 return col1;
	}
	
	@InitBinder
	 private void initBinder(WebDataBinder binder) {
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
	@ModelAttribute("ethnicCriteria")
	protected EthnicSearchCriteria formBackingObject(HttpServletRequest request)
			throws Exception {			
		
		EthnicSearchCriteria criteria= (EthnicSearchCriteria)
			request.getSession().getAttribute("ethnicCriteria");
		
		if (StringUtils.isNotEmpty(request.getParameter("reset"))){
			request.getSession().removeAttribute("ethnicCriteria");
			criteria = new EthnicSearchCriteria();	
		}
		
		if (criteria == null){
			criteria = new EthnicSearchCriteria();			
		}
		return criteria;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public String getView(){
		return "ethnic/ethnicSearch";
	}

	/**
	 * Puts the criteria on the session and forwards to the controller to do the search.
	 */
	@RequestMapping(method= RequestMethod.POST)
	protected ModelAndView onSubmitInternal(
			HttpServletRequest request,	HttpServletResponse response, 
			@ModelAttribute("ethnicCriteria") EthnicSearchCriteria criteria ,	BindException errors)
			throws Exception {
		
		criteria.setAttributeSelections(StringUtil.truncateArray(criteria.getAttributeSelections()));
		criteria.setAttribute(null);
		
		request.getSession().setAttribute("ethnicCriteria", criteria);
	
		if (StringUtils.isEmpty(request.getParameter("summary"))){
			return forwardToSearch();
		}
		ModelAndView mv = new ModelAndView(new RedirectView("ethnicSummary.htm"));
		return mv;
	}
}