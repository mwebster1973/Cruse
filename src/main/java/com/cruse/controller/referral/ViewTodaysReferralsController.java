package com.cruse.controller.referral;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.controller.CruseAbstractController;
import com.cruse.domain.referral.ReferralSearchCriteria;

@Controller
@RequestMapping("/viewTodaysReferrals.htm")
public class ViewTodaysReferralsController extends CruseAbstractController{


	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		ReferralSearchCriteria criteria = new ReferralSearchCriteria();
		criteria.setIndividualDate(new Date());
		request.getSession().setAttribute("referralCriteria", criteria);
		
		return new ModelAndView(new RedirectView("referralSearch.htm"));
	}
	

}
