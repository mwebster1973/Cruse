/*
 Populate the menu item with the content of the list.
*/
function processOptionList(selectChoiceList, menuItem, defaultOption) {

    clearList(menuItem);
		
	if (selectChoiceList == null) {
		return;
	}
	
	if (defaultOption != null){		
		addOption(menuItem, "", defaultOption);
	}

	if( selectChoiceList.childNodes != null && selectChoiceList.childNodes.length > 0) {		
		for (loop = 0; loop < selectChoiceList.childNodes.length; loop++) {

			var choice = selectChoiceList.childNodes[loop];
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
			var selected = false;
			
			addOption(menuItem, listValue, listText, selected);
		}	
	}
	
	
}



// In order to return a combined desc rather than just the value
function processOptionListCombinedDesc(selectChoiceList, menuItem, defaultOption) {

	clearList(menuItem);
	
	if (selectChoiceList== null) {
		return;
	}
	
	
	if (defaultOption != null)
		{
		addOption(menuItem, "", defaultOption);
		}

	if( selectChoiceList.childNodes != null && selectChoiceList.childNodes.length > 0) {		
		for (loop = 0; loop < selectChoiceList.childNodes.length; loop++) {

			var choice = selectChoiceList.childNodes[loop];
			var id = choice.getElementsByTagName("id")[0];
			var value = choice.getElementsByTagName("combinedKey")[0];
			
			var listValue = "";
			if (id.childNodes[0] != null) {
				listValue = id.childNodes[0].nodeValue;
			}
			var listText = "";
			if (value.childNodes[0] != null) {
				listText = value.childNodes[0].nodeValue;
			}
			addOption(menuItem, listValue, listText);
		}	
	}
	
	
}



/*
Add an option to a list.
*/
function addOption(elList, listValue, listText, selected) {

	var elOptNew = document.createElement('option' );
	if( selected ){
		elOptNew.selected = 'selected';
	}
	elOptNew.text = listText;
	elOptNew.value = listValue;
  
	try {
		elList.add(elOptNew, null); // standards compliant; doesn't work in IE
	}
	catch(ex) {
    	elList.add(elOptNew); // IE only
	}  
}

/*
Clear the contents of a list
*/
function clearList(list) {

	var i;
	for (i = list.length - 1; i>=0; i--) {
      list.remove(i);
	}
}

/*
Determine whether a select box has selected values
*/
function hasSelectedValues(select) {

	var selections  = false;
	for (var i = 0; i < select.options.length; i++) {
    	if (select.options[i].selected) {     
      		selections = true;
     	}      
	}

	return selections;
}

/*
Retrieve a string of selected values with a : seperating each item
*/
function getSelectedValues (select) {
	var s="";
	
	for (var i = 0; i < select.options.length; i++) {
		if (select.options[i].selected) {     
			if (s.length>0){
				s = s + ":";
			}
			s = s + select.options[i].value;      
		}      
	}
	return s;
}


/*
Return a Single value
*/
function getSelectedValue (select) {
	return getSelectedValues(select);
}



function updatePageElement(value, pageElement) {
	if (value != null && value.childNodes[0]!= null) {
		document.getElementById(pageElement).firstChild.nodeValue = value.childNodes[0].nodeValue
	} else {
		document.getElementById(pageElement).firstChild.nodeValue = ' ';
	}
	
}
//the following 2 function helps to avoid session timeouts.

// this function is called at the jsp
function keepSessionActive(){
	var sessionActivator = window.setInterval('keepSessionActiveHelper()', 600000);
}

// helper function
function keepSessionActiveHelper(){
	initialiseRequest();
	requester.open("GET", "keepSessionActive.jsp");	
	requester.send(null);
	return true;
}
/*
 Initialise the requester. 
*/
function initialiseRequest() {
	if (requester != null && requester.readyState != 0 && requester.readyState != 4) {
		requester.abort();
		
	}
	try	{
		requester = new XMLHttpRequest();
	}
	catch (error) {
		try {
			requester = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (error) {
			requester = null;
			
			return false;

		}
	}
}




/*
Retrive a string containing the values of all the components that contain the same name
*/
function getSelections(name){

	var s="";
	var elems = document.getElementsByName(name);
	
	for (var i=0; i < elems.length; i++){
		if (elems[i].value!=''){
			s = s + elems[i].value +  ":";      
		}
	}
	return s;
}

/*
Function called from an ajax enabled page. 
*/
function processSelectList(collection, selectComponentName){

	var component = document.getElementById(selectComponentName+"table");

	var outerComponent = document.getElementById(selectComponentName+".component");


	var listAsString="";
	
	if (collection!= null){
		outerComponent.alt = collection.childNodes.length;
		
		for (loop = 0; loop < collection.childNodes.length; loop++) {
			
			var choice = collection.childNodes[loop];
			var id = choice.getElementsByTagName("id")[0];
			var value = choice.getElementsByTagName("value")[0];
			
			var listValue = "";
			if (id.childNodes[0] != null) {
				listValue = id.childNodes[0].nodeValue + "£$" + selectComponentName;
			}
			var listText = "";
			if (value.childNodes[0] != null) {
				listText = value.childNodes[0].nodeValue;
			}
			
			var clickEvent = "toggleSelection(this);";
			if (component.alt!= null){
				clickEvent = clickEvent + component.alt;
			}
			
			var selection =  (document.getElementById(listValue)!= null);
			var imageStyle = 'none';
			var backgroundCol = '';
			if (selection){
				imageStyle = 'inline';
				backgroundCol = '#ffffcc;';
			}
			
			var row = "<div onmouseover=\"javascript:this.style.cursor='pointer';\" id=\""+listValue+"td\" style=\"padding-left:2px;width:100%;border-bottom:1px solid #ccccff;line-height:15px;float:left;clear:both;background:"+backgroundCol+"'\" onClick=\""+clickEvent+"\" >";

			row = row + " <img align=\left\" id=\""+listValue+"img\" src=\"/DART_WEB/images/tick.gif\" ";
			row = row + " style=\"display:"+imageStyle+";margin-top:4px;margin-right:3px;margin-bottom:-1px;\"/>";
			row = row + listText;
			
			row = row + " <div style=\"display:none\" id=\""+listValue+"holder\" alt=\"" + selectComponentName + "\">";
			
			if (selection){
				row = row + 
 					"<input id=\""+listValue+"\" type=\"hidden\" name=\"" + selectComponentName+ "\" value=\""+id.childNodes[0].nodeValue+"\" />";
			} else{
				row = row + "&nbsp;";
			}			
			row = row + "</div></div>";			
			
			listAsString = listAsString + row;
		}
	}
	component.innerHTML = listAsString;
	displaySelectionCount(selectComponentName);
}

function displaySelectionCount(componentName){
	var selectionSize = document.getElementsByName(componentName).length;	
    var inputComponent = document.getElementById(componentName + '.inputComponent');
    if (inputComponent!= null && typeof inputComponent != "undefined") {
       selectionSize = selectionSize-1;
   	}
	
	var collectionSize = document.getElementById(componentName+".component").alt;
	
	var countString = '('+selectionSize+'/'+collectionSize+')';	
	document.getElementById(componentName+".count").innerHTML = countString;
	
}

/*
Function called by the jlr select component to set a row to select, or unselected
*/
function toggleSelection(tdComp){

	var name = tdComp.id;
	var objId = name.substring(0,name.length-2);
	var inputComp = document.getElementById(objId);
	var imgComp = document.getElementById(objId+'img');
	var inputHolder = document.getElementById(objId+'holder');

	if (inputComp == null){
		imgComp.style.display='inline';
		tdComp.style.background='#ffffcc';
		
		var objVal = objId.substring(0,objId.indexOf("£$",0));

		inputHolder.innerHTML = 
			"<input id=\""+objId+"\" type=\"hidden\" name=\"" + inputHolder.alt+ "\" value=\""+objVal+"\" />";
	} else {
		imgComp.style.display='none';	
		tdComp.style.background='';
		inputHolder.innerHTML = "&nbsp;";
	}
	displaySelectionCount(inputHolder.alt);
}
