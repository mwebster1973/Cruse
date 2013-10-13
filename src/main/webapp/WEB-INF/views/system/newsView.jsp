<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<butter:crumb link="viewNews.htm" returnTitle="View News Details" parameters="newsId=${news.id}"></butter:crumb>
 
<butter:backLink />

<butter:pageContainer pageTitle="View News Details">
		<butter:tab title="News Item">
		<butter:tabLink type="print"></butter:tabLink>
			<c:if test="${ user.itAdmin || user.admin }">
				<butter:tabLink type="edit" altText="Edit news item" link="editNews.htm?newsId=${news.id}"/>	
			</c:if>
		
			<butter:tabBody type="edit">
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.startDate"></butter:label>
					<butter:field>
						<fmt:formatDate pattern="EEE d MMM yyyy HH:mm" value="${news.startDate}"/>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol/>

			</butter:bodyRow>	
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.endDate"></butter:label>
					<butter:field>
						<fmt:formatDate pattern="EEE d MMM yyyy HH:mm" value="${news.endDate}"/>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol/>
			</butter:bodyRow>	
		<butter:spacer></butter:spacer>
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.headline"></butter:label>
					<butter:field>
					<span style="font-size:1.5em;">
						<c:out value="${news.headline}" escapeXml="false"></c:out>
					</span>
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>
		<butter:spacer></butter:spacer>			
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.summary"></butter:label>
					<butter:field>
					<span style="color:#ff0000;">
						<butter:formatLargeText name="news" property="summary"></butter:formatLargeText>										
					</span>
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>
		<butter:spacer></butter:spacer>				
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.content"></butter:label>
					<butter:field>
						<butter:formatLargeText name="news" property="content"></butter:formatLargeText>															
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>									
			</butter:tabBody>
		</butter:tab>
</butter:pageContainer>
<butter:actionBar floating="true">
	<c:if test="${ user.itAdmin || user.admin }">
		<butter:button title="Edit" onclick="document.URL='editNews.htm?newsId=${news.id}'"/>
		<butter:button title="Duplicate" onclick="document.URL='editNews.htm?clone=true&newsId=${news.id}'"/>
	</c:if>
</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />