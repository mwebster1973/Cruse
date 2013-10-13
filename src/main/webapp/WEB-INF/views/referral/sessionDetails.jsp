<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
	function startUp(){
		focusForm(document.forms.referralForm);
	}
	
	// Define the entry point
    $(document).ready(function()
    {
    	$(".removeDateAnchor" ).click(function(e){
    		$(this).prev().remove();
			$(this).remove();   		
    	});
    	
    	$(".multipleDateField").datepicker({
			constrainInput: false,
			dateFormat: "dd/mm/yy",
			changeMonth: true,
			changeYear: true
		});
    	
    	$(".dateAddAnchor").click(function(e){
    		var parentId = $(this).attr('id'); 
    		$("<input type='text' name='"+parentId+"' style='width:80px;'/>")
    			.appendTo($(this).parent())
    			.datepicker({
    				constrainInput: false,
    				dateFormat: "dd/mm/yy",
    				changeMonth: true,
    				changeYear: true
    			})
    			.focus();
    		// add a remove button
    		$("<a href='#'><img alt='Remove Date' src='resources/images/cross.gif' style='margin-right:10px;'/></a>")
    			.appendTo($(this).parent())
    			.click(function(e){
    				$(this).prev().remove();
    				$(this).remove();
    			})
    			
    	});    	
    	
    })

</script>
 
<butter:backLink finalLocation="true"/>

<form:form commandName="currentReferral" id="referralForm" >
<butter:pageContainer pageTitle="Edit Session Details">
		<butter:tab title="Session details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.allocationDate"></butter:label>
						<butter:field>
							<butter:dateInput path="allocationDate" label="Allocaiton Date"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol/>
				</butter:bodyRow>
				<butter:spacer></butter:spacer>
		 		<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.counsellor" maxCharacters="1000"></butter:label>
						<butter:field>
							<butter:textarea rows="4" path="counsellor" alt="Counsellor|N"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow> 
				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.outpost"></butter:label>
						<butter:field>
							<butter:select path="outpost" alt="Outpost|N" items="${outpostList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.sessions"></butter:label>
						<butter:field>
						<butter:text path="numberOfSessions" size="4" maxlength="4" 
								cssClass="shortText"
								alt="Number of Sessions|Y|Integer"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.ending"></butter:label>
						<butter:field>
							<butter:select path="ending" alt="Ending|N" items="${endingList}"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.closureDate"></butter:label>
						<butter:field>
							<butter:dateInput path="closureDate"  label="closure Date" ></butter:dateInput>
							
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.firstAppointmentDate"></butter:label>
						<butter:field>
							<butter:dateInput path="firstAppointmentDate"  label="first appointment date" ></butter:dateInput>
						</butter:field>
					</butter:bodyCol>
                    <butter:bodyCol>
						<butter:label key="referral.telephoneSupport"></butter:label>
						<butter:field>					
						   <form:checkbox path="telephoneSupport" cssClass="check"/>
						</butter:field>
					</butter:bodyCol>					
				</butter:bodyRow>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.firstOngoingAppointmentDate"></butter:label>
						<butter:field>
							<butter:dateInput path="firstOngoingAppointmentDate"  label="first ongoing appointment date" ></butter:dateInput>
						</butter:field>
					</butter:bodyCol>
                    <butter:bodyCol>
						<butter:label key="referral.coreCompleted"></butter:label>
						<butter:field>				
							<form:checkbox path="coreCompleted" cssClass="check"/>	
						</butter:field>
					</butter:bodyCol>					
				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.coreDates"></butter:label>
						<butter:field>
							<div id="coreDatesDiv" style="display:inline;">
							    <a href="#" class="dateAddAnchor" id="coreDates">Add</a>
								<c:forEach items="${currentReferral.coreDates}" var="coreDate"  varStatus="idx">
									<input class="multipleDateField" type='text' name='coreDates' style='width:80px;' value="<fmt:formatDate value="${coreDate}" pattern="dd/MM/yyyy"/>"/>
									<a href="#" class="removeDateAnchor"><img alt="Remove Date" src="resources/images/cross.gif"/></a>
								</c:forEach>							
							</div>
						</butter:field>
					</butter:bodyCol>                    					
				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.dnaDates"></butter:label>
						<butter:field>
							<div id="dnaDatesDiv" style="display:inline;">
							    <a href="#" class="dateAddAnchor" id="dnaDates">Add</a>
								<c:forEach items="${currentReferral.dnaDates}" var="dnaDate"  varStatus="idx">
									<input class="multipleDateField" type='text' name='dnaDates' style='width:80px;' value="<fmt:formatDate value="${dnaDate}" pattern="dd/MM/yyyy"/>"/>
									<a href="#" class="removeDateAnchor"><img alt="Remove Date" src="resorces/images/cross.gif"/></a>
								</c:forEach>							
							</div>
						</butter:field>
					</butter:bodyCol>                    					
				</butter:bodyRow>

				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.cancellationDates"></butter:label>
						<butter:field>
							<div id="cancellationDatesDiv" style="display:inline;">
							    <a href="#" class="dateAddAnchor" id="cancellationDates">Add</a>
								<c:forEach items="${currentReferral.cancellationDates}" var="cancelDate"  varStatus="idx">
									<input class="multipleDateField" type='text' name='cancellationDates' style='width:80px;' value="<fmt:formatDate value="${cancelDate}" pattern="dd/MM/yyyy"/>"/>
									<a href="#" class="removeDateAnchor"><img alt="Remove Date" src="resources/images/cross.gif"/></a>
								</c:forEach>							
							</div>
						</butter:field>
					</butter:bodyCol>                    					
				</butter:bodyRow>				
				
				
									
				
				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.comments" maxCharacters="1000" ></butter:label>
						<butter:field>
							<butter:textarea rows="4" path="comments" alt="Comments|N"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
			</butter:tabBody>
		</butter:tab>
		
	</butter:pageContainer>
	</form:form>
	<butter:actionBar floating="true">
		<butter:button title="Save" onclick="javascript:ValidateForm(document.forms.referralForm);"/>  			
	  	<butter:cancelButton/>
	</butter:actionBar>  
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />