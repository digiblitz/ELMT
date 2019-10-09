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
// JavaScript Document
//========================= Event Level===================

function meeEveLevelCheck(){
	if(document.frmMeeEventLevelEdit.eventlevel.value==""){
		alert("Enter Event Name.");
		document.frmMeeEventLevelEdit.eventlevel.focus();
		return false;
	}

	if(document.frmMeeEventLevelEdit.eventlevel.value.length>80){
		alert(" Event Name Should not exceed 80 Characters.");
		document.frmMeeEventLevelEdit.eventlevel.focus();
		return false;
	}

	
	if(document.frmMeeEventLevelEdit.eventlevel.value.indexOf(' ')==0){
		alert("Enter Valid Event Name.");
		document.frmMeeEventLevelEdit.eventlevel.focus();
		return false;
	}

	if(document.frmMeeEventLevelEdit.eventlevel.value.indexOf('  ')!==-1){
		alert("Enter Valid Event Name.");
		document.frmMeeEventLevelEdit.eventlevel.focus();
		return false;
	}
//=======================eventcode==============================
	
	if(document.frmMeeEventLevelEdit.eventcode.value==""){
		alert("Enter Event Code.");
		document.frmMeeEventLevelEdit.eventcode.focus();
		return false;
	}

	if(document.frmMeeEventLevelEdit.eventcode.value.indexOf(' ')==0){
		alert("Enter Valid Event Code.");
		document.frmMeeEventLevelEdit.eventcode.focus();
		return false;
	}

	if(document.frmMeeEventLevelEdit.eventcode.value.indexOf('  ')!==-1){
		alert("Enter Valid Event Code.");
		document.frmMeeEventLevelEdit.eventcode.focus();
		return false;
	}
	
	//alert("document.frmMeeEventLevelEdit.eventcode.value.length() :"+document.frmMeeEventLevelEdit.eventcode.value.length);
	
	if(document.frmMeeEventLevelEdit.eventcode.value.length >20){
		alert("Event Code should not exceed 20 characters.");
		document.frmMeeEventLevelEdit.eventcode.focus();
		return false;
	}

/*=======================jumping==============================
	
	if(document.frmMeeEventLevelEdit.jumping.value==""){
		alert("Enter Some Comments.");
		document.frmMeeEventLevelEdit.jumping.focus();
		return false;
	}

	if(document.frmMeeEventLevelEdit.jumping.value.indexOf(' ')==0){
		alert("Enter Valid Comments.");
		document.frmMeeEventLevelEdit.jumping.focus();
		return false;
	}

	if(document.frmMeeEventLevelEdit.jumping.value.indexOf('  ')!==-1){
		alert("Enter Valid Comments.");
		document.frmMeeEventLevelEdit.jumping.focus();
		return false;
	}
	
	if(document.frmMeeEventLevelEdit.jumping.value.length >80){
		alert("Comments are out of range.");
		document.frmMeeEventLevelEdit.jumping.focus();
		return false;
	}*/
	return true;
}

