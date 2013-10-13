<%@include file="/WEB-INF/views/common/taglibs.jsp"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

 
<butter:crumb link="viewUser.htm"  
	parameters="id=${currentUser.userId}"  
	returnTitle="User Details"></butter:crumb>
<butter:backLink />


<butter:pageContainer pageTitle="User Details">
	<butter:tab title="Main details" >
		<c:if test="${ user.itAdmin || user.admin }">
			<butter:tabLink type="edit" altText="Edit user" link="editUser.htm?id=${currentUser.userId}"/>  
	   </c:if>
	    <butter:tabLink type="print"/>
		<butter:tabBody type="view">
		  <butter:bodyRow>
			<butter:bodyCol>
			    <butter:label key="user.userid"/>
	            <butter:field>
		  				<c:out value="${currentUser.userId}"/>
                       (<c:out value="${currentUser.firstName}"/>&nbsp;<c:out value="${currentUser.surname}"/>)		          
		  		</butter:field>						       
			</butter:bodyCol>
			<butter:bodyCol></butter:bodyCol>
		  </butter:bodyRow>		  
		  
		  <butter:spacer></butter:spacer>  
		
		  <butter:bodyRow>
			<butter:bodyCol>
				<butter:label key="active?" helpKey="active?" />
				<butter:field>
					<butter:activeFlag name="currentUser" property="active"></butter:activeFlag>
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
					<butter:activeFlag name="currentUser" property="itAdmin"></butter:activeFlag>						
				  </butter:field>	
		        </butter:bodyCol>		        	        
		    
		        <butter:bodyCol>
		        	<butter:label key="user.admin" />
			        <butter:field>
			        	<butter:activeFlag name="currentUser" property="admin"></butter:activeFlag>						
					</butter:field>	
		        </butter:bodyCol>	        	        
		    </butter:bodyRow>	
		   		<butter:bodyRow>
		        <butter:bodyCol>
		          <butter:label key="user.counsellor" />
		          <butter:field>
					<butter:activeFlag name="currentUser" property="counsellor"></butter:activeFlag>						
				  </butter:field>	
		        </butter:bodyCol>		        	        
		        <butter:bodyCol>
		          <butter:label key="user.dataEntry" />
		          <butter:field>
					<butter:activeFlag name="currentUser" property="dataEntry"></butter:activeFlag>						
				  </butter:field>	
		        </butter:bodyCol>
		    </butter:bodyRow>	  
		</butter:tabBody>
	</butter:tab>
	
	<jsp:include page="/WEB-INF/views/common/audit.jsp" />
	
	</butter:pageContainer>
	 <butter:actionBar floating="true">
	      <butter:rowNavigator name="users" viewAction="viewUser.htm"></butter:rowNavigator>	  	 
	      
	      <butter:buttonGroup>
	      	<c:if test="${user.admin || currentUser.userId == user.userId}">
	      		<butter:button title="Change Password" onclick="window.location.href='changePassword.htm?id=${currentUser.userId}'"></butter:button>
	      	
	      	</c:if>
	      
	      </butter:buttonGroup>      
	</butter:actionBar>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />