package com.cruse.domain.ethnic;

import java.util.ArrayList;
import java.util.Date;

import com.butter.search.ButterCriteria;
import com.butter.util.CombinedKeyUtil;
import com.butter.util.DateUtil;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.breakdown.BooleanBreakdown;

public class EthnicSearchCriteria extends ButterCriteria{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4519096439146763503L;

	private Date individualDate;
	
	private Date dateRangeStart;
	private Date dateRangeEnd;
	
	private String[] combinedEthnicBackgrounds;
	private String[] combinedReligions;
	
	
	public static final String SUMMARY_ORDER_DISPLAY = "display";
	public static final String SUMMARY_ORDER_HIGHEST = "highest";
	public static final String SUMMARY_ORDER_ALPHABETICAL = "alpha";
	private String summaryOrder = SUMMARY_ORDER_DISPLAY;
	
	private boolean includeZero = false;
	
	private String attribute;
	private String[] attributeSelections;


	
	
	
	protected void attachDescriptions() {
		if (individualDate!= null){
			super.appendCriteriaDescription("Individual date", DateUtil.formatDate(individualDate, "EEE d MMM yyyy"));
			
		}
		
		if (dateRangeStart!= null && dateRangeEnd!= null){
			StringBuffer desc = new StringBuffer();
			desc.append(DateUtil.formatDate(dateRangeStart, "EEE d MMM yyyy"));
			desc.append(" and ");
			desc.append(DateUtil.formatDate(dateRangeEnd, "EEE d MMM yyyy"));
			super.appendCriteriaDescription("Date range between", desc.toString());
		}
		else if (dateRangeStart!=null ){
			super.appendCriteriaDescription("Dates from", DateUtil.formatDate(dateRangeStart, "EEE d MMM yyyy"));
		}
		else if (dateRangeEnd!=null ){
			super.appendCriteriaDescription("Dates to", DateUtil.formatDate(dateRangeEnd, "EEE d MMM yyyy"));
		}		
		
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_ETHNIC_BACK), getSelectedDescriptions(Admin.ENTITY_NAME_ETHNIC_BACK));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_RELIGEON), getSelectedDescriptions(Admin.ENTITY_NAME_RELIGEON));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_AREA), getSelectedDescriptions(Admin.ENTITY_NAME_AREA));
		super.appendCriteriaDescription(Admin.getEntitySingular(Admin.ENTITY_NAME_PCT), getSelectedDescriptions(Admin.ENTITY_NAME_PCT));
		super.appendCriteriaDescription("Carer", getSelectedDescriptions("carer"));
		super.appendCriteriaDescription("Registered Disabled", getSelectedDescriptions("registeredDisabled"));
		super.appendCriteriaDescription("Consider themself Disabled", getSelectedDescriptions("considerDisabled"));
		
		if (super.criteriaDescriptions.size()==0){
			super.appendCriteriaDescription("Search", "All");
		}
	}
	
	public String[] getSelectedEthnicBackgroundDescriptions(){
		return CombinedKeyUtil.splitDescriptions(combinedEthnicBackgrounds);
	}

	public String[] getSelectedEthnicBackgroundKeys(){
		return CombinedKeyUtil.splitKeys(combinedEthnicBackgrounds);
	}
	
	public String[] getSelectedReligionsDescriptions(){
		return CombinedKeyUtil.splitDescriptions(combinedReligions);
	}

	public String[] getSelectedReligionsKeys(){
		return CombinedKeyUtil.splitKeys(combinedReligions);
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
	public String[] getCombinedEthnicBackgrounds() {
		return combinedEthnicBackgrounds;
	}
	public void setCombinedEthnicBackgrounds(String[] combinedEthnicBackgrounds) {
		this.combinedEthnicBackgrounds = combinedEthnicBackgrounds;
	}
	public String[] getCombinedReligions() {
		return combinedReligions;
	}
	public void setCombinedReligions(String[] combinedReligions) {
		this.combinedReligions = combinedReligions;
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
	

}
