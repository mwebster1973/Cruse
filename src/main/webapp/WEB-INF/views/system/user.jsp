<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
 
  function startUp(){  	     
	focusForm(document.forms.userForm);
  }
  
  function saveUser(id){    	
	document.forms.userForm.action="<%=request.getContextPath()%>/editUser.htm?id="+id;	  		
	ValidateForm(document.forms.userForm)
  }
  
  function addUser(){    	
	document.forms.userForm.action="<%=request.getContextPath()%>/addUser.htm";
	ValidateForm(document.forms.userForm)
  }
  
  function addAnotherUser(){
  	document.forms.userForm.action="<%=request.getContextPath()%>/addUser.htm?addAnother=true";
	ValidateForm(document.forms.userForm)
  }
  

  
</script>


<% String title = "Add User"; %>

<c:if test="${ not empty param.id }" >
	<% title = "Edit User &amp; Roles"; %>
</c:if>

<butter:backLink finalLocation="true"/>

<!-- END OF PAGE TITLE BAR COMPONENT -->
<form:form commandName="currentUser" id="userForm">
	<butter:pageContainer pageTitle="<%= title %>">

	<butter:tab title="Enter details" alert="* Required">
		<butter:tabBody type="edit">
		    <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.forename" mandatory="true"/>
	            <butter:field>
	                <butter:text path="firstName" maxlength="50" alt="forename|Y"/>
	            </butter:field>
			</butter:bodyCol>
						<butter:bodyCol>
			   <butter:label key="user.surname" mandatory="true"/>
	            <butter:field>
	                <butter:text path="surname" maxlength="50" alt="surname|Y"/>
	            </butter:field>
			</butter:bodyCol>
			 </butter:bodyRow>
		  <butter:spacer></butter:spacer>
		    <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.userid" mandatory="true"/>
	            <butter:field>
	                <butter:text id="cdsidComp" path="userId" maxlength="10" cssStyle="width : 50%;" upperCaseNoSpaces="true" alt="userid|Y" />
	            </butter:field>
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>
		  <c:if test="${ empty param.id }" >
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.password" mandatory="true"/>
	            <butter:field>
	            	<form:password path="password" maxlength="10" alt="password|Y" />
	            </butter:field>
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>
		  </c:if>
		
		  
		  <butter:spacer></butter:spacer>  
		
		  <butter:bodyRow>
			<butter:bodyCol>
				<butter:label key="active?" helpKey="active?" />
				<butter:field>
					<form:checkbox path="active"
						cssClass="check" value="Y" />
				</butter:field>		
			</butter:bodyCol>
			<butter:bodyCol/>
		  </butter:bodyRow>		
																			
		</butter:tabBody>
	</butter:tab>
	
	<butter:tab title="Roles">
		<butter:tabBody>
		    <butter:bodyRow>
		        <butter:bodyCol>
		          <butter:label key="user.itAdmin" />
		          <butter:field>
					<form:checkbox cssClass="check" path="itAdmin"/>												
				  </butter:field>
		        </butter:bodyCol>			    
		        <butter:bodyCol>
		          <butter:label key="user.admin" />
		          <butter:field>
					<form:checkbox cssClass="check" path="admin" />	
				  </butter:field>
		        </butter:bodyCol>	        
		    </butter:bodyRow>
		   		<butter:bodyRow>
		        <butter:bodyCol>
		          <butter:label key="user.counsellor" />
		          <butter:field>
					<form:checkbox cssClass="check" path="counsellor"/>												
				  </butter:field>
		        </butter:bodyCol>			    
		        <butter:bodyCol>
		          <butter:label key="user.dataEntry" />
		          <butter:field>
					<form:checkbox cssClass="check" path="dataEntry"/>												
				  </butter:field>
		        </butter:bodyCol>
		    </butter:bodyRow>
		</butter:tabBody>
	</butter:tab>
	
	</butter:pageContainer>
	
	<butter:actionBar floating="true">
	  <butter:buttonGroup>	
	  	<c:choose>
	  		<c:when test="${param.id!= null}">
				<butter:button onclick="JavaScript:saveUser('${param.id}')" title="Save"></butter:button>	  			
	  		</c:when>	  	
	  		<c:otherwise>
	  			<c:out value="${param.id}"></c:out>
				<butter:button onclick="JavaScript:addUser()" title="Save"></butter:button>	  				  			  		
				<butter:button onclick="JavaScript:addAnotherUser()" title="Save & Add another"></butter:button>	  				  			  						
	  		</c:otherwise>
	  	</c:choose>
		<butter:cancelButton />
	  </butter:buttonGroup>
	</butter:actionBar>
</form:form>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />