package com.cruse.controller.group;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.controller.CruseSearchController;
import com.cruse.domain.group.Group;
import com.cruse.domain.group.GroupSessionSearchCriteria;
import com.cruse.service.AdminService;
import com.cruse.service.GroupSessionService;

@Controller
@RequestMapping("/sessionSearch.htm")
public class GroupSessionSearchController extends CruseSearchController {

	@Autowired GroupSessionService sessionService;
	@Autowired AdminService adminService;
	
	
	/**
	 * Perform a search. If calling from the menu
	 */
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		GroupSessionSearchCriteria criteria = (GroupSessionSearchCriteria) request
				.getSession().getAttribute("sessionCriteria");

		if (criteria == null || StringUtils.isNotEmpty(request.getParameter("reset"))) {
			criteria = new GroupSessionSearchCriteria();
			request.getSession().setAttribute("sessionCriteria", criteria);
		}

		ModelAndView mv = new ModelAndView("group/sessionSearchResults");
		// forward to the search results page.
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		
		Collection<Group> groups = adminService.getGroups();
		mv.addObject("groups", groups);
		for (Group group:groups){
			criteria.setGroup(group);
			mv.addObject(group.getCode()+"List", sessionService.groupSessionSearch(criteria));
			mv.addObject(group.getCode()+"criteriaDescription", 
					super.getSelectedQueryCriteria(criteria));
		}
		criteria.setGroup(null);
		
		return mv;
	}
}