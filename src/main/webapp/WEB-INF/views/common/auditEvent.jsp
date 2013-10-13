<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>
 
    <butter:tab title="Audit history" collapsible="true">		
    	<c:choose>
			<c:when test="${not empty auditHistory}">
			  <butter:tabBody type="results">		    
						<butter:table id="auditHistory" 
						   name="auditHistory"							   
						   sortedProperty="changeDate"
						   sortedDirection="D" >
					<butter:column property="changeDate" titleKey="dateChanged" width="15%" formatKey="date.display.longTimeFormat"/>
					<butter:column property="changerCDSID" titleKey="changedBy" type="cdsid" width="10%" />
					<butter:column property="eventType" titleKey="eventType" />
					<butter:column property="changeDescription" titleKey="description"/>	
				</butter:table>			
				</butter:tabBody>
			</c:when>
			<c:otherwise>				
		  	   <butter:tabBody type="text">
		  	 		No changes made
	  		   </butter:tabBody>
	  		 </c:otherwise>
  		</c:choose>
    </butter:tab>

 