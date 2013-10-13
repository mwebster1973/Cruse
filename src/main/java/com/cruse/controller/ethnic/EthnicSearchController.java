package com.cruse.controller.ethnic;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.controller.CruseSearchController;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.ethnic.EthnicSearchCriteria;
import com.cruse.service.EthnicService;
import com.cruse.service.SystemService;

@Controller
@RequestMapping("/ethnicSearch.htm")
public class EthnicSearchController extends CruseSearchController {

	@Autowired
	private EthnicService ethnicService;
	/**
	 * Perform a search. If calling from the menu
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		EthnicSearchCriteria criteria = (EthnicSearchCriteria) request
				.getSession().getAttribute("ethnicCriteria");

		if (StringUtils.isNotEmpty(request.getParameter("reset"))) {
			criteria = new EthnicSearchCriteria();
			request.getSession().setAttribute("ethnicCriteria", criteria);
		}

		if (criteria == null) { // if no criteria then do the search again
			RedirectView view = new RedirectView("ethnicSearchRequest.htm");
			return new ModelAndView(view);
		}

//		Collection results = ((SystemService)getSystemService()).userSearch(criteria);
		Collection<EthnicEntry> results = ethnicService.ethnicSearch(criteria);

		// forward to the search results page.
		ModelAndView mv = new ModelAndView("ethnic/ethnicSearchResults");
		mv.addObject("ethnics", results);
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		return mv;
	}
}