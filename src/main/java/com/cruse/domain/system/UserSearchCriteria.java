//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   UserSearchCriteria.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:42  $
//*
//******************************************************************************

package com.cruse.domain.system;


import java.io.Serializable;

import com.butter.search.ButterCriteria;
import com.cruse.domain.system.User;

/**
* Perform a search of users 
*/
public class UserSearchCriteria extends ButterCriteria implements Serializable{
	
	/**
	 * Generated Serial version uid
	 */
	private static final long serialVersionUID = 1489911880984714994L;
	
	public static String SEARCH_TYPE_CONTAINS = "CONTAINS";
	public static String SEARCH_TYPE_BEGINS = "BEGINS";
	
	private String userName;
	private String userNameSearchType = SEARCH_TYPE_CONTAINS;
	private String[] selectedRoles;
	
	/**
	 * Default constructor
	 *
	 */
	public UserSearchCriteria() {
		
	}
	

	/**
	 * @return Returns the selectedRoles.
	 */
	public String[] getSelectedRoles() {
		return selectedRoles;
	}
	/**
	 * @param selectedRoles The selectedRoles to set.
	 */
	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the userNameSearchType.
	 */
	public String getUserNameSearchType() {
		return userNameSearchType;
	}
	/**
	 * @param userNameSearchType The userNameSearchType to set.
	 */
	public void setUserNameSearchType(String userNameSearchType) {
		this.userNameSearchType = userNameSearchType;
	}

	/* 
	 * method overridden to attach the descriptions of the search
	 */
	protected void attachDescriptions() {
		if (getUserName()!= null && getUserName().length()>0){
			
			String desc = null;
			if (getUserNameSearchType()!= null && getUserNameSearchType().equals(SEARCH_TYPE_BEGINS)){
				desc = "Begins with";	
			} else{
				desc = "Contains";
			}
			super.appendCriteriaDescription("User", desc + " "+ getUserName());
		}
		
		
		if (selectedRoles!= null){
			StringBuffer roleDescriptions = new StringBuffer();
			for (int x=0; x < selectedRoles.length; x++){
				if (roleDescriptions.length()>0){
					roleDescriptions.append(", ");
				}
				roleDescriptions.append( User.getRoleDescription(selectedRoles[x]));
			}			
			super.appendCriteriaDescription("Specific Roles", roleDescriptions.toString());
		}
		
		if (criteriaDescriptions.size()==0){
			super.appendCriteriaDescription("Users", "Show All");
		}		
	}
	
	
	/** 
	 * method overridden to reset the search type
	 */
	public void reset() {
		userName = null;
		userNameSearchType = SEARCH_TYPE_CONTAINS;
		selectedRoles = null;
	}	
}
