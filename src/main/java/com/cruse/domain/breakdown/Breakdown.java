package com.cruse.domain.breakdown;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.butter.domain.ButterEntity;
import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.admin.Count;
import com.cruse.domain.ethnic.EthnicSearchCriteria;

public abstract class Breakdown extends ButterEntity {
	private String description;
	private String property;
	protected ArrayList<Count> countList = new ArrayList<Count>();
	private HashMap<String, Count> map = new HashMap<String, Count>();
	private int countSequence =0;

	
	protected Breakdown(String description, String property) {
		this.description = description;
		this.property = property;
	}

	public Count addOption(String description, int sequence){
		Count count = new Count(description, sequence);
		countList.add(count);
		map.put(description,count);
		return count;
	}
	
	
	public final void recordCount(Object value){
		
		try{
			Object propertyValue = PropertyUtils.getProperty(value, property);
			if (propertyValue== null){
				return;
			}
			recordCountForProperty(propertyValue);
		} catch (Exception mfe){
			mfe.printStackTrace();
			throw new RuntimeException(mfe);
		}
	}
	
	protected abstract void recordCountForProperty(Object propertyValue);
	
	protected void increment(String description, Integer sequence){
		Count count = map.get(description);
		if (count== null){
			if (sequence == null){
				sequence = ++countSequence;
			}
			count = addOption(description, sequence);
		}
		count.increment();
	}
	

	public String getDescription() {
		return description;
	}

	public ArrayList<Count> getCountList() {
		return countList;
	}

	public void setCountList(ArrayList<Count> countList) {
		this.countList = countList;
	}

	public String getBreakdown(){
		StringBuffer buffy = new StringBuffer();
		for (int x=0; x< countList.size();x++){
			if (x>0){
				buffy.append(",     ");
			}
			buffy.append(countList.get(x).getDescription());
			buffy.append(": <b>");
			buffy.append(countList.get(x).getCount());
			buffy.append("</b>");
		}
		return buffy.toString();
	}
	
	public String getUniqueIdentifier(){
		return this.getDescription();
	}
	
	public void sort(String property){
		if (property.equals(EthnicSearchCriteria.SUMMARY_ORDER_ALPHABETICAL)){
			Collections.sort(countList, new OrderByAphabet());
		} else if (property.equals(EthnicSearchCriteria.SUMMARY_ORDER_DISPLAY)){
			Collections.sort(countList, new OrderByDisplay());
		} else if (property.equals(EthnicSearchCriteria.SUMMARY_ORDER_HIGHEST)){
			Collections.sort(countList, new OrderByHighest());
		}
		
	}
	
	private class OrderByDisplay implements Comparator{

		public int compare(Object o1, Object o2) {
			Count c1 = (Count)o1;
			Count c2 = (Count)o2;
			
			Integer i1 = c1.getSequence();
			Integer i2 = c2.getSequence();
			if (i1.compareTo(i2)!=0){
				return i1.compareTo(i2);
			} else{
				return c1.getDescription().compareTo(c2.getDescription());
			}
		}
	}
	
	/**
	 * Highest then alphebetical
	 * @author martin
	 *
	 */
	private class OrderByHighest implements Comparator{

		public int compare(Object o1, Object o2) {
			Count c1 = (Count)o1;
			Count c2 = (Count)o2;
			
			Integer i1 = c1.getCount();
			Integer i2 = c2.getCount();
			if (i1.compareTo(i2)!=0){
				return 0 - i1.compareTo(i2);
			} else{
				return c1.getDescription().compareTo(c2.getDescription());
			}
		}
	}
	
	private class OrderByAphabet  implements Comparator{

		public int compare(Object o1, Object o2) {
			Count c1 = (Count)o1;
			Count c2 = (Count)o2;
			
			return c1.getDescription().compareTo(c2.getDescription());
			
		}
	}

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "Breakdown";
	}

	@Override
	public void validateEntity() throws ButterEntityValidationException {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void recordAllOptions();
}


