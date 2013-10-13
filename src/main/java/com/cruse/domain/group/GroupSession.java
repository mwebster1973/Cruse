package com.cruse.domain.group;

import java.util.Date;

import com.butter.domain.ButterEntity;
import com.butter.exception.ButterEntityValidationException;

public class GroupSession extends ButterEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7504515250495636602L;
	
	private int id;
	
	private Group group;
	private Date sessionDate;
	private int attendedCount;
	private int volenterCount;
	private String notes;
	
	
	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "GroupSession";
	}
	@Override
	public void validateEntity() throws ButterEntityValidationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getUniqueIdentifier() {
		// TODO Auto-generated method stub
		return ""+this.getId();
	}
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
	public Date getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	public int getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(int attendedCount) {
		this.attendedCount = attendedCount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getVolenterCount() {
		return volenterCount;
	}
	public void setVolenterCount(int volunterCount) {
		this.volenterCount = volunterCount;
	}
}
