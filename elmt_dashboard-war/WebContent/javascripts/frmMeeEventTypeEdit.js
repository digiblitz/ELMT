#-------------------------------------------------------------------------------
# * * Copyright: 2019 digiBlitz Foundation
#  * * 
#  * * License: digiBlitz Public License 1.0 (DPL) 
#  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
#  * * 
#  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
#  * * 
#  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
#  * * 
#  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
/*function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`0123456789;:"+"\\"+"\""+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}


function isAlpha(str){
	stringCheck="0123456789";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}*/


function myvalidate(){

//-------------------------------------Event Type name[ Edit]------------------------------------------------------------

if((document.frmMeeEventTypeEdit.eventName.value=="")||(document.frmMeeEventTypeEdit.eventName.value.indexOf(' ')==0))
   {alert(" Event Type name cannot be empty ");
     document.frmMeeEventTypeEdit.eventName.focus();
    return false; }
	
	if((document.frmMeeEventTypeEdit.eventName.value.length >80 ))
 {alert(" Event Type name exceeds the maximum of 80 characters");
  document.frmMeeEventTypeEdit.eventName.focus();
 return false;}
 
 if(document.frmMeeEventTypeEdit.eventName.value.indexOf('  ')!==-1){
		alert("Enter Valid Event Name.");
		document.frmMeeEventTypeEdit.eventName.focus();
		return false;
	}
	
	/*if(isAlpha(document.frmMeeEventTypeEdit.eventName.value)){
		alert("Enter valid Event Type Name.");
		document.frmMeeEventTypeEdit.eventName.focus();
		return false;
	}*/
	
	

    return true;
    }
	
