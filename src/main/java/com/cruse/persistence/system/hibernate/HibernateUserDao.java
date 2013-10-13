//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   HibernateUserDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:00  $
//*
//******************************************************************************

package com.cruse.persistence.system.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.cruse.config.NamedAttributes;
import com.cruse.config.Roles;
import com.cruse.domain.system.User;
import com.cruse.domain.system.UserSearchCriteria;
import com.cruse.persistence.CruseHibernateDao;
import com.cruse.persistence.system.UserDao;

/**
* Hibernate version of the dao to retrieve the user dao.
*/
public class HibernateUserDao extends CruseHibernateDao implements UserDao {

	/**
	 * Insert a user.
	 * 
	 * @param User
	 *            The data transfer object for a user
	 */
	public void insert(User dto, String user){
		
		this.getHibernateTemplate().save(dto);
		
		getAuditDao().auditCreation(dto.getEntityName(), dto.getUniqueIdentifier(),
					user);		
	}


	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid
	 *            The primary key for the user
	 * @return User
	 */
	public User get(String cdsid){
		return (User)this.getHibernateTemplate().get(User.class, cdsid);		
	}



	/**
	 * Perform an audit during an update.
	 * 
	 * @param User
	 *            newDto to audit
	 * @param user
	 *            the user performing the update
	 */
	private void audit(User newDto, String user){
		
		User existingDto = (User)getDatabaseEntity(newDto);
		
		// Determine whether any values have changed by building up a combined
		// string
		ButterAuditLog log = new ButterAuditLog(newDto, user);

		log.audit(NamedAttributes.ATTRIBUTE_SURNAME, existingDto.getSurname(),
				newDto.getSurname());

		log.audit(NamedAttributes.ATTRIBUTE_FORENAME, existingDto
				.getFirstName(), newDto.getFirstName());

		log.audit(NamedAttributes.ATTRIBUTE_ACTIVE_YN, existingDto.isActive(),
				newDto.isActive());

		// log all the user roles
		log.audit(User.getRoleDescription(Roles.ROLE_ADMIN), existingDto
				.isAdmin(), newDto.isAdmin());
		
		log.audit(User.getRoleDescription(Roles.ROLE_COUNSELLOR), existingDto
				.isCounsellor(), newDto.isCounsellor());

		log.audit(User.getRoleDescription(Roles.ROLE_IT_ADMIN), existingDto
				.isItAdmin(), newDto.isItAdmin());
		
		log.audit(User.getRoleDescription(Roles.ROLE_DATA_ENTRY), existingDto
				.isDataEntry(), newDto.isDataEntry());

		if (!log.isEmpty()) {
			ButterAudit audit = log.generateAudit();
			getAuditDao().insertAudit(audit, user);
		}
		this.getSession().evict(existingDto);
	}

	/**
	 * Updates a User.
	 * 
	 * @param User
	 *            The data transfer object for a user 
	 */
	public void update(User dto, String user){
		audit(dto, user);
		this.getHibernateTemplate().update(dto);
	}

	/**
	 * Retrieve a collection of users for a specified role. This should not be
	 * used for populating list boxes as the method of reference service should
	 * be used for this.
	 * 
	 * @param roleCode
	 *            The role to retrieve a collection
	 */
	@SuppressWarnings("unchecked")
	public Collection<User> getUsersByRole(String roleCode){
		
		return this.getHibernateTemplate().find("from User user where ? = some elements(user.roles) ", roleCode);
	}

	/**
	 * Searches for existing users
	 * 
	 * @param UserSearchCriteria
	 *            The criteria when searching for users
	 * @return Collection of User
	 */
	@SuppressWarnings("unchecked")
	public Collection<User> search(UserSearchCriteria criteria){
	
		StringBuffer sql = new StringBuffer("from User user where user=user ");
		
		ArrayList<Object> params = new ArrayList<Object>();

		String user = criteria.getUserName();

		if (user != null && user.length() > 0) {
			if (criteria.getUserNameSearchType().equals(
					UserSearchCriteria.SEARCH_TYPE_CONTAINS)) {
				user = "%" + user.toUpperCase() + "%";
			} else {
				user = user.toUpperCase() + "%";
			}

			// Start formatting the first section of the query to search against
			// CDSID
			sql.append(" AND ( UPPER(user.userId) LIKE ? ");
			params.add(user);
			sql.append(" OR    UPPER(user.firstName) LIKE ? ");
			params.add(user);
			sql.append(" OR    UPPER(user.surname) LIKE ? )");
			params.add(user);
		}

		if (criteria.getSelectedRoles() != null) {
			for (int x=0; x< criteria.getSelectedRoles().length; x++){
				sql.append(" and  ? = some elements(user.roles)");
				params.add(criteria.getSelectedRoles()[x]);
			}
		}
		return this.getHibernateTemplate().find(sql.toString(), params.toArray());

	}
}