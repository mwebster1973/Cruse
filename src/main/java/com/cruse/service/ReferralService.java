package com.cruse.service;


import java.util.Collection;

import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.referral.Referral;
import com.cruse.domain.referral.ReferralSearchCriteria;


/**
* Interface for referral service
*/
public interface ReferralService{

	public Referral getReferral(String id);

	public void insertReferral(Referral entry, String user) throws ButterEntityValidationException;

	public void updateReferral(Referral entry, String user)  throws ButterEntityValidationException;

	public Collection<Referral> referralSearch(ReferralSearchCriteria criteria);
	
	public Collection<Breakdown> referralSummary(ReferralSearchCriteria criteria);
	
	public Referral getReferralByNo(String referralNo);
}