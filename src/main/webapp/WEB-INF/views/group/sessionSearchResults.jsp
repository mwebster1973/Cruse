<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:crumb link="sessionSearch.htm" returnTitle="Session search results"></butter:crumb>

<butter:pageContainer pageTitle="Session search results" >

	<butter:tab title="Search criteria">
	  	<butter:tabLink type="modifySearch" altText="Modify search" link="sessionSearchRequest.htm"/>
	   	<butter:tabBody type="criteria">
	   		<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
	  	</butter:tabBody>
	</butter:tab>
	
	<c:forEach items="${groups}" var="group" >
		<butter:tab title="${group.description} sessions">
			<butter:tabLink type="add" link="addGroupSession.htm?group=${group.code}" altText="Add a session "></butter:tabLink>
			<butter:tabLink type="export" link="sessionSearch.htm?${group.code}List.export=Y" />
			 <butter:tabBody type="results">
			<butter:table id="${group.code}List" keyColumn="id" criteriaName="${group.code}criteriaDescription"
					viewAction="editGroupSession.htm?group=${group.code}"
					pageSize="8"
					name="${group.code}List" sortedDirection="D" 
					sortedProperty="sessionDate" refreshAction="sessionSearch.htm">

				<butter:column property="sessionDate" titleKey="session.date" formatKey="date.display.longFormat"/>
				<butter:column property="attendedCount" titleKey="session.client.count"/>
				<butter:column property="volenterCount" titleKey="session.volunteer.count"/>
				<butter:column property="notes" titleKey="session.notes"/>
			</butter:table>			
			</butter:tabBody>
		</butter:tab>		
	</c:forEach>
</butter:pageContainer>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>