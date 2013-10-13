<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

 
<butter:crumb link="viewGp.htm"  
	parameters="id=${gp.id}"  
	returnTitle="GP Details"></butter:crumb>
<butter:backLink />


<butter:pageContainer pageTitle="GP Details">
	<butter:tab title="Main details" >
		<c:if test="${ user.itAdmin || user.admin }">
			<butter:tabLink type="edit" altText="Edit" link="editGp.htm?id=${gp.id}"/>  
	   </c:if>
	    <butter:tabLink type="print"/>
		<butter:tabBody type="view">
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="gp.surgeryName"/>
	            <butter:field>
		  				<c:out value="${gp.surgeryName}"/>
                </butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>		
		  
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="gp.postCode"/>
	            <butter:field>
		  				<c:out value="${gp.postCode}"/>
                </butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>
		  
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="gp.telephoneNumber"/>
	            <butter:field>
		  		   <c:out value="${gp.telephoneNumber}"/>
                </butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>	  
		</butter:tabBody>
	</butter:tab>
	
	<jsp:include page="/WEB-INF/views/common/audit.jsp" />
	
	</butter:pageContainer>
	 <butter:actionBar floating="false">
	      <butter:rowNavigator name="gps" viewAction="viewGp.htm"></butter:rowNavigator>	  	       
	</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />