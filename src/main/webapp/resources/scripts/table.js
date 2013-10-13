	function showCustomDiv(tableName, showing){
	  var comp = document.getElementById(tableName+'.customControls');
	  if (showing){
		  comp.style.display='block';
	  } else {
		  comp.style.display='none';
	  }
	}
	
	function attachParameter(currentLink, parameter){	
		var attachToken = "?";
		if (currentLink.indexOf("?")>-1){
			attachToken = "&";
		}
		return currentLink + attachToken + parameter;
	}
	
	function hideTableColumn(refreshAction, tableName){
		var selectionComp = document.getElementById(tableName+'.columnSelector');
		var value = selectionComp.options[selectionComp.selectedIndex].value;
		document.location.href=attachParameter(refreshAction, tableName+".hide="+value);
	}

	function showTableColumn(refreshAction, tableName){	
		var selectionComp = document.getElementById(tableName+'.columnSelector');
		var value = selectionComp.options[selectionComp.selectedIndex].value;
		document.location.href=attachParameter(refreshAction, tableName+".show="+value);
	}

	function saveMyView(refreshAction, tableName){	

		document.location.href=attachParameter(refreshAction, "TABLE_ACTION_SAVE="+tableName);	
	}

	function restoreMyView(refreshAction, tableName){	
		var link = attachParameter(refreshAction, "TABLE_ACTION_RESTORE="+tableName);		
		document.location.href=link;
	}
	
	// Used in TableTag for select all functionality
	function toggleAllCheckBox(fieldName,value){
		var objCheckBoxes = document.getElementsByName(fieldName);
		if(!objCheckBoxes){
			return;
			}
		var countCheckBoxes = objCheckBoxes.length;
		if(!countCheckBoxes)
			objCheckBoxes.checked = value;
		else
			for(var i = 0; i < countCheckBoxes; i++)
				objCheckBoxes[i].checked = value;
	}
	// used to clear selection
	function syncAllState(name){  
	  var elems=document.getElementsByName(name);
	  check = true;
      for (i=0; i< elems.length; i++){
		if (elems[i].checked==false){
			check=false;
		}
	  }
 	  document.getElementById( "selectAll"+name).checked = check;
  }
	