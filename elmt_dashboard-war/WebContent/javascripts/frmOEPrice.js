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
//-------------------function for character validation--------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//function for Alpha Numeric
function isnotAlphaNumeric(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//----------------------------------------------------------------------------------------------------
function isnotAlphaNumeric1(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?=~-`.,;:][{}"+"\\";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//-----------------------function for pure Integer Numbers
function isnotInteger(str){
stringIntCheck="0123456789";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}
function isnotIntegerChk(str){
stringIntCheck="0123456789.";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}

function myvalidate()
{
	if(document.frmInsertPrice.txtDue.value!=""){	
	if(isnotIntegerChk(document.frmInsertPrice.txtDue.value)){
	alert("Enter a valid Due Date Price.");
	document.frmInsertPrice.txtDue.focus();
	return false;
	}
				
	if(isnotAlphaNumeric(document.frmInsertPrice.txtDue.value)){
	alert("Enter a valid Due Date Price.");
	document.frmInsertPrice.txtDue.focus();
	return false;
	}
	
	if(document.frmInsertPrice.txtDue.value.indexOf('.')!=-1){
	alert("Enter a Valid Due Date Price.");
	document.frmInsertPrice.txtDue.focus();
	return false;
	}
	
	if(document.frmInsertPrice.txtDue.value.indexOf(" ")==0){
	alert("Enter a Valid Due Date Price.");
	document.frmInsertPrice.txtDue.focus();
	return false;
	}
	}
	
	
	if(document.frmInsertPrice.txtAfterDue.value!=""){	
	if(isnotIntegerChk(document.frmInsertPrice.txtAfterDue.value)){
	alert("Enter a valid After Due Date Price.");
	document.frmInsertPrice.txtAfterDue.focus();
	return false;
	}
				
	if(isnotAlphaNumeric(document.frmInsertPrice.txtAfterDue.value)){
	alert("Enter a valid After Due Date Price.");
	document.frmInsertPrice.txtAfterDue.focus();
	return false;
	}
	
	if(document.frmInsertPrice.txtAfterDue.value.indexOf('.')!=-1){
	alert("Enter a Valid After Due Date Price.");
	document.frmInsertPrice.txtAfterDue.focus();
	return false;
	}
	
	if(document.frmInsertPrice.txtAfterDue.value.indexOf(" ")==0){
	alert("Enter a Valid After Due Date Price.");
	document.frmInsertPrice.txtAfterDue.focus();
	return false;
	}
	}
	
	
	if(document.frmInsertPrice.txtDepAmt.value!=""){	
	if(isnotIntegerChk(document.frmInsertPrice.txtDepAmt.value)){
	alert("Enter a valid Deposit Price.");
	document.frmInsertPrice.txtDepAmt.focus();
	return false;
	}
				
	if(isnotAlphaNumeric(document.frmInsertPrice.txtDepAmt.value)){
	alert("Enter a valid Deposit Price.");
	document.frmInsertPrice.txtDepAmt.focus();
	return false;
	}
	
	if(document.frmInsertPrice.txtDepAmt.value.indexOf('.')!=-1){
	alert("Enter a Valid Deposit Price.");
	document.frmInsertPrice.txtDepAmt.focus();
	return false;
	}
	
	if(document.frmInsertPrice.txtDepAmt.value.indexOf(" ")==0){
	alert("Enter a Valid Deposit Price.");
	document.frmInsertPrice.txtDepAmt.focus();
	return false;
	}
	}
return true;
}
