<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

	
<butter:crumb link="maintainGp.htm" returnTitle="${screenName}"></butter:crumb>
	
<butter:pageContainer pageTitle="${screenName}">	
	
	<butter:tab title=" ${fn:length(gps)} items found">
		<c:if test="${user.itAdmin || user.admin}">
			<butter:tabLink type="add" altText="Add Item" link="addGp.htm"/>
		</c:if>
		<butter:tabLink type="export" link="maintainGp.htm?gps.export=Y"/>
		<butter:tabLink type="print" />
		<butter:tabBody type="results">
			<butter:table id="gps" pageSize="18"
					refreshAction="maintainGp.htm"
					keyColumn="id"
					viewAction="viewGp.htm"
					exportTitle="${screenName}"
					name="gps">

				<butter:column property="surgeryName" titleKey="gp.surgeryName"  />	
				<butter:column property="postCode" 		 titleKey="gp.postCode" />
				<butter:column property="telephoneNumber" 	titleKey="gp.telephoneNumber"  />
		  	   </butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>
<butter:generateKeys name="gps"></butter:generateKeys>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />