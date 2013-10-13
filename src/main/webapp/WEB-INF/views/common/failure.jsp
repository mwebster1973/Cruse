<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>


<div class="page_title" style="margin-left:59px;">The system has encountered an error!</div>

<br/><br/>

<% 	Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");  %>
Exception Info:  <%= exception.getMessage() %><br />
<pre style="font-size:12pt;">
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</pre>