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
function frmAppRefundval()
{

//============================ For Number of Issues ===========================
	
	if(!((document.frmAdvAppRefund.chCheck.checked)||(document.frmAdvAppRefund.chCharity.checked)||(document.frmAdvAppRefund.chShopping.checked)||(document.frmAdvAppRefund.chCredit.checked)||(document.frmAdvAppRefund.chRenewal.checked))){
		alert("Please Select any option in the Refund Type.");
		return false;
	}
	//================  Charity
	
	
	
	if(document.frmAdvAppRefund.chCheck.checked)
	{if(document.frmAdvAppRefund.textCheck.value=="")
		{ alert("Enter a valid check detail");
		  document.frmAdvAppRefund.textCheck.focus();
		   return false; }
		   if(!Number(document.frmAdvAppRefund.textCheck.value))
	  {	alert("Enter valid check ");
		document.frmAdvAppRefund.textCheck.focus();
		return false; }
	 	if(!(document.frmAdvAppRefund.textCheck.value==""))
	    { if((document.frmAdvAppRefund.textCheck.value.indexOf('.'))!=-1)
			{fstr=document.frmAdvAppRefund.textCheck.value;
			 fstr1=document.frmAdvAppRefund.textCheck.value.indexOf('.');
			 mm = (fstr.substring(fstr1,document.frmAdvAppRefund.textCheck.value.length));
			 str=(Number(mm.length));
			 if((str)>3)
			 {	alert("Check is Not Valid.");
				document.frmAdvAppRefund.textCheck.focus();
				return false;}
			 }
	     }
	}

	
	if(document.frmAdvAppRefund.txtCharity.checked )
	{  if(document.frmAdvAppRefund.txtCharity.value=="")
		{ alert("Charity cannot be empty");
		 document.frmAdvAppRefund.txtCharity.focus();
		 return false; }
	  if(!Number(document.frmAdvAppRefund.txtCharity.value))
	  {	alert("Enter valid Charity ");
		document.frmAdvAppRefund.txtCharity.focus();
		return false; }
	 	if(!(document.frmAdvAppRefund.txtCharity.value==""))
	    { if((document.frmAdvAppRefund.txtCharity.value.indexOf('.'))!=-1)
			{fstr=document.frmAdvAppRefund.txtCharity.value;
			 fstr1=document.frmAdvAppRefund.txtCharity.value.indexOf('.');
			 mm = (fstr.substring(fstr1,document.frmAdvAppRefund.txtCharity.value.length));
			 str=(Number(mm.length));
			 if((str)>3)
			 {	alert("Charity is Not Valid.");
				document.frmAdvAppRefund.txtCharity.focus();
				return false;}
			 }
	     }
	}

	
	
	
	
	if(document.frmAdvAppRefund.chShopping.checked )
	{ if(document.frmAdvAppRefund.textShopping.value=="")
		{ alert("Enter a valid Shopping detail");
		  document.frmAdvAppRefund.textShopping.focus();
		   return false; }
		   if(!Number(document.frmAdvAppRefund.textShopping.value))
	  {	alert("Enter valid Shopping ");
		document.frmAdvAppRefund.textShopping.focus();
		return false; }
	 	if(!(document.frmAdvAppRefund.textShopping.value==""))
	    { if((document.frmAdvAppRefund.textShopping.value.indexOf('.'))!=-1)
			{fstr=document.frmAdvAppRefund.textShopping.value;
			 fstr1=document.frmAdvAppRefund.textShopping.value.indexOf('.');
			 mm = (fstr.substring(fstr1,document.frmAdvAppRefund.textShopping.value.length));
			 str=(Number(mm.length));
			 if((str)>3)
			 {	alert("Shopping is Not Valid.");
				document.frmAdvAppRefund.textShopping.focus();
				return false;}
			 }
	     }
		   
	}

	
	
	if(document.frmAdvAppRefund.chRenewal.checked )
	{ if(document.frmAdvAppRefund.textRenewa.value=="")
		{ alert("Enter a valid Renewal ");
		  document.frmAdvAppRefund.textRenewa.focus();
		   return false; }
		    if(!Number(document.frmAdvAppRefund.textRenewa.value))
	  {	alert("Enter valid Renewal");
		document.frmAdvAppRefund.textRenewa.focus();
		return false; }
	 	if(!(document.frmAdvAppRefund.textRenewa.value==""))
	    { if((document.frmAdvAppRefund.textRenewa.value.indexOf('.'))!=-1)
			{fstr=document.frmAdvAppRefund.textRenewa.value;
			 fstr1=document.frmAdvAppRefund.textRenewa.value.indexOf('.');
			 mm = (fstr.substring(fstr1,document.frmAdvAppRefund.textRenewa.value.length));
			 str=(Number(mm.length));
			 if((str)>3)
			 {	alert("Renewal is Not Valid.");
				document.frmAdvAppRefund.textRenewa.focus();
				return false;}
			 }
	     }
	}

	if(document.frmAdvAppRefund.chCredit.checked )
	{ if(document.frmAdvAppRefund.textCredit.value=="")
		{ alert("Enter a valid Credit detail");
		  document.frmAdvAppRefund.textCredit.focus();
		   return false; }
		   f(!Number(document.frmAdvAppRefund.textCredit.value))
	  {	alert("Enter valid Credit ");
		document.frmAdvAppRefund.textCredit.focus();
		return false; }
	 	if(!(document.frmAdvAppRefund.textCredit.value==""))
	    { if((document.frmAdvAppRefund.textCredit.value.indexOf('.'))!=-1)
			{fstr=document.frmAdvAppRefund.textCredit.value;
			 fstr1=document.frmAdvAppRefund.textCredit.value.indexOf('.');
			 mm = (fstr.substring(fstr1,document.frmAdvAppRefund.textCredit.value.length));
			 str=(Number(mm.length));
			 if((str)>3)
			 {	alert("Credit is Not Valid.");
				document.frmAdvAppRefund.textCredit.focus();
				return false;}
			 }
	     }
	}
	
	
	

	

			


				return true;
			
	}
	
	
