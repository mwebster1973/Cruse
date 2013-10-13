//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   GeneralAdminRule.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:04  $
//*
//******************************************************************************
package com.cruse.service.rule;

import com.butter.validation.AbstractUseCaseRule;
import com.butter.validation.ButterSecurityCheck;
import com.cruse.config.Roles;
import com.cruse.domain.system.User;

public class GeneralAdminRule extends AbstractUseCaseRule {

	private User user;

	public GeneralAdminRule(final User user) {
		super();
		this.user = user;
	}

	public boolean fireRule() {				
    	String[] roles = new String[]{  Roles.ROLE_ADMIN, Roles.ROLE_IT_ADMIN};
    	ButterSecurityCheck securityCheck = new ButterSecurityCheck( user, roles );
    	return executeCheck(securityCheck) ;
	}
}