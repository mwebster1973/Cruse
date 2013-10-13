package com.cruse.domain.breakdown;

import java.util.List;

public class IntegerBreakdown extends Breakdown {

	private List<Long> options;
	private String suffix;
	
	public IntegerBreakdown(String description, String property, List<Long> allOptions, String suffix) {
		super(description, property);
		options = allOptions;
		this.suffix = suffix;
	}

	@Override
	public void recordAllOptions() {
		int countSequence = 0;
		for (Object val: options){
			String description = val.toString() + suffix;
			addOption(description, ++countSequence);
		}
	}
	
	@Override
	protected void recordCountForProperty(Object propertyValue) {
		increment(propertyValue + suffix, null);
	}
}

