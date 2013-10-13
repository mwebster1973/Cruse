<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
 
<script>
	function startUp(){
		focusForm(document.forms.rgForm);
	}
</script>

<% String pageTitle = "Add Group Membership"; %>

<c:if test="${not empty param.id }" >
	<% pageTitle = "Edit Group Membership"; %>
</c:if>

<butter:backLink finalLocation="true"/>
<form:form commandName="group" id="rgForm">
<butter:pageContainer pageTitle="<%=pageTitle%>">
		<butter:tab title="Enter details" alert="* Mandatory">
			<butter:tabBody type="edit">
				<butter:bodyRow>
					<butter:bodyCol>
					<butter:label key="group.description" mandatory="${empty param.id}"/>
						<butter:field>
							<c:choose>
								<c:when test="${empty param.id}">
									<butter:select path="group" alt="Group|Y" items="${groupList}"/>
								</c:when>
								<c:otherwise>
									${group.group.description}
								</c:otherwise>
							</c:choose>

						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol></butter:bodyCol>
				</butter:bodyRow>	
				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="group.status">
						</butter:label>
						<butter:field>
							<butter:select path="status" items="${statusList}" cssStyle="width:100px;"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="group.waiting.list.date">
						</butter:label>
						<butter:field>
							<butter:dateInput path="waitingListDate" label="Waiting list Date" mandatory="false"/>
						</butter:field>
					</butter:bodyCol>
					
				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol/>
					<butter:bodyCol>
						<butter:label key="group.joined.date">
						</butter:label>
						<butter:field>
							<butter:dateInput path="joinedDate" label="Joined Date" mandatory="false"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="group.ending">
						</butter:label>
						<butter:field>
							<butter:select path="ending" items="${endingsList}" cssStyle="width:100px;"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="group.closed.date">
						</butter:label>
						<butter:field>
							<butter:dateInput path="closedDate" label="Closed Date" mandatory="false"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				<butter:spacer></butter:spacer>
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="group.notes" maxCharacters="2000"/>
						<butter:field>
							<butter:textarea alt="Notes|N|textarea|2000" path="notes" rows="4" onchange="limitText(this, 2000)" />
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
			</butter:tabBody>
		</butter:tab>
	</butter:pageContainer>
</form:form>

<butter:actionBar floating="false">
	<butter:button title="Save" onclick="ValidateForm(document.forms.rgForm);"/>
	<butter:cancelButton/>
</butter:actionBar>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />