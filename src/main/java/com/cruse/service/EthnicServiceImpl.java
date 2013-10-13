package com.cruse.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.butter.exception.ButterEntityValidationException;
import com.cruse.Config;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.breakdown.AdminBreakdown;
import com.cruse.domain.breakdown.BooleanBreakdown;
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.ethnic.EthnicEntry;
import com.cruse.domain.ethnic.EthnicSearchCriteria;
import com.cruse.domain.referral.Referral;
import com.cruse.persistence.ethnic.EthnicEntryDao;

public class EthnicServiceImpl implements EthnicService {

	private EthnicEntryDao ethnicEntryDao;
	private Config config;
	

	public EthnicEntryDao getEthnicEntryDao() {
		return ethnicEntryDao;
	}

	public void setEthnicEntryDao(EthnicEntryDao ethnicEntryDao) {
		this.ethnicEntryDao = ethnicEntryDao;
	}

	public Collection<EthnicEntry> ethnicSearch(EthnicSearchCriteria criteria) {
		return ethnicEntryDao.search(criteria);
	}
	
	public Collection<Breakdown> ethnicSummary(EthnicSearchCriteria criteria){
		Collection<EthnicEntry> results = this.ethnicSearch(criteria);
		
		Collection<Breakdown> breakdowns = new ArrayList<Breakdown>();
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_ETHNIC_BACK,"ethnicBackground"));
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_RELIGEON, "religion"));
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_AREA, "area"));
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_PCT, "pct"));
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_AGE, "ageOfClient"));
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_GENDER, "gender"));
		breakdowns.add(new BooleanBreakdown("Carer", "carer"));
		breakdowns.add(new BooleanBreakdown("Registered Disabled", "registeredDisabled"));
		breakdowns.add(new BooleanBreakdown("Consider themself Disabled", "considerDisabled"));
		
		// include all viable options
		if (criteria.isIncludeZero()){
			for (Breakdown breakdown: breakdowns){
				breakdown.recordAllOptions();
			}
		}
		
		// record the count from the referrals
		for (EthnicEntry e:results){
			for (Breakdown breakdown: breakdowns){
				breakdown.recordCount(e);
			}
		}
		
		// order breakdowns
		for (Breakdown breakdown: breakdowns){
			breakdown.sort(criteria.getSummaryOrder());
		}
		return breakdowns;
	}

	public EthnicEntry getEthnicEntry(String id) {
		return ethnicEntryDao.get(id);
	}

	@Override
	@Transactional
	public void insertEthnicEntry(EthnicEntry entry, String user)
			throws ButterEntityValidationException {
		ethnicEntryDao.insert(entry, user);
		
	}

	@Transactional
	public void updateEtnicEntry(EthnicEntry entry, String user)
			throws ButterEntityValidationException {
		ethnicEntryDao.update(entry, user);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
