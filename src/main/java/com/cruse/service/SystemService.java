//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   SystemService.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:44  $
//*
//******************************************************************************

package com.cruse.service;

import java.util.Collection;

import com.butter.exception.ButterApplicationException;
import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.system.NewPasswordRequest;
import com.cruse.domain.system.User;
import com.cruse.domain.system.UserSearchCriteria;

/**
 * Local interface for Enterprise Bean: SystemService
 */
public interface SystemService extends com.butter.service.SystemService{

	
	/**
	   * Retrieve a user for a given cdsId
	   * @param cdsId  The unique identified for a user
	   * @return  The User object
	   */
	public User getUser(String cdsId);

	/**
	 * Retrieve a collection of users for a specified role. This should not be used for populating list boxes
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection getUsersByRole(String roleCode);

	/**
	   * Insert a user onto the system
	   * @param User object
	   * @param CDSID
	   */
	public void insertUser(User userDto, String user) throws ButterEntityValidationException;

	/**
	   * Update a user
	   * @param  CDSID
	   * @param  User object
	   */
	public void updateUser(User userDto, String user)  throws ButterEntityValidationException;

	/**
	   * Get a Collection of User objects
	   * @return A collection of User objects
	   */
	public Collection userSearch(UserSearchCriteria userSearchCriteriaDto);
	
	public void changePasssword(NewPasswordRequest request, User user) throws ButterApplicationException;
}