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

function advTypeCheck(){
	if(document.frmCreateAdvType.mediaType_sel.selectedIndex == 0 ){
        alert ( "Select a Media Type." );
		document.frmCreateAdvType.mediaType_sel.focus();
        return false;
    }
	
	if(document.frmCreateAdvType.txtAdvName.value==""){
		alert("Advertisement Type Name cannot be empty.");
		document.frmCreateAdvType.txtAdvName.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtAdvName.value.length>80){
		alert("Advertisement Type Name should not exceed 80 characters.");
		document.frmCreateAdvType.txtAdvName.focus();
		return false;
	}

	if(document.frmCreateAdvType.txtAdvName.value.indexOf(' ')==0){
		alert("Enter valid Advertisement Type Name.");
		document.frmCreateAdvType.txtAdvName.focus();
		return false;
	}
//onclick="(window.location.href('adv-confirmation-page.html'));"
	if(document.frmCreateAdvType.txtAdvName.value.indexOf('  ')!==-1){
		alert("Enter valid Advertisement Type Name.");
		document.frmCreateAdvType.txtAdvName.focus();
		return false;
	}

	if(document.frmCreateAdvType.textarea.value==""){
		alert("Description cannot be empty.");
		document.frmCreateAdvType.textarea.focus();
		return false;
	}

	if(document.frmCreateAdvType.textarea.value.length>1024){
		alert("Description should not exceed 1024 characters.");
		document.frmCreateAdvType.textarea.focus();
		return false;
	}
	
	if(document.frmCreateAdvType.textarea.value.indexOf(' ')==0){
		alert("Enter valid Description.");
		document.frmCreateAdvType.textarea.focus();
		return false;
	}

	if(document.frmCreateAdvType.textarea.value.indexOf('  ')!==-1){
		alert("Enter valid Description.");
		document.frmCreateAdvType.textarea.focus();
		return false;
	}

	return true;
}
