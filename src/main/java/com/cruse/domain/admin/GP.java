package com.cruse.domain.admin;

import com.butter.domain.ButterReferenceEntity;
import com.butter.exception.ButterEntityValidationException;

public class GP extends ButterReferenceEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String surgeryName;
	private String postCode;
	private String telephoneNumber;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String getUniqueDescription() {
		return getSurgeryName();
	}

	@Override
	public String getEntityName() {
		return "GP";
	}

	@Override
	public void validateEntity() throws ButterEntityValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUniqueIdentifier() {
		return getId()+"";
	}
	
	public String getDescription(){
		return this.getSurgeryName() + "," + this.getPostCode() + ","+this.getTelephoneNumber();
	}

}
