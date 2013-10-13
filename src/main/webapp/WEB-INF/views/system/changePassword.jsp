<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
 
  function startUp(){  	     
	focusForm(document.forms.passwordForm);
  }
</script>

<butter:backLink finalLocation="true"/>

<!-- END OF PAGE TITLE BAR COMPONENT -->
<form:form commandName="passwordRequest" id="passwordForm">
	<butter:pageContainer pageTitle="Change password">

	<butter:tab title="Enter details" alert="* Required">
		<butter:tabBody type="edit">
		    <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.forename" mandatory="true"/>
	            <butter:field>
	            		${currentUser.firstName }
            </butter:field>
			</butter:bodyCol>
				<butter:bodyCol>
			   <butter:label key="user.surname" mandatory="true"/>
	            <butter:field>
	                ${currentUser.surname}
	            </butter:field>
			</butter:bodyCol>
			 </butter:bodyRow>

		    <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.userid" mandatory="true"/>
	            <butter:field>
	            	${currentUser.userId}
	            </butter:field>
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>
		  <butter:spacer></butter:spacer>
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.new.password" mandatory="true"/>
	            <butter:field>
	            	<form:password path="newPassword" maxlength="10" alt="new password|Y" />
	            </butter:field>
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.verify.password" mandatory="true"/>
	            <butter:field>
	            	<form:password path="verifyNewPassword" maxlength="10" alt="verify new password|Y" />
	            </butter:field>
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>										
		</butter:tabBody>
	</butter:tab>
	
	
	
	</butter:pageContainer>
	
	<butter:actionBar floating="true">
	  <butter:buttonGroup>	
	  		<butter:button onclick="ValidateForm(document.forms.passwordForm)" title="Save"></butter:button>	  				  			  			
			<butter:cancelButton />
	  </butter:buttonGroup>
	</butter:actionBar>
</form:form>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />