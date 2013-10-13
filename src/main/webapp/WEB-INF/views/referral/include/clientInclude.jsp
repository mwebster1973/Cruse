<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 
		
<butter:tab title="Client Details">
	<butter:tabBody type="edit">
	  	<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.client" mandatory="true"></butter:label>
						<butter:field>
								<butter:text path="clientInitials" size="8" maxlength="10" 
								cssClass="shortText"
								alt="Client initials|Y"
								upperCaseNoSpaces="true"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.clientTelephoneNo"></butter:label>
						<butter:field>
						<butter:text path="clientTelephoneNo"  maxlength="100" 
									alt="Client telephone|N"/>
						</butter:field>
					</butter:bodyCol>
			</butter:bodyRow>
			<c:if test="${ user.admin || user.counsellor}">
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.clientName"></butter:label>
						<butter:field>
							<butter:text path="clientName" size="200" maxlength="200" 
									alt="Client Name|N"/>
						
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol>
						<butter:label key="referral.mobilePhoneNumber"></butter:label>
						<butter:field>
						<butter:text path="mobilePhoneNumber"  maxlength="100" 
									alt="Monile telephone|N"/>
						</butter:field>
					</butter:bodyCol>
					
				</butter:bodyRow>
				
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label key="referral.email"></butter:label>
						<butter:field>
						<butter:text path="email"  maxlength="100" 
									alt="Email|N"/>
						</butter:field>
					</butter:bodyCol>
				<butter:bodyCol>
						<butter:label key="referral.workPhoneNumber"></butter:label>
						<butter:field>
						<butter:text path="workPhoneNumber"  maxlength="100" 
									alt="Work telephone|N"/>
						</butter:field>
					</butter:bodyCol>
				</butter:bodyRow>
				 <butter:spacer></butter:spacer>	
				<butter:bodyRow>
					<butter:bodyCol>
						<butter:label maxCharacters="1000" key="referral.clientAddress"></butter:label>
						<butter:field>
							<butter:textarea rows="4" path="clientAddress" alt="Counsellor|N"/>
						</butter:field>
					</butter:bodyCol>
					<butter:bodyCol></butter:bodyCol>
					
				</butter:bodyRow>
			</c:if>
		</butter:tabBody>
	</butter:tab>
	