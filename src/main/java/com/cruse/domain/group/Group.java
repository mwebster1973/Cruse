package com.cruse.domain.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cruse.domain.admin.Admin;
import com.cruse.domain.referral.ReferralGroup;

public class Group extends Admin{

	private Set<ReferralGroup> referrals;
	private Set<GroupSession> sessions;
	
	public String getEntityName(){
		return Admin.ENTITY_NAME_GROUP;
	}
	
	
	public Set<ReferralGroup> getReferrals() {
		return referrals;
	}

	public void setReferrals(Set<ReferralGroup> referrals) {
		this.referrals = referrals;
	}


	public List<ReferralGroup> getWaitingList() {
		List<ReferralGroup> grp = new ArrayList<ReferralGroup>();
		for(ReferralGroup rg : getReferrals()){
			if (rg.getStatus()== null){
				continue;
			}
			if (rg.getStatus().equalsIgnoreCase(ReferralGroup.STATUS_WAITING_LIST)){
				grp.add(rg);
			}
		}
		return grp;
	}

	public List<ReferralGroup> getActiveList() {
		List<ReferralGroup> grp = new ArrayList<ReferralGroup>();
		for(ReferralGroup rg : getReferrals()){
			if (rg.getStatus()== null){
				continue;
			}
			if (rg.getStatus().equalsIgnoreCase(ReferralGroup.STATUS_ACTIVE)){
				grp.add(rg);
			}
		}
		return grp;
	}

	public List<ReferralGroup> getClosedList() {
		List<ReferralGroup> grp = new ArrayList<ReferralGroup>();
		for(ReferralGroup rg : getReferrals()){
			if (rg.getStatus()== null){
				continue;
			}
			if (rg.getStatus().equalsIgnoreCase(ReferralGroup.STATUS_CLOSED)){
				grp.add(rg);
			}
		}
		return grp;
	}


	public Set<GroupSession> getSessions() {
		return sessions;
	}


	public void setSessions(Set<GroupSession> sessions) {
		this.sessions = sessions;
	}
	
	public List<GroupSession> getSessionList(){
		List<GroupSession> grp = new ArrayList<GroupSession>();
		for(GroupSession ses : getSessions()){
			grp.add(ses);
		}
		return grp;
	}
	
	public String dumpAttributes() {
		return "";
	}
	
}
