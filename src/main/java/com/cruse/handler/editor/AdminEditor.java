package com.cruse.handler.editor;

import java.beans.PropertyEditorSupport;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.butter.util.CombinedKeyUtil;
import com.cruse.Config;
import com.cruse.domain.admin.Admin;

public class AdminEditor extends PropertyEditorSupport {
	
	protected Config config;
	protected String entityName;

	public AdminEditor(Config config, String newEntityName) {
		this.config = config;
		this.entityName = newEntityName;
	}
	
    public String getAsText() {
    	if (this.getValue()!= null){
    		return ((Admin) getValue()).getDescription();
    	}
    	return null;
	}
    
    public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotEmpty(text)){
			Admin admin = (Admin)getAdminForCode(config.getAdminList(entityName), CombinedKeyUtil.splitKey(text));
			setValue(admin);
		} else{
			setValue(null);
		}
	}
    
    public Admin getAdminForCode(Collection<Admin> admins, String id){
    	for (Admin a:admins){
    		if (a.getCode().equals(id)){
    			return a;
    		}
    	}
    	return null;
    }
}

