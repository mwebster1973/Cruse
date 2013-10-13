package com.cruse.controller.ethnic;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.butter.tag.navigator.EntityKeyList;
import com.butter.util.StringUtil;
import com.cruse.controller.CruseViewController;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.system.User;
import com.cruse.service.EthnicService;
import com.cruse.service.SystemService;

@Controller
@RequestMapping("/viewEthnicEntry.htm")
public class EthnicViewController extends CruseViewController {
	
	@Autowired
	private EthnicService ethnicService;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = request.getParameter("id");		
		if( ! StringUtil.isPopulated( param )){
			throw new InvalidParameterException("No id in view controller");
		}
		
		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("ethnics");
		if (col!= null){
			col.setSelectedRow(param);
		}
		
      ModelAndView mav = new ModelAndView("ethnic/ethnicView");
      EthnicEntry entity= ethnicService.getEthnicEntry(param);
      mav.addObject("currentEthnicEntry", entity);        
      super.addAuditHistory(mav, entity);
      
      return mav; 
	}

}