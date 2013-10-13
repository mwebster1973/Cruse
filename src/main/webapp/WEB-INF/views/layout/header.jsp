<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<title>Birmingham Cruse Statistics System</title>
	  
	<link id="cssFile" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/mainstyle.css" />
	<link href="${pageContext.request.contextPath}/resources/styles/mainprint.css" rel="stylesheet" type="text/css" media="print" />
	
	<link type="text/css" href="${pageContext.request.contextPath}/resources/styles/jquery-ui-1.8.5.custom.css" rel="stylesheet" />	
	
	   
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/yahoo-dom-event.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/animation-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/dragdrop-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/mootools.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/generic.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/c_config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/c_smartmenus.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/formvalidation.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/clock.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/table.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/addToList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/ajaxHelper.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/datetimepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/filterBuilder.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-ui-1.8.5.custom.min.js"></script>
</head>

<body onload="getCookieData(); startUp();" onkeypress="checkCR;">

<script type = "text/javascript">
    // Define the entry point
    $(document).ready(function()
    {
    	$( ".dateField" ).datepicker({
			showOn: "button",
			constrainInput: false,
			dateFormat: "dd/mm/yy",
			buttonImage: "resources/images/date_picker.gif",
			changeMonth: true,
			changeYear: true,
			buttonImageOnly: true
		});
    })
</script>

<script>
	function getCookieData(){
	}
</script>

<div class="header_container">

  <table cellspacing="0" cellpadding="0" border="0" width="100%;">
	<tr>
		<td class="app_title">Birmingham Cruse Statistics System</td>
		
		<c:if test="${ user !=  null}">
			<td align="center" class="dontprintme">
				<table cellspacing="0" cellpadding="2">
				<tr>
				<td colspan="2" style="font-weight:bold;color:#ffffff;white-space:nowrap;text-align:left;">Quick Search - Enter Referral No.</td>
				</tr>
				<tr>
				<td>
					<form action="quickSearch.htm?reset=true&clearTrail=true" id="quickSearchForm">
				<input type="text" id="quickSearchValue" name="quickSearchText"  onclick="if( this.value == '' ){this.value = ''};" 
					onkeypress="javascript:if(event.keyCode == 13){this.value=removeSpaceUpperValue(this.value); this.form.submit();}"
						  style="border:1px solid #000000;background:#ffffff;font-size:12px;width:170px;height:16px;"
						  title="Quick search - Enter Referral No(num/year)."/>
					</form>
				</td>
				<td>
				<input class="button" style="margin-left:-20px;" type="button" title="Click here to search for referrals" value="Search&nbsp;" onclick="javascript:if(document.getElementById('quickSearchValue').value=='Enter Test Request No.'){alert('Please enter a value to search on')} else {document.getElementById('quickSearchValue').value=removeSpaceUpperValue(document.getElementById('quickSearchValue').value); document.getElementById( 'quickSearchForm' ).submit() };"/></td>
				</tr>
				</table>
			</td>
			<td style="font-weight:bold;" valign="top" align="center" class="dontprintme" style="padding-top: 5px;">
				<a href="logout.htm">Logout</a>
			</td>
		</c:if>

	</tr>
  </table> 
</div>

	<jsp:include page="/WEB-INF/views/layout/nav.jsp"></jsp:include>

	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/wz_tooltip.js"></script>
