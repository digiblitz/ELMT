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
function frmRefundReqval()
{
//============================ For Balance Amount =============================
if(document.frmRefundReq.txtarea.value==""){
		alert("Comments cannot be empty.");
		document.frmRefundReq.txtarea.focus();
		return false;
	}

	if(document.frmRefundReq.txtarea.value.length>1024){
		alert("Special Instruction exceeds the maximum of 1024 characters.");
		document.frmRefundReq.txtarea.focus();
		return false;
	}

	if(document.frmRefundReq.txtarea.value.indexOf(' ')==0){
		alert("Enter Valid Comments.");
		document.frmRefundReq.txtarea.focus();
		return false;
	}

	if(document.frmRefundReq.txtarea.value.indexOf('  ')!==-1){
		alert("Enter Valid Comments.");
		document.frmRefundReq.txtarea.focus();
		return false;
	}


		}
	}
//	===============Claim Amount

	if(document.frmRefundReq.ClaimAmount.value==""){
		alert("Claim Amount cannot be empty");
		document.frmRefundReq.ClaimAmount.focus();
		return false;
	}

	if(!Number(document.frmRefundReq.ClaimAmount.value)){
		alert("Enter valid Claim Amount");
		document.frmRefundReq.txtAmount.focus();
		return false;
	}

	if(!(document.frmRefundReq.ClaimAmount.value=="")){
		if((document.frmRefundReq.ClaimAmount.value.indexOf('.'))!=-1){
			fstr=document.frmRefundReq.ClaimAmount.value;
			fstr1=document.frmRefundReq.ClaimAmount.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmRefundReq.ClaimAmount.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Claim Amount is Not Valid.");
				document.frmRefundReq.ClaimAmount.focus();
				return false;
			}
		}
	}
	
	var txam=document.frmRefundReq.txtAmount.value;
	var clam=document.frmRefundReq.ClaimAmount.value;
	if(clam>txam)
	{
alert("Claim Amount must be less than Amount");
document.frmRefundReq.ClaimAmount.focus();
return false;
	} 
	
	return true;
}


