<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
 
<script>
	function startUp(){
		focusForm(document.forms.gpForm);
	}
</script>


<butter:backLink finalLocation="true"/>
<form:form commandName="gp" id="gpForm">
	<butter:pageContainer pageTitle="${screenName}">
		<butter:tab title="Enter details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="gp.surgeryName" mandatory="true"/>
						<butter:field>
						  <butter:text path="surgeryName" alt="Surgery name|Y|50" maxlength="50"></butter:text>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="gp.postCode" mandatory="true"/>
						<butter:field>
						  <butter:text path="postCode" alt="postcode|Y|10" maxlength="10"></butter:text>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="gp.telephoneNumber" mandatory="true"/>
						<butter:field>
						  <butter:text path="telephoneNumber" alt="Telephone Number|Y|20" maxlength="20"></butter:text>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
			</butter:tabBody>
		</butter:tab>
	</butter:pageContainer>
</form:form>

<butter:actionBar floating="false">
	<butter:button title="Save" onclick="ValidateForm(document.forms.gpForm);"/>
	<butter:cancelButton/>
</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />