package com.cruse.controller.referral;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.butter.tag.navigator.EntityKeyList;
import com.butter.util.StringUtil;
import com.cruse.controller.CruseViewController;
import com.cruse.domain.referral.Referral;
import com.cruse.service.ReferralService;

@Controller
@RequestMapping("/viewReferral.htm")
public class ReferralViewController extends CruseViewController {
	
	@Autowired
	private ReferralService referralService;
	
	@ModelAttribute("currentReferral")
	public Referral getReferral(@RequestParam("id") String param){
		 return referralService.getReferral(param);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected  ModelAndView handleRequestInternal(HttpServletRequest request,
			@ModelAttribute("currentReferral") Referral referral,
			HttpServletResponse response) throws Exception {
		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("referrals");
		if (col!= null){
			col.setSelectedRow(referral.getId()+"");
		}
		
      ModelAndView mav = new ModelAndView("referral/referralView");      
      super.addAuditHistory(mav, referral);      
      return mav; 
	}

}