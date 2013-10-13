<%@include file="/WEB-INF/views/common/taglibs.jsp"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:crumb link="ethnicSummary.htm"
	returnTitle="Cultural Background Summary"></butter:crumb>

<butter:pageContainer pageTitle="Cultural Background Summary">

	<butter:tab title="Search criteria">
		<butter:tabLink type="modifySearch" altText="Modify search"
			link="ethnicSearchRequest.htm" />
		<butter:tabBody type="criteria">
			<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
			<butter:saveSearch criteriaName="ethnicCriteria"
				viewName="ethnicSummary.htm" tableIdentifier="ethnicSummary" />
		</butter:tabBody>
	</butter:tab>

	<butter:tab title="Summary details">
		<butter:tabLink type="print" />
		<butter:tabLink type="export" link="ethnicSummaryReport.htm" />
		<butter:tabBody type="results">
			<butter:table id="ethnicSummary" pageSize="18" name="ethnicSummary"
				refreshAction="ethnicSummary.htm"
				viewAction="ethnicSummaryDetail.htm"
				exportTitle="Cultural Background Search Summary"
				criteriaName="criteriaDescription">

				<butter:column property="description" titleKey="attribute" />
				<butter:column property="breakdown" titleKey="breakdown"  />
			</butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>

<butter:actionBar floating="true">
	<butter:button title="Show results" onclick="window.location.href='ethnicSearch.htm'"></butter:button>

</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>