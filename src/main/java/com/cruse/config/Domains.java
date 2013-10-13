//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   Domains.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:50:14  $
//*
//******************************************************************************

package com.cruse.config;


/**
* Domains used for ref values
*/
public class Domains {

//	public static final String DOMAIN_ENGINES 		 	= "ENGINES";
//	public static final String DOMAIN_TRANSMISSIONS  	= "TRANSMISSIONS";
//	public static final String DOMAIN_DRIVES 		 	= "DRIVES";
//	public static final String DOMAIN_OIL_ACTIVITIES 	= "OIL_ACTIVITIES";
//	public static final String DOMAIN_FLUIDS 			= "FLUIDS";
//	public static final String DOMAIN_TEST_LOG_STATUSES = "TEST_LOG_STATUSES";
	public static final String DOMAIN_DEPARMENTS 		= "DEPARMENTS";
//	public static final String DOMAIN_GIS 				= "GIS";
//	public static final String DOMAIN_EVALUATIONS 		= "EVALUATIONS";
	
	/**
	 * Return a singular version of the name of the domain
	 */
	public static String getDomainDescription(String domainCode){
		
//		if (domainCode.equals(DOMAIN_ENGINES)) return "Engine";
//		
//		if (domainCode.equals(DOMAIN_TRANSMISSIONS)) return "Transmission";
//		
//		if (domainCode.equals(DOMAIN_DRIVES)) return "Drive";
//		
//		if (domainCode.equals(DOMAIN_OIL_ACTIVITIES)) return "Oil Activity";
//		
//		if (domainCode.equals(DOMAIN_TEST_LOG_STATUSES)) return "Test Log Status";
//		
//		if (domainCode.equals(DOMAIN_FLUIDS)) return "Fluid";
//		
		if (domainCode.equals(DOMAIN_DEPARMENTS)) return "Deparment";
//		
//		if (domainCode.equals(DOMAIN_GIS)) return "GIS Number";
//		
//		if (domainCode.equals(DOMAIN_EVALUATIONS)) return "Evaluation";
		
		// if can't fin then throw illegal argument exception
		throw new IllegalArgumentException("Domain" + domainCode+ "is not recognised");		
	}
	
	/**
	 * Return a plural version of the domain. By default this just adds S
	 */
	public static String getDomainsDescription(String domainCode){
		
//		if (domainCode.equals(DOMAIN_TEST_LOG_STATUSES)) {
//			return "Test Log Statuses";
//		} else if (domainCode.equals(DOMAIN_OIL_ACTIVITIES)) {
//			return "Oil Activities";
//		} 
		return getDomainDescription(domainCode) + "s";
	}
	
}
