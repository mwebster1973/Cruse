package com.cruse.controller.referral;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* Controller used to edit an ethnic entry.
*/
@Controller
@RequestMapping("/editSessionDetails.htm")
public class SessionEditController extends ReferralEditController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getView(){
		return "referral/sessionDetails";
	}
}
