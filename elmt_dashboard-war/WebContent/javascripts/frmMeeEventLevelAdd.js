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
	if(document.frmMeeEventLevelCreate.eventlevel.value==""){
		alert("Enter Event Name.");
		document.frmMeeEventLevelCreate.eventlevel.focus();
		return false;
	}

	if(document.frmMeeEventLevelCreate.eventlevel.value.length>80){
		alert("Event Name Should not exceed 80 Characters.");
		document.frmMeeEventLevelCreate.eventlevel.focus();
		return false;
	}

	
	if(document.frmMeeEventLevelCreate.eventlevel.value.indexOf(' ')==0){
		alert("Enter Valid Event Name.");
		document.frmMeeEventLevelCreate.eventlevel.focus();
		return false;
	}

	if(document.frmMeeEventLevelCreate.eventlevel.value.indexOf('  ')!==-1){
		alert("Enter Valid Event Name.");
		document.frmMeeEventLevelCreate.eventlevel.focus();
		return false;
	}
//=======================eventcode==============================
	
	if(document.frmMeeEventLevelCreate.eventcode.value==""){
		alert("Enter Event Code.");
		document.frmMeeEventLevelCreate.eventcode.focus();
		return false;
	}

	if(document.frmMeeEventLevelCreate.eventcode.value.indexOf(' ')==0){
		alert("Enter Valid event Code.");
		document.frmMeeEventLevelCreate.eventcode.focus();
		return false;
	}

	if(document.frmMeeEventLevelCreate.eventcode.value.indexOf('  ')!==-1){
		alert("Enter Valid Event Code.");
		document.frmMeeEventLevelCreate.eventcode.focus();
		return false;
	}
	
	if(document.frmMeeEventLevelCreate.eventcode.value.length >20){
		alert("Event Code Should not exceed 20 Characters.");
		document.frmMeeEventLevelCreate.eventcode.focus();
		return false;
	}

//=======================jumping==============================
	/*
	if(document.frmMeeEventLevelCreate.jumping.value==""){
		alert("Enter Some Comments.");
		document.frmMeeEventLevelCreate.jumping.focus();
		return false;
	}

	if(document.frmMeeEventLevelCreate.jumping.value.indexOf(' ')==0){
		alert("Enter Valid Comments.");
		document.frmMeeEventLevelCreate.jumping.focus();
		return false;
	}

	if(document.frmMeeEventLevelCreate.jumping.value.indexOf('  ')!==-1){
		alert("Enter Valid Comments.");
		document.frmMeeEventLevelCreate.jumping.focus();
		return false;
	}
	
	if(document.frmMeeEventLevelCreate.jumping.value.length >80){
		alert("Comments are out of range.");
		document.frmMeeEventLevelCreate.jumping.focus();
		return false;
	}*/
	return true;
}

