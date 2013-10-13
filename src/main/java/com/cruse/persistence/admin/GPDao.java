package com.cruse.persistence.admin;

import java.util.Collection;

import com.cruse.domain.admin.GP;


public interface GPDao {

	/**
	 * Insert a GP
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void insert (GP dto, String user);
	
	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid The primary key for the user
	 * @return User
	 */
	public GP get(String id);
	
	/**
	 * Updates a User.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void update (GP dto, String user);
	
	/**
	 * Retrieve a collection of GPS
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection<GP> getAll();
}
