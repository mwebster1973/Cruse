<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
	function startUp(){
		focusForm(document.forms.sessionSearchForm);	
	}

	  function search(){    	
			document.forms.sessionSearchForm.action="<%=request.getContextPath()%>/sessionSearchRequest.htm";
			ValidateForm(document.forms.sessionSearchForm)
		  }
			  
	  
</script>


<butter:backLink finalLocation="true"/>

<form:form commandName="sessionCriteria" id="sessionSearchForm">

<butter:pageContainer pageTitle="Session Search">
	
  <butter:tab title="Date criteria">
	<butter:tabBody type="edit">
	    <butter:bodyRow>
			<butter:bodyCol>
			  <butter:label key="dates.range" dateInput="true"/>
			  <butter:field>
			  	  <butter:dateInput path="dateRangeStart"></butter:dateInput>
			  	  <butter:dateInput path="dateRangeEnd"></butter:dateInput>
            </butter:field>
	        </butter:bodyCol>
	        <butter:bodyCol></butter:bodyCol>
	    </butter:bodyRow>
    </butter:tabBody>
  </butter:tab>	
  
</butter:pageContainer>

<butter:actionBar floating="true">
   <butter:button onclick="search();" title="Show results"></butter:button>
   <butter:button onclick="javascript:location.href='sessionSearchRequest.htm?reset=true'" title="Reset"></butter:button>

</butter:actionBar>

</form:form>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />

