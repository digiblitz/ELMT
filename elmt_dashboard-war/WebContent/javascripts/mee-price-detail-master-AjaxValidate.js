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

//==================================== For Activity Category ==============================

	if(document.frmMeePriceMaster.selCatagoryTypeId.selectedIndex==0){
		alert("Please select the Event Type.");
		document.frmMeePriceMaster.selCatagoryTypeId.focus();
		return false;
	}
	
//==================================== Specification ==============================
if(document.frmMeePriceMaster.selSpecificationId.selectedIndex==0){
		alert("Please select the Specification Category.");
		document.frmMeePriceMaster.selSpecificationId.focus();
		return false;
	}
//==================================== User Type ==============================
if(document.frmMeePriceMaster.selUserTypeId.selectedIndex==0){
		alert("Please select the User Type Category.");
		document.frmMeePriceMaster.selUserTypeId.focus();
		return false;
	}

//==================================== Normal Price ==============================

if(document.frmMeePriceMaster.txtNormalPrice.value==""){
		alert("Normal Price is Empty");
		document.frmMeePriceMaster.txtNormalPrice.focus();
		return false;
	}	
if(document.frmMeePriceMaster.txtNormalPrice.value!=""){
	if(!Number(document.frmMeePriceMaster.txtNormalPrice.value)){
		alert("Normal Price is not valid");
		document.frmMeePriceMaster.txtNormalPrice.focus();
		return false;
	}	
	if((document.frmMeePriceMaster.txtNormalPrice.value.indexOf('  ')!=-1)||(document.frmMeePriceMaster.txtNormalPrice.value.indexOf(' ')==0)){
		alert("Normal Price is not valid");
		document.frmMeePriceMaster.txtNormalPrice.focus();
		return false;
	}
		
	if((document.frmMeePriceMaster.txtNormalPrice.value.indexOf('.'))!=-1){
		fstr=document.frmMeePriceMaster.txtNormalPrice.value;
		fstr1=document.frmMeePriceMaster.txtNormalPrice.value.indexOf('.');
		mm = (fstr.substring(fstr1,document.frmMeePriceMaster.txtNormalPrice.value.length));
		str=(Number(mm.length));
	if((str)>3){
		alert("Amount is Not Valid.");
		document.frmMeePriceMaster.txtNormalPrice.focus();
		return false;
		}
		
	}

}

//==================================== Due Date Price ==============================

if(document.frmMeePriceMaster.txtDuePrice.value==""){
		alert("Due Date Price is Empty");
		document.frmMeePriceMaster.txtDuePrice.focus();
		return false;
	}	
if(document.frmMeePriceMaster.txtDuePrice.value!=""){
	if(!Number(document.frmMeePriceMaster.txtDuePrice.value)){
		alert("Due Date Price is not valid");
		document.frmMeePriceMaster.txtDuePrice.focus();
		return false;
	}	
	if((document.frmMeePriceMaster.txtDuePrice.value.indexOf('  ')!=-1)||(document.frmMeePriceMaster.txtDuePrice.value.indexOf(' ')==0)){
		alert("Due Date Price is not valid");
		document.frmMeePriceMaster.txtDuePrice.focus();
		return false;
	}
		
	if((document.frmMeePriceMaster.txtDuePrice.value.indexOf('.'))!=-1){
		fstr=document.frmMeePriceMaster.txtDuePrice.value;
		fstr1=document.frmMeePriceMaster.txtDuePrice.value.indexOf('.');
		mm = (fstr.substring(fstr1,document.frmMeePriceMaster.txtDuePrice.value.length));
		str=(Number(mm.length));
	if((str)>3){
		alert("Amount is Not Valid.");
		document.frmMeePriceMaster.txtDuePrice.focus();
		return false;
		}
		
	}

 }
	return true;
}

