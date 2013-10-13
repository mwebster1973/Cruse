package com.cruse.service;

import java.util.Collection;

import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.GP;
import com.cruse.domain.group.Group;

public interface AdminService {

	/**
	 * Insert a admin entity.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void insert (Admin dto, String user);
	
	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid The primary key for the user
	 * @return User
	 */
	public Admin get(String cdsid, String entityName);
	
	/**
	 * Updates a User.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void update (Admin dto, String user);
	
	/**
	 * Retrieve a collection of users for a specified role. This should not be used for populating list boxes
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection<Admin> getAll(String entityName);
	
	/**
	 * Retrieve a collection of groups;
	 * @return
	 */
	public Collection<Group> getGroups();
	
	
	/**
	 * Insert a GP
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void insertGP (GP dto, String user);
	
	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid The primary key for the user
	 * @return User
	 */
	public GP getGP(String id);
	
	/**
	 * Updates a User.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void updateGP (GP dto, String user);
	
	/**
	 * Retrieve a collection of GPS
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection<GP> getAllGP();
	
}
