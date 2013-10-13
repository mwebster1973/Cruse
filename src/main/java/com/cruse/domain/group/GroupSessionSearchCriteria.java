package com.cruse.domain.group;


import java.util.Date;

import com.butter.search.ButterCriteria;
import com.butter.util.DateUtil;

public class GroupSessionSearchCriteria extends ButterCriteria{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4519096439146763503L;
	
	private Date dateRangeStart;
	private Date dateRangeEnd;
	
	private Group group;
	
	protected void attachDescriptions() {
		
		String dateDesc = "Session Date";
		
		if (dateRangeStart!= null && dateRangeEnd!= null){
			StringBuffer desc = new StringBuffer();
			desc.append(DateUtil.formatDate(dateRangeStart, "EEE d MMM yyyy"));
			desc.append(" and ");
			desc.append(DateUtil.formatDate(dateRangeEnd, "EEE d MMM yyyy"));
			super.appendCriteriaDescription(dateDesc + " between", desc.toString());
		}
		else if (dateRangeStart!=null ){
			super.appendCriteriaDescription(dateDesc+ " from", DateUtil.formatDate(dateRangeStart, "EEE d MMM yyyy"));
		}
		else if (dateRangeEnd!=null ){
			super.appendCriteriaDescription(dateDesc+ " to", DateUtil.formatDate(dateRangeEnd, "EEE d MMM yyyy"));
		}
		
		if (group!= null){
			super.appendCriteriaDescription("Group", group.getDescription());
		}
		
		if (super.criteriaDescriptions.size()==0){
			super.appendCriteriaDescription("Search", "All");
		}
	}
	
	public void reset() {
		
	}

	public Date getDateRangeStart() {
		return dateRangeStart;
	}
	public void setDateRangeStart(Date dateRangeStart) {
		this.dateRangeStart = dateRangeStart;
	}
	public Date getDateRangeEnd() {
		return dateRangeEnd;
	}
	public void setDateRangeEnd(Date dateRangeEnd) {
		this.dateRangeEnd = dateRangeEnd;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
