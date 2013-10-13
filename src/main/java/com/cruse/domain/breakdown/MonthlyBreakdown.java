package com.cruse.domain.breakdown;

import java.util.Calendar;
import java.util.Date;


import com.butter.util.DateUtil;

public class MonthlyBreakdown extends Breakdown{

	private Date startDate;
	private Date endDate;

	public MonthlyBreakdown(String description, String property, Date startDate, Date endDate) {
		super(description, property);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Override
	public void recordAllOptions() {
		if (startDate!= null && endDate!= null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			
			int sequence=0;
			while (cal.getTime().getTime()<= endDate.getTime()){
				String desc = DateUtil.formatDate(cal.getTime(),"MMM-yyyy");
				this.addOption(desc, sequence++);
				cal.add(Calendar.MONTH, 1);
			}
		}
	}
	
	@Override
	protected void recordCountForProperty(Object propertyValue) {
		Date dateValue = (Date)propertyValue;
		String monthlyDate = DateUtil.formatDate(dateValue,"MMM-yyyy");
		increment(monthlyDate, null);
	}
}
