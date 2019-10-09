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

function showLevels(chkBxNam,divId){
		if(document.frmCreateDimenDetail.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmCreateDimenDetail.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmCreateDimenDetail.elements[chkBxId].checked == true){
				document.frmCreateDimenDetail.elements[txtBxId].disabled = false;	
		}else {
				document.frmCreateDimenDetail.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){
		if(document.frmCreateDimenDetail.elements[radBtnId].checked == true){
				document.frmCreateDimenDetail.elements[txtBxId].disabled = false;	
		}else{
				document.frmCreateDimenDetail.elements[txtBxId].disabled = "disabled";	
		} 
}
function disable(radBtnNewId,txtBxId){
		if(document.frmCreateDimenDetail.elements[radBtnNewId].checked == true){
				document.frmCreateDimenDetail.elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmCreateDimenDetail.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "none";	
		}else {
				document.getElementById(divId).style.display = "block";	
		} 
}


/*
function dispAmt(){
	var docForm = document.frmCreateDimenDetail;
		if(docForm.selDisp.value != null){
			docForm.amount.value = docForm.selDisp.value;
		}
}
*/
function myvalidate()
{
	//--------------------------------drop down--------------------------------
	if(document.formAdvertisement.selMediaType.selectedIndex == 0 ){
        alert ( "Please select a Media Type." );
		document.formAdvertisement.selMediaType.focus();
        return false;
    }
	if(document.formAdvertisement.selDisplayType.selectedIndex == 0 ){
        alert ( "Please select a Advertisement Type." );
		document.formAdvertisement.selDisplayType.focus();
        return false;
    }
	if(document.formAdvertisement.selDisplaySubType.selectedIndex == 0 ){
        alert ( "Please select a Advertisement Sub-Type</span>." );
		document.formAdvertisement.selDisplaySubType.focus();
        return false;
    }
	if(document.formAdvertisement.selDimensionName.selectedIndex == 0 ){
        alert ( "Please select a Dimension Type." );
		document.formAdvertisement.selDimensionName.focus();
        return false;
    }
	//--------------------------------text boxes-----------------------------------
	//-----------------------------Advertisement Height------------------------
	if(document.formAdvertisement.txtAdvHeight.value==""){
		alert(" Advertisement Height cannot be empty.");
		document.formAdvertisement.txtAdvHeight.focus();
		return false;
	}

	if(document.formAdvertisement.txtAdvHeight.value.length>80){
		alert("Advertisement Height exceeds the maximum of 80 characters.");
		document.formAdvertisement.txtAdvHeight.focus();
		return false;
	}
   if(document.formAdvertisement.txtAdvHeight.value.indexOf('  ')!==-1){
		alert("Enter valid Advertisement Height.");
		document.formAdvertisement.txtAdvHeight.focus();
		return false;
	}
	

	if(document.formAdvertisement.txtAdvHeight.value.indexOf(' ')==0){
		alert("Enter valid Advertisement Height.");
		document.formAdvertisement.txtAdvHeight.focus();
		return false;
	}
	//-----------------------------------Advertisement width---------------------------
	
	if(document.formAdvertisement.txtAdvWidth.value==""){
		alert(" Advertisement Width cannot be empty.");
		document.formAdvertisement.txtAdvWidth.focus();
		return false;
	}

	if(document.formAdvertisement.txtAdvWidth.value.length>80){
		alert("Advertisement Width exceeds the maximum of 80 characters.");
		document.formAdvertisement.txtAdvWidth.focus();
		return false;
	}
   if(document.formAdvertisement.txtAdvWidth.value.indexOf('  ')!==-1){
		alert("Enter valid Advertisement Width.");
		document.formAdvertisement.txtAdvWidth.focus();
		return false;
	}
	

	if(document.formAdvertisement.txtAdvWidth.value.indexOf(' ')==0){
		alert("Enter valid Advertisement Width.");
		document.formAdvertisement.txtAdvWidth.focus();
		return false;
	}
	
	//----------------------------------------------Dimensional Unit-------------------------------
	
	if(document.formAdvertisement.txtDimensionalUnit.value==""){
		alert(" Dimensional Unit cannot be empty.");
		document.formAdvertisement.txtDimensionalUnit.focus();
		return false;
	}

	if(document.formAdvertisement.txtDimensionalUnit.value.length>80){
		alert("Dimensional Unit exceeds the maximum of 80 characters.");
		document.formAdvertisement.txtDimensionalUnit.focus();
		return false;
	}
   if(document.formAdvertisement.txtDimensionalUnit.value.indexOf('  ')!==-1){
		alert("Enter valid Dimensional Unit.");
		document.formAdvertisement.txtDimensionalUnit.focus();
		return false;
	}
	

	if(document.formAdvertisement.txtDimensionalUnit.value.indexOf(' ')==0){
		alert("Enter valid Dimensional Unit.");
		document.formAdvertisement.txtDimensionalUnit.focus();
		return false;
	}
	
//-------------------------------------------------Upload Dimensional Sample------------------------------
	/*if(document.frmCreateDimenDetail.txtAdvHeight.value==""){
		alert(" Advertisement Height cannot be empty.");
		document.frmCreateDimenDetail.txtAdvHeight.focus();
		return false;
	}

	if(document.frmCreateDimenDetail.txtAdvHeight.value.length>80){
		alert("Advertisement Height exceeds the maximum of 80 characters.");
		document.frmCreateDimenDetail.txtAdvHeight.focus();
		return false;
	}
   if(document.frmCreateDimenDetail.txtAdvHeight.value.indexOf('  ')!==-1){
		alert("Enter valid Advertisement Height.");
		document.frmCreateDimenDetail.txtAdvHeight.focus();
		return false;
	}
	

	if(document.frmCreateDimenDetail.txtAdvHeight.value.indexOf(' ')==0){
		alert("Enter valid Advertisement Height.");
		document.frmCreateDimenDetail.txtAdvHeight.focus();
		return false;
	}*/
	
return true;
}

		
