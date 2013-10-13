<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
 
<script>
	function startUp(){
		focusForm(document.forms.configForm);
	}
</script>


<% String title = "Add Configuration"; %>

<c:if test="${not empty param.id }" >
	<% title = "Edit Configuration"; %>
</c:if>


<butter:backLink finalLocation="true"/>
<form:form commandName="config" id="configForm">
	<butter:pageContainer pageTitle="<%=title%>">
		<butter:tab title="Enter details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="config.parameter" mandatory="${empty param.id }"/>
						<butter:field>
							<c:choose>
							  <c:when test="${empty param.id }">
								  <butter:text id="parameterComp" path="configParam" alt="parameter|Y|CODE|50" maxlength="50"></butter:text>
							  </c:when>							
							  <c:otherwise>
							  	<c:out value="${config.configParam}"></c:out>
							  </c:otherwise>
							</c:choose>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>					
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="config.value" mandatory="true" maxCharacters="500"/>
						<butter:field>
							<butter:textarea cssClass="textArea"  path="configValue"
									rows="4"
									alt="value|Y|LIMITEDLENGTH|500"
									onkeyup="limitText(this, 'config.value');"/> 
									
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>					
				</butter:bodyRow>				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="config.description" mandatory="true" maxCharacters="500"/>
						<butter:field>
						    <butter:textarea 
									cssClass="textArea"  path="configDescription"
									rows="4"
									alt="description|Y|LIMITEDLENGTH|500"
									onkeyup="limitText(this, 'config.description');"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>					
				</butter:bodyRow>
			</butter:tabBody>
		</butter:tab>
	</butter:pageContainer>
</form:form>

<butter:actionBar floating="true">
	<butter:button title="Save" onclick="ValidateForm(document.forms.configForm);"/>
	<butter:cancelButton/>
</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />