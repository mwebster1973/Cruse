<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

	
<butter:crumb link="maintainAdmin.htm" parameters="domain=${param.domain}" returnTitle="${screenName}"></butter:crumb>
	
<butter:pageContainer pageTitle="${screenName}">	
	
	<butter:tab title=" ${fn:length(admins)} items found">
		<c:if test="${user.itAdmin || user.admin}">
			<butter:tabLink type="add" altText="Add Item" link="addAdmin.htm?domain=${param.domain}"/>
		</c:if>
		<butter:tabLink type="export" link="maintainAdmin.htm?domain=${param.domain}&admins.export=Y"/>
		<butter:tabLink type="print" />
		<butter:tabBody type="results">
			<butter:table id="admins" pageSize="18"
					refreshAction="maintainAdmin.htm?domain=${param.domain}"
					viewAction="viewAdmin.htm?domain=${param.domain}"
					exportTitle="${screenName}"
					name="admins">
				
				<butter:column property="sequence" titleKey="ref.sequenceNo"  />	
				<butter:column property="code" 		 titleKey="ref.code" />
				<butter:column property="description" 	titleKey="ref.description"  />
				<c:if test="${param.domain=='Area'}">
					<butter:column property="pct.description" 	titleKey="pct"  />
				</c:if>
		  	   </butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>
<butter:generateKeys name="admins"></butter:generateKeys>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />