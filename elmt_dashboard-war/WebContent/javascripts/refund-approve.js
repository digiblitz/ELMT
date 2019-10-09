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
function frmRefoundAppval()
{
	
	
	chosen="";
	len = document.frmAdvMag.radiobutton.length ;
	for(i=0;i<len;i++){
		if(document.frmAdvMag.radiobutton[i].checked){
			chosen= document.frmAdvMag.radiobutton[i].value;
		}
	}
	if(chosen=='Rejected')
	{
		document.frmAdvMag.appamt.value=0;
	}
	if(chosen=="Approved")
	{
var val=document.frmAdvMag.appamt.value+".0";
		if(document.frmAdvMag.clamt.value<document.frmAdvMag.appamt.value){
		//alert("document.frmAdvMag.appamt.value"+val);
		//alert("document.frmAdvMag.clamt.value"+document.frmAdvMag.clamt.value);
		alert("Approval amount can't be greater than claim amount");
		return false;}

		//document.frmAdvMag.appamt.focus();	
	}
	if(chosen==""){
		alert("Check any of the Approval Status Option.");
		return false;
	}

	
	
	// ================ Approved Refund Amount
	
	if(document.frmAdvMag.appamt.value==""){
		alert("Approved Refund Amount cannot be empty");
		document.frmAdvMag.appamt.focus();
		return false;
	}

        if(document.frmAdvMag.appamt.length>40){
		alert("Approved Refund Amount is out of range");
		document.frmAdvMag.appamt.focus();
		return false;
	}
	
	if(chosen=="Approved")
	{
	if(!Number(document.frmAdvMag.appamt.value)){
		alert("Enter valid Approved Refund Amount");
		document.frmAdvMag.appamt.focus();
		return false;
	}
	}

	if(!(document.frmAdvMag.appamt.value=="")){
		if((document.frmAdvMag.appamt.value.indexOf('.'))!=-1){
			fstr=document.frmAdvMag.appamt.value;
			fstr1=document.frmAdvMag.appamt.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmAdvMag.appamt.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Approved Refund Amount is Not Valid.");
				document.frmAdvMag.appamt.focus();
				return false;
			}
		}
	}
	
	return true;
	
}
function rej()
{
	document.frmAdvMag.appamt.value=0;
	document.frmAdvMag.submit.focus();
}
function accpt()
{
	document.frmAdvMag.appamt.value.focus();
}
