//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   HibernateButterConfigDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:02  $
//*
//******************************************************************************

package com.cruse.persistence.system.hibernate;

import java.util.Collection;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.butter.config.ButterConfig;
import com.butter.config.ButterConfigDao;
import com.cruse.persistence.CruseHibernateDao;


public class HibernateConfigDao extends CruseHibernateDao implements ButterConfigDao{
	
	public ButterConfig get(String parameter) {
		return (ButterConfig)getHibernateTemplate().get(ButterConfig.class, parameter);
	}

	public String insert(ButterConfig dto, String user){
		Object id = getHibernateTemplate().save(dto);	
		
		// after the insert do the audit.
		getAuditDao().auditCreation(dto.getEntityName(), dto.getUniqueIdentifier(), user);
		return id.toString();		
	}

	@SuppressWarnings("unchecked")
	public Collection<ButterConfig> search(){
		return getHibernateTemplate().find("from ButterConfig");
	}

	public void update(ButterConfig dto, String user){
		audit(dto, user);
		getHibernateTemplate().update(dto);
	}
	
	/**
	 * Perform an audit during an update.
	 * @param RestraintTypeDto newDto
	 * @param user
	 */
	private void audit(ButterConfig newDto, String user){

		ButterConfig existingDto = (ButterConfig)super.getDatabaseEntity(newDto);
		
		// Determine whether any values have changed by building up a combined string
		ButterAuditLog log = new ButterAuditLog(newDto, user);
		
		log.audit("Description" ,
				existingDto.getConfigDescription(),
				  newDto.getConfigDescription());
		
		log.audit("Value",
				existingDto.getConfigValue(),
				  newDto.getConfigValue());
		
		if (!log.isEmpty()){
			ButterAudit audit = log.generateAudit();
			getAuditDao().insertAudit(audit, user);
		}
	}
}
