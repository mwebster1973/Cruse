package com.cruse.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

import org.springframework.transaction.annotation.Transactional;

import com.butter.exception.ButterEntityValidationException;
import com.cruse.Config;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.breakdown.AdminBreakdown;
import com.cruse.domain.breakdown.BooleanBreakdown;
import com.cruse.domain.breakdown.Breakdown;
import com.cruse.domain.breakdown.FreeTextBreakdown;
import com.cruse.domain.breakdown.IntegerBreakdown;
import com.cruse.domain.breakdown.MonthlyBreakdown;
import com.cruse.domain.referral.Referral;
import com.cruse.domain.referral.ReferralSearchCriteria;
import com.cruse.persistence.referral.ReferralDao;

public class ReferralServiceImpl implements ReferralService {

	private ReferralDao referralDao;
	private Config config;
	

	public ReferralDao getReferralDao() {
		return referralDao;
	}

	public void setReferralDao(ReferralDao referralDao) {
		this.referralDao = referralDao;
	}

	public Collection<Referral> referralSearch(ReferralSearchCriteria criteria) {
		return getReferralDao().search(criteria);
	}
	
	public Referral getReferralByNo(String referralNo){
		return getReferralDao().getReferralByNo(referralNo);
	}
	
	
	
	
	public Collection<Breakdown> referralSummary(ReferralSearchCriteria criteria){
		ArrayList<Referral> results = (ArrayList)this.referralSearch(criteria);
		
		Collection<Breakdown> breakdowns = new ArrayList<Breakdown>();
		
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_CONTACT_BY, "contactBy"));
		breakdowns.add(new AdminBreakdown(config, Admin.ENTITY_NAME_BEREAVED_OF,"bereavedOf"));
		
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_LENGTH,"lengthOfBereavement"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_GENDER,"gender"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_AGE,"ageOfClient"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_HEAR_OF_CRUSE,"hearOfCruse"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_AREA,"area"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_CAUSE_OF_DEATH,"causeOfDeath"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_PCT,"pct"));
		
		breakdowns.add(new BooleanBreakdown("Multiple Loss", "multipleLoss"));
		breakdowns.add(new BooleanBreakdown("Carer","carer"));
		breakdowns.add(new BooleanBreakdown("Home Visit","homeVisit"));
		breakdowns.add(new BooleanBreakdown("Pre-Bereavement","preBereavement"));
		breakdowns.add(new FreeTextBreakdown("Staff Initials","staffInitials"));
		
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_OUTPOST,"outpost"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_ENDING,"ending"));
		
		Date firstDate = null;
		Date lastDate = null;
		if (results.size()>0){
			firstDate = results.get(0).getReferralDate();
			lastDate = results.get(results.size()-1).getReferralDate();

			if (criteria.getDateRangeStart()!= null && criteria.getDateRangeStart().getTime()< firstDate.getTime()){
				firstDate = criteria.getDateRangeStart();
			}
			if (criteria.getDateRangeEnd()!= null && criteria.getDateRangeEnd().getTime()> lastDate.getTime()){
				lastDate = criteria.getDateRangeEnd();
			}
		}
		breakdowns.add(new MonthlyBreakdown("Referral Date","referralDate",firstDate, lastDate));

		ArrayList<Long> waitingListValues = this.orderWaitingLists(results, criteria.isIncludeZero());
		breakdowns.add(new IntegerBreakdown("Days Waiting","daysOnWaitingList", waitingListValues," days"));
		
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_CLIENT_RESIDENCE, "clientResidence"));
		breakdowns.add(new AdminBreakdown(config,Admin.ENTITY_NAME_PLACE_OF_DEATH, "placeOfDeath"));
		
		breakdowns.add(new BooleanBreakdown("Receiving Telephone Support","telephoneSupport"));
		breakdowns.add(new BooleanBreakdown("CORE Completed","coreCompleted"));
		breakdowns.add(new BooleanBreakdown("Service","service"));
		

		// include all viable options
		if (criteria.isIncludeZero()){
			for (Breakdown breakdown: breakdowns){
				breakdown.recordAllOptions();
			}
		}
		
		// record the count from the referrals
		for (Referral e:results){
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
	
	private ArrayList<Long> orderWaitingLists(ArrayList<Referral> referrals, boolean defaults){
		TreeSet<Long> set = new TreeSet<Long>();
		for (Referral r:referrals){
			Long days = r.getDaysOnWaitingList();
			if (days!= null){
				set.add(days);	
			}
		}
		if (defaults && set.size()>0){
			long min = set.first();
			long max = set.last();
			
			for (long x= min; x< max; x++){
				set.add(x);
			}
		}
		
		ArrayList<Long> results = new ArrayList<Long>();
		for (Long res: set){
			results.add(res);
		}
		return results;
	}
	
	
	
	
	

	public Referral getReferral(String id) {
		return getReferralDao().get(id);
	}

	@Override
	@Transactional
	public void insertReferral(Referral entry, String user)
			throws ButterEntityValidationException {
		getReferralDao().insert(entry, user);
		
	}

	@Transactional
	public void updateReferral(Referral entry, String user)
			throws ButterEntityValidationException {
		getReferralDao().update(entry, user);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
