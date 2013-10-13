//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   HibernateDataManager.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:46  $
//*
//******************************************************************************

package com.cruse.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.butter.domain.ButterReference;

public class HibernateDataManager extends HibernateDaoSupport implements DataManager {
	
//	/** 
//	 * Get Collection of JLRReference objects - defaults to all Attribute Values.
//	 * @param categoryAttributeId
//	 * @return Collection of JLRReference objects for categoryAttribute
//	 */
//	public Collection<JLRReference> getAttributeValues(String categoryAttributeId, String filtertext) {
//		
//		String query = "Select distinct av.categoryAttribute.id || '_'  || av.attValue  , av.categoryAttribute.title || ' : ' || av.attValue, av.attValue from AttributeValue as av ";
//		query += " where  av.categoryAttribute.id = " + categoryAttributeId;	
//		query += " and  av.attValue != null ";	
//		if (StringUtil.isPopulated(filtertext)) {
//			query += " and upper(av.attValue) like '%" + filtertext.toUpperCase() + "%'" ;			
//		}
//		query+= " order by av.attValue ";
//		List results = this.getHibernateTemplate().find( query );
//		List<JLRReference> categoryAttributes = createReferenceDtos(results);
//		return categoryAttributes;
//	}
	
	
	public ArrayList<ButterReference> createReferenceDtos(List objectArray) {
		ArrayList<ButterReference> results = new ArrayList<ButterReference>();
		for (Object row : objectArray) {
			if (row instanceof Object[]) { // create a reference dto from a
											// code and description
				Object[] array = (Object[]) row;
				ButterReference dto = new ButterReference(array[0].toString(),
						array[1].toString());
				results.add(dto);
			} else {// create a reference dto from a description
				ButterReference dto = new ButterReference(row.toString());
				results.add(dto);
			}
		}
		return results;
	}






}
