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
//========================= For ADVT Media===================

function advMediaCheck(){
	if(document.frmAdvMediaMaster.mediaName.value==""){
		alert("Enter Media Name.");
		document.frmAdvMediaMaster.mediaName.focus();
		return false;
	}

	if(document.frmAdvMediaMaster.mediaName.value.length>80){
		alert("Entered Media Name Should be Below 80 Characters.");
		document.frmAdvMediaMaster.mediaName.focus();
		return false;
	}

	
	if(document.frmAdvMediaMaster.mediaName.value.indexOf(' ')==0){
		alert("Enter Valid Media Name.");
		document.frmAdvMediaMaster.mediaName.focus();
		return false;
	}

	if(document.frmAdvMediaMaster.mediaName.value.indexOf('  ')!==-1){
		alert("Enter Valid Media Name.");
		document.frmAdvMediaMaster.mediaName.focus();
		return false;
	}

	
	if(document.frmAdvMediaMaster.mediaDescription.value==""){
		alert("Enter Some Comments.");
		document.frmAdvMediaMaster.mediaDescription.focus();
		return false;
	}

	if(document.frmAdvMediaMaster.mediaDescription.value.indexOf(' ')==0){
		alert("Enter Valid Comments.");
		document.frmAdvMediaMaster.mediaDescription.focus();
		return false;
	}

	if(document.frmAdvMediaMaster.mediaDescription.value.indexOf('  ')!==-1){
		alert("Enter Valid Comments.");
		document.frmAdvMediaMaster.mediaDescription.focus();
		return false;
	}
	
	if(document.frmAdvMediaMaster.mediaDescription.value.length >1024){
		alert("Comments are out of range.");
		document.frmAdvMediaMaster.mediaDescription.focus();
		return false;
	}

	return true;
}

