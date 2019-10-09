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

//========================= For ADVT Dimension Add ===================

function advDimCheck(){
	if(document.frmAdvDimensionAdd.txtDimensionName.value==""){
		alert(" Dimension Name cannot be empty.");
		document.frmAdvDimensionAdd.txtDimensionName.focus();
		return false;
	}

	if(document.frmAdvDimensionAdd.txtDimensionName.value.length>80){
		alert("Dimension Name is out of range.");
		document.frmAdvDimensionAdd.txtDimensionName.focus();
		return false;
	}

	if(document.frmAdvDimensionAdd.txtDimensionName.value.indexOf(' ')==0){
		alert("Enter Valid Dimension Name.");
		document.frmAdvDimensionAdd.txtDimensionName.focus();
		return false;
	}

	if(document.frmAdvDimensionAdd.txtDimensionName.value.indexOf('  ')!==-1){
		alert("Enter Valid Dimension Name.");
		document.frmAdvDimensionAdd.txtDimensionName.focus();
		return false;
	}

	return true;
}

//============================ For ADVT Dimension Edit ==============================

function advDimEditCheck(){
		if(document.frmAdvDimensionEdit.txtDimensionEditName.value==""){
		alert("Dimension Name cannot be empty.");
		document.frmAdvDimensionEdit.txtDimensionEditName.focus();
		return false;
	}

	if(document.frmAdvDimensionEdit.txtDimensionEditName.value.length>80){
		alert("Dimension Name is out of range.");
		document.frmAdvDimensionEdit.txtDimensionEditName.focus();
		return false;
	}

	if(document.frmAdvDimensionEdit.txtDimensionEditName.value.indexOf(' ')==0){
		alert("Enter Valid Dimension Name.");
		document.frmAdvDimensionEdit.txtDimensionEditName.focus();
		return false;
	}

	if(document.frmAdvDimensionEdit.txtDimensionEditName.value.indexOf('  ')!==-1){
		alert("Enter Valid Dimension Name.");
		document.frmAdvDimensionEdit.txtDimensionEditName.focus();
		return false;
	}
//<%=dimEditName%>
	return true;
}
