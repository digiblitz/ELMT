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
/*


	if(document.frmRefundReq.txtAmount.value=="")
	{	alert("Balance Amount cannot be empty");
		document.frmRefundReq.txtAmount.focus();
		return false; }

	if(!Number(document.frmRefundReq.txtAmount.value))
	{	alert("Enter valid Balance Amount");
		document.frmRefundReq.txtAmount.focus();
		return false; }

	if(!(document.frmRefundReq.txtAmount.value==""))
	{ if((document.frmRefundReq.txtAmount.value.indexOf('.'))!=-1)
	    {	fstr=document.frmRefundReq.txtAmount.value;
		fstr1=document.frmRefundReq.txtAmount.value.indexOf('.');
		mm = (fstr.substring(fstr1,document.frmRefundReq.txtAmount.value.length));
		str=(Number(mm.length));
		if((str)>3){
			alert("Balance Amount is Not Valid.");
			document.frmRefundReq.txtAmount.focus();
			return false;
			}
	  }
	}*/

//	===============Claim Amount

	if(document.frmRefundReq.claimAmount.value=="")
	{
		alert("Claim Amount cannot be empty");
		document.frmRefundReq.claimAmount.focus();
		return false;
	}

	if(!Number(document.frmRefundReq.claimAmount.value))
	{
		alert("Enter valid Claim Amount");
		document.frmRefundReq.claimAmount.focus();
		return false;
	}

	if(document.frmRefundReq.claimAmount.value != "")
	{
		if(document.frmRefundReq.claimAmount.length>40)
		{
			   document.frmRefundReq.claimAmount.focus();
			   return false;
		}
	}

		if((document.frmRefundReq.claimAmount.value.indexOf('.'))!=-1)
		{	
			fstr=document.frmRefundReq.claimAmount.value;
			fstr1=document.frmRefundReq.claimAmount.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmRefundReq.claimAmount.value.length));
			str=(Number(mm.length));
			if((str)>3)
			{ 
				alert("Claim Amount is Not Valid.");
			   document.frmRefundReq.claimAmount.focus();
			   return false; 
			 }
		}
	
//-----------------------------to check if the balance amount is less than claimed amount-------------------------------------------
	/*
	var txam=Number(document.frmRefundReq.txtAmount.value);
	var clam=Number(document.frmRefundReq.claimAmount.value);
	if((clam) > (txam))
	{
	alert("Claim Amount must be less than Amount");
	document.frmRefundReq.claimAmount.focus();
	return false;
	}*/ 
//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
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


	return true;
}



function Sumup()
{
	sum = 0;
	donCount = Number(document.frmRefundReq.donCbxCt.value);	
	//alert("donCount :"+donCount);
	
	for(i=0;i<donCount;i++)
	{
		dontbname="donTb"+i;//String.valueOf(i);
		doncbname="donCb"+i;
		
		//alert("dontbname :"+dontbname);
		//alert("doncbname :"+doncbname);
		var val="";
		//alert("document.getElementById(doncbname).checked :" +document.getElementById(doncbname).checked);
		
		if(document.getElementById(doncbname).checked == true)
		{
			val=document.getElementById(dontbname).value;
			//alert("val :"+val);		
			sum = sum + Number(val);
			
		}
		
	}
	
	if(document.frmRefundReq.memb_avail.value == "yes")
	{
		if(document.frmRefundReq.membTypFee.checked == true)
		{
				
			sum = sum + Number(document.frmRefundReq.mem_amt.value);
		}
	}
	
	document.frmRefundReq.claimAmount.value = sum;
	
}
