<%@include file="/WEB-INF/views/common/taglibs.jsp"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:crumb link="referralSummary.htm"
	returnTitle="Referral Summary"></butter:crumb>

<butter:pageContainer pageTitle="Referral Summary">

	<butter:tab title="Search criteria">
		<butter:tabLink type="modifySearch" altText="Modify search"
			link="referralSearchRequest.htm" />
		<butter:tabBody type="criteria">
			<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
			<butter:saveSearch criteriaName="referralCriteria"
				viewName="referralSummary.htm" tableIdentifier="referralSummary" />
		</butter:tabBody>
	</butter:tab>

	<butter:tab title="Summary details">
		<butter:tabLink type="print" />
		<butter:tabLink type="export" link="referralSummaryReport.htm" />
		<butter:tabBody type="results">
			<butter:table id="referralSummary" pageSize="18" name="referralSummary"
				refreshAction="referralSummary.htm"
				viewAction="referralSummaryDetail.htm"
				exportTitle="Referral Search Summary"
				criteriaName="criteriaDescription">

				<butter:column property="description" titleKey="attribute" />
				<butter:column property="breakdown" titleKey="breakdown"  />
			</butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>

<butter:actionBar floating="true">
	<butter:button title="Show results" onclick="window.location.href='referralSearch.htm'"></butter:button>

</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
