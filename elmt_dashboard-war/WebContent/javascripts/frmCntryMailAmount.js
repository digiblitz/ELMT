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
	
	
if((document.frmCntryMailAmount.year.selectedIndex==0)||(document.frmCntryMailAmount.year.options.value=="Select One"))
{
	alert('Select the Membership Year');
	document.frmCntryMailAmount.year.focus();
	return false;
}	
	
//--------------------------------------------------------	
	
if((document.frmCntryMailAmount.memberMailtypId.selectedIndex==0)||(document.frmCntryMailAmount.memberMailtypId.options.value=="Select One"))
{
	alert('Select the Membership Type');
	document.getElementById('memberMailtypId').focus();
	return false;
}
//-------------------------------------Country Mail Type name------------------------------------------------------------

if((document.frmCntryMailAmount.countryMailTypeName.value=="")||(document.frmCntryMailAmount.countryMailTypeName.value.indexOf(' ')==0))
   {alert(" Country Mail Type name cannot be empty ");
     document.frmCntryMailAmount.countryMailTypeName.focus();
    return false; }
	
	if((document.frmCntryMailAmount.countryMailTypeName.value.length >80 ))
 {alert("Country Mail Type name exceeds the maximum of 80 characters");
  document.frmCntryMailAmount.countryMailTypeName.focus();
 return false;}
 
 //-------------------------------------Amount--------------------------------------------------------------
 if((document.frmCntryMailAmount.countryMailPrice.value=="")||(document.frmCntryMailAmount.countryMailPrice.value.indexOf(' ')==0))
   {alert(" Amount cannot be empty ");
     document.frmCntryMailAmount.countryMailPrice.focus();
    return false; }
	
   if(!(document.frmCntryMailAmount.countryMailPrice.value=="")){
	   {if(!Number(document.frmCntryMailAmount.countryMailPrice.value))
       {alert("Amount is not valid");
       document.frmCntryMailAmount.countryMailPrice.focus();
        return false;}}
			if((document.frmCntryMailAmount.countryMailPrice.value.indexOf('.'))!=-1){
				fstr=document.frmCntryMailAmount.countryMailPrice.value;
				fstr1=document.frmCntryMailAmount.countryMailPrice.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmCntryMailAmount.countryMailPrice.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Amount is not valid ");
					document.frmCntryMailAmount.countryMailPrice.focus();
					return false;
				}
			} 
		}
		if((document.frmCntryMailAmount.countryMailPrice.value.length >16 ))
 {alert("Amount exceeds the maximum characters");
  document.frmCntryMailAmount.countryMailPrice.focus();
 return false;}			
    return true;
    }
	