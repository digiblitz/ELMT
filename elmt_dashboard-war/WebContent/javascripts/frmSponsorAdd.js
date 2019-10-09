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
//================================== Sponsor Add Form ====================================
function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`;:"+"\\"+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

function onSpoValidate(){
	if(document.frmSponsPlan.planName.value==""){
		alert("Enter valid Sponsorship Plan.");
		document.frmSponsPlan.planName.focus();
		return false;
	}

	if(document.frmSponsPlan.planName.value.length>80){
		alert("Entered Sponsorship Plan Name is out of range.");
		document.frmSponsPlan.planName.focus();
		return false;
	}

	

	if(document.frmSponsPlan.planName.value.indexOf(' ')==0){
		alert("Enter valid Sponsorship Plan Name.");
		document.frmSponsPlan.planName.focus();
		return false;
	}

	if(document.frmSponsPlan.planName.value.indexOf('  ')!==-1){
		alert("Enter valid Sponsorship Plan Name.");
		document.frmSponsPlan.planName.focus();
		return false;
	}

	if(document.frmSponsPlan.planAmount.value==""){
		alert("The Total Plan Amount Field can't be blank");
		document.frmSponsPlan.planAmount.focus();
		return false;
	}

	if(!Number(document.frmSponsPlan.planAmount.value)){
		alert("Enter valid Plan Amount");
		document.frmSponsPlan.planAmount.focus();
		return false;
	}
	if(document.frmSponsPlan.planAmount.value.indexOf('.')>7){
		alert("Enter valid Plan Amount");
		document.frmSponsPlan.planAmount.focus();
		return false;
	}
	if((document.frmSponsPlan.planAmount.value.indexOf('.'))==-1){
		if(document.frmSponsPlan.planAmount.value.length>7){
			alert("Enter valid Plan Amount");
			document.frmSponsPlan.planAmount.focus();
			return false;
		}
	}
		
	if(!(document.frmSponsPlan.planAmount.value=="")){
		if((document.frmSponsPlan.planAmount.value.indexOf('.'))!=-1){
			fstr=document.frmSponsPlan.planAmount.value;
			fstr1=document.frmSponsPlan.planAmount.value.lastIndexOf('.');
			mm = (fstr.substring(fstr1,document.frmSponsPlan.planAmount.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Plan Amount is not valid");
				document.frmSponsPlan.planAmount.focus();
				return false;
			}
		}
	}
	
	if(document.frmSponsPlan.planDescription.value==""){
		alert("Enter Plan Description");
		document.frmSponsPlan.planDescription.focus();
		return false;
	}

	if(document.frmSponsPlan.planDescription.value.indexOf(' ')==0){
		alert("Enter valid Plan Description");
		document.frmSponsPlan.planDescription.focus();
		return false;
	}

	if(document.frmSponsPlan.planDescription.value.length>1024){
		alert("Plan Description is out of Range.");
		document.frmSponsPlan.planDescription.focus();
		return false;
	}

	if(document.frmSponsPlan.planDescription.value.indexOf('  ')!==-1){
		alert("Enter valid Plan Description.");
		document.frmSponsPlan.planDescription.focus();
		return false;
	}
	return true;
}

// ===========================================sponsor request form===============================================================



function onValidate(){
	if(document.frmSponsReg.companyName.value==""){
		alert("Enter the Company Name.");
		document.frmSponsReg.companyName.focus();
		return false;
	}
	
	if(document.frmSponsReg.companyName.value.length>80){
 		alert("Company Name is out of Range.");
	 	document.frmSponsReg.companyName.focus();
	 	return false;
	 }
if(document.frmSponsReg.companyName.value.indexOf(' ')==0){
		alert("Enter valid Company Name.");
		document.frmSponsReg.companyName.focus();
		return false;
	}
if(document.frmSponsReg.companyName.value.indexOf('  ')!==-1){
		alert("Enter valid Company Name.");
		document.frmSponsReg.companyName.focus();
		return false;
	}	

	if(document.frmSponsReg.planId.value==""){
		alert("Please select the Plan Type");
		document.frmSponsReg.planId.focus();
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
