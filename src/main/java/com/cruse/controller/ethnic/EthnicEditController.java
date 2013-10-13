package com.cruse.controller.ethnic;

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

import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Age;
import com.cruse.domain.admin.Area;
import com.cruse.domain.admin.BereavedOf;
import com.cruse.domain.admin.CauseOfDeath;
import com.cruse.domain.admin.ContactBy;
import com.cruse.domain.admin.EthnicBackground;
import com.cruse.domain.admin.Gender;
import com.cruse.domain.admin.Pct;
import com.cruse.domain.admin.Religion;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.handler.editor.AdminEditor;
import com.cruse.service.EthnicService;


/**
* Controller used to edit an ethnic entry.
*/
@Controller
@RequestMapping({"/editEthnicEntry.htm","/addEthnicEntry.htm"})
public class EthnicEditController extends CruseFormController {
	
	@Autowired
	private EthnicService ethnicService;
	
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
	      binder.registerCustomEditor(Religion.class, new AdminEditor(config, Admin.ENTITY_NAME_RELIGEON));
	      binder.registerCustomEditor(EthnicBackground.class, new AdminEditor(config, Admin.ENTITY_NAME_ETHNIC_BACK));
	      binder.registerCustomEditor(Area.class, new AdminEditor(config, Admin.ENTITY_NAME_AREA));
	      binder.registerCustomEditor(Pct.class, new AdminEditor(config, Admin.ENTITY_NAME_PCT));
	      
	      binder.registerCustomEditor(Age.class, new AdminEditor(config, Admin.ENTITY_NAME_AGE));
	      binder.registerCustomEditor(Gender.class, new AdminEditor(config, Admin.ENTITY_NAME_GENDER));

	   }
	
	@ModelAttribute("ethnicBackgroundList")
	protected Collection<Admin> getEthnicBackgroundList(){
		return config.getAdminList(Admin.ENTITY_NAME_ETHNIC_BACK);
	}
	
	@ModelAttribute("religionList")
	protected Collection<Admin> getReligeonList(){
		return config.getAdminList(Admin.ENTITY_NAME_RELIGEON);
	}
	
	@ModelAttribute("areaList")
	protected Collection<Admin> getAreaList(){
		return config.getAdminList(Admin.ENTITY_NAME_AREA);
	}
	
	@ModelAttribute("pctList")
	protected Collection<Admin> getPctList(){
		return config.getAdminList(Admin.ENTITY_NAME_PCT);
	}
	
	@ModelAttribute("ageList")
	protected Collection<Admin> getAgeList(){
		return config.getAdminList(Admin.ENTITY_NAME_AGE);
	}
	
	@ModelAttribute("genderList")
	protected Collection<Admin> getGenderList(){
		return config.getAdminList(Admin.ENTITY_NAME_GENDER);
	}
	
	@ModelAttribute("currentEthnicEntry")
	public EthnicEntry getEntity(@RequestParam("id") String id){
		if (id != null) {			
			return ethnicService.getEthnicEntry(id);
		}
		return new EthnicEntry();		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "ethnic/ethnic";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String  save(
			HttpServletRequest request,
			@RequestParam(value="id", required=false) String id,
			@ModelAttribute("currentEthnicEntry") EthnicEntry entry, BindingResult errors)
			throws Exception {
		
		// Perform an update of an existing user or add a new user
		if (StringUtils.isEmpty(id)) {
			ethnicService.insertEthnicEntry(entry, getUser(request).getUserId());
			this.addInsertMessage(request, "Cultural Background");
		} else {
			ethnicService.updateEtnicEntry(entry, getUser(request).getUserId());
			this.addUpdateMessage(request, "Cultural Background");
		}

		if (StringUtils.isNotEmpty(request.getParameter("addAnother"))){			
			return "redirect:addEthnicEntry.htm";
		}
		
		return "viewEthnicEntry.htm?id="+entry.getSeqId();
		
	}
}
