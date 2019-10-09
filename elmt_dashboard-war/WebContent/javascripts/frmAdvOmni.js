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
/*  Javascript Document 
******************************************************************************************************************
	Product Name: Integrated Enterprise Dashboard - Association / Club V0.5
	Organization: digiBlitz Technologies (P) Ltd.
    Created by: K.N.Sathish
    Created Date: 07/08/2006  
*****************************************************************************************************************
*/

function showLevels(chkBxNam,divId){
		if(document.frmAdvOmni.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmAdvOmni.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmAdvOmni.elements[chkBxId].checked == true){
				document.frmAdvOmni.elements[txtBxId].disabled = false;	
		}else {
				document.frmAdvOmni.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){	
		if(document.frmAdvOmni.elements[radBtnId].checked == true){
				document.frmAdvOmni.elements[txtBxId].disabled = false;	
		}else{
				document.frmAdvOmni.elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.frmAdvOmni.elements[radBtnNewId].checked == true){
				document.frmAdvOmni.elements[txtBxId].disabled = "disabled";	
		}
}
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmAdvOmni.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}


/*
function dispAmt(){
	var docForm = document.frmAdvOmni;
		if(docForm.selDisp.value != null){
			docForm.amount.value = docForm.selDisp.value;
		}
}
*/


		