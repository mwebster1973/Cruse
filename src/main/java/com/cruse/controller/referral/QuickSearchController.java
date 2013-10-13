package com.cruse.controller.referral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.butter.validation.ButterMessage;
import com.cruse.controller.CruseController;
import com.cruse.domain.referral.Referral;
import com.cruse.service.ReferralService;

@Controller
public class QuickSearchController extends CruseController {

	@Autowired ReferralService referralService;

	@RequestMapping(value="/quickSearch.htm", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		String referralNo = request.getParameter("quickSearchText");
		
		Referral referral = referralService.getReferralByNo(referralNo);
		if (referral!= null){
			RedirectView rd = new RedirectView("viewReferral.htm");
			rd.setExposeModelAttributes(true);
			ModelAndView mv = new ModelAndView(rd);
			mv.addObject("id", referral.getId());
			return mv;
		}
		this.addErrorMessage(request, new ButterMessage("error.no.referral", referralNo));
		
		return new ModelAndView(new RedirectView("Welcome.htm"));
	}

}
