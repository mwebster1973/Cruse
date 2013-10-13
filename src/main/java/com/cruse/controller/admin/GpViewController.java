package com.cruse.controller.admin;

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
import com.cruse.domain.admin.GP;
import com.cruse.service.AdminService;

@Controller
@RequestMapping("/viewGp.htm")
public class GpViewController extends CruseViewController {
	
	@Autowired
	private AdminService adminService;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = request.getParameter("id");		
		if( ! StringUtil.isPopulated( param )){
			throw new InvalidParameterException("No id in ViewGpController");
		}

		EntityKeyList col = (EntityKeyList)request.getSession().getAttribute("gps");
		if (col!= null){
			col.setSelectedRow(param);
		}
		
        ModelAndView mav = new ModelAndView("admin/gpView");
        
        GP dto= adminService.getGP(param);
        mav.addObject("gp", dto);        
        mav.addObject("entitySingular", "GP");
        super.addAuditHistory(mav, dto);
        
        return mav; 
	}
}