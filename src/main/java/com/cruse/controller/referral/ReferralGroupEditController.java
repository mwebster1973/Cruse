package com.cruse.controller.referral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.butter.domain.ButterReference;
import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.group.Group;
import com.cruse.domain.group.GroupSession;
import com.cruse.domain.referral.Referral;
import com.cruse.domain.referral.ReferralGroup;
import com.cruse.handler.editor.AdminEditor;
import com.cruse.service.ReferralService;


/**
* Controller used to edit a user.
*/
@Controller
@RequestMapping({"/editReferralGroup.htm","/addReferralGroup.htm"})
public class ReferralGroupEditController extends CruseFormController {
	
	@Autowired
	private ReferralService referralService;
	
	@Autowired private Config config;

	public ReferralGroupEditController() {
		this.setFormView("referral/referralGroup");
		this.setSuccessView("viewReferral.htm");
		this.setCommandName("group");
	}	
	
	@InitBinder
	 private void initBinder(WebDataBinder binder) {
       binder.registerCustomEditor(Group.class, new AdminEditor(config, Admin.ENTITY_NAME_GROUP));
       
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
	
	@ModelAttribute("groupList")
	protected ArrayList<Admin> getGroupList(@ModelAttribute("group") ReferralGroup rg ){
		 if (rg.getId()!= -1){
			 ArrayList<Admin> groupList = new ArrayList<Admin>( config.getAdminList(Admin.ENTITY_NAME_GROUP));
			 for (ReferralGroup grp: rg.getReferral().getGroups()){
				 if (grp.getId()!= -1 && grp.getGroup()!= null){
					 groupList.remove(grp.getGroup());
				 }
			 }
			 return groupList;
		 }
		 return null;
	}
	
	@ModelAttribute("statusList")
	protected ArrayList<ButterReference> getStatuses(){
		 ArrayList<ButterReference> statuses = new ArrayList<ButterReference>();
		 statuses.add(new ButterReference(ReferralGroup.STATUS_WAITING_LIST));
		 statuses.add(new ButterReference(ReferralGroup.STATUS_ACTIVE));
		 statuses.add(new ButterReference(ReferralGroup.STATUS_CLOSED));
		 return statuses;
	}
	
	@ModelAttribute("endingsList")
	protected ArrayList<ButterReference> getEndings(){
		 ArrayList<ButterReference> endings = new ArrayList<ButterReference>();
		 endings.add(new ButterReference(ReferralGroup.ENDING_PLANNED));
		 endings.add(new ButterReference(ReferralGroup.ENDING_PREMATURe));
		 return endings;
	}
	
	@ModelAttribute("group")
	public ReferralGroup getEntity(@RequestParam("id") String id,
			@RequestParam("referralId") String referralId){		
		Referral ref = referralService.getReferral(referralId);
		
		if (id != null) {			
			for (ReferralGroup rg: ref.getGroups()){
				if (rg.getId()== Integer.parseInt(id)){
					return rg;
				}
			}
		}
		ReferralGroup group = new ReferralGroup();
		group.setStatus(ReferralGroup.STATUS_WAITING_LIST);
		group.setReferral(ref);
		return group;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "referral/referralGroup";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected String save(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("group") ReferralGroup rg, 
			BindingResult errors)
			throws Exception {

		String id = request.getParameter("id");
		String referralId = request.getParameter("referralId");
		try{
			Referral ref = referralService.getReferral(referralId);
			// Perform an update of an existing user or add a new user
			if (StringUtils.isEmpty(id)) {
				ref.getGroups().add(rg);
				rg.setReferral(ref);
				checkDates(rg);
				referralService.updateReferral(ref, getUser(request).getUserId());
				this.addInsertMessage(request, "Group");
			} else {
				checkDates(rg);
				referralService.updateReferral(ref, getUser(request).getUserId());
				this.addUpdateMessage(request,  "Group");
			}
			return "viewReferral.htm?id="+referralId;
		}catch (DataIntegrityViolationException de){
			de.printStackTrace();
			ModelAndView view = this.getModelAndView(this.getFormView());
			errors.reject("error.duplicate.code", new Object[]{"Group"},
			"Unknown Error");
			return "referral/referralGroup";
		}
	}

	private void checkDates(ReferralGroup rg){
		if (rg.getStatus().equalsIgnoreCase(ReferralGroup.STATUS_WAITING_LIST) && rg.getWaitingListDate()== null){
			rg.setWaitingListDate(new Date());
		}
		if (rg.getStatus().equalsIgnoreCase(ReferralGroup.STATUS_ACTIVE) && rg.getJoinedDate()== null){
			rg.setJoinedDate(new Date());
		}
		if (rg.getStatus().equalsIgnoreCase(ReferralGroup.STATUS_CLOSED) && rg.getClosedDate()== null){
			rg.setClosedDate(new Date());
		}
		
		
	}

}
