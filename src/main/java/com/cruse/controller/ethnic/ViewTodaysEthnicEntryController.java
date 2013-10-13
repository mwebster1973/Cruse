package com.cruse.controller.ethnic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cruse.controller.CruseAbstractController;
import com.cruse.domain.ethnic.EthnicSearchCriteria;

@Controller
@RequestMapping("/viewTodaysEthnicEntries.htm")
public class ViewTodaysEthnicEntryController extends CruseAbstractController{


	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

		EthnicSearchCriteria criteria = new EthnicSearchCriteria();
		criteria.setIndividualDate(new Date());
		request.getSession().setAttribute("ethnicCriteria", criteria);
		
		return new ModelAndView(new RedirectView("ethnicSearch.htm"));
	}
	

}
