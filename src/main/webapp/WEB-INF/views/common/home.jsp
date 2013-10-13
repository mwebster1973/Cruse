

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
 

<script>

	function startUp(){
		focusForm(document.forms.userForm);	
	}
	
	function changeUser(){
		document.URL= "Welcome.htm?changeUser=" + userChange.value;
	}
	
	function runSavedSearch(searchId)
	{
		document.URL= "runSavedSearch.htm?searchId=" + searchId;
	}
	
	function removeSavedSearch(searchId)
	{
	 	document.URL= "removeSavedSearch.htm?home=true&searchId=" + searchId;
	}
	
</script>
<form:form action="login.htm" commandName="newUser" id="userForm"> 
<butter:pageContainer pageTitle="Welcome ${user.firstName}">
<butter:crumb link="Welcome.htm" returnTitle="home page" root="true" />

<div class="main_body">

<butter:spacer></butter:spacer>


<table>
<tr>
<td style="width:246px">
	<img width="250" style="margin-top:4px;" src="${pageContext.request.contextPath}/images/cruselogo.gif"	alt="Cruse Logo"></img> 
</td>
<td style="width:100%;">
<c:choose>
		<c:when test="${ user ==  null}">
			<div style="width:600px;">  
			<butter:tab title="Login" id="mainDetails" alert="Please login to access this system">
				<div style="width:100%;">		
					<butter:tabBody type="edit">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="line-height:14px;">
										<butter:label mandatory="true" key="user.userid"></butter:label>
										</td>
									<td style="line-height:14px;"
										>
										<butter:text path="userId" alt="UserId|Y" autoSubmit="true"></butter:text>	
									</td>
									<td></td>
								</tr>
								<tr>
									<td style="line-height:14px;">
										<butter:label mandatory="true" key="user.password"></butter:label>
										</td>
									<td style="width:50%; line-height:14px;"> <form:password alt="UserId|Y" path="password" onkeypress="javascript:if(event.keyCode == 13){this.form.submit();}"/>  </td>
										
									<td><butter:button  onclick="javascript:ValidateForm(document.getElementById('userForm'))" title="Login"/></td>
								</tr>
						</table>
						</butter:tabBody>
					</div>
				</butter:tab>

				</div>
			
		</c:when>
		<c:otherwise>
			<butter:tab title="My Saved Searches" id="mainDetails">
		<c:choose>
			<c:when test="${ fn:length(savedSearches)==0}">
				<butter:tabLink type="info" altText="Information"
					message="Throughout the system you will have the opportunity to save your current search. \\n\\n1) Under the search criteria on a results page, click on \\'Add to My Saved Searches\\'\\n2) Enter a unique name for the search and click save. \\n3) A quick-link will then appear under \\'My Saved Searches\\'." />
				<butter:tabBody type="edit">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding-left:3px;vertical-align:top;">You currently
							have no saved searches</td>
						</tr>
					</table>
				</butter:tabBody>
			</c:when>
			<c:otherwise>
				<butter:tabLink type="info" altText="Information"
					message="Your saved searches are listed below. Click on the link to automatically run the search" />

				<butter:tabBody type="edit">
					<div style="width:100%;height:118px;overflow-y:scroll;overflow-x:hidden;margin-bottom:2px;padding-left:0px;">		
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="vertical-align:top;">
							<table width=100% border="0" cellspacing="0" cellpadding="0">

								<c:forEach var="search" items="${savedSearches}">		
									<tr>
										<td class="leftLong" style="line-height:14px;"><a
											href="javascript:runSavedSearch('<c:out value="${search.id}"/>')">
											<c:out value="${search.searchName}"/></a></td>
										<td style="padding-left:30px;line-height:14px;"
											class="rightLong"><a
											href="javascript:removeSavedSearch('<c:out value="${search.id}"/>')">Remove</a></td>
									</tr>
								</c:forEach>

							</table>
							</td>
						</tr>
					</table>
					</div>
				</butter:tabBody>
			</c:otherwise>
		</c:choose>
	</butter:tab> 
		
		</c:otherwise>
	</c:choose>


	<butter:tab title="News and Announcements" id="newsAnnouncements">
		<butter:tabBody type="edit">
				<c:choose>
					<c:when test="${fn:length(savedSearches)==0}">
						<div style="width:100%;height:250px;overflow-y:scroll;overflow-x:hidden;margin-bottom:2px;padding-left:0px;">	
					</c:when>
					<c:otherwise>
						<div style="width:100%;height:194px;overflow-y:scroll;overflow-x:hidden;margin-bottom:2px;padding-left:0px;">				
					</c:otherwise>
				</c:choose>
	
				<c:forEach var="newsItem" items="${news}">
					<div style="width:100%;color:#ff0000;font-weight:bold;">
						<c:out value="${newsItem.headline}" />
						<span style="color:#000000;font-weight:normal;font-style:italic;">
						-  <fmt:formatDate pattern="EEE d MMM yyyy" value="${newsItem.startDate}"  /></span></div>
		
						<div style="width:100%;">
							<butter:formatLargeText name="newsItem" property="summary" /> 
							<c:if test="${newsItem.content!= null}">
								<a href="viewNews.htm?newsId=${newsItem.id}">Read more...</a>
							</c:if>
					</div>
					<butter:spacer></butter:spacer>
				</c:forEach>

		</butter:tabBody> 
	</butter:tab>

</td>
</tr>
</table>


</div>

</butter:pageContainer>
				</form:form>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>




