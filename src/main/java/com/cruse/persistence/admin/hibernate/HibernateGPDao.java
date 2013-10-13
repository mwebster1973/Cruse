package com.cruse.persistence.admin.hibernate;

import java.util.Collection;

import org.hibernate.Session;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.butter.domain.ButterEntity;
import com.cruse.config.NamedAttributes;
import com.cruse.domain.admin.GP;
import com.cruse.persistence.CruseHibernateDao;
import com.cruse.persistence.admin.GPDao;

public class HibernateGPDao extends CruseHibernateDao implements GPDao{
	
	/**
	 * Insert a user.
	 * 
	 * @param User
	 *            The data transfer object for a user
	 */
	public void insert(GP dto, String user){
		
		this.getHibernateTemplate().save(dto);
		getAuditDao().auditCreation("GP", dto.getId()+"", user);		
	}


	/**
	 * Retrieve an existing GP
	 * 
	 * @param cdsid
	 *            The primary key for the user
	 * @return User
	 */
	public GP get(String id){
		return (GP)this.getHibernateTemplate().get(GP.class, Integer.parseInt(id));		
	}


	/**
	 * Perform an audit during an update.
	 * 
	 * @param User
	 *            newDto to audit
	 * @param user
	 *            the user performing the update
	 */
	private void audit(GP newDto, String user){
		
		GP existingDto = (GP)getDatabaseGp(newDto.getId(), newDto);
		
		// Determine whether any values have changed by building up a combined
		// string
		ButterAuditLog log = new ButterAuditLog(newDto, user);

		log.audit(NamedAttributes.ATTRIBUTE_SURGERY_NAME, existingDto.getSurgeryName(), newDto.getSurgeryName());
		log.audit(NamedAttributes.ATTRIBUTE_GP_POSTCODE, existingDto.getPostCode(), newDto.getPostCode());
		log.audit(NamedAttributes.ATTRIBUTE_GP_TELEPHONE, existingDto.getTelephoneNumber(), newDto.getTelephoneNumber());

		if (!log.isEmpty()) {
			ButterAudit audit = log.generateAudit();
			getAuditDao().insertAudit(audit, user);
		}
		this.getSession().evict(existingDto);
	}
	
	protected ButterEntity getDatabaseGp(int id, ButterEntity entity){
		Session ses = this.getSessionFactory().openSession();
		ButterEntity existingDto = (ButterEntity)ses.get(GP.class,id);
		if (existingDto== null){
			existingDto = entity;
		}
		ses.close();
		return existingDto;
	}

	/**
	 * Updates a User.
	 * 
	 * @param User
	 *            The data transfer object for a user 
	 */
	public void update(GP dto, String user){
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
	public Collection<GP> getAll(){
		return this.getHibernateTemplate().find("from GP order by surgeryName");
	}
	
}


