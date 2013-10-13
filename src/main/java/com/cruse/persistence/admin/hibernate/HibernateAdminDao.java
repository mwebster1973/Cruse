package com.cruse.persistence.admin.hibernate;

import java.util.Collection;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.cruse.config.NamedAttributes;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Area;
import com.cruse.persistence.CruseHibernateDao;
import com.cruse.persistence.admin.AdminDao;

public class HibernateAdminDao extends CruseHibernateDao implements AdminDao{
	
	/**
	 * Insert a user.
	 * 
	 * @param User
	 *            The data transfer object for a user
	 */
	public void insert(Admin dto, String user){
		
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
	public Admin get(String code, String entityName){
		return (Admin)this.getHibernateTemplate().get(Admin.getEntityClass(entityName), code);		
	}


	/**
	 * Perform an audit during an update.
	 * 
	 * @param User
	 *            newDto to audit
	 * @param user
	 *            the user performing the update
	 */
	private void audit(Admin newDto, String user){
		
		Admin existingDto = (Admin)getDatabaseEntity(newDto);
		
		// Determine whether any values have changed by building up a combined
		// string
		ButterAuditLog log = new ButterAuditLog(newDto, user);

		log.audit(NamedAttributes.ATTRIBUTE_CODE, existingDto.getCode(),
				newDto.getCode());

		log.audit(NamedAttributes.ATTRIBUTE_DESCRIPTION, existingDto
				.getDescription(), newDto.getDescription());

		log.audit(NamedAttributes.ATTRIBUTE_SEQUENCE, existingDto.getSequence(),
				newDto.getSequence());

		if (existingDto instanceof Area || newDto instanceof Area)
		log.audit(NamedAttributes.ATTRIBUTE_PCT, ((Area)existingDto).getPct(),
				((Area)newDto).getPct());

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
	public void update(Admin dto, String user){
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
	public Collection<Admin> getAll(String entityName){
		return this.getHibernateTemplate().find("from "+ entityName + " order by sequence, code");
	}

	
}
