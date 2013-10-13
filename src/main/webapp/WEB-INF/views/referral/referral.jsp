<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
	function startUp(){
		focusForm(document.forms.referralForm);
	}

  function add(){    	
		document.forms.referralForm.action="<%=request.getContextPath()%>/addReferral.htm";
		ValidateForm(document.forms.referralForm);
	  }
		  
  function addAnother(){
  	document.forms.referralForm.action="<%=request.getContextPath()%>/addReferral.htm?addAnother=true";
	ValidateForm(document.forms.referralForm);
  }
	
</script>

<script type="text/javascript">
	var requester = null;

	
	function areaSelected(){
		document.getElementById('areaComp').value;
		var selection = document.getElementById('areaComp').value;
		if (selection.length >0){
			initialiseRequest();	
			requester.onreadystatechange = ajaxComeBack;
			requester.open("GET", "areaChangeAjax.htm?selectItem=Area&dropDown="+selection);		
			requester.send(null);
		}		
	}
	
	function ajaxComeBack(){
	  if (requester.readyState == 4) {	
		try {
			if (requester.status == 200)
			{
				// make all the labels bold.
				var pct  = requester.responseXML.getElementsByTagName("PCT")[0];							
				document.getElementById('pctComp').value= pct.firstChild.nodeValue;
				
			} else if (requester.status != 0) {
				alert("There was an error while retrieving the URL: " + requester.statusText);
			}
		}
		catch (error) {
			alert(error.description);
		}
	} 
	return true;
  }
</script>
 
<butter:backLink finalLocation="true"/>

<% String title = "Add Referral"; %>

<c:if test="${not empty param.id }" >
	<% title = "Edit Referral"; %>
</c:if>

<form:form commandName="currentReferral" id="referralForm" >
<butter:pageContainer pageTitle="<%=title %>">
		<butter:tab title="Referral details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.no" mandatory="true"></butter:label>
						<butter:field>
							<butter:text path="referralNo" size="8" maxlength="10" 
								cssClass="shortText"
								alt="Referral No.|Y"
								upperCaseNoSpaces="true"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.date" dateInput="true" mandatory="true"></butter:label>
						<butter:field>
							<butter:dateInput path="referralDate" mandatory="true" label="Referral Date" ></butter:dateInput>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
			
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.staff" mandatory="true"></butter:label>
						<butter:field>
							<butter:text path="staffInitials" size="8" maxlength="10" 
								cssClass="shortText"
								alt="Staff initials|Y"
								upperCaseNoSpaces="true"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>

				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.contact" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="contactBy" alt="Contact by|Y" items="${contactByList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.bereaved.of" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="bereavedOf" alt="Bereaved of|Y" items="${bereavedOfList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.multiple.loss"></butter:label>
						<butter:field>
						    <form:checkbox path="multipleLoss" cssClass="check"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow> 
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.length" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="lengthOfBereavement" alt="Length of bereavement|Y" items="${lengthOfBereavementList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.cause" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="causeOfDeath" alt="Cause of death|Y" items="${causeOfDeathList}"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.gender" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="gender" alt="Gender|Y" items="${genderList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.age" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="ageOfClient" alt="Age of Client|Y" items="${ageList}"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.hear" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="hearOfCruse" alt="Client hear of cruse|Y" items="${hearOfCruseList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.placeOfDeath" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="placeOfDeath" alt="Place of death|Y" items="${placeOfDeathList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.clientResides" mandatory="true"></butter:label>
						<butter:field>
							<butter:select path="clientResidence" alt="Client resides in|Y" items="${clientResidenceList}"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				
				<butter:spacer></butter:spacer>
				<butter:bodyRow>		
					<butter:bodyCol>
						<butter:label key="referral.area" mandatory="true"></butter:label>
						<butter:field>
							<butter:select id="areaComp" onchange="areaSelected()" path="area" alt="Area code|Y" items="${areaList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="pct" mandatory="true"></butter:label>
						<butter:field>
							<butter:select id="pctComp" path="pct" alt="PCT|Y" items="${pctList}"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.gp" mandatory="false"></butter:label>
						<butter:field>
							<butter:select path="gp" alt="GP|N" items="${gpList}" itemValue="id" itemLabel="description"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.gpNotes"></butter:label>
						<butter:field>
							<butter:text path="gpNotes" size="40" maxlength="200" alt="GP Notes|N"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				
				<butter:spacer></butter:spacer>
				<butter:bodyRow>	
					<butter:bodyCol>
						<butter:label key="referral.carer"></butter:label>
						<butter:field>
							<form:checkbox path="carer" cssClass="check"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.pre.bereavement"></butter:label>
						<butter:field>
							<form:checkbox path="preBereavement" cssClass="check"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>		
				<butter:bodyRow>	
					<butter:bodyCol>
						<butter:label key="referral.home.visit"></butter:label>
						<butter:field>
							<form:checkbox path="homeVisit" cssClass="check"/>
						</butter:field>
					</butter:bodyCol>
					
					<butter:bodyCol>
						<butter:label key="referral.service"></butter:label>
						<butter:field>
							<form:checkbox path="service" cssClass="check"/>
						</butter:field>
					</butter:bodyCol>
						
				</butter:bodyRow>
				
				
				<butter:spacer></butter:spacer>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.outcome"></butter:label>
						<butter:field>
							<butter:text path="enquiryOutcome" size="40" maxlength="50" alt="Enquiry outcome|N"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>

			</butter:tabBody>
		</butter:tab>
		
		<c:if test="${param.id== null}">
			<jsp:include page="include/clientInclude.jsp" />
		</c:if>
		
	</butter:pageContainer>
	</form:form>
	<butter:actionBar floating="false">
		 <c:choose>
	  		<c:when test="${param.id!= null}">
				<butter:button title="Save" onclick="javascript:ValidateForm(document.forms.referralForm);"/>  			
	  		</c:when>	  	
	  		<c:otherwise>
				<butter:button onclick="JavaScript:add()" title="Save"></butter:button>					  			  		
				<butter:button onclick="JavaScript:addAnother()" title="Save & Add another"></butter:button>	  				  			  						
	  		</c:otherwise>
	  	</c:choose>
	
	
	<butter:cancelButton/>
</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />