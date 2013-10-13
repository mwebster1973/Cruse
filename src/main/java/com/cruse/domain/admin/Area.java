package com.cruse.domain.admin;

public class Area extends Admin{

	private Pct pct;
	
	public String getEntityName(){
		return Admin.ENTITY_NAME_AREA;
	}

	public Pct getPct() {
		return pct;
	}

	public void setPct(Pct pct) {
		this.pct = pct;
	}
}
