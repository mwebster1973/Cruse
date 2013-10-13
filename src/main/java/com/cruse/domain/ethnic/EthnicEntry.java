package com.cruse.domain.ethnic;

import java.util.Date;

import com.butter.domain.ButterEntity;
import com.butter.exception.ButterEntityValidationException;
import com.cruse.domain.admin.Age;
import com.cruse.domain.admin.Area;
import com.cruse.domain.admin.CauseOfDeath;
import com.cruse.domain.admin.EthnicBackground;
import com.cruse.domain.admin.Gender;
import com.cruse.domain.admin.Pct;
import com.cruse.domain.admin.Religion;

public class EthnicEntry extends ButterEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7504515250495636602L;
	
	private int seqId;
	private EthnicBackground ethnicBackground;
	private Religion religion;
	private Date dateEntererd = new Date();
	
	private Area area;
	private Pct pct;
	private boolean carer;
	private boolean registeredDisabled;
	private boolean considerDisabled;
	
	private Gender gender;
	private Age ageOfClient;
 
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Age getAgeOfClient() {
		return ageOfClient;
	}

	public void setAgeOfClient(Age ageOfClient) {
		this.ageOfClient = ageOfClient;
	}

	public int getSeqId() {
		return seqId;
	}

	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}

	
	public Date getDateEntererd() {
		return dateEntererd;
	}

	public void setDateEntererd(Date dateEntererd) {
		this.dateEntererd = dateEntererd;
	}



	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "EthnicEntry";
	}

	/**
	 * Do any validation
	 */
	public void validateEntity() throws ButterEntityValidationException {
	}

	public String getUniqueIdentifier() {
		return ""+getSeqId();
	}
	
	public EthnicBackground getEthnicBackground() {
		return ethnicBackground;
	}

	public void setEthnicBackground(EthnicBackground ethicBackground) {
		this.ethnicBackground = ethicBackground;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Pct getPct() {
		return pct;
	}

	public void setPct(Pct pct) {
		this.pct = pct;
	}

	public boolean isCarer() {
		return carer;
	}

	public void setCarer(boolean carer) {
		this.carer = carer;
	}

	public boolean isRegisteredDisabled() {
		return registeredDisabled;
	}

	public void setRegisteredDisabled(boolean registeredDisabled) {
		this.registeredDisabled = registeredDisabled;
	}

	public boolean isConsiderDisabled() {
		return considerDisabled;
	}

	public void setConsiderDisabled(boolean considerDisabled) {
		this.considerDisabled = considerDisabled;
	}

}
