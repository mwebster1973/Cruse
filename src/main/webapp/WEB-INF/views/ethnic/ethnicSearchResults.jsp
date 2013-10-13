<%@include file="/WEB-INF/views/common/taglibs.jsp"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:crumb link="ethnicSearch.htm"
	returnTitle="Cultural Background Search Results"></butter:crumb>

<butter:pageContainer pageTitle="Cultural Background Search Results">

	<butter:tab title="Search criteria">
		<butter:tabLink type="modifySearch" altText="Modify search"
			link="ethnicSearchRequest.htm" />
		<butter:tabBody type="criteria">
			<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
			<butter:saveSearch criteriaName="ethnicCriteria"
				viewName="ethnicSearch.htm" tableIdentifier="ethnics" />
		</butter:tabBody>
	</butter:tab>

	<butter:tab title="${fn:length(ethnics)} records found">
		<c:if test="${ user.counsellor || user.admin || user.dataEntry }">
			<butter:tabLink type="add" link="addEthnicEntry.htm"
				altText="Add new record" />
		</c:if>
		<butter:tabLink type="print" />
		<butter:tabLink type="export" link="ethnicSearch.htm?ethnics.export=Y" />
		<butter:tabBody type="results">
			<butter:table id="ethnics" pageSize="18" name="ethnics"
				refreshAction="ethnicSearch.htm"
				viewAction="viewEthnicEntry.htm"
				exportTitle="Cultural Background Search Results"
				sortedProperty="seqId"
				sortedDirection="D"
				criteriaName="criteriaDescription">

				<butter:column property="seqId" titleKey="ethnic.id" />
				<butter:column property="dateEntererd" titleKey="ethnic.date.entered"  />
				<butter:column property="ethnicBackground.description" titleKey="ethnic.background" />
				<butter:column property="religion.description" titleKey="ethnic.religion" />
				<butter:column property="area.description" titleKey="referral.area" />
				<butter:column property="pct.description" titleKey="pct" />	
				<butter:column property="registeredDisabled" titleKey="ethnic.registered.disabled.short" type="checked"  />
				<butter:column property="considerDisabled" titleKey="ethnic.consider.disabled.short" type="checked"  />			
				<butter:column property="carer" titleKey="referral.carer" type="checked"  />
				<butter:column property="gender.description" titleKey="referral.gender"/>
				<butter:column property="ageOfClient.description" titleKey="referral.age"/>				
				

			</butter:table>
			<butter:generateKeys name="ethnics"></butter:generateKeys>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>

<butter:actionBar floating="true">
	<butter:button title="Show summary" onclick="window.location.href='ethnicSummary.htm'"></butter:button>

</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>