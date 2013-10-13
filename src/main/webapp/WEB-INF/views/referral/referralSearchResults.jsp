<%@include file="/WEB-INF/views/common/taglibs.jsp"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:crumb link="referralSearch.htm"
	returnTitle="Referral Search Results"></butter:crumb>

<butter:pageContainer pageTitle="Referral Search Results">

	<butter:tab title="Search criteria">
		<butter:tabLink type="modifySearch" altText="Modify search"
			link="referralSearchRequest.htm" />
		<butter:tabBody type="criteria">
			<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
			<butter:saveSearch criteriaName="referralCriteria"
				viewName="referralSearch.htm" tableIdentifier="referrals" />
		</butter:tabBody>
	</butter:tab>

	<butter:tab title="${fn:length(referrals)} records found">
		<c:if test="${ user.admin || user.counsellor|| user.dataEntry  }">
			<butter:tabLink type="add" link="addReferral.htm"
				altText="Add new referral" />
		</c:if>
		<butter:tabLink type="print" />
		<butter:tabLink type="customise" onClick="showCustomDiv('referrals',true);" />
		
		<butter:tabLink type="export" link="referralSearch.htm?referrals.export=Y" />
		
		<butter:tabBody type="results">
			<butter:table id="referrals" pageSize="18" name="referrals"
				refreshAction="referralSearch.htm"
				viewAction="viewReferral.htm"
				exportTitle="Referral Search Results"
				sortedProperty="id"
				sortedDirection="D"
				criteriaName="criteriaDescription">

				<butter:sectionHeader titleKey="section.referral.details"></butter:sectionHeader>
				<butter:column property="id" titleKey="referral.id" />
				<butter:column property="referralNo" titleKey="referral.no" />		
				<butter:column property="referralDate" titleKey="referral.date" />
				<butter:column property="staffInitials" titleKey="referral.staff" export="only" />				
				<butter:column property="contactBy.description" titleKey="referral.contact" export="only" />
				<butter:column property="bereavedOf.description" titleKey="referral.bereaved.of" export="only" />
				<butter:column property="multipleLoss"  type="checked" titleKey="referral.multiple.loss" export="only" />
				<butter:column property="lengthOfBereavement.description" titleKey="referral.length" export="only" />
				<butter:column property="causeOfDeath.description" titleKey="referral.cause" export="only" />
				<butter:column property="gender.description" titleKey="referral.gender" export="only" />
				<butter:column property="ageOfClient.description" titleKey="referral.age"  export="only" />
				<butter:column property="hearOfCruse.description" titleKey="referral.hear" export="only"  />
				
				<butter:column property="area.description" titleKey="referral.area" />
				<butter:column property="pct.description" titleKey="pct" export="only"  />
				<butter:column property="carer" titleKey="referral.carer" export="only" type="checked"  />
				<butter:column property="preBereavement" titleKey="referral.pre.bereavement" export="only" type="checked" />
				<butter:column property="homeVisit" titleKey="referral.home.visit" export="only" type="checked" />
				<butter:column property="enquiryOutcome" export="only" titleKey="referral.outcome" />
				
				<butter:sectionHeader titleKey="section.client.details"></butter:sectionHeader>
				<butter:column property="clientInitials" titleKey="referral.client" />
				<c:if test="${ user.admin || user.counsellor }">
					<butter:column property="clientName" titleKey="referral.clientName" export="only" />
					<butter:column property="clientAddress" titleKey="referral.clientAddress" export="only" />
					<butter:column property="clientTelephoneNo" titleKey="referral.clientTelephoneNo" export="only" />
					<butter:column property="email" titleKey="referral.email" export="only" />
					<butter:column property="mobilePhoneNumber" titleKey="referral.mobilePhoneNumber" export="only" />
					<butter:column property="workPhoneNumber" titleKey="referral.workPhoneNumber" export="only" />
				</c:if>
				
				<butter:sectionHeader titleKey="section.session.details"></butter:sectionHeader>
				<butter:column property="allocationDate" titleKey="referral.allocationDate" />
				<butter:column property="daysOnWaitingList" titleKey="referral.days.waiting" />
				<butter:column property="daysWaiting" titleKey="referral.days.on.waiting.list"/>
				<c:if test="${ user.admin || user.counsellor }">
					<butter:column property="counsellor" titleKey="referral.counsellor" export="only" />
				</c:if>
				<butter:column property="outpost.description" titleKey="referral.outpost" export="only" />
				<butter:column property="numberOfSessions" titleKey="referral.sessions.short" export="only" />
				<butter:column property="ending.description" titleKey="referral.ending" export="only" />
				<butter:column property="closureDate" titleKey="referral.closureDate" export="only" />
				<c:if test="${ user.admin || user.counsellor }">
					<butter:column property="comments" titleKey="referral.comments" export="only" />
				</c:if>
				<butter:column property="firstAppointmentDate" titleKey="referral.firstAppointmentDate" export="only" />
				<butter:column property="firstOngoingAppointmentDate" titleKey="referral.firstOngoingAppointmentDate" export="only" />
				<butter:column property="telephoneSupport" titleKey="referral.telephoneSupport" export="only" type="checked"  />
				<butter:column property="coreCompleted" titleKey="referral.coreCompleted" export="only" type="checked"  />				
				<butter:column property="service" titleKey="referral.service" export="only" type="checked"  />
				
			</butter:table>
			<butter:generateKeys name="referrals"></butter:generateKeys>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>

<butter:actionBar floating="true">
	<butter:button title="Show summary" onclick="window.location.href='referralSummary.htm'"></butter:button>

</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>