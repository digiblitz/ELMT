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
// JavaScript Document: memberid-only Numeric
//---------------------------------------------------------------------------------------
//-----------------------function for pure Integer Numbers
var str="";
function isnotInteger(str){
stringIntCheck="0123456789";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}
//---------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
//-------------------function for character validation--------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=-~.,`0123456789;:][{}"+"\\"+"\'";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//---------------------------------------------------------------------------------------	
//------------------------------------------------------------------------------------------
//------------------------function for Alpha Numeric
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
//----------------------------------------------------------------------------------------------------
//--------------------------------function for Valid Email
function isnotVlaidEmail(str){
			 strmail=str;
			 firstat = strmail.indexOf("@");
			 lastat = strmail.lastIndexOf("@");
			 strmain=strmail.substring(0,firstat);
			 strsub=strmail.substring(firstat,str.length);
			 flag=false;
			 if(strmail.length>120)
			 { flag=true;alert("1");}
			 if(strmain.indexOf('  ')!=-1 || firstat==0 || firstat!=lastat || strsub.indexOf(' ')!=-1 )
			 {flag=true;}

			stringMailCheck1="!#$%^&*()_+|<>?/=-~,;:][{}"+"\\"+"\'"+"\"";
			f3=1;
			for(j=0;j<strsub.length;j++)
			{if(stringMailCheck1.indexOf(strsub.charAt(j))!=-1)
			{f3=0;}}
			if(f3==0)
			{flag=true;}

			stringMailCheck2="!@#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'"+"\"";
			f4=1;
			for(j=0;j<strmain.length;j++)
			{if(stringMailCheck2.indexOf(strmain.charAt(j))!=-1)
			{f4=0;}}
			if(f4==0)
			{flag=true;}
			
			 k=0;
			 strlen=strsub.length;
			 for(i=0;i<strlen-1;i++)
			 { if(strsub.charAt(i)=='.')
			 {k=k+1;}}
			 if(k>2)
			 { flag=true;}
			 if(firstat==-1 || lastat==-1)
			 {flag=true;}
			 if(Number(strmain))
			 {flag=true;}
			 if(firstat != lastat )
			 {flag=true;}
			 firstdot=strmail.indexOf(".",firstat);
			 lastdot=strmail.lastIndexOf(".");
			 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
			 {flag=true;}
			
			return flag;
}
//--------------------------------------------------------------------------------
//-------------function for doublespace validation -----------------------------

function Dospace(str){
if(str.indexOf("  ")!=-1 || str.indexOf(' ')==0)
{return true;}
else {return false;}
}
//---------------------------------------------------------------------------
//-----------------------------function for zipcode--------
function isnotZipcode(str){
	stringZip="0123456789";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(stringZip.indexOf(str.charAt(j))!=-1){
			fzip=0;
		}
	}
	if(fzip==0){
		return false;
	}
	else{
		return true;
	}
}
//------------------------------------------------------------------------------------------
function isnotDate(str){
	maxdays=0;
	 todaysDate=new Date();
	 nowDate=todaysDate.getDate();
	 nowYear=todaysDate.getYear();
	 nowMonth1=todaysDate.getMonth();
	 dateflag=false;
	 nowMonth=1+nowMonth1;
	if(nowDate<10)
	{nowDate="0"+nowDate;}
	if(nowMonth<10)
	{nowMonth="0"+nowMonth;}
	
strdate=str;
mm = Number(strdate.substring(0,2));
dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{dateflag=true;}
 if(yyyy<nowYear || yyyy>2100)
{dateflag=true;}
switch (mm) {
case 1:
case 3:
case 5:
case 7:
case 8:
case 10:
case 12:
maxdays=31;
break;
case 4:
case 6:
case 9:
case 11:
maxdays=30;
break;
case 2:
maxdays=(yyyy%4==0?29:28);
default:
maxdays=0;} 
if((yyyy==nowYear)&&(mm<nowMonth))
{dateflag=true;}
if(dd>maxdays || dd<1)
{ dateflag=true;}
 if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear))
{dateflag=true;}
return dateflag;
}
//-----------------------------------------------------------------------------------------------
function displayButton(){
if(document.frmUserSignup.chsUserName.value.length >=4 )
{document.frmUserSignup.chkAvail_id.disabled=false;}
else
{document.frmUserSignup.chkAvail_id.disabled=true;}
}
 //-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
function myvalidate(){
	/*
//--------------------for HLC Member Id	
	if(document.frmUserSignup.HLCmemId.value=="")
	{ alert("Enter HLC member Id");
	 document.frmUserSignup.HLCmemId.focus();
	 return false;
	 }
	 if(isnotInteger(document.frmUserSignup.HLCmemId.value)||document.frmUserSignup.HLCmemId.value.length>25)
	 {alert("Enter a valid HLC number");
	  document.frmUserSignup.HLCmemId.focus();
	  return false;
	 }

//-------------------for First Name----
if(document.frmUserSignup.fname.value=="")
{ alert("Enter a First Name");
 document.frmUserSignup.fname.focus();
 return false;}
 if(isnotAlpha(document.frmUserSignup.fname.value)|| document.frmUserSignup.fname.value.length > 80||Dospace(document.frmUserSignup.fname.value))
 {alert("Enter a valid First Name");
  document.frmUserSignup.fname.focus();
 return false;}
 //-------------------for Last Name----
if(document.frmUserSignup.lname.value=="")
{ alert("Enter a Last Name");
 document.frmUserSignup.lname.focus();
 return false;}
 if(isnotAlpha(document.frmUserSignup.lname.value)|| document.frmUserSignup.lname.value.length > 80||Dospace(document.frmUserSignup.lname.value))
 {alert("Enter a valid Last Name");
  document.frmUserSignup.lname.focus();
 return false;}
//--------------------------------------------------
//--------for Date of Birth

if(document.frmUserSignup.date.value=="")
 { alert("Enter Date of Birth");
  document.frmUserSignup.date.focus();
  return false;}

if(isnotDate(document.frmUserSignup.date.value))
{ alert("Enter valid Date of Birth");
  document.frmUserSignup.date.focus();
  return false;}

//----------------------for Email ----
if(document.frmUserSignup.email.value=="")
{ alert("Enter a Email");
 document.frmUserSignup.email.focus();
 return false;}
 if(isnotVlaidEmail(document.frmUserSignup.email.value)||Dospace(document.frmUserSignup.email.value))
 {alert("Enter a valid Email");
  document.frmUserSignup.email.focus();
 return false;}
//------------------------for Zipcode----------------
 if(document.frmUserSignup.zipcode.value=="")
 { alert("Enter a zipcode");
   document.frmUserSignup.zipcode.focus();
   return false;
 }
 if(isnotZipcode(document.frmUserSignup.zipcode.value)||document.frmUserSignup.zipcode.value.length<2||document.frmUserSignup.zipcode.value.length>20)
 {alert("Enter a valid zipcode");
   document.frmUserSignup.zipcode.focus();
   return false;
 }*/
if(document.frmUserSignup.chsUserName.value=="")
{ alert("Enter a User Name");
 document.frmUserSignup.chsUserName.focus();
 return false;}
 
if(document.frmUserSignup.chsUserName.value.indexOf(" ")==0)
{ alert("Enter a valid User Name");
 document.frmUserSignup.chsUserName.focus();
 return false;}

if(isnotAlphaNumeric(document.frmUserSignup.chsUserName.value)|| document.frmUserSignup.chsUserName.value.length > 25 || document.frmUserSignup.chsUserName.value.length < 4 )
 {alert("Enter a valid User Name");
  document.frmUserSignup.chsUserName.focus();
 return false;}
//---------------------for Password---
if(document.frmUserSignup.passwrd.value=="")
{ alert("Enter a Password");
 document.frmUserSignup.passwrd.focus();
 return false;}

if(document.frmUserSignup.passwrd.value.indexOf(" ")==0)
{ alert("Enter a valid Password");
 document.frmUserSignup.passwrd.focus();
 return false;}

 if(document.frmUserSignup.passwrd.value.length>12 || document.frmUserSignup.passwrd.value.length<6)
 {alert("Enter a valid Password");
  document.frmUserSignup.passwrd.focus();
 return false;}
//------------------------for retype Password--
if(document.frmUserSignup.repasswrd.value=="")
{alert("Retype Password");
 document.frmUserSignup.repasswrd.focus();
 return false;}

if(document.frmUserSignup.passwrd.value!=document.frmUserSignup.repasswrd.value)
{alert("Password Mismatch");
 document.frmUserSignup.repasswrd.focus();
 return false;}
//----------------------------------------------------
return true;
}
