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

//============================== For Advertisement Display ============================
function advDisplayAdd(){
	if(document.frmCreateAdvType.mediaDispId.selectedIndex == 0 ){
        alert ( "Select a Media Type." );
		document.frmCreateAdvType.mediaDispId.focus();
        return false;
    }

	if(document.frmCreateAdvType.txtDisplayType.value==""){
		alert("Advertisement Type Name cannot be empty.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value.length>80){
		alert("Advertisement Type Name should not exceed 80 characters.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value.indexOf(' ')==0){
		alert("Enter valid Advertisement Type Name.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value.indexOf('  ')!==-1){
		alert("Enter valid Advertisement Type Name.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value==""){
		alert("Dispaly Type Description cannot be empty.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value.length>1024){
		alert("Display Type Description should not exceed 1024 characters.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value.indexOf(' ')==0){
		alert("Enter valid Display Type Description.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value.indexOf('  ')!==-1){
		alert("Enter valid Display Type Description.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	return true;
}
//================================= For Display Edit ===================================
function advDisplayEdit(){
	
	if(document.frmCreateAdvType.mediaDispId.selectedIndex==0){
		alert("Select a Media Type.");
		document.frmCreateAdvType.mediaDispId.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value==""){
		alert("Advertisement Type Name cannot be empty.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value.length>80){
		alert("Advertisement Type Name should not exceed 80 characters.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value.indexOf(' ')==0){
		alert("Enter valid Advertisement Type Name.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtDisplayType.value.indexOf('  ')!==-1){
		alert("Enter valid Advertisement Type Name.");
		document.frmCreateAdvType.txtDisplayType.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value==""){
		alert("Display Type Description cannot be empty.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value.length>1024){
		alert("Display Type Description should not exceed 1024 characters.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value.indexOf(' ')==0){
		alert("Enter valid Display Type Description.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtareaDisplayTypeDesc.value.indexOf('  ')!==-1){
		alert("Enter valid Display Type Description.");
		document.frmCreateAdvType.txtareaDisplayTypeDesc.focus();
		return false;
	}

	return true;
}