package com.cruse.domain.referral;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.butter.search.ButterCriteria;
import com.butter.util.CombinedKeyUtil;
import com.butter.util.DateUtil;
import com.cruse.domain.admin.Admin;

public class ReferralSearchCriteria extends ButterCriteria{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4519096439146763503L;

	private Date individualDate;
	
	private Date dateRangeStart;
	private Date dateRangeEnd;
	
	public static final String DATE_TYPE_REFERRAL="R";
	public static final String DATE_TYPE_ALLOCATION="A";
	public static final String DATE_TYPE_CLOSURE="C";
	
	
	public static final String SUMMARY_ORDER_DISPLAY = "display";
	public static final String SUMMARY_ORDER_HIGHEST = "highest";
	public static final String SUMMARY_ORDER_ALPHABETICAL = "alpha";
	private String summaryOrder = SUMMARY_ORDER_DISPLAY;
	
	private boolean includeZero = false;

	private String dateType = DATE_TYPE_REFERRAL;
		
		
	private String attribute;
	private String[] attributeSelections;
	
	private String clientInitials;
	private String clientName;
	
	protected void attachDescriptions() {
		
		
		super.appendCriteriaDescription("Client Initials", clientInitials);
		super.appendCriteriaDescription("Client Name", clientName);
		
		
		String dateDesc = "Referral Date";
		if (this.getDateType().equals(DATE_TYPE_ALLOCATION)){
			dateDesc = "Allocation Date";
		} else if (this.getDateType().equals(DATE_TYPE_CLOSURE)){
			dateDesc = "Closure Date";
		}
		if (individualDate!= null){
			super.appendCriteriaDescription(dateDesc, DateUtil.formatDate(individualDate, "EEE d MMM yyyy"));	
		}
		

		
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
		
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_AGE), getSelectedDescriptions(Admin.ENTITY_NAME_AGE));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_AREA), getSelectedDescriptions(Admin.ENTITY_NAME_AREA));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_BEREAVED_OF), getSelectedDescriptions(Admin.ENTITY_NAME_BEREAVED_OF));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_CAUSE_OF_DEATH), getSelectedDescriptions(Admin.ENTITY_NAME_CAUSE_OF_DEATH));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_CONTACT_BY), getSelectedDescriptions(Admin.ENTITY_NAME_CONTACT_BY));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_GENDER), getSelectedDescriptions(Admin.ENTITY_NAME_GENDER));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_HEAR_OF_CRUSE), getSelectedDescriptions(Admin.ENTITY_NAME_HEAR_OF_CRUSE));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_LENGTH), getSelectedDescriptions(Admin.ENTITY_NAME_LENGTH));
		super.appendCriteriaDescription("Multiple Loss", getSelectedDescriptions("multipleLoss"));
		super.appendCriteriaDescription("Carer", getSelectedDescriptions("carer"));
		super.appendCriteriaDescription("Home Visit", getSelectedDescriptions("homeVisit"));
		super.appendCriteriaDescription("Pre-Bereavement", getSelectedDescriptions("preBereavement"));
		super.appendCriteriaDescription("Receiving Telephone Support", getSelectedDescriptions("telephoneSupport"));
		super.appendCriteriaDescription("CORE Completed", getSelectedDescriptions("coreCompleted"));
		super.appendCriteriaDescription("Service", getSelectedDescriptions("service"));
		
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_OUTPOST), getSelectedDescriptions(Admin.ENTITY_NAME_OUTPOST));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_ENDING), getSelectedDescriptions(Admin.ENTITY_NAME_ENDING));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_CLIENT_RESIDENCE), getSelectedDescriptions(Admin.ENTITY_NAME_CLIENT_RESIDENCE));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_PLACE_OF_DEATH), getSelectedDescriptions(Admin.ENTITY_NAME_PLACE_OF_DEATH));


		
		
		if (super.criteriaDescriptions.size()==0){
			super.appendCriteriaDescription("Search", "All");
		}
	}
	
	public void reset() {
		
	}

	
	public Date getIndividualDate() {
		return individualDate;
	}
	public void setIndividualDate(Date individualDate) {
		this.individualDate = individualDate;
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
	
	public boolean isIncludeZero() {
		return includeZero;
	}

	public void setIncludeZero(boolean includeZero) {
		this.includeZero = includeZero;
	}

	public String getSummaryOrder() {
		return summaryOrder;
	}

	public void setSummaryOrder(String summaryOrder) {
		this.summaryOrder = summaryOrder;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String[] getAttributeSelections() {
		return attributeSelections;
	}
	
	
	
	public String getClientInitials() {
		return clientInitials;
	}

	public void setClientInitials(String clientInitials) {
		this.clientInitials = clientInitials;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String[] getSelectedKeys(String entityName){
		if (attributeSelections== null || attributeSelections.length==0){
			return null;
		}
		String[] keys = CombinedKeyUtil.splitKeys(attributeSelections);
		ArrayList<String> selections = new ArrayList<String>();
		for (String key:keys){
			String entity = CombinedKeyUtil.splitKey(key, ";;").substring(key.indexOf(".")+1);
			
			if (entity.equalsIgnoreCase(entityName)){
				selections.add(CombinedKeyUtil.splitDescription(key, ";;"));
			}
		}
		if (selections.size()==0){
			return null;
		}
		String[] results = new String[selections.size()];
		int x=0;
		for (String result: selections){
			results[x++] = result;
		}
		return results;
	}
	
	public String[] getSelectedDescriptions(String entityName){
		if (attributeSelections== null || attributeSelections.length==0){
			return null;
		}
		String[] keys = CombinedKeyUtil.splitKeys(attributeSelections);
		ArrayList<String> selections = new ArrayList<String>();
		int pos = 0;
		for (String key:keys){
			String entity = CombinedKeyUtil.splitKey(key, ";;").substring(key.indexOf(".")+1);
			
			if (entity.equalsIgnoreCase(entityName)){
				String desc = CombinedKeyUtil.splitDescription(attributeSelections[pos]);
				desc = desc.substring(0, desc.indexOf("("));
				selections.add(desc);
			}
			pos++;
		}
		if (selections.size()==0){
			return null;
		}
		String[] results = new String[selections.size()];
		int x=0;
		for (String result: selections){
			results[x++] = result;
		}
		return results;
	}

	

	public void setAttributeSelections(String[] attributeSelections) {
		this.attributeSelections = attributeSelections;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
}
