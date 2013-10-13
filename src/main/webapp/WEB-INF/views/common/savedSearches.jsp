<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="butter" uri="/WEB-INF/butter.tld" %>
 
<script type="text/javascript"  >
  function runSavedSearch(searchId)
  {
  	document.URL= "runSavedSearch.htm?searchId=" + searchId;
  }
  
  function removeSavedSearch(searchId)
  {
   	document.URL= "removeSavedSearch.htm?searchId=" + searchId;
  }
</script>
	<butter:tab title="My saved searches" id="savedSearches">
		<c:choose>
			<c:when test="${empty savedSearches}">
				<butter:tabLink type="info" altText="Information" message="When you have defined and run a search for the first time, you will have the option to \\'Save My Search\\'.\\n\\n1) Under the search criteria on the results page, click on \\'Add to My Saved Searches\\'\\n2) Enter a unique name for the search and click save. \\n3) A quick-link will then appear under \\'My Saved Searches\\' for you to use on future visits to this page." />
				<butter:tabBody type="criteria">
					You currently have no saved searches
			   </butter:tabBody>
			</c:when>
			<c:otherwise>
				<butter:tabLink type="info" altText="Information" message="Your saved searches are listed below. Click on the link to automatically run the search" />
				<butter:tabBody type="criteria">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<c:forEach var="mySearches" items="${savedSearches}">	
							<tr>
								<td nowrap class="leftLong" style="line-height:14px;">
								
									<a href="javascript:runSavedSearch('<c:out value="${mySearches.id}"/>')">
									<c:out value="${mySearches.searchName}"/></a>
								</td>
								<td style="padding-left:30px;line-height:14px;" class="rightLong"><a href="javascript:removeSavedSearch('
									<c:out value="${mySearches.id}"/>')">Remove</a></td>
							</tr>
						</c:forEach>

					</table>
				</butter:tabBody>
			</c:otherwise>
		</c:choose>
	</butter:tab>