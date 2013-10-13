package com.cruse.persistence.referral;

import java.util.Collection;

import com.cruse.domain.referral.Referral;
import com.cruse.domain.referral.ReferralSearchCriteria;



public interface ReferralDao {
	/**
	 * Insert a referral.
	 * 
	 * @param referral	The entity representing the domain object
	 */
	public void insert (Referral referral, String user);
	
	/**
	 * Retrieve an existing for existing referral.
	 * 
	 * @param id The primary key for the entry
	 * @return Referral
	 */
	public Referral get(String id);
	
	/**
	 * Updates a Referral.
	 * 
	 * @param Referral	the domain object to update
	 */
	public void update (Referral referral, String user);
	
	
	/**
	 * Searches for existing users
	 * 
	 * @param ReferralSearchCriteria	The criteria when searching for referral
	 * @return Collection of Referrals
	 */
	public Collection<Referral> search(ReferralSearchCriteria criteria);
	
	
	public Referral getReferralByNo(String referralNo);

}