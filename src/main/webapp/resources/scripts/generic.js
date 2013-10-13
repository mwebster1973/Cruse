function startUp() { 
}

function openFMSWindow( link ){
	fms = window.open( link + '&clearTrail=true', 'fms' , 'width=950,height=650,resizable=1,scrollbars=1,location=1,toolbar=1,menubar=1');
    fms.focus();        	
}

function checkCR(evt) {
var evt = (evt) ? evt : ((event) ? event : null);
var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
if ((evt.keyCode == 13) && (node.type=="text")) {return false;}
}


document.onkeypress = checkCR;
  
function change(that, bgcolor){
that.style.backgroundColor = bgcolor;
}


var ie5=(document.getElementById && document.all);
var ns6=(document.getElementById && !document.all);

nPlus = 1   //the % of fading for each step
speed = 50  //the speed

// You don't have to edit below this line
nOpac = 100
function FadeImg(){
    if(document.getElementById){
        document.getElementById('img1').style.visibility="visible";
        imgs = document.getElementById('img2');
	opacity = nOpac+nPlus;
	nOpac = opacity;
	setTimeout('FadeImg()',speed);
    if(opacity>100 || opacity<0){
        nPlus=-nPlus;
    }
    if(ie5){
        imgs.style.filter="alpha(opacity=0)";
	imgs.filters.alpha.opacity = opacity;
    }
    if(ns6){
        imgs.style.MozOpacity = 0 + '%';
	imgs.style.MozOpacity = opacity + '%';
    }
  }
}



function classChange(element,newclass) {
element.className = newclass;
}

function classChangeName(element,newclass) {
	classChange(document.getElementById(element), newclass);
}


function classSwitch(element1 , element2 ) {

	if(element1.className == "hide")  {
		element1.className = "datafield";
		element2.className = "hide";
	}
	else {
		element2.className = "datafield";
		element1.className = "hide";
	}	
}

function updateValue(id,newValue) {
id.value = newValue;
}

function clearDefault(el) {
  if (el.value=="dd/mm/yyyy"){
  	el.value = ""
  }
}

function printpage() {
window.print();  
}

function getIdForTr(tr){
	var cell = tr.getElementsByTagName("td")[0];
	return cell.innerHTML;
}

function getIdForTd(td){
	return getIdForTr(td.parentNode);
}

function getPagingInfo(jspName){
    var qs = window.location.search.substring(1);
    var qsParm = new Array();
	var parms = qs.split('&');
	var url = "";
	for (var i=0; i<parms.length; i++) {
		var pos = parms[i].indexOf("-p");
		if (pos > 0) 
		{
			var key = parms[i].substring(0,pos);
			url = url + '&pageName=' + key;
			
			var val = parms[i].substring(pos+1);
			if( val != "" ) {  
				url = url + "&" +  val 
			}
		}
		
		var pos1 = parms[i].indexOf("-s");
		if (pos1 > 0) {
			val1 = parms[i].substring(pos1+1);
			if( val1 != "" ){  
				url = url + "&" +  val1 
			}
		}
		
		var pos2 = parms[i].indexOf("-o");
		if (pos2 > 0) 
		{
			val2 = parms[i].substring(pos2+1);
			if( val2 != "" ){  
				url = url + "&" +  val2 
			}
		}		
	}	 
	if( jspName != null && jspName != "" ){  
		url = url + '&jspName=' + jspName;	
	}
	return url;
}

function addMouseoverToTD(td){
	 var cols = td.parentNode.getElementsByTagName("td");
     for (k = 0; k < cols.length; k++) {			   				    	
		if (cols[k].className== "dataTableCell") {
			classChange(cols[k],'dataTableCellOver')
		}
	}
}

function addMouseoutToTD(td){
	 var cols = td.parentNode.getElementsByTagName("td");
     for (k = 0; k < cols.length; k++) {			   				    	
		if (cols[k].className== "dataTableCellOver") {
			classChange(cols[k],'dataTableCell')
		}
	}
}


function trim( obj ) {
    a = obj.replace(/^\s+/, '');
    return a.replace(/\s+$/, '');
}

//
//  Function to create or update a cookie.
//    name      - String object containing the cookie name.
//    value     - String object containing the cookie value.  May contain any valid 
//                string characters.
//    [expires] - Date object containing the expiration data of the cookie.  If
//                omitted or null, expires the cookie at the end of the current session.
//    [path]    - String object indicating the path for which the cookie is valid.
//                If omitted or null, uses the path of the calling document.
//    [domain]  - String object indicating the domain for which the cookie is valid.  
//                If omitted or null, uses the domain of the calling document.
//    [secure]  - Boolean (true/false) value indicating whether cookie transmission
//                requires a secure channel (HTTPS).  
//
//  The first two parameters are required.  The others, if supplied, must
//  be passed in the order listed above.  To omit an unused optional field,
//  use null as a place holder.  For example, to call SetCookie using name,
//  value and path, you would code:
//
//      SetCookie ("myCookieName", "myCookieValue", null, "/");
//
//  Note that trailing omitted parameters do not require a placeholder.
//
//  To set a secure cookie for path "/myPath", that expires after the
//  current session, you might code:
//
//      SetCookie (myCookieVar, cookieValueVar, null, "/myPath", null, true);
//
function SetCookie (name,value,expires,path,domain,secure) {


    // Override optional cookie parameter values and implement later if required.
	expires = new Date ();
	expires.setTime (expires.getTime() + (30 * 24 * 60 * 60 * 1000)); // 30 days from now 
    
    path = null;
    domain = null; 
    secure = false;

    document.cookie = name + "=" + escape (value) + "; " +
                      "expires=" + expires.toGMTString();
                      
    // document.cookie = name + "=" + escape (value) +
    // ((expires) ? "; expires=" + expires.toGMTString() : "") +
    // ((path) ? "; path=" + path : "") +
    // ((domain) ? "; domain=" + domain : "") +
    // ((secure) ? "; secure" : "");

}

//
//  Function to return the value of the cookie specified by "name".
//  name - String object containing the cookie name.
//  returns - String object containing the cookie value, or null if
//     the cookie does not exist.
//


function GetCookie (name) {

	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;

	var i = 0;

	while (i < clen) {

    	var j = i + alen;

	    if (document.cookie.substring(i, j) == arg)
     	  return getCookieVal (j);

	    i = document.cookie.indexOf(" ", i) + 1;

    	if (i == 0) break; 

  	}

	return null;
}

//
// "Internal" function to return the decoded value of a cookie
//
function getCookieVal (offset) {

	var endstr = document.cookie.indexOf (";", offset);

	if (endstr == -1)
    	endstr = document.cookie.length;

	return unescape(document.cookie.substring(offset, endstr));
}

///////////////////////////////////////////////////////////
// Function used to load the collapsible state of the tab//
// This should be called from the onLoad of the page     //
///////////////////////////////////////////////////////////

function loadTabState(nameOfTab){
    id = GetCookie('View'+ nameOfTab);
    var collapsedTab = document.getElementById(nameOfTab + '_collapse');
    var expandedTab = document.getElementById(nameOfTab + '_expand');
    if (id=='true' && expandedTab != null && typeof expandedTab != "undefined") {
	   	classChange(expandedTab,'hide');
	   	classChange(collapsedTab,'show');							
    }
}



function getScrollY() {
 var scrOfY = 0
  if( typeof( window.pageYOffset ) == 'number' ) {
    //Netscape compliant
    scrOfY = window.pageYOffset;
  } else if( document.body && ( document.body.scrollTop ) ) {
    //DOM compliant
    scrOfY = document.body.scrollTop;
  } else if( document.documentElement && ( document.documentElement.scrollTop ) ) {
    //IE6 standards compliant mode
    scrOfY = document.documentElement.scrollTop;
  }
	 
	SetCookie('yScroll', scrOfY);
}

function storeScroll(name){
   var scrOfY = 0
  if( typeof( window.pageYOffset ) == 'number' ) {
    //Netscape compliant
    scrOfY = window.pageYOffset;
  } else if( document.body && ( document.body.scrollTop ) ) {
    //DOM compliant
    scrOfY = document.body.scrollTop;
  } else if( document.documentElement && ( document.documentElement.scrollTop ) ) {
    //IE6 standards compliant mode
    scrOfY = document.documentElement.scrollTop;
  }
  SetCookie(name, scrOfY);
}



//
// Opens new super cds window with the given cdsid
//
function viewCds(cdsid)
{		
	    //if(cdsid.length > 0)
       		window.open ("http://www.sdcds.ford.com/ldap.cgi?ID=node1&user=" +  cdsid);        
       	return;
}

function statusAllChanged(formName,fieldName,value){
	if(!document.forms[formName]){
		return;
		}
	var objCheckBoxes = document.forms[formName].elements[fieldName];
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

function updateCheckboxAll(formName,fieldName){
	if(!document.forms[formName]){
		return;
		}
	var objCheckBoxes = document.forms[formName].elements[fieldName];
	if(!objCheckBoxes){
		return;
		}
	var countCheckBoxes = objCheckBoxes.length;
	var total = 0;
	var max = countCheckBoxes;
	var idxAll = 0;
	for (var idx = 0; idx < max; idx++) {
		if(objCheckBoxes[idx].value!='ALL'){
			if ( objCheckBoxes[idx].checked==true) {
			    total += 1;
   			}
   		}else{
   			idxAll=idx;
   		}
	}
	if(total<(max-1)){
		objCheckBoxes[idxAll].checked=false;
	}else{
		objCheckBoxes[idxAll].checked=true;	
	}
}

	function confirmRequest( url, message ){
	   
       if( confirm( message ) ){
	      document.forms[0].action = url;
	      document.forms[0].submit();
	   }
	}
	
   function limitText(limitField, limitNum) {
		if (limitField.value.length < limitNum - 100) {
		    document.getElementById( 'fieldLength' ).value = limitField.value.length
		    document.getElementById( 'fieldLengthWarning' ).style.display = 'none'
		} 
		else {		    
		    document.getElementById( 'fieldLength' ).value = limitField.value.length
		    document.getElementById( 'fieldLengthWarning' ).style.display = 'block'
	    }		
   }
   		
   function stopRKey(evt) { 
      var evt = (evt) ? evt : ((event) ? event : null); 
      var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
      if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
   } 
   
   
function popUp(URL) {
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=600,height=270,left = 500,top = 450');");
}

	function showSaveSearch(show){
		if (show) {
			document.getElementById('addMySearchLink').style.display = 'none';
			document.getElementById('mySavedSearches').style.display = 'block';
			
		} else {
			document.getElementById('addMySearchLink').style.display = 'block';
			document.getElementById('mySavedSearches').style.display = 'none';
			document.getElementById('searchName').value='';
		}
	}
   
