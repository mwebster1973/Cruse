package com.cruse.persistence.referral.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.butter.audit.ButterAudit;
import com.butter.audit.ButterAuditLog;
import com.butter.domain.ButterEntity;
import com.butter.util.DateUtil;
import com.cruse.config.NamedAttributes;
import com.cruse.domain.admin.Admin;
import com.cruse.domain.referral.Referral;
import com.cruse.domain.referral.ReferralSearchCriteria;
import com.cruse.persistence.CruseHibernateDao;
import com.cruse.persistence.referral.ReferralDao;

public class HibernateReferralDao extends CruseHibernateDao implements
		ReferralDao {

	public void insert(Referral dto, String user) {

		dto.setUpdatedDate(new Date());
		this.getHibernateTemplate().save(dto);

		getAuditDao().auditCreation(dto.getEntityName(),
				dto.getUniqueIdentifier(), user);
	}

	public Referral get(String id) {
		Referral ref = (Referral) this.getHibernateTemplate().get(Referral.class,new Integer(id));
		return ref;
	}
	
	
	public Referral getReferralByNo(String referralNo){
		StringBuffer sql = new StringBuffer(
		"from Referral referral where referral.referralNo =? ");
		
		ArrayList<Referral> refs =  (ArrayList<Referral>)this.getHibernateTemplate().find(sql.toString(), referralNo);
		
		if (refs.size()==1){
			return refs.get(0);
		}
		return null;
	}
	

	/**
	 * Retrieve the database version of an entity. This is useful when
	 * performing an audit and retrieving the old version of that object.
	 * 
	 * @param entity
	 * @return
	 */
	protected ButterEntity getDatabaseEntity(Referral entity) {
		Session ses = this.getSessionFactory().openSession();
		ButterEntity existingDto = (ButterEntity) ses.get(entity.getClass(),
				entity.getId());
		if (existingDto == null) {
			existingDto = entity;
		}
		ses.close();
		return existingDto;
	}

	/**
	 * Perform an audit during an update.
	 * 
	 * @param User
	 *            newDto to audit
	 * @param user
	 *            the user performing the update
	 */
	private void audit(Referral newDto, String user) {

		Referral existingDto = (Referral) getDatabaseEntity(newDto);

		// Determine whether any values have changed by building up a combined
		// string
		ButterAuditLog log = new ButterAuditLog(newDto, user);

		log.audit("Referral No.",existingDto.getReferralNo(),
				newDto.getReferralNo());
		
		log.audit(NamedAttributes.ATTRIBUTE_CLIENT_INITIALS, 
				existingDto.getClientInitials(),
				newDto.getClientInitials());
		
		log.audit(NamedAttributes.ATTRIBUTE_STAFF_INITIALS, 
				existingDto.getStaffInitials(),
				newDto.getStaffInitials());
		
		
		log.audit(NamedAttributes.ATTRIBUTE_REFERRAL_DATE, 
				DateUtil.formatDate(
				existingDto.getReferralDate(), "EEE d MMM yyyy"), 
				DateUtil
				.formatDate(newDto.getReferralDate(), "EEE d MMM yyyy"));

		log.audit(NamedAttributes.ATTRIBUTE_CONTACT_BY, 
				existingDto.getContactBy(),
				newDto.getContactBy());
		
		log.audit(NamedAttributes.ATTRIBUTE_BEREAVED_OF, 
				existingDto.getBereavedOf(),
				newDto.getBereavedOf());
		
		log.audit(NamedAttributes.ATTRIBUTE_MULTIPLE_LOSS, 
				existingDto.isMultipleLoss(),
				newDto.isMultipleLoss());
		
		log.audit(NamedAttributes.ATTRIBUTE_TELEPHONE_SUPPORT, 
				existingDto.isTelephoneSupport(),
				newDto.isTelephoneSupport());
		
		log.audit(NamedAttributes.ATTRIBUTE_CORE_COMPLETED, 
				existingDto.isCoreCompleted(),
				newDto.isCoreCompleted());
		
		log.audit(NamedAttributes.ATTRIBUTE_SERVICE, 
				existingDto.isService(),
				newDto.isService());
		
		
		log.audit(NamedAttributes.ATTRIBUTE_LENGTH_OF_BEREAVEMENT, 
				existingDto.getLengthOfBereavement(),
				newDto.getLengthOfBereavement());
		
		log.audit(NamedAttributes.ATTRIBUTE_GENDER, 
				existingDto.getGender(),
				newDto.getGender());
		
		log.audit(NamedAttributes.ATTRIBUTE_AGE_OF_CLIENT, 
				existingDto.getAgeOfClient(),
				newDto.getAgeOfClient());
		
		log.audit(NamedAttributes.ATTRIBUTE_HEAR_OF_CRUSE, 
				existingDto.getHearOfCruse(),
				newDto.getHearOfCruse());
		
		log.audit(NamedAttributes.ATTRIBUTE_AREA, 
				existingDto.getArea(),
				newDto.getArea());
		
		log.audit(NamedAttributes.ATTRIBUTE_GP, 
				existingDto.getGp(),
				newDto.getGp());
		
		log.audit(NamedAttributes.ATTRIBUTE_GP_NOTES, 
				existingDto.getGpNotes(),
				newDto.getGpNotes());
		
		log.audit(NamedAttributes.ATTRIBUTE_CAUSE_OF_DEATH, 
				existingDto.getCauseOfDeath(),
				newDto.getCauseOfDeath());
		
		log.audit(NamedAttributes.ATTRIBUTE_PCT, 
				existingDto.getPct(),
				newDto.getPct());
		
		log.audit("Carer", 
				existingDto.isCarer(),
				newDto.isCarer());
		
		log.audit("Pre-Bereavement", 
				existingDto.isPreBereavement(),
				newDto.isPreBereavement());
		
		log.audit("Home Visit", 
				existingDto.isHomeVisit(),
				newDto.isHomeVisit());
		log.audit("Number of sessions", 
				existingDto.getNumberOfSessions(),
				newDto.getNumberOfSessions());
		
		log.audit(NamedAttributes.ATTRIBUTE_ENQUIRY_OUTCOME, 
				existingDto.getEnquiryOutcome(),
				newDto.getEnquiryOutcome());
		
		log.audit("Outpost", 
				existingDto.getOutpost(),
				newDto.getOutpost());
		
		log.audit("Ending", 
				existingDto.getEnding(),
				newDto.getEnding());

		log.audit("Comments", 
				existingDto.getComments(),
				newDto.getComments());
		
		String allocDateBefore = null;
		String allocDateAfter = null;
		if (existingDto.getAllocationDate()!= null){
			allocDateBefore = DateUtil.formatDate(
					existingDto.getAllocationDate(), "EEE d MMM yyyy");
		}
		if (newDto.getAllocationDate()!= null){
			allocDateAfter = DateUtil
			.formatDate(newDto.getAllocationDate(), "EEE d MMM yyyy");
		}
		log.audit("Allocation Date", 
				allocDateBefore, 
				allocDateAfter);

		log.audit("Client Name", 
				existingDto.getClientName(),
				newDto.getClientName());

		log.audit("Client Address", 
				existingDto.getClientAddress(),
				newDto.getClientAddress());

		log.audit("Counsellor", 
				existingDto.getCounsellor(),
				newDto.getCounsellor());

	
		if (!log.isEmpty()) {
			ButterAudit audit = log.generateAudit();
			getAuditDao().insertAudit(audit, user);
		}
		this.getSession().evict(existingDto);
	}

	/**
	 * Updates a User.
	 * 
	 * @param User
	 *            The data transfer object for a user
	 */
	public void update(Referral dto, String user) {
		audit(dto, user);
		dto.setUpdatedDate(new Date());
		this.getHibernateTemplate().update(dto);
	}

	private void attachOptions(StringBuffer buffy, String[] options) {
		buffy.append("(");
		for (int x = 0; x < options.length; x++) {
			if (x > 0) {
				buffy.append(",");
			}
			buffy.append("'" + options[x] + "'");
		}
		buffy.append(")");
	}

	/**
	 * Searches for existing users
	 * 
	 * @param UserSearchCriteria
	 *            The criteria when searching for users
	 * @return Collection of User
	 */
	@SuppressWarnings("unchecked")
	public Collection<Referral> search(ReferralSearchCriteria criteria) {

		StringBuffer sql = new StringBuffer(
				"from Referral referral where referral=referral ");

		ArrayList<Object> params = new ArrayList<Object>();
		
		String dateType = "referralDate";
		if (criteria.getDateType().equals(ReferralSearchCriteria.DATE_TYPE_ALLOCATION)){
			dateType="allocationDate";
		} else if (criteria.getDateType().equals(ReferralSearchCriteria.DATE_TYPE_CLOSURE)){
			dateType="closureDate";
		}
		
		if (StringUtils.isNotEmpty(criteria.getClientName())){
			sql.append(" AND UPPER(referral.clientName) like ? ");
			params.add("%"+ criteria.getClientName().toUpperCase()+ "%");
		}
		
		if (StringUtils.isNotEmpty(criteria.getClientInitials())){
			sql.append(" AND referral.clientInitials like ? ");
			params.add("%"+ criteria.getClientInitials()+ "%");
		}
		
		

		if (criteria.getIndividualDate() != null) {

			sql.append(" AND day(referral."+dateType+") = ?");
			sql.append(" AND month(referral."+dateType+") = ?");
			sql.append(" AND year(referral."+dateType+") = ?");

			Calendar cal = Calendar.getInstance();
			cal.setTime(criteria.getIndividualDate());
			params.add(cal.get(Calendar.DATE));
			params.add(cal.get(Calendar.MONTH) + 1);
			params.add(cal.get(Calendar.YEAR));
		}

		if (criteria.getDateRangeStart() != null) {
			sql.append(" AND referral."+dateType+" >= ?");
			params.add(criteria.getDateRangeStart());
		}
		if (criteria.getDateRangeEnd() != null) {
			sql.append(" AND referral."+dateType+" <= ?");
			params.add(criteria.getDateRangeEnd());
		}
		String[] keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_AGE);
		if (keys!= null){
			sql.append(" AND referral.ageOfClient.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_CONTACT_BY);
		if (keys!= null){
			sql.append(" AND referral.contactBy.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_BEREAVED_OF);
		if (keys!= null){
			sql.append(" AND referral.bereavedOf.code IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_OUTPOST);
		if (keys!= null){
			sql.append(" AND referral.outpost.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_ENDING);
		if (keys!= null){
			sql.append(" AND referral.ending.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_LENGTH);
		if (keys!= null){
			sql.append(" AND referral.lengthOfBereavement.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_GENDER);
		if (keys!= null){
			sql.append(" AND referral.gender.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_AGE);
		if (keys!= null){
			sql.append(" AND referral.ageOfClient.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_HEAR_OF_CRUSE);
		if (keys!= null){
			sql.append(" AND referral.hearOfCruse.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_AREA);
		if (keys!= null){
			sql.append(" AND referral.area.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_CAUSE_OF_DEATH);
		if (keys!= null){
			sql.append(" AND referral.causeOfDeath.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_PCT);
		if (keys!= null){
			sql.append(" AND referral.pct.code IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys("carer");
		if (keys!= null){
			sql.append(" AND referral.carer IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys("homeVisit");
		if (keys!= null){
			sql.append(" AND referral.homeVisit IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys("telephoneSupport");
		if (keys!= null){
			sql.append(" AND referral.telephoneSupport IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys("coreCompleted");
		if (keys!= null){
			sql.append(" AND referral.coreCompleted IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys("service");
		if (keys!= null){
			sql.append(" AND referral.service IN ");
			attachOptions(sql,keys);
		}
		
		
						
		keys = criteria.getSelectedKeys("multipleLoss");
		if (keys!= null){
			sql.append(" AND referral.multipleLoss IN ");
			attachOptions(sql,keys);
		}
		
		keys = criteria.getSelectedKeys("preBereavement");
		if (keys!= null){
			sql.append(" AND referral.preBereavement IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_PLACE_OF_DEATH);
		if (keys!= null){
			sql.append(" AND referral.placeOfDeath IN ");
			attachOptions(sql,keys);
		}
		keys = criteria.getSelectedKeys(Admin.ENTITY_NAME_CLIENT_RESIDENCE);
		if (keys!= null){
			sql.append(" AND referral.clientResidence IN ");
			attachOptions(sql,keys);
		}
		
		sql.append(" order by referral.referralDate");
		return this.getHibernateTemplate().find(sql.toString(),
				params.toArray());

	}
}
