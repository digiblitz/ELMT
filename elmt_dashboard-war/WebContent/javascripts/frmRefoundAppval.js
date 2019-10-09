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
	len = document.frmRefoundApp.radioApprStat.length ;
	for(i=0;i<len;i++){
		if(document.frmRefoundApp.radioApprStat[i].checked){
			chosen= document.frmRefoundApp.radioApprStat[i].value;
		}
	}

	if(chosen==""){
		alert("Check any of the Approval Status Option.");
		return false;
	}
	
	
	
	// ================ Approved Refund Amount
	
	if(document.frmRefoundApp.AppRefAmount.value==""){
		alert("Approved Refund Amountcannot be empty");
		document.frmRefoundApp.AppRefAmount.focus();
		return false;
	}

	if(!Number(document.frmRefoundApp.AppRefAmount.value)){
		alert("Enter valid Approved Refund Amount");
		document.frmRefoundApp.AppRefAmount.focus();
		return false;
	}

	if(!(document.frmRefoundApp.AppRefAmount.value=="")){
		if((document.frmRefoundApp.AppRefAmount.value.indexOf('.'))!=-1){
			fstr=document.frmRefoundApp.AppRefAmount.value;
			fstr1=document.frmRefoundApp.AppRefAmount.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmRefoundApp.AppRefAmount.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Approved Refund Amount is Not Valid.");
				document.frmRefoundApp.AppRefAmount.focus();
				return false;
			}
		}
	}
	
	return true;
	
}
