package com.cruse.controller.referral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
import com.cruse.domain.referral.ReferralSearchCriteria;
import com.cruse.domain.system.User;

@Controller

public class ReferralSearchRequestController extends CruseSearchRequestController {
	
	@Autowired Config config;

	public ReferralSearchRequestController() {
//		this.setFormView("referral/referralSearch");
//		this.setCommandName("referralCriteria");
//		this.setSuccessView("referralSearch.htm");
	}
	
	@ModelAttribute("attributeList")
	protected ArrayList<ButterReference> getAttributes(HttpServletRequest request,Object command,Errors errors)
      throws Exception{		 
		 ArrayList<ButterReference> attributeList= (ArrayList<ButterReference>)Admin.createReferralList();
		 
		 attributeList.add(new ButterReference("multipleLoss", "Multiple Loss"));
		 attributeList.add(new ButterReference("carer", "Carer"));
		 attributeList.add(new ButterReference("preBereavement", "Pre-Bereavement"));
		 attributeList.add(new ButterReference("homeVisit", "Home Visit"));
		 attributeList.add(new ButterReference("telephoneSupport", "Receiving Telephone Support"));
		 attributeList.add(new ButterReference("coreCompleted", "Core Completed"));
		 attributeList.add(new ButterReference("service", "Service"));
		 
		 return attributeList;
	 }
	
	@ModelAttribute("savedSearches")
	protected Collection getSavedSearches(HttpServletRequest request)
      throws Exception{
		 Collection col1 = getSavedSearches(request, "referrals");
		 Collection col2 = getSavedSearches(request, "referralSummary");
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
	  

	@ModelAttribute("referralCriteria")
	protected Object getCriteria(HttpServletRequest request)
			throws Exception {			
		
		ReferralSearchCriteria criteria= (ReferralSearchCriteria)
			request.getSession().getAttribute("referralCriteria");
		
		if (StringUtils.isNotEmpty(request.getParameter("reset"))){
			request.getSession().removeAttribute("referralCriteria");
			criteria = new ReferralSearchCriteria();	
		}
		
		if (criteria == null){
			criteria = new ReferralSearchCriteria();			
		}
		return criteria;
	}
	
	@RequestMapping(value="referralSearchRequest.htm", method = RequestMethod.GET)
	protected String getView(){
		return "referral/referralSearch";
	}
	
	/**
	 * Puts the criteria on the session and forwards to the controller to do the search.
	 */
	@RequestMapping(value="referralSearchRequest.htm", method = RequestMethod.POST)
	protected String onSubmitInternal(
			HttpServletRequest request,	@Validated @ModelAttribute("referralCriteria") ReferralSearchCriteria criteria, BindingResult errors)
			throws Exception {

		criteria.setAttributeSelections(StringUtil.truncateArray(criteria.getAttributeSelections()));
		criteria.setAttribute(null);
		request.getSession().setAttribute("referralCriteria", criteria);
	
		if (StringUtils.isEmpty(request.getParameter("summary"))){
			return "redirect:referralSearch.htm";
		}
		
		return "redirect:referralSummary.htm";
	}
}