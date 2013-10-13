<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
	function startUp(){
		focusForm(document.forms.userSearchForm);	
	}
</script>

<butter:backLink finalLocation="true"/>

<form:form commandName="userCriteria" id="userSearchForm">

<butter:pageContainer pageTitle="User Search">
	
   <jsp:include page="/WEB-INF/views/common/savedSearches.jsp"/>
	
  <butter:tab title="Enter search criteria">
	<butter:tabBody type="edit">
	    <butter:bodyRow>
			<butter:bodyCol>
			  <butter:label key="user.search" />
			  <butter:field>
                  <butter:text path="userName" 
	                 maxlength="30" cssStyle="width:20%;" upperCase="true" 
	                 autoSubmit="true" />	              
	                &nbsp;&nbsp;	               
	              <form:radiobutton cssClass="radio" path="userNameSearchType" value="CONTAINS"/>Contains text
				    &nbsp;&nbsp;
	              <form:radiobutton cssClass="radio" path="userNameSearchType" value="BEGINS"/>Begins with text				    

	          </butter:field>
	        </butter:bodyCol>
	        
	    </butter:bodyRow>
    </butter:tabBody>
  </butter:tab>	

  <butter:tab title="User roles">
    <butter:tabBody type="edit">
      
	    <table width="100%" border="0">
	      <tr>	        
            <td>  
            	<form:checkbox cssClass="check" cssStyle="background:transparent;" path="selectedRoles" value="AD" />&nbsp;Admin
			</td>    		    
		  </tr>
		  <tr>
            <td>            
            	<form:checkbox cssClass="check" cssStyle="background:transparent;" path="selectedRoles" value="IA" />&nbsp;IT admin
            </td>              		 	              
		  </tr>
			  <tr>
            <td>            
            	<form:checkbox cssClass="check" cssStyle="background:transparent;" path="selectedRoles" value="CO" />&nbsp;Counsellor
            </td>              		 	              
		  </tr>
		  	<tr>
            <td>            
            	<form:checkbox cssClass="check" cssStyle="background:transparent;" path="selectedRoles" value="DE" />&nbsp;Data Entry
            </td>              		 	              
		  </tr>
		</table>
            
    </butter:tabBody>
  </butter:tab>

</butter:pageContainer>

<butter:actionBar floating="true">
   <butter:button onclick="javascript:ValidateForm(document.forms.userSearchForm)" title="Search"></butter:button>
   <butter:button onclick="javascript:location.href='userSearchRequest.htm?reset=true'" title="Reset"></butter:button>

</butter:actionBar>

</form:form>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />

