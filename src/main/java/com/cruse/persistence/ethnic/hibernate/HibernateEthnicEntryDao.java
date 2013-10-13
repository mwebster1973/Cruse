package com.cruse.persistence.ethnic.hibernate;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.hibernate.Session;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.butter.domain.ButterEntity;
import com.butter.util.DateUtil;
import com.cruse.config.NamedAttributes;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.ethnic.EthnicSearchCriteria;
import com.cruse.persistence.CruseHibernateDao;
import com.cruse.persistence.ethnic.EthnicEntryDao;

/**
* Hibernate version of the dao to retrieve the user dao.
*/
public class HibernateEthnicEntryDao extends CruseHibernateDao implements EthnicEntryDao {

	public void insert(EthnicEntry dto, String user){
		
		this.getHibernateTemplate().save(dto);
		
		getAuditDao().auditCreation(dto.getEntityName(), dto.getUniqueIdentifier(),
					user);		
	}


	public EthnicEntry get(String id){
		return (EthnicEntry)this.getHibernateTemplate().get(EthnicEntry.class, new Integer(id));		
	}


	/**
	 * Retrieve the database version of an entity. This is useful when performing
	 * an audit and retrieving the old version of that object.
	 * @param entity
	 * @return
	 */
	protected ButterEntity getDatabaseEntity(EthnicEntry entity){
		Session ses = this.getSessionFactory().openSession();
		ButterEntity existingDto = (ButterEntity)ses.get(entity.getClass(), entity.getSeqId());
		if (existingDto== null){
			existingDto = entity;
		}
		ses.close();
		return existingDto;
	}
	

	/**
	 * Perform an audit during an update.
	 * 
	 * @param User
	 *            newDto to audit
	 * @param user
	 *            the user performing the update
	 */
	private void audit(EthnicEntry newDto, String user){
		
		EthnicEntry existingDto = (EthnicEntry)getDatabaseEntity(newDto);
		
		// Determine whether any values have changed by building up a combined
		// string
		ButterAuditLog log = new ButterAuditLog(newDto, user);

		log.audit(NamedAttributes.ATTRIBUTE_DATE_ENTERED,
				DateUtil.formatDate(existingDto.getDateEntererd(), "EEE d MMM yyyy"), 
				DateUtil.formatDate(newDto.getDateEntererd(), "EEE d MMM yyyy"));
		
		log.audit(NamedAttributes.ATTRIBUTE_ETHNIC_ORIGIN,
				existingDto.getEthnicBackground().getDescription(), 
				newDto.getEthnicBackground().getDescription());
		
		log.audit(NamedAttributes.ATTRIBUTE_RELIGION,
				existingDto.getReligion().getDescription(), 
				newDto.getReligion().getDescription());	
				
		log.audit(NamedAttributes.ATTRIBUTE_AREA, 
				existingDto.getArea(),
				newDto.getArea());
		
		log.audit(NamedAttributes.ATTRIBUTE_PCT, 
				existingDto.getPct(),
				newDto.getPct());
		
		log.audit("Carer", 
				existingDto.isCarer(),
				newDto.isCarer());

		log.audit("Registered Disabled", 
				existingDto.isRegisteredDisabled(),
				newDto.isRegisteredDisabled());

		log.audit("Consider themself Disabled", 
				existingDto.isConsiderDisabled(),
				newDto.isConsiderDisabled());
		
		log.audit(NamedAttributes.ATTRIBUTE_GENDER, 
				existingDto.getGender(),
				newDto.getGender());
		
		log.audit(NamedAttributes.ATTRIBUTE_AGE_OF_CLIENT, 
				existingDto.getAgeOfClient(),
				newDto.getAgeOfClient());		

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
	public void update(EthnicEntry dto, String user){
		audit(dto, user);
		this.getHibernateTemplate().update(dto);
	}
	
	private void attachOptions(StringBuffer buffy, String[] options){
		buffy.append("(");
		for (int x=0; x< options.length; x++){
			if (x>0){
				buffy.append(",");
			}
			buffy.append("'"+options[x]+"'");
		}
		buffy.append(")");
	}


	/**
	 * Searches for existing users
	 * 
	 * @param UserSearchCriteria
	 *            The criteria when searching for users
	 * @return Collection of User
	 */
	@SuppressWarnings("unchecked")
	public Collection<EthnicEntry> search(EthnicSearchCriteria criteria){
	
		StringBuffer sql = new StringBuffer("from EthnicEntry entry where entry=entry ");
		
		ArrayList<Object> params = new ArrayList<Object>();

		if (criteria.getIndividualDate()!= null){
			
			sql.append(" AND day(entry.dateEntererd) = ?");
			sql.append(" AND month(entry.dateEntererd) = ?");
			sql.append(" AND year(entry.dateEntererd) = ?");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(criteria.getIndividualDate());
			params.add(cal.get(Calendar.DATE));
			params.add(cal.get(Calendar.MONTH)+1);   
			params.add(cal.get(Calendar.YEAR)); 
		}
		
		if (criteria.getDateRangeStart()!= null){
			sql.append(" AND entry.dateEntererd >= ?");
			params.add(criteria.getDateRangeStart());

		}
		if (criteria.getDateRangeEnd()!= null){
			sql.append(" AND entry.dateEntererd <= ?");
			params.add(criteria.getDateRangeEnd());
		}
		
		String[] keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_AREA);
		if (keys!= null){
			sql.append(" AND entry.area.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_ETHNIC_BACK);
		if (keys!= null){
			sql.append(" AND entry.ethnicBackground.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_RELIGEON);
		if (keys!= null){
			sql.append(" AND entry.religion.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_PCT);
		if (keys!= null){
			sql.append(" AND entry.pct.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys("carer");
		if (keys!= null){
			sql.append(" AND entry.carer IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys("considerDisabled");
		if (keys!= null){
			sql.append(" AND entry.considerDisabled IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys("registeredDisabled");
		if (keys!= null){
			sql.append(" AND entry.registeredDisabled IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_AGE);
		if (keys!= null){
			sql.append(" AND entry.ageOfClient.code IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_GENDER);
		if (keys!= null){
			sql.append(" AND entry.gender.code IN ");
			attachOptions(sql,keys);
		}
		
		return this.getHibernateTemplate().find(sql.toString(), params.toArray());

	}
}