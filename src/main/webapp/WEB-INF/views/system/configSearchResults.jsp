<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

	
<butter:crumb link="configSearch.htm" returnTitle="Maintain System Configuration"></butter:crumb>
	
<butter:pageContainer pageTitle="Maintain System Configuration">	
	
	<butter:tab title=" ${fn:length(configs)} configuration items found">
		<c:if test="${user.itAdmin}">
			<butter:tabLink type="add" altText="Add Config Item" link="addConfig.htm"/>
		</c:if>
		<butter:tabLink type="export" link="configSearch.htm?configs.export=Y"/>
		<butter:tabLink type="print" />
		<butter:tabBody type="results">
			<butter:table id="configs" pageSize="18"
					refreshAction="configSearch.htm"
					viewAction="viewConfig.htm"
					exportTitle="System Configurations"
					name="configs">
					
				<butter:column property="configParam" 		 titleKey="config.parameter" />
				<butter:column property="configValue" 		 titleKey="config.value"  />
				<butter:column property="configDescription" titleKey="config.description"  />
	
			</butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />