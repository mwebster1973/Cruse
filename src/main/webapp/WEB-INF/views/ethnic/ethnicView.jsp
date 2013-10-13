<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

 
<butter:crumb link="viewEthnicEntry.htm"  
	parameters="id=${currentEthnicEntry.seqId}"  
	returnTitle="Cultural Background"></butter:crumb>
	
	
<butter:backLink />


<butter:pageContainer pageTitle="Cultural Background Details">
	<butter:tab title="Main details" >
		<c:if test="${ user.admin || user.counsellor }">
		<butter:tabLink type="edit" altText="Edit entry" link="editEthnicEntry.htm?id=${currentEthnicEntry.seqId}"/>  
		</c:if>
	    <butter:tabLink type="print"/>
		<butter:tabBody type="view">
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="ethnic.date.entered"/>
	            <butter:field>
		  			<fmt:formatDate pattern="EEE d MMM yyyy" value="${currentEthnicEntry.dateEntererd}" />
		  		</butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol>
			    <butter:label key="ethnic.id"/>
	            <butter:field>
		  			<c:out value="${currentEthnicEntry.seqId}"/>
		  		</butter:field>						       
			</butter:bodyCol>

		  </butter:bodyRow>		  
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="ethnic.background"/>
	            <butter:field>
		  			<c:out value="${currentEthnicEntry.ethnicBackground.description}"/>
		  		</butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol>
			    <butter:label key="ethnic.religion"/>
	            <butter:field>
		  			<c:out value="${currentEthnicEntry.religion.description}"/>
		  		</butter:field>						       
			</butter:bodyCol>
		  </butter:bodyRow>
		  <butter:spacer></butter:spacer>
		  <butter:bodyRow>	
				<butter:bodyCol>
					<butter:label key="referral.area"></butter:label>
					<butter:field>
						${currentEthnicEntry.area.description}
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol>
					<butter:label key="pct"></butter:label>
					<butter:field>
						${currentEthnicEntry.pct.description}
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>
			<butter:spacer></butter:spacer>
			<butter:bodyRow>	
				<butter:bodyCol>
					<butter:label key="ethnic.registered.disabled"></butter:label>
					<butter:field>
						<butter:activeFlag name="currentEthnicEntry" property="registeredDisabled"></butter:activeFlag>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol>
					<butter:label key="referral.carer"></butter:label>
					<butter:field>
						<butter:activeFlag name="currentEthnicEntry" property="carer"></butter:activeFlag>
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>	
						<butter:bodyRow>	
				<butter:bodyCol>
					<butter:label key="ethnic.consider.disabled"></butter:label>
					<butter:field>
						<butter:activeFlag name="currentEthnicEntry" property="considerDisabled"></butter:activeFlag>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol/>
			</butter:bodyRow>	
            <butter:spacer></butter:spacer>
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="referral.gender"></butter:label>
					<butter:field>
					   <c:if test="${not empty currentEthnicEntry.gender}">
						${currentEthnicEntry.gender.description}
					   </c:if>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol>
					<butter:label key="referral.age"></butter:label>
					<butter:field>
						<c:if test="${not empty currentEthnicEntry.ageOfClient}">
						   ${currentEthnicEntry.ageOfClient.description}
                        </c:if>						   
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>       		  
		</butter:tabBody>
	</butter:tab>
	
	
	<jsp:include page="/WEB-INF/views/common/audit.jsp" />
	
	</butter:pageContainer>
	 <butter:actionBar floating="false">
	      <butter:rowNavigator name="ethnics" viewAction="viewEthnicEntry.htm"></butter:rowNavigator>	  	       
	</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />