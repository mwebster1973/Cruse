//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   HibernateSearchViewDao.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:56  $
//*
//******************************************************************************

package com.cruse.persistence.system.hibernate;

import java.util.Collection;
import java.util.List;

import com.butter.search.ButterSearchView;
import com.butter.search.ButterSearchViewDao;
import com.cruse.persistence.CruseHibernateDao;

/**
 * Default implementation of the saved search functionality
 */
public class HibernateSearchViewDao extends CruseHibernateDao implements
		ButterSearchViewDao {

	/**
	 * Protected constructor
	 */
	public HibernateSearchViewDao() {
	}

	/**
	 * @param searchViewId
	 */
	public void removeSearch(String searchViewId){		
		getHibernateTemplate().delete(loadSearch(searchViewId));
		
	}

	/**
	 * Retrieve all saved searches for the user.
	 * 
	 * @param cdsid
	 * @return Collection of ReferenceDto's
	 */
	@SuppressWarnings("unchecked")
	public Collection<ButterSearchView> getSavedSearches(String cdsid){
		return this.getHibernateTemplate().find(
				"from ButterSearchView view where view.cdsid = ? and view.criteriaBlob is not null",
				cdsid.toUpperCase());
	}

	/**
	 * @param cdsid
	 * @param tableId
	 * @return Collection of ReferenceDto's
	 */
	@SuppressWarnings("unchecked")
	public Collection<ButterSearchView> getSavedSearches(String cdsid, String tableId){
		return this
				.getHibernateTemplate()
				.find(
						"from ButterSearchView view where view.cdsid = ? and view.tableId=? and view.criteriaBlob is not null",
						new Object[] { cdsid.toUpperCase(), tableId });
	}

	/**
	 * @param searchViewId
	 * @return SearchViewDto
	 */
	public ButterSearchView loadSearch(String searchViewId){
		ButterSearchView searchViewDto = (ButterSearchView) this
				.getHibernateTemplate().get(ButterSearchView.class,
						searchViewId);

		return searchViewDto;
	}

	/**
	 * @param searchViewDto
	 */
	public void insertSearch(ButterSearchView searchViewDto){

		String id = this.getHibernateTemplate().save(searchViewDto).toString();
		searchViewDto.setId(id);		
	}

	/**
	 * @param userContext
	 * @param searchViewDto
	 */
	public void saveUserView(String cdsId, ButterSearchView searchViewDto){

		// Establish sequence id for supplied table id for user view

		ButterSearchView existingSearchViewDto = loadUserView(cdsId,
				searchViewDto.getTableId());
		if (existingSearchViewDto != null) {
			this.getHibernateTemplate().delete(existingSearchViewDto);
		}
		this.insertSearch(searchViewDto);		
	}


	/**
	 * @param userContext
	 * @param tableId
	 * @return SearchViewDto
	 */
	@SuppressWarnings("unchecked")
	public ButterSearchView loadUserView(String cdsId, String tableId){

		ButterSearchView userViewDto = null;
		List<ButterSearchView> col = this
				.getHibernateTemplate()
				.find(
						"from ButterSearchView view where view.cdsid = ? and view.tableId=? and view.criteriaBlob is null",
						new Object[] { cdsId.toUpperCase(),
								tableId });
		if (col != null && col.size()>0) {
			userViewDto = col.get(0);
		}

		return userViewDto;
	}
}
