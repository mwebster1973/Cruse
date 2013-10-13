<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>

<butter:backLink finalLocation="true" />
<p/>
<div class="page_title" style="margin-left:59px;">Sorry. You do not have the authority to perform the requested action!</div>
<br/><br/>

<logic:messagesPresent>
	<html:messages id="error">		
	</html:messages>
</logic:messagesPresent>

<div style="position:absolute;top:94px;left:20px;"><img src="<%=request.getContextPath()%>/images/exclamation.jpg" width="80" height="80" /></div>

