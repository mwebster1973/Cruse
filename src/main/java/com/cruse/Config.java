//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   Config.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:42  $
//*
//******************************************************************************
package com.cruse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.butter.config.ButterConfig;
import com.butter.config.ButterConfigDao;
import com.butter.mail.MailConfig;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.GP;
import com.cruse.persistence.admin.AdminDao;
import com.cruse.persistence.admin.GPDao;

/**
 * Configuration object collecting all the database parameters. Using Spring IOC
 * the config object will be passed into objects that require to access the
 * configuration information.
 * 
 * @todo Make all the properties settable.
 * @todo Test the config object is the same object accross requests.
 */
public class Config implements MailConfig {

	// Hashmap of the properties.
	private HashMap<String, String> properties = new HashMap<String, String>();

	// Version of the application. This is passed in by Spring by looking at the
	// manifest
	// file
	private String version;

	// Dao object passed in by spring to retrieve the attributes from the
	// database
	private ButterConfigDao dao;
	private AdminDao adminDao;
	
	private GPDao gpDao;
	
	
	public GPDao getGpDao() {
		return gpDao;
	}

	public void setGpDao(GPDao gpDao) {
		this.gpDao = gpDao;
	}

	private HashMap<String, Collection<Admin>> adminListMap = new HashMap<String, Collection<Admin>>();
		
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * Constructor accepting the dao.
	 */
	public Config(ButterConfigDao dao) throws Exception {
		this.dao = dao;
		refresh();
	}

	/**
	 * Method called to refresh the config.
	 */
	public void refresh() {
		Collection<ButterConfig> dbRows = dao.search();
		for (Iterator<ButterConfig> it = dbRows.iterator(); it.hasNext();) {
			ButterConfig dto = (ButterConfig) it.next();
			this.properties.put(dto.getConfigParam(), dto.getConfigValue());
		}
		this.properties.put("BUILD_VERSION", version);
	
		adminListMap.clear();
	}

	/**
	 * Retrieve the application version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Set the application version.
	 * 
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
		this.properties.put("BUILD_VERSION", version);
	}

	/**
	 * @return Retrieve the database name that the application is configured to.
	 */
	public String getDatabase() {
		return (String) this.properties.get("DATABASE");
	}

	/**
	 * @return Retrieve the version of the application from the manifest file.
	 *         This assumes that the manifest file is within the location
	 */
	public String getBuildVersion() {

		return (String) this.properties.get("BUILD_VERSION");
	}

	/**
	 * Set the build version number. This is called by the application watch
	 * when the application is started
	 * 
	 * @param version
	 */
	public void setBuildVersion(String version) {
		this.properties.put("BUILD_VERSION", version);
	}

	/**
	 * Retrieve the the Database version. This version is stored on the database
	 * 
	 * @return A string for the version
	 */
	public String getDatabaseVersion() {
		return (String) this.properties.get("DB_VERSION");
	}

	/**
	 * @return the location of the SMTP host.
	 */
	public String getEmailSmtpHost() {
		return (String) this.properties.get("EMAIL_SMTP_HOST");
	}

	/**
	 * Retrieve the location of the default address. This is used during testing
	 * to send all email to a dummy account.
	 */
	public String getEmailDefaultAddress() {
		return (String) this.properties.get("EMAIL_DEF_ADDRESS");
	}

	/**
	 * Determin whether the application server should be sending any emails.
	 * 
	 * @return true if emails are to be sent
	 */
	public boolean isEmailOn() {
		String email = (String) this.properties.get("EMAIL_ON");

		String overide = System.getProperty("EMAIL_ON");
		if (overide != null && overide.equalsIgnoreCase("false")) {
			return false;
		}
		return (email != null && email.equals("Y"));
	}

	/**
	 * Retrieve the URI of the application this will be something like
	 * http://localhost:9080/VCPM_WEB/
	 */
	public String getUri() {
		return (String) this.properties.get("WEB_APP_URI");
	}

	/**
	 * Determine whether the application is running in development. This
	 * includes
	 * 
	 */
	public boolean isLocal() {
		//return getDatabase().equalsIgnoreCase("LOCAL");
		return true;
	}

	/**
	 * Determine whether the application is running in development. This
	 * includes
	 * 
	 */
	public boolean isDevelopment() {
		boolean development = getDatabase().equalsIgnoreCase("LOCAL")
				|| getDatabase().equalsIgnoreCase("DEVELOPMENT");
		return development;
	}

	/**
	 * Determine whether the application is running in QA
	 */
	public boolean isQA() {
		return getDatabase().equalsIgnoreCase("QA Test");
	}

	/**
	 * Determine whether the application is running in production
	 */
	public boolean isProduction() {
		return getDatabase().equalsIgnoreCase("PRODUCTION");
	}

	/**
	 * Retrieve the banner message to display at top of all pages
	 */
	public String getBannerMessage() {
		return (String) this.properties.get("BANNER_MESSAGE");
	}

	/**
	 * Determine whether a banner message should be displayed
	 */
	public boolean isBannerMessageOn() {
		String bannerMessageControl = (String) this.properties
				.get("BANNER_MESSAGE_ON");
		return (bannerMessageControl != null && !bannerMessageControl
				.equals("OFF"));
	}

	/**
	 * Retrieve the username to be used for ldap
	 */
	public String getLdapUsername() {
		return (String) this.properties.get("LDAP_USERNAME");
	}

	/**
	 * Retrieve the username to be used for ldap
	 */
	public String getLdapPassword() {
		return (String) this.properties.get("LDAP_PASSWORD");
	}

	/**
	 * Retrieve
	 */
	public String[] getSystemErrorEmailAddresses() {
		String emailAddresses = (String) this.properties
				.get("SYSTEM_ERROR_ADDRESSES");
		return split(emailAddresses);
	}

	public String getEmailFromAddress() {
		return (String) this.properties.get("EMAIL_FROM_ADDRESS");
	}

	/**
	 * Utility method to split a String into a String array
	 * 
	 * @param string
	 * @return String[]
	 */
	protected String[] split(String string) {

		String[] cdsIDs = new String[] {};
		if (string != null) {
			StringTokenizer st = new StringTokenizer(string, ",");

			cdsIDs = new String[st.countTokens()];

			for (int i = 0; st.hasMoreTokens(); i++) {
				cdsIDs[i] = st.nextToken().trim();
			}
		}
		return cdsIDs;
	}

	public String getApplicationName() {
		return "Birmingham Cruse Statistics System";
	}

	/**
	 * Set the Emails on or not. This is set to no in the junit test
	 * 
	 * @param on
	 */
	public void setEmailOn(boolean on) {
		String yesNo = on ? "Y" : "N";
		this.properties.put("EMAIL_ON", yesNo);
	}

	public String getEmailSuffix() {
		return (String) this.properties.get("EMAIL_SUFFIX");
	}
	
	public Collection<Admin> getAdminList(String entityName){
		Collection<Admin> list = adminListMap.get(entityName);
		if (list== null){
			list = adminDao.getAll(entityName);
			adminListMap.put(entityName, list);
		}
		return list;
	}
	
	public Collection<GP> getGPList(){
		return getGpDao().getAll();
	}
	
	
	public HashMap<String, String> getProperties() {
		return properties;
	}

	public ButterConfigDao getDao() {
		return dao;
	}	
}
