//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   CruseHibernateDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:44  $
//*
//******************************************************************************

package com.cruse.persistence;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.butter.audit.ButterAuditDao;
import com.butter.domain.ButterEntity;

public class CruseHibernateDao extends HibernateDaoSupport{
	
	@Autowired
	protected ButterAuditDao auditDao;

	public void setAuditDao(ButterAuditDao auditDao) {
		this.auditDao = auditDao;
	}

	/**
	 * Retrieve the database version of an entity. This is useful when performing
	 * an audit and retrieving the old version of that object.
	 * @param entity
	 * @return
	 */
	protected ButterEntity getDatabaseEntity(ButterEntity entity){
		Session ses = this.getSessionFactory().openSession();
		ButterEntity existingDto = (ButterEntity)ses.get(entity.getClass(), entity.getUniqueIdentifier());
		if (existingDto== null){
			existingDto = entity;
		}
		ses.close();
		return existingDto;
	}
	
	protected ButterEntity getDatabaseEntityInteger(ButterEntity entity){
		Session ses = this.getSessionFactory().openSession();
		ButterEntity existingDto = (ButterEntity)ses.get(entity.getClass(), new Integer( entity.getUniqueIdentifier()));
		if (existingDto== null){
			existingDto = entity;
		}
		ses.close();
		return existingDto;
	}

	public ButterAuditDao getAuditDao() {
		return auditDao;
	}
}
