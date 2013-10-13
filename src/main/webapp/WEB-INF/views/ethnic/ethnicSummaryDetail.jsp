<%@include file="/WEB-INF/views/common/taglibs.jsp"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<butter:backLink finalLocation="true"/>

<butter:pageContainer pageTitle="${title}">

	<butter:tab title="Search criteria">
		<butter:tabBody type="criteria">
			<c:out value="${criteriaDescription}" escapeXml="false"></c:out>
			</butter:tabBody>
	</butter:tab>
	

	<butter:tab title="Summary details">
		<butter:tabLink type="print" />
		<butter:tabLink type="export" link="ethnicSummaryDetail.htm?id=${param.id}&ethnicSummaryDetail.export=Y" />
		<butter:tabBody type="results">
			<butter:table id="ethnicSummaryDetail" pageSize="18" name="detail"
				refreshAction="ethnicSummaryDetail.htm?id=${param.id}"
				exportTitle="${title}"
				sortedProperty="${displayCol}"
				sortedDirection="${displaySequence}"
				criteriaName="criteriaDescription">

				<butter:column property="sequence" titleKey="ref.sequenceNo" />
				<butter:column property="description" titleKey="attribute" />
				<butter:column property="count" titleKey="breakdown"  />
			</butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
