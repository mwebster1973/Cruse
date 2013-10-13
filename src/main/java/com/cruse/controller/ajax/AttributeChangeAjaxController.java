package com.cruse.controller.ajax;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.butter.domain.ButterReference;
import com.cruse.Config;
import com.cruse.controller.CruseAjaxController;
import com.cruse.domain.admin.Admin;

@Controller
@RequestMapping("/attributeChangeAjax.htm")
public class AttributeChangeAjaxController extends CruseAjaxController{

	@Autowired
	private Config config;
	
	protected void attachChoices(HttpServletRequest request,
			String changedComponent, String[] selectedValues,
			StringBuffer outBuffer) throws Exception {
		
		String filterText = request.getParameter("filterText");
		String dropDown = request.getParameter("dropDown");
		
		Collection<ButterReference> refs = null;
		if (StringUtils.isNotEmpty(dropDown)){
			
			if (dropDown.equals("carer")){
				refs = createYesNoList("Carer", dropDown);
			}
			else if (dropDown.equals("registeredDisabled")){
				refs = createYesNoList("Registered Disabled", dropDown);
			}
			else if (dropDown.equals("considerDisabled")){
				refs = createYesNoList("Consider themself Disabled", dropDown);
			}
			else if (dropDown.equals("homeVisit")){
				refs = createYesNoList("Home Visit", dropDown);
			}
			else if (dropDown.equals("telephoneSupport")){
				refs = createYesNoList("Receiving Telephone Support", dropDown);
			}
			else if (dropDown.equals("coreCompleted")){
				refs = createYesNoList("Core Completed", dropDown);
			}
			else if (dropDown.equals("service")){
				refs = createYesNoList("service", dropDown);
			}
			else if (dropDown.equals("multipleLoss")){
				refs = createYesNoList("Multiple Loss", dropDown);
			}
			else if (dropDown.equals("preBereavement")){
				refs = createYesNoList("Pre-Bereavement", dropDown);
			} else{
				refs = createReferenceList(config.getAdminList(dropDown), filterText);
			}
		}
		
		
		String strOut = buildXML(refs, "attributeValues", true);
		
		System.out.println(strOut);
		//String strOut = buildXML(config.getAgeList(), "attributeValues", true);
		outBuffer.append(strOut);
		
		return;
	}
	
	private ArrayList<ButterReference> createYesNoList(String attributeDescription, String attributeName){
		ArrayList<ButterReference> refs = new ArrayList<ButterReference>();
		
		refs.add(new ButterReference(attributeName+ ";;"+ "Y", "Yes("+attributeDescription+")" ));
		refs.add(new ButterReference(attributeName+ ";;"+ "N", "No("+attributeDescription+")" ));
	
		return refs;
	}
	
	private ArrayList<ButterReference> createReferenceList(Collection<Admin> adminList,  String filter){

		ArrayList<ButterReference> refs = new ArrayList<ButterReference>();
		for (Admin a: adminList){
			
			if (StringUtils.isEmpty(filter) || a.getDescription().toUpperCase().indexOf(filter.toUpperCase())>-1){
				ButterReference ref = new ButterReference(a.getEntityName()+ ";;"+ a.getCode(), a.getDescription() + "("+ Admin.getEntitySingular(a.getEntityName()) + ")" );
				refs.add(ref);
			}
		}
		return refs;
	}
}