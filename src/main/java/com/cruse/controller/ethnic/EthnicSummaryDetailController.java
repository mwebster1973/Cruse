package com.cruse.controller.ethnic;

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
import com.cruse.domain.admin.Admin;
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.ethnic.EthnicSearchCriteria;
import com.cruse.service.EthnicService;

@Controller
@RequestMapping("/ethnicSummaryDetail.htm")
public class EthnicSummaryDetailController extends CruseSearchController {

	@Autowired
	private EthnicService ethnicService;
	/**
	 * Perform a search. If calling from the menu
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		EthnicSearchCriteria criteria = (EthnicSearchCriteria) request
				.getSession().getAttribute("ethnicCriteria");

		if (criteria == null) { // if no criteria then do the search again
			RedirectView view = new RedirectView("ethnicSearchRequest.htm");
			return new ModelAndView(view);
		}

		Collection<Breakdown> results = ethnicService.ethnicSummary(criteria);

		Breakdown breakdown = null;
		String attribute = request.getParameter("id");
		for (Breakdown breakdown2: results){
			if (breakdown2.getDescription().equalsIgnoreCase(attribute)){
				breakdown = breakdown2;
			}
		}
		
		String displayCol = "attribute.sequence";
		String displaySequence = "A";
		
		if (criteria.getSummaryOrder().equals(EthnicSearchCriteria.SUMMARY_ORDER_ALPHABETICAL)){
			 displayCol = "attribute.description";
		} else if (criteria.getSummaryOrder().equals(EthnicSearchCriteria.SUMMARY_ORDER_HIGHEST)){
			displayCol = "count";
			displaySequence = "D";
		}
		
		// forward to the search results page.
		ModelAndView mv = new ModelAndView("ethnic/ethnicSummaryDetail");
		mv.addObject("title", attribute + " breakdown");
		mv.addObject("detail", breakdown.getCountList());
		mv.addObject("displayCol", displayCol);
		mv.addObject("displaySequence", displaySequence);
		
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		return mv;
	}
}