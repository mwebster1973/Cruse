<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
	function startUp(){
		focusForm(document.forms.ethnicForm);
	}

  function add(){    	
		document.forms.ethnicForm.action="<%=request.getContextPath()%>/addEthnicEntry.htm";
		ValidateForm(document.forms.ethnicForm)
	  }
		  
  function addAnother(){
  	document.forms.ethnicForm.action="<%=request.getContextPath()%>/addEthnicEntry.htm?addAnother=true";
	ValidateForm(document.forms.ethnicForm)
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

<% String title = "Add Cultural Background"; %>

<c:if test="${not empty param.newsId }" >
	<% title = "Edit Cultural Background"; %>
</c:if>

<form:form commandName="currentEthnicEntry" id="ethnicForm" >
<butter:pageContainer pageTitle="<%=title %>">
		<butter:tab title="Enter details" alert="* Mandatory">
			<butter:tabBody type="edit">
								<butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="ethnic.date.entered" mandatory="true"/>
	            <butter:field>
	            	<butter:dateInput path="dateEntererd" mandatory="true" label="Date entered"/>
		  		</butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol>
			    <butter:label key="ethnic.id"/>
	            <butter:field>
		  			<c:out value="${currentEthnicEntry.seqId}"/>
		  		</butter:field>						       
			</butter:bodyCol>

		  </butter:bodyRow>		  
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="ethnic.background" mandatory="true"/>
	            <butter:field>
	            	<butter:select path="ethnicBackground" items="${ethnicBackgroundList}" alt="Ethnic origin|Y">
	            	</butter:select>
		  			
		  		</butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol>
			    <butter:label key="ethnic.religion" mandatory="true"/>
	            <butter:field>
	            	<butter:select path="religion" items="${religionList}" alt="Religion|Y">
	            	</butter:select>
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
					<butter:label key="ethnic.registered.disabled"></butter:label>
					<butter:field>
						<form:checkbox path="registeredDisabled" cssClass="check"/>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol>
					<butter:label key="referral.carer"></butter:label>
					<butter:field>
						<form:checkbox path="carer" cssClass="check"/>
					</butter:field>
				</butter:bodyCol>
			</butter:bodyRow>	
						<butter:bodyRow>	
				<butter:bodyCol>
					<butter:label key="ethnic.consider.disabled"></butter:label>
					<butter:field>
						<form:checkbox path="considerDisabled" cssClass="check"/>
					</butter:field>
				</butter:bodyCol>
				<butter:bodyCol/>
			</butter:bodyRow>	
		  		  <butter:spacer></butter:spacer>
		  	<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.gender"></butter:label>
						<butter:field>
							<butter:select path="gender" alt="Gender|N" items="${genderList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.age"></butter:label>
						<butter:field>
							<butter:select path="ageOfClient" alt="Age of Client|N" items="${ageList}"/>
						</butter:field>
					</butter:bodyCol>
			</butter:bodyRow>
		  
		  
		  
		  
		  
			</butter:tabBody>
			
			
			
			
		</butter:tab>
	</butter:pageContainer>
</form:form>
<butter:actionBar floating="false">

		 <c:choose>
	  		<c:when test="${param.id!= null}">
				<butter:button title="Save" onclick="javascript:ValidateForm(document.forms.ethnicForm);"/>  			
	  		</c:when>	  	
	  		<c:otherwise>
				<butter:button onclick="JavaScript:add()" title="Save"></butter:button>					  			  		
				<butter:button onclick="JavaScript:addAnother()" title="Save & Add another"></butter:button>	  				  			  						
	  		</c:otherwise>
	  	</c:choose>
	
	
	<butter:cancelButton/>
</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />