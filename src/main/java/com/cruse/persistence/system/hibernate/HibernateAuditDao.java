//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   HibernateAuditDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:00  $
//*
//******************************************************************************

package com.cruse.persistence.system.hibernate;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditDao;

public class HibernateAuditDao extends HibernateDaoSupport implements ButterAuditDao {

	public void auditCreation(String parentType, String parentId, String cdsId){

		ButterAudit audit = new ButterAudit();
		audit.setParentId(parentId);
		audit.setParentType(parentType);
		audit.setChangerCDSID(cdsId);
		audit.setChangeDescription("Record Created");
		audit.setEventType(ButterAudit.EVENT_TYPE_INSERT);
		insertAudit(audit, cdsId);
	}
	

	/**
	 * Retrieve audit history for specified parent type and parent id 
	 * @param parentType
	 * @param parentId
	 * @return Collection of AuditDto objects
	 */
	@SuppressWarnings("unchecked")
	public Collection getAuditHistory(String parentType, String parentId){
		return this.getHibernateTemplate().find("from ButterAudit aud where aud.parentType=? and aud.parentId = ?",
				new Object[]{parentType, parentId}
		);
	}


	/**
	 * Insert a Audit onto the system
	 * @param Audit to insert
	 */
	public void insertAudit(ButterAudit audit, String user){
	
		String id = this.getHibernateTemplate().save(audit).toString();
		audit.setId(id);
	}
}