package com.cruse.domain.admin;

import java.util.ArrayList;
import java.util.Collection;

public class Count {

	private int count;
	private String description;
	private int sequence;
	
	public String getUniqueIdentifier(){
		return description;
	}
	
	public Count(String description, int newSequence) {
		this.description = description;
		this.sequence = newSequence;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void increment() {
		count++;
	}

	public String getDescription() {
		return description;
	}

	public int getSequence() {
		return sequence;
	}

}
