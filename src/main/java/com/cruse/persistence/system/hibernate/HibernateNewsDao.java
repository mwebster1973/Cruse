//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   HibernateNewsDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:02  $
//*
//******************************************************************************

package com.cruse.persistence.system.hibernate;
//****************************************************************
//*
//* $Workfile:   HibernateNewsDao.java  $
//* $Revision:   1.0  $
//* $Author:   mwebst28  $
//* $Date:   Oct 29 2009 14:50:02  $
//*
//*****************************************************************


import java.sql.Timestamp;
import java.util.Collection;

import com.butter.news.ButterNews;
import com.butter.news.ButterNewsDao;
import com.cruse.persistence.CruseHibernateDao;
public class HibernateNewsDao extends CruseHibernateDao implements ButterNewsDao{


	
	/**
	 * Retrieve all the news items
	 */	
	@SuppressWarnings("unchecked")
	public Collection<ButterNews> getAllNews(){
		return this.getHibernateTemplate().find("from ButterNews news order by news.startDate desc");
	}
	
	/**
	 * Retrieve all the news items
	 */
	@SuppressWarnings("unchecked")
	public Collection<ButterNews> getCurrentNews(){
		Timestamp queryDate = new Timestamp(new java.util.Date().getTime());
		
		return this.getHibernateTemplate().find("from ButterNews news " +
				"where news.startDate<= ? AND "+
				"( news.endDate >= ? OR news.endDate is null) "+ 
				"order by news.startDate desc", new Object[]{queryDate, queryDate});
		
	}

	/**
	 * Retrieve a single news items
	 */
	public ButterNews getNews(String newsId){
		return (ButterNews)getHibernateTemplate().get(ButterNews.class, newsId);				
	}

	/**
	 * Insert a single news item
	 */
	public String insertNews(ButterNews dto){
		String id = getHibernateTemplate().save(dto).toString();
		dto.setId(id);
		return id;	
	}

	/**
	 * Update a news item
	 */
	public void updateNews(ButterNews dto){
		this.getHibernateTemplate().update(dto);
	}

}
