<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
 
<script>
	function startUp(){
		focusForm(document.forms.adminForm);
	}
</script>


<butter:backLink finalLocation="true"/>
<form:form commandName="admin" id="adminForm">
	<butter:pageContainer pageTitle="${screenName}">
		<butter:tab title="Enter details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
					<butter:label key="ref.sequenceNo" mandatory="true"/>
						<butter:field>
						  <butter:text path="sequence" alt="sequence|Y|INTEGER" maxlength="3"></butter:text>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>	
			
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="ref.code" mandatory="${empty param.id }"/>
						<butter:field>
							<c:choose>
							  <c:when test="${empty param.id }">
								  <butter:text upperCaseNoSpaces="true" id="codeComp" path="code" alt="code|Y|CODE|10" maxlength="10"></butter:text>
							  </c:when>							
							  <c:otherwise>
							  	<c:out value="${admin.code}"></c:out>
							  </c:otherwise>
							</c:choose>
						</butter:field>
					</butter:bodyCol>
				<butter:bodyCol>
					<butter:label key="ref.description" mandatory="true"/>
						<butter:field>
						  <butter:text path="description" alt="desciption|Y|10" maxlength="50"></butter:text>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>	
				
				<c:if test="${param.domain=='Area'}">
		  		<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="pct" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="pct" alt="PCT|Y" items="${pctList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>	
				</c:if>
			</butter:tabBody>
		</butter:tab>
	</butter:pageContainer>
</form:form>

<butter:actionBar floating="false">
	<butter:button title="Save" onclick="ValidateForm(document.forms.adminForm);"/>
	<butter:cancelButton/>
</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />