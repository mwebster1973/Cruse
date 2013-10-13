//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   User.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:51:42  $
//*
//******************************************************************************

package com.cruse.domain.system;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.butter.domain.ButterUser;
import com.cruse.config.Roles;

/**
 * Class used to represent a data transfer object for a user.
 */
public class User extends ButterUser {

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private static final long serialVersionUID = 3006761183259360291L;

	/**
	 * Determine whether the user is an administrator
	 * 
	 * @return True or False
	 */
	public boolean isCounsellor() {
		return hasRole(Roles.ROLE_COUNSELLOR);
	}

	public void setCounsellor(boolean counsellor) {
		if (counsellor) {
			addRole(Roles.ROLE_COUNSELLOR);
		} else {
			removeRole(Roles.ROLE_COUNSELLOR);
		}
	}

	/**
	 * Determine whether the user is an administrator
	 * 
	 * @return True or False
	 */
	public boolean isAdmin() {
		return hasRole(Roles.ROLE_ADMIN);
	}

	public void setAdmin(boolean admin) {
		if (admin) {
			addRole(Roles.ROLE_ADMIN);
		} else {
			removeRole(Roles.ROLE_ADMIN);
		}
	}

	/**
	 * Determine whether the user is an IT administrator
	 * 
	 * @return True or False
	 */
	public boolean isItAdmin() {
		return hasRole(Roles.ROLE_IT_ADMIN);
	}

	public void setItAdmin(boolean role) {
		if (role) {
			addRole(Roles.ROLE_IT_ADMIN);
		} else {
			removeRole(Roles.ROLE_IT_ADMIN);
		}
	}
	
	public boolean isDataEntry() {
		return hasRole(Roles.ROLE_DATA_ENTRY);
	}

	public void setDataEntry(boolean role) {
		if (role) {
			addRole(Roles.ROLE_DATA_ENTRY);
		} else {
			removeRole(Roles.ROLE_DATA_ENTRY);
		}
	}
	
	

	// ///////////////////////
	// Get and Set methods //
	// ///////////////////////

	public static String getRoleDescription(String roleCode) {
		if (StringUtils.equals(roleCode, Roles.ROLE_ADMIN)) {
			return "Admin";
		}
		if (StringUtils.equals(roleCode, Roles.ROLE_COUNSELLOR)) {
			return "Counsellor";
		}
		if (StringUtils.equals(roleCode, Roles.ROLE_IT_ADMIN)) {
			return "IT Admin";
		}
		if (StringUtils.equals(roleCode, Roles.ROLE_DATA_ENTRY)) {
			return "Data Entry";
		}
		return "";
	}

	/**
	 * Retrieve a comma seperated description of all the roles for the users
	 * 
	 */
	public String getRoleDescriptions() {
		StringBuffer buffy = new StringBuffer();

		if (isAdmin()) {
			this.addToBuffer(buffy,
					getRoleDescription(Roles.ROLE_ADMIN));
		}
		if (isCounsellor()) {
			this.addToBuffer(buffy,
					getRoleDescription(Roles.ROLE_COUNSELLOR));
		}
		if (isItAdmin()) {
			this.addToBuffer(buffy, getRoleDescription(Roles.ROLE_IT_ADMIN));
		}
		if (isDataEntry()) {
			this.addToBuffer(buffy, getRoleDescription(Roles.ROLE_DATA_ENTRY));
		}
		return buffy.toString();
	}

	/**
	 * Attach a role to a buffy. This will add a comma before the item if the
	 * list is not empty
	 * 
	 * @param buffy
	 * @param role
	 */
	private void addToBuffer(StringBuffer buffy, String role) {
		if (buffy.length() > 0) {
			buffy.append(", ");
		}
		buffy.append(role);
	}

	/**
	 * Retrieve the name of the entity. This is used to identify the audit
	 * entries
	 */
	public String getEntityName() {
		return "User";
	}

	/**
	 * Any cross field validation
	 */
	public void validateEntity() {
	}
}