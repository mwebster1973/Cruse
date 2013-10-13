//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   UserDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:04  $
//*
//******************************************************************************

package com.cruse.persistence.system;


import java.util.Collection;

import com.cruse.domain.system.User;
import com.cruse.domain.system.UserSearchCriteria;



/**
* Interface identifying the methods for the user interface.
*/
public interface UserDao {
	
	/**
	 * Insert a user.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void insert (User dto, String user);
	
	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid The primary key for the user
	 * @return User
	 */
	public User get(String cdsid);
	
	/**
	 * Updates a User.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void update (User dto, String user);
	
	/**
	 * Retrieve a collection of users for a specified role. This should not be used for populating list boxes
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection<User> getUsersByRole(String roleCode);
	
	
	/**
	 * Searches for existing users
	 * 
	 * @param UserSearchCriteria	The criteria when searching for users
	 * @return Collection of User
	 */
	public Collection<User> search(UserSearchCriteria criteria);

}