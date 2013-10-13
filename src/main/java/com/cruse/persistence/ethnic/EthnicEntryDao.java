package com.cruse.persistence.ethnic;


import java.util.Collection;

import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.ethnic.EthnicSearchCriteria;
import com.cruse.domain.system.User;
import com.cruse.domain.system.UserSearchCriteria;



/**
* Interface identifying the methods for the user interface.
*/
public interface EthnicEntryDao {
	
	/**
	 * Insert an ethnic entry.
	 * 
	 * @param entry	The entity representing an  transfer object for a user
	 */
	public void insert (EthnicEntry entry, String user);
	
	/**
	 * Retrieve an existing for existing Ehnic entry.
	 * 
	 * @param id The primary key for the entry
	 * @return EthnicEntry
	 */
	public EthnicEntry get(String id);
	
	/**
	 * Updates a EthnicEntry.
	 * 
	 * @param EthnicEntry	The data transfer object for a EthnicEntry
	 */
	public void update (EthnicEntry dto, String user);
	
	
	/**
	 * Searches for existing users
	 * 
	 * @param UserSearchCriteria	The criteria when searching for users
	 * @return Collection of User
	 */
	public Collection<EthnicEntry> search(EthnicSearchCriteria criteria);

}