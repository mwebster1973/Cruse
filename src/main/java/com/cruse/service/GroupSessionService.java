package com.cruse.service;

import java.util.Collection;

import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.group.GroupSession;
import com.cruse.domain.group.GroupSessionSearchCriteria;

public interface GroupSessionService {

	public GroupSession getGroupSession(String id);

	public void insertGroupSession(GroupSession entry, String user) throws ButterEntityValidationException;

	public void updateGroupSession(GroupSession entry, String user)  throws ButterEntityValidationException;

	public Collection<GroupSession> groupSessionSearch(GroupSessionSearchCriteria criteria);
	
}
