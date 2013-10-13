function populateFilterBuilder(collection, selectComponentName) {

	var component = document.getElementById(selectComponentName+".workingArea");

	document.getElementById(selectComponentName+".table").style.display = 'block';
	var listAsString="";
	
	if (collection!= null && collection.childNodes.length>0){
		for (loop = 0; loop < collection.childNodes.length; loop++) {
			
			var choice = collection.childNodes[loop];
			var id = choice.getElementsByTagName("id")[0];
			var value = choice.getElementsByTagName("value")[0];
			
			var listValue = "";
			if (id.childNodes[0] != null) {
				listValue = id.childNodes[0].nodeValue;
			}
			var listText = "";
			if (value.childNodes[0] != null) {
				listText = value.childNodes[0].nodeValue;
			}
			
			var clickEvent = "selectWorkingItem(this,'"+selectComponentName+"');";
			if (component.alt!= null){
				clickEvent = clickEvent + component.alt;
			}

			
			var row = "<div onmouseover=\"javascript:this.style.cursor='pointer';\" id=\""+listValue+"\" style=\"padding-left:3px;width: 100%;border-bottom:1px solid #ccccff; background-color: #F6F9FF;line-height:16px;\" onClick=\""+clickEvent+"\" >";
			row = row + listText;
			row = row + "</div>";			
			
			listAsString = listAsString + row;
			
		}
	} else{		
		listAsString = "<div style=\"width:100%;font-weight:bold;color:#ff0000;\">&nbsp;&nbsp;No features found. Please refine your search criteria and try again.</div>";
	}
	var divSpacer = "<div style=\"width:100%;padding-left:3px;\">&nbsp;</div>";
	component.innerHTML = listAsString + divSpacer;




}

function removeBuildItem(comp, componentName){
	var name = comp.id;
	var objId = name.substring(0,name.length-10);
	
	//remove the item from the build list
	document.getElementById(objId+".buildItem").style.display = 'none';
	document.getElementById(objId+".buildItem").innerHTML = '';
	document.getElementById(objId+".buildItem").id = 'DEAD';
	
	//add ths item to the working area

	if (document.getElementById(objId)!= null){
		document.getElementById(objId).style.display="block";
	}
	displayNoRowsMessage(componentName);
	
}

function displayNoRowsMessage(componentName){
	if (document.getElementsByName(componentName).length>1){
		document.getElementById(componentName+".noRowsMessage").style.display="none";
	} else{
		document.getElementById(componentName+".noRowsMessage").style.display="block";
	}
}


function selectWorkingItem(component, componentName) {

	//remove the item from the working area
	var id = component.id;
	document.getElementById(id).style.display = 'none';	
	
	var description = component.innerHTML;
	
	
	var builderArea = document.getElementById(componentName+".builderArea");

			var row = "<a id=\""+id+".buildLink\" onclick='removeBuildItem(this, \""+componentName+"\");' >";
			row = row +"<div style=\"background:#ffffcc;border-bottom:1px solid #ccccff;\" onmouseover=\"javascript:this.style.cursor='pointer';\" id=\""+id+".buildItem\">";
			row = row + " <img  src=\"/Cruse/images/tick.gif\" style=\"margin:0px 0px 0px 5px;\"/> ";
			row = row + " <input type=\"hidden\" name=\""+componentName+"\" value=\""+id+"\"/> ";
			row = row + description;
			row = row + "</div>";
			row = row + "</a>";
	builderArea.innerHTML = builderArea.innerHTML+row;
	displayNoRowsMessage(componentName);

}

