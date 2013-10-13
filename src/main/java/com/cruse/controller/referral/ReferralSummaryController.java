package com.cruse.controller.referral;

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
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.ethnic.EthnicSearchCriteria;
import com.cruse.domain.referral.ReferralSearchCriteria;
import com.cruse.service.EthnicService;
import com.cruse.service.ReferralService;

@Controller
@RequestMapping("/referralSummary.htm")
public class ReferralSummaryController extends CruseSearchController {

	@Autowired
	private ReferralService referralService;
	/**
	 * Perform a search. If calling from the menu
	 */
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReferralSearchCriteria criteria = (ReferralSearchCriteria) request
				.getSession().getAttribute("referralCriteria");

		if (StringUtils.isNotEmpty(request.getParameter("reset"))) {
			criteria = new ReferralSearchCriteria();
			request.getSession().setAttribute("referralCriteria", criteria);
		}

		if (criteria == null) { // if no criteria then do the search again
			RedirectView view = new RedirectView("referralSearchRequest.htm");
			return new ModelAndView(view);
		}

		Collection<Breakdown> results = referralService.referralSummary(criteria);

		// forward to the search results page.
		ModelAndView mv = new ModelAndView("referral/referralSummary");
		mv.addObject("referralSummary", results);
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		return mv;
	}
}