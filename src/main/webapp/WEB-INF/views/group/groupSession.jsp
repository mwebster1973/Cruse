<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
	function startUp(){
		focusForm(document.forms.sessionForm);
	}

  function add(){    	
		document.forms.sessionForm.action="<%=request.getContextPath()%>/addGroupSession.htm?group=${currentSession.group.code}";
		ValidateForm(document.forms.sessionForm);
	  }
		  
  function addAnother(){
  	document.forms.sessionForm.action="<%=request.getContextPath()%>/addGroupSession.htm?group=${currentSession.group.code}&addAnother=true";
	ValidateForm(document.forms.sessionForm);
  }
</script>
 
<butter:backLink finalLocation="true"/>

<c:set var="title" value="Add "></c:set>
<c:if test="${not empty param.id }" >
	<c:set var="title" value="Edit "></c:set>
</c:if>

<form:form commandName="currentSession" id="sessionForm" >
<butter:pageContainer pageTitle="${title}${currentSession.group.description} Session">
		<butter:tab title="Session details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="session.date" dateInput="true" mandatory="true"></butter:label>
						<butter:field>
							<butter:dateInput path="sessionDate" mandatory="true" label="Session Date" ></butter:dateInput>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>

				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="session.client.count"></butter:label>
						<butter:field>
							<butter:text path="attendedCount" size="5" maxlength="5" alt="No. Clients|Y"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>

				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="session.volunteer.count"></butter:label>
						<butter:field>
							<butter:text path="volenterCount" size="5" maxlength="5" alt="No. Volenteers|Y"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>

				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label maxCharacters="1000" key="session.notes"></butter:label>
						<butter:field>
							<butter:textarea rows="4" path="notes" alt="Notes|N"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				
			</butter:tabBody>
		</butter:tab>
		
	</butter:pageContainer>
	</form:form>
	<butter:actionBar floating="true">
		 <c:choose>
	  		<c:when test="${param.id!= null}">
				<butter:button title="Save" onclick="javascript:ValidateForm(document.forms.sessionForm);"/>  			
	  		</c:when>	  	
	  		<c:otherwise>
				<butter:button onclick="JavaScript:add()" title="Save"></butter:button>					  			  		
				<butter:button onclick="JavaScript:addAnother()" title="Save & Add another"></butter:button>	  				  			  						
	  		</c:otherwise>
	  	</c:choose>
	
	
	<butter:cancelButton/>
</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />