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
function onSpoValidate(){
	if(document.frmSponsPlan.planName.value==""){
		alert("Enter the Plan Name..");
		document.frmSponsPlan.planName.focus();
		return false;
	}
	if(document.frmSponsPlan.planAmount.value==""){
		alert("Enter the Plan Amount..");
		document.frmSponsPlan.planAmount.focus();
		return false;
	}
	if(!Number(document.frmSponsPlan.planAmount.value)){
	alert("Enter a valid Number in total Amount Field");
	document.frmSponsPlan.planAmount.focus();
	return false;
	}
	if(document.frmSponsPlan.planDescription.value==""){
		alert("Please type the Plan Description..");
		document.frmSponsPlan.planDescription.focus();
		return false;
	}
	return true;
}

// ===========================================sponsor request form===============================================================
function onValidate(){
	if(document.frmSponsReg.companyName.value==""){
		alert("Enter the Company Name..");
		document.frmSponsReg.companyName.focus();
		return false;
	}

 	stringCheck="!@#$%^&* ()_+`|<>?/=-~.,0123456789"+"\\";

 	str1=document.frmSponsReg.companyName.value;
 	f=1;
 	for(j=0;j<document.frmSponsReg.companyName.value.length-1;j++){
 		if(stringCheck.indexOf(str1.charAt(j))==-1){
 			f=0;
 		}
 	}

 	if(f!=0){
 		alert("Enter valid Company Name");
 		document.frmSponsReg.companyName.focus();
 		return false;
 	}

 	if(Number(document.frmSponsReg.companyName.value)){
 		alert("Enter valid Company Name");
 		document.frmSponsReg.companyName.focus();
 		return false;
 	}

	if(document.frmSponsReg.planId.value==""){
		alert("Please Select the Plan Type");
		document.frmSponsReg.planId.focus();
		return false;
	}

	if(document.frmSponsReg.sponsorAmount.value==""){
 		alert("Amount Field should not be empty");
	 	document.frmSponsReg.sponsorAmount.focus();
	 	return false;
	 }
	 if(document.frmSponsReg.comments.value==""){
 		alert("Please enter your Comments ");
	 	document.frmSponsReg.comments.focus();
	 	return false;
	 }
	
	if(document.frmSponsReg.comments.value.length>255){
 		alert("Comments Field is out of Range.");
	 	document.frmSponsReg.comments.focus();
	 	return false;
	 }

	return true;
}


