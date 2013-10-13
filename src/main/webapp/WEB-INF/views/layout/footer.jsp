<%@page import="java.util.Calendar"%>
 
<div class="footer"> 
<% String year = Integer.toString(Calendar.getInstance().get( Calendar.YEAR )); %>
Copyright © <%= year %> Websterver Design Ltd. All rights reserved. Version: ${Config.buildVersion}
</div>	
 