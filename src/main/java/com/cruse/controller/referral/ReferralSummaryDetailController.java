
package com.cruse.controller.referral;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.controller.CruseSearchController;
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.referral.ReferralSearchCriteria;
import com.cruse.service.ReferralService;

@Controller
@RequestMapping("/referralSummaryDetail.htm")
public class ReferralSummaryDetailController extends CruseSearchController {

	@Autowired
	private ReferralService service;
	/**
	 * Perform a search. If calling from the menu
	 */
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReferralSearchCriteria  criteria = (ReferralSearchCriteria) request
				.getSession().getAttribute("referralCriteria");

		if (criteria == null) { // if no criteria then do the search again
			RedirectView view = new RedirectView("referralSearchRequest.htm");
			return new ModelAndView(view);
		}

		Collection<Breakdown> results = service.referralSummary(criteria);

		Breakdown breakdown = null;
		String attribute = request.getParameter("id");
		for (Breakdown breakdown2: results){
			if (breakdown2.getDescription().equalsIgnoreCase(attribute)){
				breakdown = breakdown2;
			}
		}
		
		String displayCol = "attribute.sequence";
		String displaySequence = "A";
		
		if (criteria.getSummaryOrder().equals(ReferralSearchCriteria.SUMMARY_ORDER_ALPHABETICAL)){
			 displayCol = "attribute.description";
		} else if (criteria.getSummaryOrder().equals(ReferralSearchCriteria.SUMMARY_ORDER_HIGHEST)){
			displayCol = "count";
		    displaySequence = "D";
		}
		
		// forward to the search results page.
		ModelAndView mv = new ModelAndView("referral/referralSummaryDetail");
		mv.addObject("title", attribute + " breakdown");
		mv.addObject("detail", breakdown.getCountList());
		mv.addObject("displayCol", displayCol);
		mv.addObject("displaySequence", displaySequence);
		
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		return mv;
	}
}