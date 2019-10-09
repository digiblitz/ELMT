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
/*  Javascript Document 
******************************************************************************************************************
	Product Name: Integrated Enterprise Dashboard - Association / Club V0.5
	Organization: digiBlitz Technologies (P) Ltd.
    Created by: K.N.Sathish
    Created Date: 07/08/2006  
*****************************************************************************************************************
*/

function showLevels(chkBxNam,divId){
		if(document.forms['AnnualRegForm'].elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.forms['AnnualRegForm'].elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function enabDisab(chkBxId,txtBxId){
	  
		if(document.forms['AnnualRegForm'].elements[chkBxId].checked == true){
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = false;	
		}else {
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = "disabled";	
		} 
}
function enabDisabPony(radioNam,txtBxId){
		if(document.forms['AnnualRegForm'].elements[radioNam].value == "yes"){
			//alert('Hi!');
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = false;	
		}else {
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = "disabled";	
		} 
}
function enableRad(radBtnId,txtBxId){
		if(document.forms['AnnualRegForm'].elements[radBtnId].checked == true){
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = false;	
		}else{
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.forms['AnnualRegForm'].elements[radBtnNewId].checked == true){
				document.forms['AnnualRegForm'].elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.forms['AnnualRegForm'].elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}


function checkClear(){	
	document.forms['AnnualRegForm'].checkNo.value="";
	document.forms['AnnualRegForm'].checkDate.value="";
	document.forms['AnnualRegForm'].favorOf.value="";
}

function cardClear(){
	document.forms['AnnualRegForm'].cardNo.value="";
	document.forms['AnnualRegForm'].cardCvvNo.value="";
	document.forms['AnnualRegForm'].cardType.value="";
	document.forms['AnnualRegForm'].cardName.value="";
	document.forms['AnnualRegForm'].expiryMonth.value="";
	document.forms['AnnualRegForm'].expiryYear.value="";
}


function clearMemberFields(){	
	document.forms['AnnualRegForm'].memberId.value="";
	document.forms['AnnualRegForm'].emailId.value="";
	document.forms['AnnualRegForm'].firstName.value="";
	document.forms['AnnualRegForm'].lastName.value="";
	document.forms['AnnualRegForm'].address.value="";
	document.forms['AnnualRegForm'].country.value="";
	document.forms['AnnualRegForm'].state.value="";
	document.forms['AnnualRegForm'].city.value="";
	document.forms['AnnualRegForm'].zip.value="";
	document.forms['AnnualRegForm'].fax.value="";
}



function ValidateForm()
{

			var chosenMemb="";
			var len2 = document.AnnualRegForm.rself.length ;
			for(i=0;i<len2;i++)
			{if(document.AnnualRegForm.rself[i].checked)
			  { chosenMemb= document.AnnualRegForm.rself[i].value; }
			}
			if(chosenMemb=="")
			{alert("Specify You are a member or not");
			 return false;
			}

			if(chosenMemb=="yes")
			{ 
				if(document.AnnualRegForm.userNameId.value=="")
				{
					alert("Enter user Id");
					document.AnnualRegForm.userNameId.focus();
		    		   return false;
				}
				if(!(document.AnnualRegForm.userNameId.value=="")){
			   {if(!Number(document.AnnualRegForm.userNameId.value))
			   {alert("Enter a valid HLC Number");
			   document.AnnualRegForm.userNameId.focus();
				return false;}}
			  }
			  }
			return true;
}
