<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<butter:crumb link="viewConfig.htm" returnTitle="Configuration Details" parameters="id=${config.configParam}"/>
<butter:backLink/>
	 
<butter:pageContainer pageTitle="Configuration Details">
	<butter:tab title="Main details">
		<butter:tabLink type="print"></butter:tabLink>
		<butter:tabLink type="edit" link="editConfig.htm?id=${config.configParam}" altText="Edit"></butter:tabLink>
		
			<butter:tabBody type="view">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="config.parameter"/>
						<butter:field>
							<c:out value="${config.configParam}"></c:out>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="config.value"/>
						<butter:field>
							<c:out value="${config.configValue}"></c:out>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="config.description"/>
						<butter:field>
							<c:out value="${config.configDescription}"></c:out>						
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
			</butter:tabBody>
		</butter:tab>
		
		<jsp:include page="/WEB-INF/views/common/audit.jsp"/>
		
</butter:pageContainer>
<butter:actionBar floating="true">
	<butter:button onclick="window.location='editConfig.htm?id=${config.configParam}'" title="Edit"></butter:button>
</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />