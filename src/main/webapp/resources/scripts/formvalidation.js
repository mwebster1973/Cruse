// Move focus to the first editable field on a form. This 
// is called from the body load event.
function focusForm(form){
		for (i=0;i<form.elements.length;i++) {
    	 var obj = form.elements[i];
    
	     if (obj.type == 'text' && !obj.readOnly) {		
	     	obj.focus();
	     	return;
	     }
	     if (obj.type == 'textarea') {		
	     	obj.focus();
	     	return;
	     }	  
	      if (obj.type == 'select-one') {		
	     	obj.focus();
	     	return;
	     }	        
		}
	}
	


// Sets all the checkboxes to false and all drop downs 
// to the first element in the list - used by reset button
// on search page to clear selection criteria.
function resetForm(formobj) {

   for (i=0;i<formobj.elements.length;i++) {
     obj = formobj.elements[i]

     if (obj.type == 'checkbox') {		
	   obj.checked=false;
	 }  

     if (obj.type.substr(0,6) == 'select') {
	   obj.options.selectedIndex=0
	 }  

     if (obj.type.substr(0,6) == 'text') {
	   obj.value="";
	 }  
   }
}

function ValidateForm(formobj) { 
	return ValidateForm2(formobj, "true");
}

// Validate form for submission
function ValidateForm2(formobj, submit) {
   for (i=0;i<formobj.elements.length;i++) {
   obj = formobj.elements[i]



   // Identify Validation required - check for disabled
   if (obj.getAttribute("alt")) {	
   disabledlayer = false
   obj1 = obj

   // Find if obj is on a DIV
   while (obj1.tagName != 'BODY') {
   if (obj1.tagName == 'DIV') {
    if (obj1.style.display == 'none') {
     disabledlayer = true
     break;
    }
   }
    obj1 = obj1.parentNode
   }

   if ((obj.getAttribute("alt") != '') && (!disabledlayer) && (!obj.disabled)) {
   valset = obj.getAttribute("alt").split("|")

   // Trim input
   obj.value = TrimSpaces(obj.value)
   obj_value = obj.value
   obj_name = valset[0]

   // valset[1] specifies whether the field is mandatory
   if (valset[1] == 'Y') {
    obj_mandatory = true
   } else {
    obj_mandatory = false
   }
   obj_type = valset[2]
   obj_params = valset.slice(3)
   
   // Mandatory
   if (obj_mandatory) {
      	if ((obj_value == '') && ((obj.type == 'text') || (obj.type == 'textarea')  || (obj.type == 'password') )) return SetFocus('Please enter ' + obj_name, obj)
      	if ((!obj.checked) && (obj.type == 'checkbox')) return SetFocus(obj_name,obj)
	    if ( obj_type && obj_type.toUpperCase() == 'DATE' && obj_value == 'dd/mm/yyyy' ) return SetFocus('Please enter ' + obj_name, obj)
		 // Check selection has been made on selection boxes NB. This assumes that there will be an empty
		 // option at the top of the dropdown list. If it is impossible to de-select, then don't put the alt tag on the field
		 // as the user can't get it wrong if he tries!
	     if (obj.type.substr(0,6) == 'select') {
	       if (obj.options.selectedIndex==0) {
	         return SetFocus('Please select ' + obj_name, obj)
	       }
	     }
   }

   // Validation Type
   if (obj_type) {  
    switch (obj_type.toUpperCase()) {
     // Alphanumeric 0-9 a-z A-Z Space Hyphen / \
    case 'ALPHA':
            var Pat1 = /^[a-zA-Z -]*$/;
            if (!Pat1.test(obj_value)) { return SetFocus('Please enter valid details' ,obj) }
            break;

    case 'ALPHANUMERIC':
            break;

    case 'CODE':
            var Pat1 = /^[-a-zA-Z0-9._-]*$/;
            if (!Pat1.test(obj_value)) { return SetFocus('Please enter valid details',obj) }
            obj.value=obj.value.toUpperCase();
            obj.value = TrimSpaces(obj.value);
            break;


    // Alphanumeric with Extended characters
    case 'ANEXTENDED':
            var Pat1 = /^[-a-zA-Z0-9_ \\\/.,\x27!;@:"ú\$%\^&\*()]*$/;
            if (!Pat1.test(obj_value)) { return SetFocus('Please enter valid details',obj) }
            break;

    // Numeric 0-9 NUMERIC|Min|Max
    case 'NUMERIC':
            var Pat1 = /^[0-9.]*$/;
            if (!Pat1.test(obj_value)) { return SetFocus('Please enter a valid number for ' + obj_name,obj) }
            var numberOfDecimalPoints =  (obj_value.split(".")).length - 1  
            // check there is only one decimal point  
            if( numberOfDecimalPoints > 1 )  { return SetFocus('Only once decimal point should be entered for ' + obj_name,obj) }              
        	 // Perform Range Checking
            if (obj_params != '') {
               obj_value = parseFloat(obj_value)
               
               // Minimum
               if (obj_params[0]) {
             	  if (obj_value < obj_params[0]) { return SetFocus('Please enter a number >= ' + obj_params[0] , obj) }
               }
               // Maximum
               if (obj_params[1]) {
             	  if (obj_value > obj_params[1]) { return SetFocus('Please enter a number <= ' + obj_params[1], obj) }
               }               
            	
            }
            break;
                   

    // Integer 0-9 INTEGER|Min|Max
    case 'INTEGER':
            var Pat1 = /^[0-9]*$/;
            if (!Pat1.test(obj_value)) { return SetFocus('Please enter a valid whole number for ' + obj_name,obj) }
            // Perform Range Checking
            if (obj_params != '') {
               obj_value = parseFloat(obj_value)
               // Minimum
               if (obj_params[0]) {
             	  if (obj_value < obj_params[0]) { return SetFocus('Please enter a whole number >= ' + obj_params[0] , obj) }
               }
               // Maximum
               if (obj_params[1]) {		
                  if (obj_value > obj_params[1]) { return SetFocus('Please enter a whole number <= ' + obj_params[1], obj) }
               }
            }
            break;
	
    // Numeric with two decimal places CURRENCY|Min|Max
   	case 'CURRENCY':
            var Pat1 = /^\d+\.\d\d$/;
            var Pat2 = /^\d+$/;
			var Pat3 = /^\d+\.\d$/;	
            if ((obj_value.length > 0) && ((!Pat1.test(obj_value)&&(!Pat3.test(obj_value))) && !Pat2.test(obj_value))) { return SetFocus('Please enter a valid number for ' + obj_name,obj) }
            // Perform Range Checking

            if (obj_params != '') {
             obj_value = parseFloat(obj_value)
             // Minimum
             if (obj_params[0]) {
                if (obj_value < obj_params[0]) { return SetFocus('Please enter a number >= ' + obj_params[0],obj) }
             }
             // Maximum
             if (obj_params[1]) {
                if (obj_value > obj_params[1]) { return SetFocus('Please enter a number <= ' + obj_params[1], obj) }
             }
            }
            break;
            
	// Numeric with two decimal places DECIMAL|Max decimal places|Min|Max
   	case 'DECIMAL':
            var Pat1 = /^[0-9.]*$/;
            if (!Pat1.test(obj_value)) { return SetFocus('Please enter a valid number for ' + obj_name, obj) }
            
            numberSet = obj_value.split(".")
            var numberOfDecimalPoints =  numberSet.length - 1  
            // check there is only one decimal point  
            if( numberOfDecimalPoints > 1 )  { return SetFocus('Only one decimal point should be entered for ' + obj_name, obj) } 
                                 
        	// Perform Range Checking
            if (obj_params != '') {
               obj_value = parseFloat(obj_value)
               
               // Minimum
               if (obj_params[1]) {
             	  if (obj_value < obj_params[1]) { return SetFocus('Please enter a number >= ' + obj_params[1] , obj) }
               }
               // Maximum
               if (obj_params[2]) {
             	  if (obj_value > obj_params[2]) { return SetFocus('Please enter a number <= ' + obj_params[2], obj) }
               }               
            	
            }
            // check decimal places
            if (obj_params[0]) {            	
            	if( numberSet.length > 1 ){
            		// there is a decimal point
	            	var decimalPlaces = numberSet[1].length
	            	if( decimalPlaces > obj_params[0] ){ return SetFocus('Max decimal places  for ' + obj_name + ' is ' + obj_params[0], obj ) } 
	            }
            } 
            break;            
	
    // Email Address xxx@xxx.xxx
    case 'EMAIL':
          obj_value = obj_value.toLowerCase();
          obj.value = obj_value;
          Pat1 = /^([a-z0-9_\.\-])+\@(([a-z0-9\-])+\.)+([a-z0-9]{2,4})+$/;
          if (!Pat1.test(obj_value)) {
                  return SetFocus( 'Please enter a valid email address \n\ne.g. name@place.com', obj)
          }
          break;

    // UK Postcode XX1 1XX
    case 'POSTCODE':
          obj_value = obj_value.toUpperCase();
          obj.value = obj_value;
               Pat1 = /^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {0,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$/;
               if (!Pat1.test(obj_value)) {
                  return SetFocus('Please enter a valid post code.',obj)
          }
          break;

    // Phone number
    case 'PHONE':
          obj.value = RemoveAllSpaces(obj.value)
          obj_value = obj.value
          Pat1 = /^[0-9 ]*$/;
          if ((!Pat1.test(obj_value)) && (obj_value != '')) {
                  return SetFocus('Please enter valid phone details.', obj)
          }
           break;
            
	case 'MODELYEAR':
		obj.value = RemoveAllSpaces(obj.value)
		if( obj.value.length != 0 ){
			obj_value = obj.value		
			var Pat1 = /^[0-9.]*$/;
	        if (!Pat1.test(obj_value)) { return SetFocus('Please enter a valid number for ' + obj_name,obj) }
	        
	        var numberOfDecimalPoints =  (obj_value.split(".")).length - 1       
	        if( numberOfDecimalPoints > 1 )  { return SetFocus('Please enter a valid number for ' + obj_name,obj) }
	            
			if( (obj_value.length != 4) // e.g. 2010
				&& (obj_value.length != 6) // e.g. 2010.5
				&& (obj_value.length != 7) ){     // e.g. 2010.25   
	            return SetFocus('Please enter a valid model year, e.g. 2010, or 2010.25', obj)
	        }
	        // Minimum
	        if( obj_value < 1950 ) { return SetFocus('Please enter a model year >= ' + 2000 , obj) }
	        // Maximum
	        if( obj_value > 3000 ) { return SetFocus('Please enter a model year <= ' + 3000, obj) }
	    } 
       break;

    // Check to see if two values of 2 fields match
    case 'MATCH':
           obj_1 = getElem(obj_params[0])
           obj_2 = getElem(obj_params[1])
          if (obj_1.value != obj_2.value) return SetFocus(obj_name,obj_1)
            break;

    case 'EITHER_OR':  
           obj_1 = document.getElementById(obj_params[0])     
           obj_2 = document.getElementById(obj_params[1])
           
           if( obj_1.value == '' && obj_2.value == '' ) return SetFocus(obj_name, obj_1)
           break;
           
    case 'ALL_OR_NONE':    
           obj_1 = document.getElementById(obj_params[0])     
           obj_2 = document.getElementById(obj_params[1])
           
           if( (obj_1.value == '' && obj_2.value != '') || ( obj_1.value != '' && obj_2.value == '' ) ) return SetFocus(obj_name, obj_1)
           break;

    case 'NOT_BOTH':    
           obj_1 = document.getElementById(obj_params[0])     
           obj_2 = document.getElementById(obj_params[1])
           
           if( obj_1.value != '' && obj_2.value != '' ) return SetFocus(obj_name, obj_1)
           break;

     case 'AND':
           obj_1 = getElem(obj_params[0])
           obj_2 = getElem(obj_params[1])
           if( obj_1.value == '' || obj_2.value == '' ) return SetFocus(obj_name, obj_1)
           break;

		// used by the table control to ensure atleast one object is selected.
	 case 'CHECKBOX_SELECTED':	 		

	 	   selected = false;

	 	   var elems=document.getElementsByName(obj_params[0]);
		   for (z=0; z< elems.length; z++){
				if (elems[z].checked==true){
					selected = true;
				}
		   }
		   if (!selected){
			   return displayErrorMessage('Please select at least one '+obj_name);
		   }
	       break;
	       
      case 'RADIO_SELECTED':
            num = obj_params.length;
            selected = false;
            for (z=0;z<num;z++)
            {
               if( document.getElementById(obj_params[z]).checked )
               {
                  selected = true;
               }
            }
            if( selected == false ) return SetFocus('Please select ' + obj_name, document.getElementById(obj_params[num-1]))
          break;

   
    
    case 'DATE':
    
      if( obj_mandatory || ( ! obj_mandatory && obj_value != 'dd/mm/yyyy')){
          // day = getElemName(obj_params[0])[0].value
          // month = getElemName(obj_params[1])[0].value
          // year = getElemName(obj_params[2])[0].value
          // datein = day + '/' + month +'/' + year      
         var x = RemoveAllSpaces( obj_value )
         if( x.length > 0 ){   
            datein = obj_value
            var datePat = /^(\d{2})(\/)(\d{2})(\/)(\d{4})$/;                     
            var matchArray = datein.match(datePat);
           if (matchArray == null) {
               return SetFocus('Please enter a date in the format dd/mm/yyyy',obj)
           } else {
             values = obj_value.split("/")
             day = values[0]
             month = values[1]
             year = values[2]
             if ( day > 31 || month > 12 || (month==4 || month==6 || month==9 || month==11) && day==31) {
                  return SetFocus('Please enter a valid date',obj)
             }
             if (month == 2) {
                var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                if (day>29 || (day==29 && !isleap)) {
                       return SetFocus('Please enter a valid date',obj)
                }
             }
           }
           }
       } // end if
       break;
       
    case 'TIME':    
      
      var x = RemoveAllSpaces( obj_value )
      if( x.length > 0 ){   
         timein = x
         var timePat = /^(\d{2})(\:)(\d{2})$/                    
         var matchArray = timein.match(timePat);
         if (matchArray == null) {
            return SetFocus('Please enter a time in the format hh:mm',obj)
         }
       }
       values = obj_value.split(":")
       hours = values[0]
       minutes = values[1]
       if( hours > 24 || minutes > 59 ){
       	   return SetFocus('Please enter a valid time',obj)
       }
       break;
       
	case 'LIMITEDLENGTH':
	
	   var limit = valset[3]
	   if (obj_value.length > valset[3]) { //check if length greater than maxlength
           return SetFocus('You have entered '+obj_value.length+' characters in ' + obj_name + ' and have exceeded the '+ valset[3]+' character limit'  , obj); 
       }
	   break;
	

    // Textarea >> Alphanumeric with Extended characters and Max Character Check
    case 'TEXTAREA':
            //var Pat1 = /^[-a-zA-Z0-9 \\\/.,\x27!@?;|:°ú\$%\^&\*()_£[]=+~#{}:`¬|<>? \t\r\n]*$/;
            //if (!Pat1.test(obj_value)) { return SetFocus('Please enter valid details',obj) }
            
            if(valset[3]) { //check for the maxlength attribute
               if (obj_value.length>valset[3]) { //check if length greater than maxlength
               if(valset[4])
                  { return SetFocus(valset[4],obj);/*Output if the alert message IS included with the maxlength*/}
               else
                  { return SetFocus('You have entered '+obj_value.length+' characters and have exceeded the '+ valset[3]+' character limit' + ' in ' + obj_name , obj); }
               }
            }
            break;
       } // switch
      } // if - mandatory
   } // if - Validation Required
   } // if alt tag exists
  } // for
 
  if (submit=="true"){
	  formobj.submit()
  }
    return true;
}


function ClearField(obj) {
if (obj.value != '') {
   obj.value = '';
   obj.select()
   obj.onfocus = null;
   }
}

   function limitText(limitField, labelField) {
	
   	   valset = limitField.getAttribute("alt").split("|");

	   var limit = valset[3];	   
       var obj_name = valset[0];

   	   var message = "("+limitField.value.length + " characters entered)";

	   var warnLabel = labelField + "WarningLabel";
		if (limitField.value.length < limit -10 ) {		
		    document.getElementById( warnLabel).value = "";		
		} 
		else {		    
		    document.getElementById( warnLabel).value = message;
	    }	
   }
   
function displayErrorMessage(alertmess){

		
   var html = "<div style='float:left;width:42px;'><img style='margin-right:2px;' src='./images/error.jpg'></div><div style='font-weight:bold;color:#000000;float:left;width:auto;font-size:1.2em;padding-top:8px;'><span style='color:#ff0000;'>Error(s):</span>&nbsp;" + alertmess + "!</div>";
   
   try{
	   document.getElementById('errorMessages').innerHTML =  html;
   }
   catch( error ){
       alert( alertmess );
   }
}

// Set Focus to field and show message
function SetFocus(alertmess,obj1) {
   displayErrorMessage(alertmess);
   obj1.focus();
   location.href="#top";
   return;
}

// Remove all spaces in value
function RemoveAllSpaces(objval) {
   RESpace = /\s/ig;
   objval = objval.replace(RESpace, '');
   return objval
}

// Trim spaces from start and end of string
function TrimSpaces(objval) {
   // Remove Space at start
   RESpace = /^\s*/i;
   objval = objval.replace(RESpace, '');
   // Remove Space at end
   RESpace = /\s*$/i;
   objval = objval.replace(RESpace, '');
   return objval;
}
function removeSpaceUpperValue(fieldValue) {
    var trimmedValue = RemoveAllSpaces(fieldValue);
	trimmedValue = trimmedValue.toUpperCase();
	return trimmedValue;
}

// -->
