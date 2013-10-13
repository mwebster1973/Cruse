package com.cruse.handler.editor;

import java.beans.PropertyEditorSupport;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.butter.util.CombinedKeyUtil;
import com.cruse.Config;
import com.cruse.domain.admin.GP;

public class GPEditor  extends PropertyEditorSupport {
	
	protected Config config;

	public GPEditor(Config config) {
		this.config = config;
	}
	
    public String getAsText() {
    	if (this.getValue()!= null){
    		return ((GP) getValue()).getSurgeryName();
    	}
    	return null;
	}
    
    public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotEmpty(text)){
			GP admin = (GP)getGPForId(config.getGPList(), CombinedKeyUtil.splitKey(text));
			setValue(admin);
		} else{
			setValue(null);
		}
	}
    
    public GP getGPForId(Collection<GP> gps, String id){
    	for (GP g:gps){
    		String gpId = g.getId()+"";
    		if (gpId.equals(id)){
    			return g;
    		}
    	}
    	return null;
    }
}

