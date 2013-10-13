<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<script type="text/javascript">
	function startUp(){
		focusForm(document.forms.referralSearchForm);	
	}

	  function search(){    	
			document.forms.referralSearchForm.action="<%=request.getContextPath()%>/referralSearchRequest.htm";
			ValidateForm(document.forms.referralSearchForm)
		  }
			  
	  function searchSummary(){
	  	document.forms.referralSearchForm.action="<%=request.getContextPath()%>/referralSearchRequest.htm?summary=true";
		ValidateForm(document.forms.referralSearchForm)
	  }


</script>

<script type="text/javascript">
	var requester = null;

	function dropDownSelected(){
		ajaxGoGo('');
	}

	function ajaxGoGo(filterString){
		filterString = document.getElementById("attributeSelections.input").value;
		if (filterString == 'Begin typing value (or part of) to populate list') {
			filterString = '';
		}
		
		dropDown = document.getElementById('attribute').value;
		initialiseRequest();	
		requester.onreadystatechange = ajaxComeBack;
		requester.open("GET", "attributeChangeAjax.htm?selectItem=Attribute&dropDown="+dropDown+"&filterText=" + filterString);		
		requester.send(null);		
 
	}
	
	function ajaxComeBack(){
	  if (requester.readyState == 4) {	
		try {
			if (requester.status == 200)
			{
				// make all the labels bold.
				var xmlChoiceList = requester.responseXML.getElementsByTagName("choices")[0];							
				var codeList = xmlChoiceList.getElementsByTagName("attributeValues")[0];
				populateWorkArea(codeList,'attributeSelections');
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

<form:form commandName="referralCriteria" id="referralSearchForm">

<butter:pageContainer pageTitle="Referral Search">
	
   <jsp:include page="/WEB-INF/views/common/savedSearches.jsp"/>
	
		<butter:tab title="Client Details">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.client" />
						<butter:field>
							<butter:text path="clientInitials" size="8" maxlength="10"
								cssClass="shortFormItem" alt="Client initials|N"
								upperCaseNoSpaces="true" />
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol></butter:bodyCol>
				</butter:bodyRow>

				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.clientName" />
						<butter:field>
							<butter:text autoSubmit="true" path="clientName" size="200" maxlength="200"
								alt="Client Name|N" />
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol></butter:bodyCol>
				</butter:bodyRow>
			</butter:tabBody>
		</butter:tab>	
	
  <butter:tab title="Date criteria">
	<butter:tabBody type="edit">
	
		<butter:bodyRow>
			<butter:bodyCol>
			  <butter:label key="dates.type"/>
			  <butter:field>
			  	  <form:radiobutton cssClass="radio" path="dateType" title="Referral" value="R"></form:radiobutton>Referral Date&nbsp;&nbsp;&nbsp;
			  	  <form:radiobutton cssClass="radio" path="dateType" title="Allocation" value="A"></form:radiobutton>Allocation Date&nbsp;&nbsp;&nbsp;
			  	  <form:radiobutton cssClass="radio" path="dateType" title="Allocation" value="C"></form:radiobutton>Closure Date
            </butter:field>
	        </butter:bodyCol>
	        <butter:bodyCol></butter:bodyCol>
	    </butter:bodyRow>
	    <butter:bodyRow>
			<butter:bodyCol>
			  <butter:label key="dates.individual" dateInput="true"/>
			  <butter:field>
			  	  <butter:dateInput path="individualDate"></butter:dateInput>
            </butter:field>
	        </butter:bodyCol>
	        <butter:bodyCol></butter:bodyCol>
	    </butter:bodyRow>
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
  
  <butter:tab title="Attributes">
  <butter:tabBody type="edit">
  	<butter:bodyRow>
  		<butter:bodyCol>
  			<butter:label key="attribute"></butter:label>
  			<butter:field>
  				<butter:select id="attribute" onchange="dropDownSelected()" path="attribute" items="${attributeList}"/>
  			</butter:field>
  		</butter:bodyCol>
  		<butter:bodyCol/>
  	</butter:bodyRow>
  	<butter:bodyRow>
  		<butter:bodyCol>
  			<butter:label key="attribute.selection"></butter:label>
  			<butter:field>
  				<butter:addToList ajaxGoGo="ajaxGoGo();" generateBlank="true" path="${attributeSelections}" ></butter:addToList>
  			</butter:field>
  		</butter:bodyCol>
  	</butter:bodyRow>
  	
  </butter:tabBody>
  </butter:tab>
  
  <butter:tab title="Summary options">
	<butter:tabBody type="edit">
	    <butter:bodyRow>
			<butter:bodyCol>
			  <butter:label key="summary.order" />
			  <butter:field>
			  <form:radiobutton cssClass="radio" path="summaryOrder" value="display" title="Display order"/>Display order
			  <form:radiobutton cssClass="radio" path="summaryOrder" value="highest" title="Highest count"/>Highest count
			  <form:radiobutton cssClass="radio" path="summaryOrder" value="alpha" title="Alphabetical"/>Alphabetical

            </butter:field>
	        </butter:bodyCol>
			<butter:bodyCol/>
	    </butter:bodyRow>
	    <butter:bodyRow>
			<butter:bodyCol>
			  <butter:label key="summary.include.zero" />
			  <butter:field>
			  	<form:radiobutton cssClass="radio" path="includeZero" value="true" title="Yes"/>Yes 
			  	<form:radiobutton cssClass="radio" path="includeZero" value="false" title="No"/>No
  
            </butter:field>
	        </butter:bodyCol>
			<butter:bodyCol/>
	    </butter:bodyRow>
    </butter:tabBody>
  </butter:tab>	
  
  
</butter:pageContainer>

<butter:actionBar floating="true">
   <butter:button onclick="search();" title="Show results"></butter:button>
   <butter:button onclick="searchSummary();" title="Show summary"></butter:button>
   
   <butter:button onclick="javascript:location.href='referralSearchRequest.htm?reset=true'" title="Reset"></butter:button>

</butter:actionBar>

</form:form>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />

