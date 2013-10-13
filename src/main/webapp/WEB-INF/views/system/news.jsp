<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
	function startUp(){
		focusForm(document.forms.newsForm);
	}
	
</script>
 
<butter:backLink finalLocation="true"/>

<% String title = "Add News Item"; %>

<c:if test="${not empty param.newsId }" >
	<% title = "Edit News Item"; %>
</c:if>

<form:form commandName="news" id="newsForm" >
<butter:pageContainer pageTitle="<%=title %>">

		<butter:tab title="Enter details" alert="* Mandatory">
			<butter:tabBody type="edit">
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.startDate" mandatory="true" dateInput="true"></butter:label>
					<butter:field>
						<butter:dateInput mandatory="true" path="startDateAsString"></butter:dateInput>
						<form:select path="startTimeAsString" items="${times}" itemLabel="description" itemValue="code" 
							onfocus="change(this, '#ffffcc');"
							onblur="change(this, '');"
							cssStyle="width:60px;"/>						
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol/>
			</butter:bodyRow>
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.endDate" mandatory="true" dateInput="true"></butter:label>
					<butter:field>
						<butter:dateInput mandatory="true" path="endDateAsString"></butter:dateInput>
						<form:select path="endTimeAsString" items="${times}" itemLabel="description" itemValue="code" 
							onfocus="change(this, '#ffffcc');"
							onblur="change(this, '');"
							cssStyle="width:60px;"/>						
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol/>
			</butter:bodyRow>			
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.headline" mandatory="true"></butter:label>
					<butter:field>
						<butter:text alt="headline|Y" path="headline" maxlength="100"/>
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>		
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.summary" mandatory="true" maxCharacters="500"></butter:label>
					<butter:field>
						<butter:textarea rows="5" alt="summary|Y|LIMITEDLENGTH|500" path="summary" onkeyup="limitText(this, 'news.summary');"/>
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>							
			<butter:bodyRow>
				<butter:bodyCol>
					<butter:label key="news.content" maxCharacters="4000"></butter:label>
					<butter:field>
				       <butter:textarea rows="10" alt="content|N|LIMITEDLENGTH|4000" path="content" onkeyup="limitText(this, 'news.content');"/>					
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>							
			</butter:tabBody>
		</butter:tab>
	</butter:pageContainer>
</form:form>
<butter:actionBar floating="true">
	<butter:button title="Save" onclick="javascript:ValidateForm(document.forms.newsForm);"/>
	<butter:cancelButton/>
</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />