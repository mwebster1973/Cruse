package com.cruse.controller.referral;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Age;
import com.cruse.domain.admin.Area;
import com.cruse.domain.admin.BereavedOf;
import com.cruse.domain.admin.CauseOfDeath;
import com.cruse.domain.admin.ClientResidence;
import com.cruse.domain.admin.ContactBy;
import com.cruse.domain.admin.Ending;
import com.cruse.domain.admin.GP;
import com.cruse.domain.admin.Gender;
import com.cruse.domain.admin.HearOfCruse;
import com.cruse.domain.admin.LengthOfBereavement;
import com.cruse.domain.admin.Outpost;
import com.cruse.domain.admin.Pct;
import com.cruse.domain.admin.PlaceOfDeath;
import com.cruse.domain.referral.Referral;
import com.cruse.handler.editor.AdminEditor;
import com.cruse.handler.editor.GPEditor;
import com.cruse.service.ReferralService;


/**
* Controller used to edit an ethnic entry.
*/
@Controller
@RequestMapping({"/editReferral.htm","/addReferral.htm"})
public class ReferralEditController extends CruseFormController {
	
	@Autowired
	private ReferralService referralService;
	
	@Autowired
	private Config  config;

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
	 
	      binder.registerCustomEditor(Age.class, new AdminEditor(config, Admin.ENTITY_NAME_AGE));
	      binder.registerCustomEditor(Area.class, new AdminEditor(config, Admin.ENTITY_NAME_AREA));;
	      binder.registerCustomEditor(BereavedOf.class, new AdminEditor(config, Admin.ENTITY_NAME_BEREAVED_OF));
	      binder.registerCustomEditor(CauseOfDeath.class, new AdminEditor(config, Admin.ENTITY_NAME_CAUSE_OF_DEATH));;
	      binder.registerCustomEditor(ContactBy.class, new AdminEditor(config, Admin.ENTITY_NAME_CONTACT_BY));

	      binder.registerCustomEditor(Gender.class, new AdminEditor(config, Admin.ENTITY_NAME_GENDER));
	      binder.registerCustomEditor(HearOfCruse.class, new AdminEditor(config, Admin.ENTITY_NAME_HEAR_OF_CRUSE));
	      binder.registerCustomEditor(LengthOfBereavement.class, new AdminEditor(config, Admin.ENTITY_NAME_LENGTH));
	      binder.registerCustomEditor(Pct.class, new AdminEditor(config, Admin.ENTITY_NAME_PCT));
	      binder.registerCustomEditor(Outpost.class, new AdminEditor(config, Admin.ENTITY_NAME_OUTPOST));
	      binder.registerCustomEditor(Ending.class, new AdminEditor(config, Admin.ENTITY_NAME_ENDING));
	      
	      binder.registerCustomEditor(ClientResidence.class, new AdminEditor(config, Admin.ENTITY_NAME_CLIENT_RESIDENCE));
	      binder.registerCustomEditor(PlaceOfDeath.class, new AdminEditor(config, Admin.ENTITY_NAME_PLACE_OF_DEATH));
	      binder.registerCustomEditor(GP.class, new GPEditor(config));
	      binder.registerCustomEditor(List.class, new PropertyEditorSupport() {
	    	  
	    	  @Override
	    	public void setAsText(String text) throws IllegalArgumentException {
	    		  List<Date> dates = new ArrayList<Date>();
	    		  String dateFormat = "dd/MM/yyyy";
	    	      SimpleDateFormat df = new SimpleDateFormat(dateFormat);
	    	      
	    		  for (String dateString:Arrays.asList(text.split("\\s*,\\s*"))){
	    			  try{
	    				  dates.add(df.parse(dateString));
	    			  } catch (Exception ex){
	    				  throw new RuntimeException(ex);
	    			  }
	    		  }
	    		  // remove duplicates
	    		  Set<Date> hs = new HashSet<Date>();
	    		  hs.addAll(dates);
	    		  dates.clear();
	    		  dates.addAll(hs);
	    		  
	    		  this.setValue(dates);

	    	}
	    	  
	    	public String getAsText() {
	    		System.out.println(getValue());
	    		return "Unknown";
	    	}
	    	  
	      }); 
	   }
	
	@ModelAttribute("ageList")
	protected Collection<Admin> getAges(){
		return config.getAdminList(Admin.ENTITY_NAME_AGE);
	}
	
	@ModelAttribute("areaList")
	protected Collection<Admin> getAreas(){
		return config.getAdminList(Admin.ENTITY_NAME_AREA);
	}
	
	@ModelAttribute("bereavedOfList")
	protected Collection<Admin> getBereavedOf(){
		return config.getAdminList(Admin.ENTITY_NAME_BEREAVED_OF);
	}
	
	@ModelAttribute("causeOfDeathList")
	protected Collection<Admin> getCausesOfDeath(){
		return config.getAdminList(Admin.ENTITY_NAME_CAUSE_OF_DEATH);
	}
	
	@ModelAttribute("contactByList")
	protected Collection<Admin> getContactBy(){
		return config.getAdminList(Admin.ENTITY_NAME_CONTACT_BY);
	}
	
	@ModelAttribute("genderList")
	protected Collection<Admin> getGenders(){
		return config.getAdminList(Admin.ENTITY_NAME_GENDER);
	}
	
	@ModelAttribute("hearOfCruseList")
	protected Collection<Admin> getHearOfCruiseList(){
		return config.getAdminList(Admin.ENTITY_NAME_HEAR_OF_CRUSE);
	}
	
	@ModelAttribute("lengthOfBereavementList")
	protected Collection<Admin> getLengthOfBeravementList(){
		return config.getAdminList(Admin.ENTITY_NAME_LENGTH);
	}
	
	@ModelAttribute("pctList")
	protected Collection<Admin> getPctList(){
		return config.getAdminList(Admin.ENTITY_NAME_PCT);
	}
	
	@ModelAttribute("endingList")
	protected Collection<Admin> getEndingList(){
		return config.getAdminList(Admin.ENTITY_NAME_ENDING);
	}
	
	@ModelAttribute("outpostList")
	protected Collection<Admin> getOutpostList(){
		return config.getAdminList(Admin.ENTITY_NAME_OUTPOST);
	}
	
	@ModelAttribute("placeOfDeathList")
	protected Collection<Admin> getPlaceOfDeathList(){
		return config.getAdminList(Admin.ENTITY_NAME_PLACE_OF_DEATH);
	}
	
	@ModelAttribute("clientResidenceList")
	protected Collection<Admin> getClientResidenceList(){
		return config.getAdminList(Admin.ENTITY_NAME_CLIENT_RESIDENCE);
	}
	
	@ModelAttribute("gpList")
	protected Collection<GP> getGpList(){
		return config.getGPList();
	}

	
	@ModelAttribute("currentReferral")
	public Referral getEntity(@RequestParam(value="id", required=false) String id){
		if (id != null) {			
			return referralService.getReferral(id);
		}
		return new Referral();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "referral/referral";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String save(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("currentReferral") Referral referral, 
			BindingResult errors)
			throws Exception {

		String id = request.getParameter("id");
		String coreDates[] =request.getParameterValues("coreDates");
		if (coreDates== null){
			referral.getCoreDates().clear();			
		}
		
		String dnaDates[] =request.getParameterValues("dnaDates");
		if (dnaDates== null){
			referral.getDnaDates().clear();			
		}
		
		String cancellationDates[] =request.getParameterValues("cancellationDates");
		if (cancellationDates== null){
			referral.getCancellationDates().clear();			
		}
		
		try{
			// Perform an update of an existing user or add a new user
			if (StringUtils.isEmpty(id)) {
				referralService.insertReferral(referral, getUser(request).getUserId());
				this.addInsertMessage(request, "Referral");
			} else {
				referralService.updateReferral(referral, getUser(request).getUserId());
				this.addUpdateMessage(request, "Referral");
			}
		} catch (DataIntegrityViolationException ex){
			errors.addError(new ObjectError("referralNo", "Another referral exists with this Referral No."));
			return "referral/referral";
		}

		if (StringUtils.isNotEmpty(request.getParameter("addAnother"))){
			return "redirect:addReferral.htm";
		} 
		return "redirect:viewReferral.htm?id="+referral.getId();		
	}
}
