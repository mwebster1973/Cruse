package com.cruse.domain.admin;

import java.util.ArrayList;
import java.util.Collection;

import com.butter.domain.ButterReference;
import com.butter.domain.ButterReferenceEntity;
import com.butter.exception.ButterEntityValidationException;
import com.butter.util.CombinedKeyUtil;
import com.cruse.domain.group.Group;

/**
 * Basic entity of all the Admin entities.
 */
public abstract class Admin extends ButterReferenceEntity{
	
	public static final String ENTITY_NAME_AGE = "Age";
	public static final String ENTITY_NAME_AREA = "Area";
	public static final String ENTITY_NAME_BEREAVED_OF = "BereavedOf";
	public static final String ENTITY_NAME_CAUSE_OF_DEATH = "CauseOfDeath";
	public static final String ENTITY_NAME_CONTACT_BY = "ContactBy";
	public static final String ENTITY_NAME_GENDER = "Gender";
	public static final String ENTITY_NAME_HEAR_OF_CRUSE = "HearOfCruse";
	public static final String ENTITY_NAME_LENGTH = "LengthOfBereavement";
	public static final String ENTITY_NAME_PCT = "Pct";
	public static final String ENTITY_NAME_OUTPOST = "Outpost";
	public static final String ENTITY_NAME_ENDING = "Ending";
	public static final String ENTITY_NAME_PLACE_OF_DEATH = "PlaceOfDeath";
	public static final String ENTITY_NAME_CLIENT_RESIDENCE = "ClientResidence";
	
	public static final String ENTITY_NAME_ETHNIC_BACK = "EthnicBackground";
	public static final String ENTITY_NAME_RELIGEON = "Religion";
	
	public static final String ENTITY_NAME_GROUP = "Group";
	
	
	private String code;
	private String description;
	private int sequence;
	
	public static ButterReference createReference(String entityName){
		return new ButterReference(entityName, Admin.getEntitySingular(entityName));
	}
	
	public static Collection<ButterReference> createReferralList(){
		ArrayList<ButterReference> list = new ArrayList<ButterReference>();
		list.add(createReference(ENTITY_NAME_AGE));
		list.add(createReference(ENTITY_NAME_AREA));
		list.add(createReference(ENTITY_NAME_BEREAVED_OF));
		list.add(createReference(ENTITY_NAME_CAUSE_OF_DEATH));
		list.add(createReference(ENTITY_NAME_CLIENT_RESIDENCE));
		list.add(createReference(ENTITY_NAME_CONTACT_BY));
		list.add(createReference(ENTITY_NAME_GENDER));
		list.add(createReference(ENTITY_NAME_HEAR_OF_CRUSE));
		list.add(createReference(ENTITY_NAME_LENGTH));
		list.add(createReference(ENTITY_NAME_PCT));
		list.add(createReference(ENTITY_NAME_OUTPOST));
		list.add(createReference(ENTITY_NAME_ENDING));
		list.add(createReference(ENTITY_NAME_PLACE_OF_DEATH));
		return list;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String getCombinedKey(){
		return CombinedKeyUtil.createCombinedKey(code, description);
	}

	@Override
	public void validateEntity() throws ButterEntityValidationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getUniqueIdentifier() {
		return this.getCode();
	}

	public static String getEntityPlural(String entityName){
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CONTACT_BY)){
			return "Contact by";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_BEREAVED_OF)){
			return "Bereaved of";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_LENGTH)){
			return "Length of bereavement";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CAUSE_OF_DEATH)){
			return "Causes of death";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_GENDER)){
			return "Genders"; 
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_AGE)){
			return "Ages";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_HEAR_OF_CRUSE)){
			return "Hear of Cruse";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_AREA)){
			return "Areas";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_ETHNIC_BACK)){
			return "Ethnic Backgrounds";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_RELIGEON)){
			return "Religions";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_PCT)){
			return "PCTs";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_OUTPOST)){
			return "Outposts";
		}
		
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_ENDING)){	
			return "Endings";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_PLACE_OF_DEATH)){	
			return "Places of death";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CLIENT_RESIDENCE)){	
			return "Client resides in";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_GROUP)){	
			return "Groups";
		}
		
		throw new IllegalArgumentException("Unknown entity" + entityName);
	}
	
	public static String getEntitySingular(String entityName){
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CONTACT_BY)){
			return "Contact by";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_BEREAVED_OF)){
			return "Bereaved of";
		}

		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_LENGTH)){
			return "Length of bereavement";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CAUSE_OF_DEATH)){
			return "Cause of death";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_GENDER)){
			return "Gender"; 
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_AGE)){
			return "Age";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_HEAR_OF_CRUSE)){
			return "Hear of Cruse";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_AREA)){
			return "Area";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_ETHNIC_BACK)){
			return "Ethnic Background";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_RELIGEON)){
			return "Religion";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_PCT)){
			return "PCT";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_OUTPOST)){
			return "Outpost";
		}
		
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_ENDING)){	
			return "Ending";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_PLACE_OF_DEATH)){	
			return "Place of death";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CLIENT_RESIDENCE)){	
			return "Client residence in";
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_GROUP)){	
			return "Group";
		}
		throw new IllegalArgumentException("Unknown entity" + entityName);
	}
	
	public static Admin createAdmin(String entityName){
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CONTACT_BY)){
			return new ContactBy();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_BEREAVED_OF)){
			return new BereavedOf();
		}
		
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_LENGTH)){
			return new LengthOfBereavement();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CAUSE_OF_DEATH)){
			return new CauseOfDeath();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_GENDER)){
			return new Gender(); 
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_AGE)){
			return new Age();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_HEAR_OF_CRUSE)){
			return new HearOfCruse();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_AREA)){
			return new Area();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_ETHNIC_BACK)){
			return new EthnicBackground();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_RELIGEON)){
			return new Religion();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_OUTPOST)){
			return new Outpost();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_ENDING)){
			return new Ending();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_PCT)){
			return new Pct();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_CLIENT_RESIDENCE)){
			return new ClientResidence();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_PLACE_OF_DEATH)){
			return new PlaceOfDeath();
		}
		if (entityName.equalsIgnoreCase(Admin.ENTITY_NAME_GROUP)){
			return new Group();
		}
		throw new IllegalArgumentException();
	}
	
	public static Class getEntityClass(String entityName){

		if (entityName.equals(Admin.ENTITY_NAME_AREA)){
			return Area.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_AGE)){
			return Age.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_BEREAVED_OF)){
			return BereavedOf.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_CAUSE_OF_DEATH)){
			return CauseOfDeath.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_CONTACT_BY)){
			return ContactBy.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_ETHNIC_BACK)){
			return EthnicBackground.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_GENDER)){
			return Gender.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_HEAR_OF_CRUSE)){
			return HearOfCruse.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_LENGTH)){
			return LengthOfBereavement.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_RELIGEON)){
			return Religion.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_PCT)){
			return Pct.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_ENDING)){
			return Ending.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_OUTPOST)){
			return Outpost.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_CLIENT_RESIDENCE)){
			return ClientResidence.class;
		}
		if (entityName.equals(Admin.ENTITY_NAME_PLACE_OF_DEATH)){
			return PlaceOfDeath.class;
		}	
		if (entityName.equals(Admin.ENTITY_NAME_GROUP)){
			return Group.class;
		}
		
		throw new IllegalArgumentException("Rubbish entity");
	}
	
	@Override
	public String getUniqueDescription() {
		return getDescription();
	}
	
}
