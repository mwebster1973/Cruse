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
import com.cruse.domain.referral.Referral;
import com.cruse.domain.referral.ReferralSearchCriteria;
import com.cruse.service.ReferralService;

@Controller
@RequestMapping("/referralSearch.htm")
public class ReferralSearchController extends CruseSearchController {

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

//		Collection results = ((SystemService)getSystemService()).userSearch(criteria);
		Collection<Referral> results = referralService.referralSearch(criteria);

		// forward to the search results page.
		ModelAndView mv = new ModelAndView("referral/referralSearchResults");
		mv.addObject("referrals", results);
		mv.addObject("criteriaDescription", 
				super.getSelectedQueryCriteria(criteria));
		
		super.loadView(request, "referrals");
		return mv;
	}
}