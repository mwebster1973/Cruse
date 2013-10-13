package com.cruse.persistence.group.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.group.GroupSession;
import com.cruse.domain.group.GroupSessionSearchCriteria;
import com.cruse.persistence.CruseHibernateDao;
import com.cruse.persistence.group.GroupSessionDao;

public class HibernateGroupSessionDao extends CruseHibernateDao implements GroupSessionDao{

	@Override
	public GroupSession get(String id) {
		return (GroupSession)
			this.getHibernateTemplate().get(GroupSession.class, new Integer(id));		
	}

	@Override
	public void insert(GroupSession dto, String user) {
		this.getHibernateTemplate().save(dto);
		getAuditDao().auditCreation(dto.getEntityName(), dto.getUniqueIdentifier(),
					user);	
	}

	@Override
	public Collection<GroupSession> search(GroupSessionSearchCriteria criteria) {
		StringBuffer sql = new StringBuffer(
		"from GroupSession session where session=session ");

		ArrayList<Object> params = new ArrayList<Object>();
		
		if (criteria.getGroup()!= null){
			sql.append(" AND session.group.code = ? ");
			params.add(criteria.getGroup().getCode());
		}
		if (criteria.getDateRangeStart() != null) {
			sql.append(" AND session.sessionDate >= ?");
			params.add(criteria.getDateRangeStart());
		}
		if (criteria.getDateRangeEnd() != null) {
			sql.append(" AND session.sessionDate <= ?");
			params.add(criteria.getDateRangeEnd());
		}
			
		return this.getHibernateTemplate().find(sql.toString(), params.toArray());
	}

	@Override
	public void update(GroupSession dto, String user) {
		audit(dto, user);
		this.getHibernateTemplate().update(dto);
		
	}
	
	private void audit(GroupSession newDto, String user){
		GroupSession existingDto = (GroupSession)getDatabaseEntityInteger(newDto);
	
	// Determine whether any values have changed by building up a combined
	// string
	ButterAuditLog log = new ButterAuditLog(newDto, user);
	
	if (!log.isEmpty()) {
		ButterAudit audit = log.generateAudit();
		getAuditDao().insertAudit(audit, user);
	}
	this.getSession().evict(existingDto);
	}
}
