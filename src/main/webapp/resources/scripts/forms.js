// forms.js

function classChange(element,newclass) {
  element.className = newclass;
}

function updateValue(id,newValue) {
  document.getElementById(id).value = newValue;
}

function clearDefault(el) {
  if (el.value=="dd/mm/yyyy"){
  	el.value = ""
  }
}


function removeChildDiv( parent, child )
{
	var d = document.getElementById( parent );
	var olddiv = document.getElementById(child);
	d.removeChild(olddiv);
}

function show_hide( show, divName )
{
   if( show == true ) {  
       document.getElementById( divName ).style.display = 'inline'
   }
   else{
	   document.getElementById( divName ).style.display = 'none'
   }       
}	


function printpage() {
	window.print();  
}
// ************