package com.cruse.domain.referral;

import java.util.Date;

import com.butter.domain.ButterEntity;
import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.group.Group;

public class ReferralGroup extends ButterEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119574892153043924L;
	
	private int id;
	private Referral referral;
	private Group group;
	private String status;
	private String ending;
	private Date joinedDate;
	private Date closedDate;
	private String notes;
	private Date waitingListDate;
	
	public static final String STATUS_WAITING_LIST = "Waiting List";
	public static final String STATUS_ACTIVE = "Active";
	public static final String STATUS_CLOSED = "Close";
	
	public static final String ENDING_PLANNED = "Planned";
	public static final String ENDING_PREMATURe = "Premature";
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEnding() {
		return ending;
	}
	public void setEnding(String ending) {
		this.ending = ending;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void validateEntity() throws ButterEntityValidationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getUniqueIdentifier() {
		return ""+getId();
	}
	public Referral getReferral() {
		return referral;
	}
	public void setReferral(Referral referral) {
		this.referral = referral;
	}
	
	public String getReferralId(){
		return ""+this.referral.getId();
	}
	public Date getWaitingListDate() {
		return waitingListDate;
	}
	public void setWaitingListDate(Date waitingListDate) {
		this.waitingListDate = waitingListDate;
	}
	
	
	
	
}
