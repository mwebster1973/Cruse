package com.cruse.domain.breakdown;


public class BooleanBreakdown extends Breakdown {

	public BooleanBreakdown(String attributeName, String property) {
		super(attributeName, property);
	}

	@Override
	public void recordAllOptions() {
		addOption("Yes", 1);
		addOption("No", 2);
	}
	 
	protected void recordCountForProperty(Object propertyValue) {
		if ((Boolean)propertyValue){
			increment("Yes", 1);
		} else {
			increment("No", 2);
		}
	}
}
