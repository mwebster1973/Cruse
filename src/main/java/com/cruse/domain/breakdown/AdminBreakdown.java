package com.cruse.domain.breakdown;

import java.util.ArrayList;
import java.util.Collection;

import com.cruse.Config;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Count;

public class AdminBreakdown extends Breakdown {
	
	private Config config;
	private String entityName;
	
	public AdminBreakdown(Config config, String entityName, String property){
		super(Admin.getEntityPlural(entityName), property);
		this.config = config;
		this.entityName = entityName;
	}


	public void recordAllOptions(){
		Collection<Admin> allAvailableOptons = config.getAdminList(entityName);
		countList = new ArrayList<Count>();
		for (Admin a : allAvailableOptons) {
			addOption(a.getDescription(), a.getSequence());
		}
	}
	
	protected void recordCountForProperty(Object propertyValue) {
		Admin adminValue = (Admin)propertyValue;
		increment(adminValue.getDescription(), adminValue.getSequence());
	}
}
