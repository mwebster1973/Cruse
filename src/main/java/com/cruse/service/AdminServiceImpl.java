package com.cruse.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.cruse.Config;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.GP;
import com.cruse.domain.group.Group;
import com.cruse.persistence.admin.AdminDao;
import com.cruse.persistence.admin.GPDao;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;
	private GPDao gpDao;
	
	public GPDao getGpDao() {
		return gpDao;
	}

	public void setGpDao(GPDao gpDao) {
		this.gpDao = gpDao;
	}

	private Config config;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * Insert a admin entity.
	 * 
	 * @param User	The data transfer object for a user
	 */
	@Transactional
	public void insert (Admin dto, String user){
		getAdminDao().insert(dto, user);
		config.refresh();
	}
	
	/**
	 * Retrieve an existing for existing User.
	 * 
	 * @param cdsid The primary key for the user
	 * @return User
	 */
	public Admin get(String code, String entityName){
		return getAdminDao().get(code, entityName);
	}
	
	/**
	 * Updates a User.
	 * 
	 * @param User	The data transfer object for a user
	 */
	@Transactional
	public void update(Admin dto, String user){
		getAdminDao().update(dto, user);
		config.refresh();
	}
	
	/**
	 * Retrieve a collection of users for a specified role. This should not be used for populating list boxes
	 * as the method of reference service should be used for this.
	 * @param	roleCode	The role to retrieve a collection
	 */
	public Collection<Admin> getAll(String entityName){
		return getAdminDao().getAll(entityName);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Group> getGroups(){
		Collection groups = this.getAll(Group.ENTITY_NAME_GROUP);
		return (Collection<Group>)groups;
	}

	@Override
	@Transactional
	public void insertGP(GP dto, String user) {
		getGpDao().insert(dto, user);
		
	}

	@Override
	public GP getGP(String id) {
		return getGpDao().get(id);
	}

	@Override
	@Transactional
	public void updateGP(GP dto, String user) {
		getGpDao().update(dto,user);
		
	}

	@Override
	public Collection<GP> getAllGP() {
		return getGpDao().getAll();
	}
}
