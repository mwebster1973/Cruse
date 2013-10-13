package com.cruse.service;

import java.util.Collection;

import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.ethnic.EthnicSearchCriteria;

/**
* Local interface for Enterprise Bean: SystemService
*/
public interface EthnicService{

	public EthnicEntry getEthnicEntry(String id);

	public void insertEthnicEntry(EthnicEntry entry, String user) throws ButterEntityValidationException;

	public void updateEtnicEntry(EthnicEntry entry, String user)  throws ButterEntityValidationException;

	public Collection<EthnicEntry> ethnicSearch(EthnicSearchCriteria criteria);
	
	public Collection<Breakdown> ethnicSummary(EthnicSearchCriteria criteria);
}