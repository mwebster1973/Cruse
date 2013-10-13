package com.cruse.controller.group;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

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

import com.butter.tag.crumb.CrumbLink;
import com.butter.tag.crumb.CrumbTrail;
import com.cruse.Config;
import com.cruse.controller.CruseFormController;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.group.Group;
import com.cruse.domain.group.GroupSession;
import com.cruse.handler.editor.AdminEditor;
import com.cruse.service.AdminService;
import com.cruse.service.GroupSessionService;


/**
* Controller used to edit an ethnic entry.
*/
@Controller
@RequestMapping({"/editGroupSession.htm","/addGroupSession.htm"})
public class GroupSessionEditController extends CruseFormController {
	
	@Autowired
	private GroupSessionService sessionService;
	
	@Autowired
	private AdminService adminService;
	
	
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
	 
	      binder.registerCustomEditor(Group.class, new AdminEditor(config, Admin.ENTITY_NAME_GROUP)); 
	   }
	
	@ModelAttribute("groupList")
	protected Collection<Admin> getGroupList(){
		return config.getAdminList(Admin.ENTITY_NAME_GROUP);
	}
	 
	
	public GroupSessionEditController() {
		this.setFormView("group/groupSession");
		this.setSuccessView("viewGroupSession.htm");
		this.setCommandName("currentSession");
	}
	
	@ModelAttribute("currentSession")
	public GroupSession getEntity(@RequestParam(value="id", required=false) String id,
			@RequestParam("group") String groupCode){
		if (id != null) {			
			return sessionService.getGroupSession(id);
		}
		if (StringUtils.isEmpty(groupCode)){
			throw new IllegalArgumentException("Group code must be specified");
		}
		
		Group grp = (Group)adminService.get(groupCode, Admin.ENTITY_NAME_GROUP);
		
		GroupSession session = new GroupSession();	
		session.setGroup(grp);
		return session;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "group/groupSession";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String save(
			HttpServletRequest request,
			@ModelAttribute("currentSession") GroupSession session,
			@RequestParam(value="id",required=false) String id,
			BindingResult errors)
			throws Exception {
		try{
			// Perform an update of an existing user or add a new user
			if (StringUtils.isEmpty(id)) {
				sessionService.insertGroupSession(session, getUser(request).getUserId());
				this.addInsertMessage(request, "Session");
			} else {
				sessionService.updateGroupSession(session, getUser(request).getUserId());
				this.addUpdateMessage(request, "Session");
			}
		} catch (DataIntegrityViolationException ex){
			errors.addError(new ObjectError("session", "Another session exists on this date."));
			return "group/groupSession";
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("addAnother"))){
			return "redirect:addGroupSession.htm?group="+session.getGroup().getCode();
		} 
			
		
		CrumbTrail trail = (CrumbTrail) request.getSession().getAttribute("crumbTrail");
		CrumbLink lastLink = trail.getBackLink(true);
			
		if (lastLink!= null){
			String url = lastLink.getAction();
			if (lastLink.getParameters()!= null){
				if (url.indexOf("?")>0){
					url = url + "&"+ lastLink.getParameters();
				} else {
					url = url + "?"+ lastLink.getParameters();
				}
			}
			return "redirect:"+url;
		} 
		
		return "redirect:viewAdmin.htm?domain=Group&id="+session.getGroup().getCode();
	}
}
