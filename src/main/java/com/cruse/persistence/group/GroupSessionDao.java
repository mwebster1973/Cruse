package com.cruse.persistence.group;

import java.util.Collection;

import com.cruse.domain.group.GroupSession;
import com.cruse.domain.group.GroupSessionSearchCriteria;

/**
 * A group session represents a group meeting in cruse. 
 */
public interface GroupSessionDao {

	/**
	 * Insert a group session entity.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void insert (GroupSession dto, String user);
	
	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid The primary key for the user
	 * @return User
	 */
	public GroupSession get(String id);
	
	/**
	 * Updates a group session.
	 * 
	 * @param User	The data transfer object for a user
	 */
	public void update (GroupSession dto, String user);
	
	/**
	 * Retrieve a collection of users for a specified role. This should not be used for populating list boxes
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection<GroupSession> search(GroupSessionSearchCriteria criteria);
}
