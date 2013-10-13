<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:crumb link="userSearch.htm" returnTitle="Users &amp; Roles"></butter:crumb>

<butter:pageContainer pageTitle="Users &amp; Roles" >

	<butter:tab title="Search criteria">
	  	<butter:tabLink type="modifySearch" altText="Modify search" link="userSearchRequest.htm"/>
	   	<butter:tabBody type="criteria">
	   		<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
		   <butter:saveSearch  criteriaName="userCriteria" viewName="userSearch.htm" tableIdentifier="users"/> 
	  	</butter:tabBody>
	</butter:tab>

    <butter:tab title="${fn:length(users)} users found">
	    <c:if test="${ user.itAdmin || user.admin }">
			<butter:tabLink type="add" link="addUser.htm" altText="Add new user" />
		</c:if>
		<butter:tabLink type="print" />
		<butter:tabLink type="export" link="userSearch.htm?users.export=Y" />
		<butter:tabBody type="results">		    
			<butter:table id="users" 
					   pageSize="18" 
					   name="users"
					   refreshAction="userSearch.htm"
					   viewAction="viewUser.htm" 
					   exportTitle="User List"
					   criteriaName="criteriaDescription">
				<butter:column property="userId" titleKey="user.userid" />
				<butter:column property="firstName" titleKey="user.forename" />
				<butter:column property="surname" titleKey="user.surname" />
				<butter:column property="roleDescriptions" titleKey="user.roleDescription"/>
				<butter:column property="active" titleKey="active?" type="checked" width="5%"/>
			</butter:table>			
			<butter:generateKeys name="users"></butter:generateKeys>
  	   </butter:tabBody>
    </butter:tab>
</butter:pageContainer>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>