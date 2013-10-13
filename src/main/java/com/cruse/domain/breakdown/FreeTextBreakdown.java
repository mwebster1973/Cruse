package com.cruse.domain.breakdown;

public class FreeTextBreakdown extends Breakdown{
	
	public FreeTextBreakdown(String description, String property){
		super(description, property);
	}
	
	@Override
	public void recordAllOptions() {		
	}

	protected void recordCountForProperty(Object propertyValue){
		String description = propertyValue.toString();
		increment(description, null);
	}
}
