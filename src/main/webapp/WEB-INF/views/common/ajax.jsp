<script>
 
	var requester = null;

	//
	// Method called when an option is selected.
	//
	function onchangeMenuItem(changedComponent) {

		initialiseRequest();	
		requester.onreadystatechange = ajaxComeBack;
		
		var opts = null;
		
		processOnChange( changedComponent )					
	}
  
	/*
	 Ajax callback method 
	*/
	function ajaxComeBack() {

	  if (requester.readyState == 4) {	
		try {
			if (requester.status == 200)
			{		
				var xmlChoiceList = requester.responseXML.getElementsByTagName("choices")[0];				
				var changedComponent = xmlChoiceList.getAttribute("changedComponent");
				processComeBack(xmlChoiceList, changedComponent);
			} 
			else if (requester.status != 0) {
				alert("There was an error while retrieving the URL: " + requester.statusText);
			}
		}
		catch (error) {
			alert(error.description);
		}
	  } 
	  return true;
	}
	
	
</script>