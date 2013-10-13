<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<butter:crumb link="newsSearch.htm" returnTitle="Maintain Homepage News"></butter:crumb>

<butter:pageContainer pageTitle="Maintain Homepage News">

	<butter:tab title=" ${fn:length(allNews)} news items found">
		<c:if test="${ user.itAdmin || user.admin }">
			<butter:tabLink type="add" altText="Add News" link="addNews.htm"/>
		</c:if>
		<butter:tabLink type="print"></butter:tabLink>
		<butter:tabLink type="export" link="newsSearch.htm?news.export=Y"/>
		<butter:tabBody type="results">
			<butter:table id="news"
					refreshAction="newsSearch.htm"
					viewAction="viewNews.htm"
					exportTitle="All News"
					name="allNews" 
					keyColumn="newsId"
					sortedProperty="startDate" 
					sortedDirection="D"
					>
				<butter:column nowrap="true" titleKey="news.startDate" property="startDate" formatKey="date.display.longTimeFormat"/>
				<butter:column nowrap="true" titleKey="news.endDate" property="endDate" formatKey="date.display.longTimeFormat"/>
				<butter:column titleKey="news.headline" property="headline"/>	
				<butter:column titleKey="news.summary" property="summary"/>	
				<butter:column titleKey="news.content" property="content"/>									
			</butter:table>
		</butter:tabBody>
	</butter:tab>
</butter:pageContainer>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
