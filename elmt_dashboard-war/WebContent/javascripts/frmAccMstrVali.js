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
function showList(){
	if(document.frmAccMaster.subItem.checked == true){
		document.frmAccMaster.subItemNo.disabled = false;
	}
	else{
		document.frmAccMaster.subItemNo.disabled = true;
	}
}

function hideList(){
	if(document.frmAccMaster.subItem.checked==true){
		document.frmAccMaster.subItemNo.disabled =false;
	}
}

function isSpecialCharacter(str){
	stringCheck="!@#$%^&*_+|:;{}[]<>?/=~.,'`;:"+"\\"+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

function chkValid(){
	if(document.frmAccMaster.accType.value == "" || document.frmAccMaster.accType.value.indexOf(' ')==0){
		alert('Select Any one of the Account Type');
		document.frmAccMaster.accType.focus();
		return false;
	}
	
	if(document.frmAccMaster.accNumber.value=="" || document.frmAccMaster.accNumber.value.indexOf(' ')==0){
		alert('Account Number cannot be empty');
		document.frmAccMaster.accNumber.focus();
		return false;
	}
	
	if(document.frmAccMaster.accName.value=="" || document.frmAccMaster.accName.value.indexOf(' ')==0){
		alert('Account Name cannot be empty');
		document.frmAccMaster.accName.focus();
		return false;
	}	
	
	if(document.frmAccMaster.accName.value!="" || document.frmAccMaster.accName.value.indexOf(' ')!=0){
		if(isSpecialCharacter(document.frmAccMaster.accName.value)){
			alert('Enter Valid Account Name cannot be empty');
			document.frmAccMaster.accName.focus();
			return false;
		}
	}
	
	if(document.frmAccMaster.subItem.checked == true){
		if(document.frmAccMaster.subItemNo.value=="" || document.frmAccMaster.subItemNo.value.indexOf(' ')==0){
			alert('Select Any one of the Sub Item');
			document.frmAccMaster.subItemNo.focus();
			return false;					
		}
	
		if(document.frmAccMaster.subItemNo.value!=""){
			var itemNo = document.frmAccMaster.accNumber.value;
			var subItemNo = document.frmAccMaster.subItemNo.value;
			if(itemNo==subItemNo){
				alert('Selected Sub Account cannot be a Sub Account of itself');
				document.frmAccMaster.subItemNo.focus();
				return false;
			}
		}		
	}
	
	if(document.frmAccMaster.accDescrption.value!="" || document.frmAccMaster.accDescrption.value.indexOf(' ')!=0){
		if(isSpecialCharacter(document.frmAccMaster.accDescrption.value)){
			alert('Enter Valid Account Description');
			document.frmAccMaster.accDescrption.focus();
			return false;
		}
	}	
	
	if(document.frmAccMaster.accBank.value!="" || document.frmAccMaster.accBank.value.indexOf(' ')!=0){
		if(isnotIntegerChk(document.frmAccMaster.accBank.value)){
			alert('Enter A valid Bank Account Number');
			document.frmAccMaster.accBank.focus();
			return false;
		}
	}
}

function itemNameNumberChk(str){
	stringIntCheck="abcdefghijklmopqrstuvwxyz0123456789.-()";
	f2=1;
	
	for(j=0;j<str.length;j++)
	{ 
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{ 
	  return false;
	}
}

function isnotIntegerChk(str){
	stringIntCheck="0123456789.()-";
	f2=1;
	
	for(j=0;j<str.length;j++)
	{ 
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{ 
	  return false;
	}
}

function enableCombo(){
	if(document.frmAccMaster.subItem.checked==true){
		document.frmAccMaster.subItemNo.disabled = false;
	}
	else{
		document.frmAccMaster.subItemNo.disabled = true;
	}
}
