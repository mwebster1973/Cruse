<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
	function startUp(){
		focusForm(document.forms.referralForm);
	}

</script>
 
<butter:backLink finalLocation="true"/>

<form:form commandName="currentReferral" id="referralForm" >
<butter:pageContainer pageTitle="Edit Session Details">
		<jsp:include page="include/clientInclude.jsp" />		
	</butter:pageContainer>
	</form:form>
	<butter:actionBar floating="false">
		<butter:button title="Save" onclick="javascript:ValidateForm(document.forms.referralForm);"/>  			
	  	<butter:cancelButton/>
	</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />