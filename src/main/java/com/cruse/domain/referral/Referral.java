package com.cruse.domain.referral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.butter.domain.ButterEntity;
import com.butter.exception.ButterEntityValidationException;
import com.butter.util.DateUtil;
import com.cruse.domain.admin.Age;
import com.cruse.domain.admin.Area;
import com.cruse.domain.admin.BereavedOf;
import com.cruse.domain.admin.CauseOfDeath;
import com.cruse.domain.admin.ClientResidence;
import com.cruse.domain.admin.ContactBy;
import com.cruse.domain.admin.Ending;
import com.cruse.domain.admin.GP;
import com.cruse.domain.admin.Gender;
import com.cruse.domain.admin.HearOfCruse;
import com.cruse.domain.admin.LengthOfBereavement;
import com.cruse.domain.admin.Outpost;
import com.cruse.domain.admin.Pct;
import com.cruse.domain.admin.PlaceOfDeath;

public class Referral extends ButterEntity {
	
	/**
	 * Generate serial version id
	 */
	private static final long serialVersionUID = -1364314773515571897L;
	
	private int id;
	private String clientInitials;
	private String staffInitials;
	private Date referralDate;
	private ContactBy contactBy;
	private BereavedOf bereavedOf;
	private boolean multipleLoss;
	private LengthOfBereavement lengthOfBereavement;
	private Gender gender;
	private Age ageOfClient;
	private HearOfCruse hearOfCruse;
	private Area area;
	private String enquiryOutcome;
	private Date updatedDate;
	private CauseOfDeath causeOfDeath;
	private Pct pct;
	
	private boolean carer;
	private boolean preBereavement;
	private boolean homeVisit;
	private boolean telephoneSupport;
	


	private boolean coreCompleted;
	private boolean service;
	
	private int numberOfSessions;
	
	private Outpost outpost;
	private Ending ending;
	private String comments;
	private Date allocationDate;
	private String clientName;
	private String clientAddress;
	private String counsellor;
	private String referralNo;
	
	private Date closureDate;
	
	private String clientTelephoneNo;
	private Date firstAppointmentDate;
	private Date firstOngoingAppointmentDate;
	
	private PlaceOfDeath placeOfDeath;
	private ClientResidence clientResidence;
	
	private String email;
	private String mobilePhoneNumber;
	private String workPhoneNumber;
	
	private Set<ReferralGroup> groups;
	
	private GP gp;
	
	private String gpNotes;
	
	private List<Date> coreDates = new ArrayList<Date>();
	private List<Date> dnaDates = new ArrayList<Date>();
	private List<Date> cancellationDates = new ArrayList<Date>();
	
	public List<Date> getDnaDates() {
		return dnaDates;
	}

	public void setDnaDates(List<Date> dnaDates) {
		this.dnaDates = dnaDates;
	}

	public List<Date> getCancellationDates() {
		return cancellationDates;
	}

	public void setCancellationDates(List<Date> cancellationDates) {
		this.cancellationDates = cancellationDates;
	}

	public List getCoreDates() {
		return coreDates;
	}

	public void setCoreDates(List coreDates) {
		this.coreDates = coreDates;
	}

	public String getGpNotes() {
		return gpNotes;
	}

	public void setGpNotes(String gpNotes) {
		this.gpNotes = gpNotes;
	}

	public GP getGp() {
		return gp;
	}

	public void setGp(GP gp) {
		this.gp = gp;
	}

	@Override
	public String getEntityName() {
		return "Referral";
	}
	
	public void validateEntity() throws ButterEntityValidationException {
	}
	
	@Override
	public String getUniqueIdentifier() {
		return ""+id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientInitials() {
		return clientInitials;
	}

	public void setClientInitials(String clientInitials) {
		this.clientInitials = clientInitials;
	}

	public String getStaffInitials() {
		return staffInitials;
	}

	public void setStaffInitials(String staffInitials) {
		this.staffInitials = staffInitials;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public ContactBy getContactBy() {
		return contactBy;
	}

	public void setContactBy(ContactBy contactBy) {
		this.contactBy = contactBy;
	}

	public BereavedOf getBereavedOf() {
		return bereavedOf;
	}

	public void setBereavedOf(BereavedOf bereavedOf) {
		this.bereavedOf = bereavedOf;
	}

	public boolean isMultipleLoss() {
		return multipleLoss;
	}

	public void setMultipleLoss(boolean multipleLoss) {
		this.multipleLoss = multipleLoss;
	}

	public LengthOfBereavement getLengthOfBereavement() {
		return lengthOfBereavement;
	}

	public void setLengthOfBereavement(LengthOfBereavement length) {
		this.lengthOfBereavement = length;
	}

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

	public HearOfCruse getHearOfCruse() {
		return hearOfCruse;
	}

	public void setHearOfCruse(HearOfCruse hearOfCruse) {
		this.hearOfCruse = hearOfCruse;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getEnquiryOutcome() {
		return enquiryOutcome;
	}

	public void setEnquiryOutcome(String enquiryOutcome) {
		this.enquiryOutcome = enquiryOutcome;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public CauseOfDeath getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(CauseOfDeath causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
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

	public boolean isPreBereavement() {
		return preBereavement;
	}

	public void setPreBereavement(boolean preBereavement) {
		this.preBereavement = preBereavement;
	}

	public boolean isHomeVisit() {
		return homeVisit;
	}

	public void setHomeVisit(boolean homeVisit) {
		this.homeVisit = homeVisit;
	}

	public int getNumberOfSessions() {
		return numberOfSessions;
	}

	public void setNumberOfSessions(int numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}

	public Outpost getOutpost() {
		return outpost;
	}

	public void setOutpost(Outpost outpost) {
		this.outpost = outpost;
	}

	public Ending getEnding() {
		return ending;
	}

	public void setEnding(Ending ending) {
		this.ending = ending;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}

	public String getReferralNo() {
		return referralNo;
	}

	public void setReferralNo(String referralNo) {
		this.referralNo = referralNo;
	}
	
	public Long getDaysOnWaitingList(){
		Date allocation = this.getAllocationDate();
		if (allocationDate == null){
			//allocation = new Date();
			return null;
		}
		return DateUtil.daysBetween2Dates(this.getReferralDate(), allocation);
	}
	
	public Long getDaysWaiting(){
		Date allocation = this.getAllocationDate();
		if (allocationDate == null){
			return DateUtil.daysBetween2Dates(this.getReferralDate(), new Date());
		}
		return null;
		}
	

	public Date getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}

	public String getClientTelephoneNo() {
		return clientTelephoneNo;
	}

	public void setClientTelephoneNo(String clientTelephoneNo) {
		this.clientTelephoneNo = clientTelephoneNo;
	}

	public Date getFirstAppointmentDate() {
		return firstAppointmentDate;
	}

	public void setFirstAppointmentDate(Date firstAppointmentDate) {
		this.firstAppointmentDate = firstAppointmentDate;
	}

	public Date getFirstOngoingAppointmentDate() {
		return firstOngoingAppointmentDate;
	}

	public void setFirstOngoingAppointmentDate(Date firstOngoingAppointmentDate) {
		this.firstOngoingAppointmentDate = firstOngoingAppointmentDate;
	}

	public PlaceOfDeath getPlaceOfDeath() {
		return placeOfDeath;
	}

	public void setPlaceOfDeath(PlaceOfDeath placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}

	public ClientResidence getClientResidence() {
		return clientResidence;
	}

	public void setClientResidence(ClientResidence clientResidence) {
		this.clientResidence = clientResidence;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public Set<ReferralGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<ReferralGroup> groups) {
		this.groups = groups;
	}

	public List<ReferralGroup> getGroupsAsList(){
		return new ArrayList(this.getGroups());
	}
	
	public boolean isTelephoneSupport() {
		return telephoneSupport;
	}

	public void setTelephoneSupport(boolean telephoneSupport) {
		this.telephoneSupport = telephoneSupport;
	}

	public boolean isCoreCompleted() {
		return coreCompleted;
	}

	public void setCoreCompleted(boolean coreCompleted) {
		this.coreCompleted = coreCompleted;
	}

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}
	
	
}

