//******************************************************************************
//* Copyright (c) 2009 Butter. All Rights Reserved.
//*
//*   $Workfile:   SystemServiceImpl.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:44  $
//*
//******************************************************************************

package com.cruse.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.butter.audit.ButterAuditDao;
import com.butter.config.ButterConfigDao;
import com.butter.exception.ButterApplicationException;
import com.butter.exception.ButterEntityValidationException;
import com.butter.news.ButterNewsDao;
import com.butter.process.ButterProcessDao;
import com.butter.search.ButterSearchViewDao;
import com.cruse.Config;
import com.cruse.domain.system.NewPasswordRequest;
import com.cruse.domain.system.User;
import com.cruse.domain.system.UserSearchCriteria;
import com.cruse.persistence.system.UserDao;

/**
 * Bean implementation class for Enterprise Bean: SystemService
 */
public class SystemServiceImpl extends com.butter.service.SystemServiceImpl implements SystemService{

	private static final long serialVersionUID = 6232232150617184059L;


	private ButterConfigDao configDao;
	
	private ButterAuditDao auditDao;
	
	private ButterNewsDao newsDao;
	
	private ButterSearchViewDao searchViewDao;
	
	private UserDao userDao;
	
	private Config config;
	
	
	
	/////////////////////////////////
	// Application Specific functionality //
	/////////////////////////////////
		
	 /**
   * Insert a user onto the system
   * @param User object
   * @param CDSID
   * @throws ButterEntityValidationException 
   */
	@Transactional
	public void insertUser(User userDto, String user) throws ButterEntityValidationException{
      	userDto.validateEntity();
		getUserDao().insert(userDto, user);
	}
  
   /**
   * Update a user
   * @param  CDSID
   * @param  User object
   * @throws ButterEntityValidationException 
   */
	@Transactional
	public void updateUser(User userDto, String user) throws ButterEntityValidationException{
     	userDto.validateEntity();
		getUserDao().update(userDto, user);

	}
  
  /**
   * Retrieve a user for a given cdsId
   * @param cdsId  The unique identified for a user
   * @return  The User object
   * @throws ButterResourceException
   */
	public User getUser(String cdsId){
		return getUserDao().get(cdsId);
	}
	
  /**
   * Get a Collection of User objects
   * @return A collection of User objects
   * @throws ButterResourceException
   */
	public Collection userSearch(UserSearchCriteria userSearchCriteriaDto){
      return getUserDao().search(userSearchCriteriaDto);
	}	
	
	/**
	 * Retrieve a collection of users for a specified role. This should not be used for populating list boxes
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 * @throws ButterResourceException
	 */
	public Collection getUsersByRole(String roleCode) {
		return getUserDao().getUsersByRole(roleCode);
	}


	public ButterConfigDao getConfigButterDao(){
		return this.getConfigDao();
	}
	
	public ButterConfigDao getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ButterConfigDao configDao) {
		this.configDao = configDao;
	}

	public ButterAuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(ButterAuditDao auditDao) {
		this.auditDao = auditDao;
	}

	public void setNewsDao(ButterNewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public ButterNewsDao getNewsDao() {
		return newsDao;
	}

	public void setSearchViewDao(ButterSearchViewDao searchViewDao) {
		this.searchViewDao = searchViewDao;
	}

	public ButterSearchViewDao getSearchViewDao() {
		return searchViewDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	protected ButterProcessDao getProcessDao() {
		return null;
	}

	public boolean validLdapUser(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}	
	
	@Transactional
	public void changePasssword(NewPasswordRequest request, User user) throws ButterApplicationException{
		if (!user.isAdmin()){
			if (!request.getUserId().equals(user.getUserId())){
				throw new ButterApplicationException("Invald password request");
			}
		}
		
		User passwordUser = getUserDao().get(request.getUserId());
		passwordUser.setPassword(request.getNewPassword());
		getUserDao().update(passwordUser, user.getUserId());
	}
}

