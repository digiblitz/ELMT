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
//------------------------ALPHA NUMERIC------------------------------------------------------------------


function isnotAlphaNumeric(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'"+"\"";
f3=1;
for(j=0;j<str.length;j++){
	if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1){
	  f3=0;
	  }
	  }
    if(f3==0){
return true;
}else{
return false;
}
}

//------------------------DOSPACE--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
//------------------------ISNOT ALPHA-------------------------------------------------------------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=-~.,`0123456789;:][{}"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//-----------------------------------is not alpha1----------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~`;:"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//------------------------ISNOT ALPHA city-------------------------------------------------------------------------
function isnotAlphaCity(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//-------------------------function for Special Character
function isnotSpecial(str){
stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
f4=1;
for(j=0;j<str.length;j++)
{if(stringSpecialCheck.indexOf(str.charAt(j))!=-1)
{f4=0;}}
if(f4==0)
{return true;}else{return false;}
}
//------------------------------------------------------------------------------------
//---------------------------form validation js---------------------------------------

function myvalidate(){


stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
stringCheck3=" ";

//---------------------------------------Event Title ----------------------------------

if(document.frmOECOrganizerEdit.txtEventTitle.value==""){
alert(" Event Title cannot be empty");
document.frmOECOrganizerEdit.txtEventTitle.focus();
return false; 
}
if(document.frmOECOrganizerEdit.txtEventTitle.value.indexOf(' ')==0){
alert("Enter Event Title ");
document.frmOECOrganizerEdit.txtEventTitle.focus();
return false;
}
/*if(isnotAlphaNumeric(document.frmOECOrganizerEdit.txtEventTitle.value)){
alert("Enter Valid Event Title ");
document.frmOECOrganizerEdit.txtEventTitle.focus();
return false;
}*/
if(Dospace(document.frmOECOrganizerEdit.txtEventTitle.value)){
alert("Enter Valid Event Title ");
document.frmOECOrganizerEdit.txtEventTitle.focus();
return false;
}
if( document.frmOECOrganizerEdit.txtEventTitle.value.length>255){
 alert("Enter within 255 characters for Event Title" );
document.frmOECOrganizerEdit.txtEventTitle.focus();
return false; 
}
	  
	  
//--------------------------start Date and End Date-----------------------------------------
var compYear = document.frmOECOrganizerEdit.compYear.value;
//alert("compYear"+compYear);
if(document.frmOECOrganizerEdit.startDate1.value=="" ){
alert("Please Select the StartDate");
document.frmOECOrganizerEdit.startDate1.focus();
return false;
}
if(document.frmOECOrganizerEdit.endDate.value==""){
alert("Please Select the EndDate");
document.frmOECOrganizerEdit.endDate.focus();
return false;
}

var stDate = document.frmOECOrganizerEdit.startDate1.value;
var enDate = document.frmOECOrganizerEdit.endDate.value;
var sDate = new Date();

sDate.setMonth(stDate.substring(0,2)-1);
sDate.setDate(stDate.substring(3,5));
sDate.setYear(stDate.substring(6,10));

var eDate = new Date();
eDate.setMonth(enDate.substring(0,2)-1);
eDate.setDate(enDate.substring(3,5));
eDate.setYear(enDate.substring(6,10));

var stTime = sDate.getTime();
var enTime = eDate.getTime();

if(stDate.substring(6,10)!=compYear){
	alert("Enter valid Start Date");
	document.frmOECOrganizerEdit.startDate1.focus();
	return false;
}
if(enDate.substring(6,10)!=compYear){
	alert("Enter valid End Date");
	document.frmOECOrganizerEdit.endDate.focus();
	return false;
}
if(stTime>enTime){
	alert("Select valid Event Start Date & End Date");
	document.frmOECOrganizerEdit.startDate1.focus();
	return false;
}

//===================================Entry Begin Date, Entry End Date and Extended Due Date=========================

if(document.frmOECOrganizerEdit.entryBeginDate.value=="" ){
alert("Please Select the Entry Reg. Begin Date");
document.frmOECOrganizerEdit.entryBeginDate.focus();
return false;
}
var evBegDate = new Date();
evBegDate.setMonth(document.frmOECOrganizerEdit.entryBeginDate.value.substring(0,2)-1);
evBegDate.setDate(document.frmOECOrganizerEdit.entryBeginDate.value.substring(3,5));
evBegDate.setYear(document.frmOECOrganizerEdit.entryBeginDate.value.substring(6,10));
var evBTime = evBegDate.getTime();
if(evBTime>stTime){
	alert("Enter valid Event Entry Begin Date");
	document.frmOECOrganizerEdit.entryBeginDate.focus();
	return false;
}
/*if(document.frmOECOrganizerEdit.entryBeginDate.value.substring(6,10)!=compYear){
	alert("Enter valid Event Entry Begin Date");
	document.frmOECOrganizerEdit.entryBeginDate.focus();
	return false;
}*/

if(document.frmOECOrganizerEdit.entryEndDate.value==""){
alert("Please Select the Entry Reg. End Date");
document.frmOECOrganizerEdit.entryEndDate.focus();
return false;
}
var evEndDate = new Date();
evEndDate.setMonth(document.frmOECOrganizerEdit.entryEndDate.value.substring(0,2)-1);
evEndDate.setDate(document.frmOECOrganizerEdit.entryEndDate.value.substring(3,5));
evEndDate.setYear(document.frmOECOrganizerEdit.entryEndDate.value.substring(6,10));
var evETime = evEndDate.getTime();
/*if(document.frmOECOrganizerEdit.entryEndDate.value.substring(6,10)!=compYear){
	alert("Enter valid Event Entry End Date");
	document.frmOECOrganizerEdit.entryEndDate.focus();
	return false;
}*/

if(evBTime>evETime){
	alert("Enter valid Event Entry Registration End Date");
	document.frmOECOrganizerEdit.entryEndDate.focus();
	return false;
}

if(document.frmOECOrganizerEdit.extDueDate.value!=""){

var extendDueDate = new Date();
extendDueDate.setMonth(document.frmOECOrganizerEdit.extDueDate.value.substring(0,2)-1);
extendDueDate.setDate(document.frmOECOrganizerEdit.extDueDate.value.substring(3,5));
extendDueDate.setYear(document.frmOECOrganizerEdit.extDueDate.value.substring(6,10));
var evExtendTime = extendDueDate.getTime();
/*if(document.frmOECOrganizerEdit.extDueDate.value.substring(6,10)!=compYear){
	alert("Enter valid Extended Due Date");
	document.frmOECOrganizerEdit.extDueDate.focus();
	return false;
}
*/
if(evETime>evExtendTime){
	alert("Enter valid Extended Due Date");
	document.frmOECOrganizerEdit.extDueDate.focus();
	return false;
}
}
//----------------------------------Area Name and State Name------------------------
if(document.frmOECOrganizerEdit.selAreaId.selectedIndex==0){
alert("Please Select the area Name");
document.frmOECOrganizerEdit.selAreaId.focus();
return false;
}
if(document.frmOECOrganizerEdit.selStateId.selectedIndex==0){
alert("Please Select the State Name");
document.frmOECOrganizerEdit.selStateId.focus();
return false;
}
//----------------------------------Event Levels -----------------------------------
 
/*chosen="";
len = document.frmOECOrganizerEdit.txtEvent.length ;
for(i=0;i<len;i++){
if(document.frmOECOrganizerEdit.txtEvent[i].checked){
chosen= document.frmOECOrganizerEdit.txtEvent[i].value;
}
}
if(chosen==""){
alert("Please select Divisions");
return false;
}*/

var levelSize=document.getElementById('allTypesVect').value;
//alert("levelSize"+levelSize);
var isChecked = false;	
for(var k=1; k<=levelSize; k++){
	var templevel = "txtEvent"+k;

if(document.getElementById(templevel).checked){
			isChecked = true;
		}
}
if(isChecked==false){
	alert("Please select Divisions");
	return false;
}	
		
//---------------------------Comments-----------------------------------------------
if(!(document.frmOECOrganizerEdit.txtOrgComm.value=="")){	
if(document.frmOECOrganizerEdit.txtOrgComm.value.length>1024){
alert("Enter Comments within 1024 Characters");	 
document.frmOECOrganizerEdit.txtOrgComm.focus();
return false;
}
}
return true;	
}
