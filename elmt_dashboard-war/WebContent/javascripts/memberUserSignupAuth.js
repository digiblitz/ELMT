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
//--------------------------------function for Valid Email
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
	f4=1;
	for(j=0;j<str.length;j++){
		if(stringSpecialCheck.indexOf(str.charAt(j))!=-1){
			f4=0;
		}
	}
	if(f4==0){
		return true;
	}
	else{
		return false;
	}
}
function isnotVlaidEmail(str){
			 strmail=str;
			 firstat = strmail.indexOf("@");
			 lastat = strmail.lastIndexOf("@");
			 strmain=strmail.substring(0,firstat);
			 strsub=strmail.substring(firstat,str.length);
			 flag=false;
			 if(strmail.length>120)
			 { flag=true;//alert("1");
			 }
			 /*if(strmain.indexOf('  ')!=-1 || firstat==0 || firstat!=lastat || strsub.indexOf(' ')!=-1 )
			 {flag=true;}*/

			stringMailCheck1="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'"+"\"";
			f3=1;
			for(j=0;j<strsub.length;j++)
			{if(stringMailCheck1.indexOf(strsub.charAt(j))!=-1)
			{f3=0;}}
			if(f3==0)
			{flag=true;}

			stringMailCheck2="!@#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'"+"\"";
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
			 if(k>3)
			 { flag=true;}
			 /*if(firstat==-1 || lastat==-1)
			 {flag=true;}*/
			 if(Number(strmain))
			 {flag=true;}
			 if(firstat != lastat )
			 {flag=true;}
			 firstdot=strmail.indexOf(".",firstat);
			 lastdot=strmail.lastIndexOf(".");
			 if(lastdot==strmail.length-1)
			 {flag=true;}
			
			return flag;
}
//-----------------------------------------------------------------------------------------------

function isnotAlpha1(str){
stringCheck="!@#$%^&+*|<>?/=~`;:"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//-----------------------------function for zipcode--------
function isnotZipcode(str){
	stringZip="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ- ";
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


//-----------------------function for pure Integer Numbers
function isnotInteger(str){
stringIntCheck="0123456789 ";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}
//------------------------------------------------------------------------------
function isnotPhone(str){
stringIntCheck="0123456789()+- ";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}
//------------------------------------------------------------------------------
function Dospace(str){
	if(str.indexOf("  ")!=-1 || str.indexOf(' ')==0)
	return true;
	else return false;
}
//---------------------------------------------------------------------------------
function myvalidate(){
// function for triming the value
String.prototype.trim = function() {
return this.replace(/^\s+|\s+$/g,"");
}	
var flagcount=0;
var phone=document.myform.phone.value;
var email=document.myform.email.value;
var zip=document.myform.zip.value;
if(phone.trim()==""){
	flagcount += 1;
}
if(email.trim()==""){
	flagcount += 1;
}
if(zip.trim()==""){
	flagcount +=1;
}
/*
if(document.myform.membStat.value=="member"){
	if(document.myform.chsUserName.value==""){
		flagcount +=1;
	}
	if(Dospace(document.myform.chsUserName.value)||isnotInteger(document.myform.chsUserName.value)){
	alert("Enter a vlaid HLC Member ID");
	document.myform.chsUserName.focus();
	return false;	
	}
}
//alert(flagcount);

if((flagcount >2 || flagcount==2) && document.myform.membStat.value=="user"){
	alert("Enter any of the two of your Details to validate");
	return false;
}
*/
//alert(flagcount);

if(flagcount >1 ){
	alert("Enter any of the two of your Details to validate");
	return false;
}

if(isnotPhone(phone.trim())){
	alert("Enter a valid Phone number");
	document.myform.phone.focus();
	return false;
}
if(isnotAlpha1(phone.trim()))
{
	alert("Enter a Valid Phone Number");
    document.myform.phone.focus();
    return false;
}

if(!(email.trim()== "")){
			strmail=email.trim();
			firstat = strmail.indexOf("@");
			lastat = strmail.lastIndexOf("@");
			strmain=strmail.substring(0,firstat);
			strsub=strmail.substring(firstat,email.trim().length);
			if(strmail.length>120){
				alert("Email is out of range");
				document.myform.email.focus();
				return false;
			}
			/*if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
				alert("Enter valid Email ");
				document.myform.email.focus();
				return false;
			}*/
			if(isnotSpecial(strmain) || isnotSpecial(strsub)){
				alert("Enter valid Email ");
				document.myform.email.focus();
				return false;
			}
			k=0;
			strlen=strsub.length;
			for(i=0;i<strlen-1;i++){
				if(strsub.charAt(i)=='.'){
					k=k+1;
				}
			}
			if(k>3){
				alert("Enter valid Email ");
				document.myform.email.focus();
				return false;
			}
			/*if(firstat==-1 || lastat==-1){
				alert("Enter valid Email " );
				document.myform.email.focus();
				return false;
			}*/
			if(Number(strmain)){
				alert("Enter valid Email ");
				document.myform.email.focus();
				return false;
			}
			if(firstat != lastat ){
				alert("Enter valid Email ");
				document.myform.email.focus();
				return false;
			}
			firstdot=strmail.indexOf(".",firstat);
			lastdot=strmail.lastIndexOf(".");
			/*if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
				alert("Enter valid Email ");
				document.myform.email.focus();
				return false;
			}*/
		}
/*if(Dospace(zip.trim())){
	alert("Enter a valid Zipcode");
	document.myform.zip.focus();
	return false;
}*/	   
//alert(isnotZipcode(zip.trim()));
if(zip.trim()!=""){
 if(isnotZipcode(zip.trim())){
	alert("Enter a valid Zipcode");
	document.myform.zip.focus();
	return false;
	}
}
}
