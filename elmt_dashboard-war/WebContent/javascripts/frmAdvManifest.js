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

function manifestUpdate(){

//====================== For Issue Type ====================================
	if(document.frmAdvManifestUpdate.chkIssueId.value==""){
        alert("Please select Issue Type.");
		document.frmAdvManifestUpdate.chkIssueId.focus();
        return false;
    }

//===================== For Advertisement Dimension =========================
	if(document.frmAdvManifestUpdate.selWinDimchkIssue.value==""){
        alert("Please select Advertisement Dimension.");
		document.frmAdvManifestUpdate.selWinDimchkIssue.focus();
        return false;
    }

//======================= For Page Number ===================================
	if(document.frmAdvManifestUpdate.txtPage.value==""){
		alert("Enter Page Number.");
		document.frmAdvManifestUpdate.txtPage.focus();
		return false;
	}
	if(!Number(document.frmAdvManifestUpdate.txtPage.value)){
		alert("Enter valid Page Number.");
		document.frmAdvManifestUpdate.txtPage.focus();
		return false;
	}
	if(document.frmAdvManifestUpdate.txtPage.value.indexOf('.')!=-1){
		alert("Enter valid Page Number.");
		document.frmAdvManifestUpdate.txtPage.focus();
		return false;
	}
	if(document.frmAdvManifestUpdate.txtPage.value.length>7){
		alert("Enter valid Page Number.");
		document.frmAdvManifestUpdate.txtPage.focus();
		return false;
	}

//======================== For Special Placements ===========================
	if(document.frmAdvManifestUpdate.txtSpecial.value==""){
		alert("Enter Special Placements.");
		document.frmAdvManifestUpdate.txtSpecial.focus();
		return false;
	}

//======================= For Special Instructions ==========================
	if(document.frmAdvManifestUpdate.txtSpInstructions.value==""){
		alert("Enter Special Instructions.");
		document.frmAdvManifestUpdate.txtSpInstructions.focus();
		return false;
	}
	if(document.frmAdvManifestUpdate.txtSpInstructions.value.length>1024){
		alert("Entered Special Instructions is out of Range.");
		document.frmAdvManifestUpdate.txtSpInstructions.focus();
		return false;
	}
	return true;
}
