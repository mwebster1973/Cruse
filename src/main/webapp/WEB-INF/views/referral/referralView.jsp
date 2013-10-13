<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

 
<butter:crumb link="viewReferral.htm"  
	parameters="id=${currentReferral.id}"  
	returnTitle="Referral Details"></butter:crumb>
<butter:backLink />


<butter:pageContainer pageTitle="Referral Details - ${currentReferral.referralNo}">
	<butter:tab title="Referral details" >
		<c:if test="${ user.admin || user.counsellor|| user.dataEntry }">
			<butter:tabLink type="edit" altText="Edit" link="editReferral.htm?id=${param.id}"/>  
	    </c:if>
	    <butter:tabLink type="print"/>
		<butter:tabBody type="view">
		  <butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.date"></butter:label>
						<butter:field>
							<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentReferral.referralDate}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.staff"></butter:label>
						<butter:field>
							${currentReferral.staffInitials}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
	
				
				<butter:bodyRow>

					<butter:bodyCol>
						<butter:label key="referral.contact"></butter:label>
						<butter:field>
							${currentReferral.contactBy.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.bereaved.of"></butter:label>
						<butter:field>
							${currentReferral.bereavedOf.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.multiple.loss" ></butter:label>
						<butter:field>
						    <butter:activeFlag name="currentReferral" property="multipleLoss"></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow> 
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.length" ></butter:label>
						<butter:field>
							${currentReferral.lengthOfBereavement.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.cause"></butter:label>
						<butter:field>
							${currentReferral.causeOfDeath.description}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.gender"></butter:label>
						<butter:field>
							${currentReferral.gender.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.age"></butter:label>
						<butter:field>
							${currentReferral.ageOfClient.description}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.hear"></butter:label>
						<butter:field>
							${currentReferral.hearOfCruse.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.placeOfDeath"></butter:label>
						<butter:field>
							${currentReferral.placeOfDeath.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.clientResides"></butter:label>
						<butter:field>
							${currentReferral.clientResidence.description}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				
				
				<butter:spacer></butter:spacer>
				<butter:bodyRow>	
					<butter:bodyCol>
						<butter:label key="referral.area"></butter:label>
						<butter:field>
							${currentReferral.area.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="pct"></butter:label>
						<butter:field>
							${currentReferral.pct.description}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>				
					<butter:bodyRow>
						<butter:bodyCol>
							<butter:label key="referral.gp"></butter:label>
							<butter:field>
								<c:if test="${not empty currentReferral.gp}">
									${currentReferral.gp.description}
								</c:if>
							</butter:field>
						</butter:bodyCol>
					</butter:bodyRow>
					<butter:bodyRow>		
						<butter:bodyCol>
							<butter:label key="referral.gpNotes"></butter:label>
							<butter:field>
								${currentReferral.gpNotes}
							</butter:field>
						</butter:bodyCol>
					</butter:bodyRow>
				
				<butter:spacer></butter:spacer>
				<butter:bodyRow>	
					<butter:bodyCol>
						<butter:label key="referral.carer"></butter:label>
						<butter:field>
							<butter:activeFlag name="currentReferral" property="carer"></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.pre.bereavement"></butter:label>
						<butter:field>
							<butter:activeFlag name="currentReferral" property="preBereavement" ></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>		
				<butter:bodyRow>	
					<butter:bodyCol>
						<butter:label key="referral.home.visit"></butter:label>
						<butter:field>
							<butter:activeFlag name="currentReferral" property="homeVisit"></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>

					<butter:bodyCol>
						<butter:label key="referral.service"></butter:label>
						<butter:field>
							<butter:activeFlag name="currentReferral" property="service"></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>	
				</butter:bodyRow>	
				<butter:spacer></butter:spacer>	
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.outcome"></butter:label>
						<butter:field>
							${currentReferral.enquiryOutcome}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>


		 		 

		</butter:tabBody>
	</butter:tab>
	
	<butter:tab title="Client Details">
		<c:if test="${ user.admin || user.counsellor|| user.dataEntry }">
			<butter:tabLink type="edit" altText="Edit" link="editClientDetails.htm?id=${param.id}"/>  
	    </c:if>
		<butter:tabBody type="view">
		  	<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="referral.client"></butter:label>
					<butter:field>
							${currentReferral.clientInitials}
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol>
					<butter:label key="referral.clientTelephoneNo"></butter:label>
					<butter:field>
							${currentReferral.clientTelephoneNo}
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>
			<c:if test="${ user.admin || user.counsellor }">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.clientName"></butter:label>
						<butter:field>
							${currentReferral.clientName}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.mobilePhoneNumber"></butter:label>
						<butter:field>
							${currentReferral.mobilePhoneNumber}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.email"></butter:label>
						<butter:field>
							${currentReferral.email}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.workPhoneNumber"></butter:label>
						<butter:field>
							${currentReferral.workPhoneNumber}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				
				 <butter:spacer></butter:spacer>	
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.clientAddress"></butter:label>
						<butter:field>
							<butter:formatLargeText name="currentReferral" property="clientAddress"></butter:formatLargeText>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
			</c:if>
		</butter:tabBody>
	</butter:tab>
	<butter:tab title="Session Details"> 
		<c:if test="${ user.admin || user.counsellor }">
			<butter:tabLink type="edit" altText="Edit" link="editSessionDetails.htm?id=${param.id}"/>  
	    </c:if>
		<butter:tabBody type="view">
			<c:choose>
				<c:when test="${ not empty currentReferral.daysWaiting}">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.allocationDate"></butter:label>
						<butter:field>
							<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentReferral.allocationDate}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.days.on.waiting.list"></butter:label>
						<butter:field>
							${currentReferral.daysWaiting}
						</butter:field>
					</butter:bodyCol>
					</butter:bodyRow>
				</c:when>
			
				<c:otherwise>
					<butter:bodyRow>
						<butter:bodyCol>
							<butter:label key="referral.allocationDate"></butter:label>
							<butter:field>${currentReferral.daysWaiting}
								<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentReferral.allocationDate}"/>
							</butter:field>
						</butter:bodyCol>
						<butter:bodyCol>
							<butter:label key="referral.days.waiting"></butter:label>
							<butter:field>
								${currentReferral.daysOnWaitingList}
							</butter:field>
						</butter:bodyCol>
					</butter:bodyRow>
					</c:otherwise>
				</c:choose>
		
		 		 
		 		 <c:if test="${ user.admin || user.counsellor }">
		 		 <butter:spacer></butter:spacer>
		 		<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.counsellor"></butter:label>
						<butter:field>
							<butter:formatLargeText name="currentReferral" property="counsellor"></butter:formatLargeText>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow> 	
				</c:if>
		 		 <butter:spacer></butter:spacer>	
		  		<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.outpost"></butter:label>
						<butter:field>
							${currentReferral.outpost.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.sessions"></butter:label>
						<butter:field>
							${currentReferral.numberOfSessions}
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.ending"></butter:label>
						<butter:field>
							${currentReferral.ending.description}
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.closureDate"></butter:label>
						<butter:field>
							<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentReferral.closureDate}"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>	
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.firstAppointmentDate"></butter:label>
						<butter:field>
							<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentReferral.firstAppointmentDate}"/>
						</butter:field>
					</butter:bodyCol>
                    <butter:bodyCol>
						<butter:label key="referral.telephoneSupport"></butter:label>
						<butter:field>
							<butter:activeFlag name="currentReferral" property="telephoneSupport"></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
                <butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.firstOngoingAppointmentDate"></butter:label>
						<butter:field>
							<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentReferral.firstOngoingAppointmentDate}"/>
						</butter:field>
					</butter:bodyCol>
                    <butter:bodyCol>
						<butter:label key="referral.coreCompleted"></butter:label>
						<butter:field>
							<butter:activeFlag name="currentReferral" property="coreCompleted"></butter:activeFlag>
						</butter:field>
					</butter:bodyCol>					
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.coreDates"></butter:label>
						<butter:field>
								${fn:length(currentReferral.coreDates)}:-
								<c:forEach items="${currentReferral.coreDates}" var="coreDate"  varStatus="idx">
									<c:if test="${idx.count>1}">,&nbsp;</c:if>
									<fmt:formatDate pattern="EEE d MMM yyyy" value="${coreDate}"/>
								</c:forEach>		
						</butter:field>
					</butter:bodyCol>					
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.dnaDates"></butter:label>
						<butter:field>
							${fn:length(currentReferral.dnaDates)}:-
								<c:forEach items="${currentReferral.dnaDates}" var="dnaDate"  varStatus="idx">
									<c:if test="${idx.count>1}">,&nbsp;</c:if>
									<fmt:formatDate pattern="EEE d MMM yyyy" value="${dnaDate}"/>
								</c:forEach>		
						</butter:field>
					</butter:bodyCol>					
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.cancellationDates"></butter:label>
						<butter:field>
						    ${fn:length(currentReferral.cancellationDates)}:-
							<c:forEach items="${currentReferral.cancellationDates}" var="cancelDate"  varStatus="idx">
								<c:if test="${idx.count>1}">,&nbsp;</c:if>
								<fmt:formatDate pattern="EEE d MMM yyyy" value="${cancelDate}"/>
							</c:forEach>		
						</butter:field>
					</butter:bodyCol>					
				</butter:bodyRow>
				
				<c:if test="${ user.admin || user.counsellor }">
				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.comments"></butter:label>
						<butter:field>
							<butter:formatLargeText name="currentReferral" property="comments"></butter:formatLargeText>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				</c:if>
		</butter:tabBody>
	</butter:tab>
	
	 <butter:tab title="Group Membership" collapsible="true" id="groupTab">	
	    	<butter:tabLink type="add" altText="Add Group Membership" link="addReferralGroup.htm?referralId=${currentReferral.id}"/>
   		  <butter:tabBody type="results">

			<butter:table id="groups" viewAction="editReferralGroup.htm?referralId=${currentReferral.id}"
					   name="currentReferral" property="groupsAsList">
				<butter:column property="group.description" titleKey="group.description" />
				<butter:column property="status" titleKey="group.status" width="10%" />
				<butter:column property="waitingListDate" titleKey="group.waiting.list.date" formatKey="date.display.longFormat"/>
				<butter:column property="joinedDate" titleKey="group.joined.date" formatKey="date.display.longFormat"/>
				<butter:column property="closedDate" titleKey="group.closed.date" formatKey="date.display.longFormat"/>
				<butter:column property="ending" titleKey="group.ending"/>	
				
			</butter:table>			
			</butter:tabBody>
     </butter:tab>	
	
	
	<c:if test="${ user.admin || user.counsellor }">
		<jsp:include page="/pages/common/audit.jsp" />
	</c:if>
	
	</butter:pageContainer>
	 <butter:actionBar floating="true">
	      <butter:rowNavigator name="referrals" viewAction="viewReferral.htm"></butter:rowNavigator>	  	       
	</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />