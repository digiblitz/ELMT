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
function myvalidate(){

//---------------------------------Media Type------------------------------------------------------------


if(document.frmGrossAmtMast.selMediaTypeId.selectedIndex==0)
{alert(" Select the Media Type ");
 document.frmGrossAmtMast.selMediaTypeId.focus();
 return false;}

//----------------------------------Issue---------------------------------------------------------------

if(document.frmGrossAmtMast.selIssueid.selectedIndex==0)
{alert(" Select An Issue ");
 document.frmGrossAmtMast.selIssueid.focus();
 return false;}

//------------------------------------An Advertisement Type-------------------------------------------

if(document.frmGrossAmtMast.selDisplayTypeId.selectedIndex==0)
{alert(" Select An Advertisement Type ");
 document.frmGrossAmtMast.selDisplayTypeId.focus();
 return false;}

//------------------------------------------Advertisement Sub-Type----------------------------------------

if(document.frmGrossAmtMast.selDisplaySubTypeId.selectedIndex==0)
{alert(" Select Advertisement Sub-Type ");
 document.frmGrossAmtMast.selDisplaySubTypeId.focus();
 return false;}

//-------------------------------------Gross Amount--------------------------------------------------


 if(document.frmGrossAmtMast.txtGrossAmount.value=="")
   {alert(" Gross Amount cannot be empty ");
     document.frmGrossAmtMast.txtGrossAmount.focus();
    return false; }
	
   if(!(document.frmGrossAmtMast.txtGrossAmount.value=="")){
	   {if(!Number(document.frmGrossAmtMast.txtGrossAmount.value))
       {alert(" Gross Amount is not valid");
       document.frmGrossAmtMast.txtGrossAmount.focus();
        return false;}}
			if((document.frmGrossAmtMast.txtGrossAmount.value.indexOf('.'))!=-1){
				fstr=document.frmGrossAmtMast.txtGrossAmount.value;
				fstr1=document.frmGrossAmtMast.txtGrossAmount.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmGrossAmtMast.txtGrossAmount.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert(" Gross Amount is not valid ");
					document.frmGrossAmtMast.txtGrossAmount.focus();
					return false;
				}
			} }
			
	return true;
	}
