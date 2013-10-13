package com.cruse.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.group.GroupSession;
import com.cruse.domain.group.GroupSessionSearchCriteria;
import com.cruse.persistence.group.GroupSessionDao;

public class GroupSessionServiceImpl implements GroupSessionService{

	private GroupSessionDao dao;
	
	
	@Override
	public GroupSession getGroupSession(String id) {
		return getDao().get(id);
	}

	@Override
	public Collection<GroupSession> groupSessionSearch(
			GroupSessionSearchCriteria criteria) {
		return getDao().search(criteria);
	}

	@Override
	@Transactional
	public void insertGroupSession(GroupSession entry, String user)
			throws ButterEntityValidationException {
		getDao().insert(entry, user);
		
	}

	@Override
	@Transactional	
	public void updateGroupSession(GroupSession entry, String user)
			throws ButterEntityValidationException {
		getDao().update(entry, user);
		
	}

	public GroupSessionDao getDao() {
		return dao;
	}

	public void setDao(GroupSessionDao dao) {
		this.dao = dao;
	}

}
