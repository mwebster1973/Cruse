<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>

<div style="float: left; font-family:arial;font-size:18px;font-weight:bold;color:#ff0000;margin: 12px 0px 12px 5px;">
Birmingham Cruse Statistics System Export						
</div>
<br/>
<div style="clear:both; float: left; font-family:arial;font-size:14px;font-weight:bold;color:#ff0000;margin: 12px 0px 12px 5px;">
${pageTitle}					
</div>

<div style="clear:both; float: left; font-family:arial;font-size:12px;font-weight:bold;color:#000000;margin: 12px 0px 12px 5px;">
Criteria : ${criteriaDescription}
</div>	

   	
   	
<c:forEach items="${summary}" var="breakdown">
 <br/>
 <div style="clear:both; float: left; font-family:arial;font-size:12px;font-weight:bold;color:#000000;margin: 12px 0px 12px 5px;">
	${breakdown.description }
 </div>   	
	<table>
	<tr>
		<td style="float: left; font-family:arial;font-size:12px;background-color:#cccccc; border: 1px solid #000000;">Attribute</td>
		<td style="float: left; font-family:arial;font-size:12px;background-color:#cccccc; border: 1px solid #000000;">Breakdown</td>
	</tr>
	<c:forEach items="${breakdown.countList}" var="count">
		<tr>
			<td style="float: left; font-family:arial;font-size:12px;background-color:grey;border: 1px solid #cccccc;">${count.description}</td>
			<td style="float: left; font-family:arial;font-size:12px;background-color:grey;border: 1px solid #cccccc;">${count.count}</td>
		</tr>
	</c:forEach>
	</table>
</c:forEach>
