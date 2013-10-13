<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

 
<butter:crumb link="viewAdmin.htm?domain=${param.domain}"  
	parameters="id=${admin.code}"  
	returnTitle="${entitySingular} Details"></butter:crumb>
<butter:backLink />


<butter:pageContainer pageTitle="${entitySingular} Details">
	<butter:tab title="Main details" >
		<c:if test="${ user.itAdmin || user.admin }">
			<butter:tabLink type="edit" altText="Edit" link="editAdmin.htm?domain=${param.domain}&id=${admin.code}"/>  
	   </c:if>
	    <butter:tabLink type="print"/>
		<butter:tabBody type="view">
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="ref.sequenceNo"/>
	            <butter:field>
		  				<c:out value="${admin.sequence}"/>
                </butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>		
		  		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="ref.code"/>
	            <butter:field>
		  				<c:out value="${admin.code}"/>
                </butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol>
			    <butter:label key="ref.description"/>
	            <butter:field>
		  				<c:out value="${admin.description}"/>
                </butter:field>						       
			</butter:bodyCol>
		  </butter:bodyRow>	  
		  
		  <c:if test="${param.domain=='Area'}">
		  	<butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="pct"/>
	            <butter:field>
		  			<c:out value="${admin.pct.description}"/>
                </butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol/>
		  </butter:bodyRow>	
		  </c:if>
		</butter:tabBody>
	</butter:tab>
	
	<c:if test="${param.domain=='Group'}">

		<butter:tab title="Sessions">
		<butter:tabLink type="add" link="addGroupSession.htm?group=${admin.code}" altText="Add a session details"></butter:tabLink>
			 <butter:tabBody type="results">
	
			<butter:table id="sessionList" keyColumn="id" viewAction="editGroupSession.htm?group=${admin.code}" pageSize="5"
					   name="admin" property="sessionList" sortedDirection="D" sortedProperty="sessionDate" refreshAction="viewAdmin.htm?domain=${param.domain}&id=${admin.code}">

				<butter:column property="sessionDate" titleKey="session.date" formatKey="date.display.longFormat"/>
				<butter:column property="attendedCount" titleKey="session.client.count"/>
				<butter:column property="volenterCount" titleKey="session.volunteer.count"/>
				<butter:column property="notes" titleKey="session.notes"/>
			
			</butter:table>			
			</butter:tabBody>
		</butter:tab>
		
		<butter:tab title="Waiting List">
               <butter:tabLink type="export" link="viewAdmin.htm?domain=${param.domain}&id=${admin.code}&waitingList.export=Y" />			
			   		  <butter:tabBody type="results">

			<butter:table exportTitle="${admin.description} - Waiting List referrals" id="waitingList" viewAction="viewReferral.htm" reflectionRowKey="referralId" keyColumn="id"
					   name="admin" property="waitingList">
				<butter:column property="referral.referralNo" titleKey="referral.no" />
				<butter:column property="referral.clientInitials" titleKey="referral.client" />
				<c:if test="${ user.admin || user.counsellor }">
					<butter:column property="referral.clientName" titleKey="referral.clientName" />
				</c:if>
				<butter:column property="referral.referralDate" titleKey="referral.date" />
			</butter:table>			
			</butter:tabBody>
		</butter:tab>
	
			<butter:tab title="Active">
               <butter:tabLink type="export" link="viewAdmin.htm?domain=${param.domain}&id=${admin.code}&activeList.export=Y" />			
               
			   		  <butter:tabBody type="results">			

			<butter:table exportTitle="${admin.description} - Active referrals" id="activeList" viewAction="viewReferral.htm" reflectionRowKey="referralId" keyColumn="id"
					   name="admin" property="activeList">
				<butter:column property="referral.referralNo" titleKey="referral.no" />
				<butter:column property="referral.clientInitials" titleKey="referral.client" />
				<c:if test="${ user.admin || user.counsellor }">
					<butter:column property="referral.clientName" titleKey="referral.clientName" />
				</c:if>
				<butter:column property="joinedDate" titleKey="group.joined.date" formatKey="date.display.longTimeFormat"/>
			</butter:table>			
			</butter:tabBody>
		</butter:tab>
		
			<butter:tab title="Closed" collapsible="true" id="closedTab">
		      <butter:tabLink type="export" link="viewAdmin.htm?domain=${param.domain}&id=${admin.code}&closedList.export=Y" />			
			   		  <butter:tabBody type="results">

			<butter:table exportTitle="${admin.description} - Closed referrals" id="closedList" viewAction="viewReferral.htm" reflectionRowKey="referralId" keyColumn="id"
					   name="admin" property="closedList">
				<butter:column property="referral.referralNo" titleKey="referral.no" />
				<butter:column property="referral.clientInitials" titleKey="referral.client" />
				<c:if test="${ user.admin || user.counsellor }">
					<butter:column property="referral.clientName" titleKey="referral.clientName" export="only" />
				</c:if>
				<butter:column property="ending" titleKey="group.ending"/>	
				<butter:column property="closedDate" titleKey="group.closed.date" formatKey="date.display.longTimeFormat"/>
			</butter:table>			
			</butter:tabBody>
		</butter:tab>
		

	
	</c:if>
	
	
	<jsp:include page="/WEB-INF/views/common/audit.jsp" />
	
	</butter:pageContainer>
	 <butter:actionBar floating="false">
	      <butter:rowNavigator name="admins" viewAction="viewAdmin.htm?domain=${param.domain}"></butter:rowNavigator>	  	       
	</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />