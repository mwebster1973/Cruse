<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>


<%@page import="com.cruse.domain.admin.Admin"%><div class="navbar_container">
  <ul id="Menu1" class="MM" style="background:#C18EDB;display:inline;">
	<li style="visibility:hidden;"><a></a></li>
	<li><a href="<%=request.getContextPath()%>/Welcome.htm" accesskey="h"><span style="text-decoration:underline;">H</span>ome</a>
	</li>	
	
	<li><a href="#" accesskey="c"><span style="text-decoration:underline;">C</span>ultural Background</a>
	  <ul>
	  	<li><a href='<%=request.getContextPath()%>/ethnicSearchRequest.htm?reset=true&clearTrail=true'>Search</a></li>
	  	<c:if test="${ user.admin || user.counsellor || user.dataEntry }">
	  		<li><a href='<%=request.getContextPath()%>/addEthnicEntry.htm?reset=true&clearTrail=true'>Add new record</a></li>
	  	</c:if>
	  	<li><a href='<%=request.getContextPath()%>/viewTodaysEthnicEntries.htm?reset=true&clearTrail=true'>Records entered today</a></li> 	 
	  </ul>
	 </li>

	<li><a href="#" accesskey="r"><span style="text-decoration:underline;">R</span>eferral</a>
	  <ul>
	  	<li><a href='<%=request.getContextPath()%>/referralSearchRequest.htm?reset=true&clearTrail=true'>Search</a></li>
	  	<c:if test="${ user.admin || user.counsellor|| user.dataEntry  }">
	  		<li><a href='<%=request.getContextPath()%>/addReferral.htm?reset=true&clearTrail=true'>Add new record</a></li>
	  	</c:if>
	  	<li><a href='<%=request.getContextPath()%>/viewTodaysReferrals.htm?reset=true&clearTrail=true'>Records entered today</a></li> 	 
	  </ul>
	 </li>	
	 <li><a href="#" accesskey="g"><span style="text-decoration:underline;">G</span>roups</a>
	  <ul>
	  	<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_GROUP%>'>Groups</a></li> 
	  	<li><a href='<%=request.getContextPath()%>/sessionSearch.htm?reset=true&clearTrail=true'>Group Sessions</a></li> 
	  					 
	  </ul>
	 </li>
	 
	<c:if test="${ user.admin || user.counsellor|| user.itAdmin }">
	<li><a href="#" accesskey="a"><span style="text-decoration:underline;">A</span>dmin</a>
	  <ul>
		 <li><a accesskey="r" href="#">Referral Form</a>
					<ul>
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_AGE%>'>Ages of client</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_AREA%>'>Area codes</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_BEREAVED_OF%>'>Bereaved of</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_CAUSE_OF_DEATH%>'>Causes of death</a></li>		
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_CLIENT_RESIDENCE%>'>Client resides in</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_CONTACT_BY%>'>Contact by</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_ENDING%>'>Endings</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_GENDER%>'>Genders</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_HEAR_OF_CRUSE%>'>Hear of Cruse</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_LENGTH%>'>Lengths of bereavement</a></li>
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_OUTPOST%>'>Outposts</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_PLACE_OF_DEATH%>'>Place of death</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_PCT%>'>PCTs</a></li>  
						<li><a href='<%=request.getContextPath()%>/maintainGp.htm?reset=true&clearTrail=true'>GPs</a></li>

					</ul>	  
				</li>
				
				<li><a accesskey="e" href="#">Ethnic Background Form</a>
					<ul>
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_ETHNIC_BACK%>'>Ethnic Backgrounds</a></li> 
						<li><a href='<%=request.getContextPath()%>/maintainAdmin.htm?reset=true&clearTrail=true&domain=<%=Admin.ENTITY_NAME_RELIGEON%>'>Religions</a></li>  
					</ul>	 
				</li>
			
									
				<c:if test="${user.admin || user.itAdmin}">
					<li><a href="<%=request.getContextPath()%>/newsSearch.htm?reset=true&clearTrail=true">Homepage News</a></li>				
				</c:if>
				<c:if test="${user.itAdmin}">
					<li><a href="<%=request.getContextPath()%>/refreshConfig.htm">Refresh Config</a></li>
					<li><a href="<%=request.getContextPath()%>/configSearch.htm?reset=true&clearTrail=true">System Configuration</a></li> 	
				</c:if>
				<li><a href="<%=request.getContextPath()%>/userSearch.htm?reset=true&clearTrail=true">Users/Roles</a></li> 
			</ul>
	</li>	
	</c:if>
			
  </ul>
  <div class="date_time">&nbsp;
  <script LANGUAGE="Javascript">

	// Array of day names
	var dayNames = new Array("Sunday","Monday","Tuesday","Wednesday",
					"Thursday","Friday","Saturday");
	
	// Array of month Names
	var monthNames = new Array(
	"January","February","March","April","May","June","July",
	"August","September","October","November","December");
	
	var now = new Date();
	document.write(dayNames[now.getDay()] + ", " + 
	monthNames[now.getMonth()] + " " + 
	now.getDate() + ", " + now.getFullYear());

  </script>
  <span id="clock" style="position:relative;"></span>
  </div>

</div>










