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
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
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

function onvalidate(){


stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
stringCheck3=" ";

//---------------------------------------Event Title ----------------------------------

if(document.frmOECAreaChairEdit.txtEventTitle.value==""){
alert(" Event Title cannot be empty");
document.frmOECAreaChairEdit.txtEventTitle.focus();
return false; 
}
if(document.frmOECAreaChairEdit.txtEventTitle.value.indexOf(' ')==0){
alert("Enter Event Title ");
document.frmOECAreaChairEdit.txtEventTitle.focus();
return false;
}

if(Dospace(document.frmOECAreaChairEdit.txtEventTitle.value)){
alert("Enter Valid Event Title ");
document.frmOECAreaChairEdit.txtEventTitle.focus();
return false;
}
if(document.frmOECAreaChairEdit.txtEventTitle.value.length>255){
 alert("Enter within 255 characters for Event Title" );
document.frmOECAreaChairEdit.txtEventTitle.focus();
return false; 
}
	  
	  
//--------------------------start Date and End Date-----------------------------------------
var compYear = document.frmOECAreaChairEdit.compYear.value;
//alert("compYear"+compYear);
if(document.frmOECAreaChairEdit.startDate1.value==""){
alert("Please Select the StartDate");
document.frmOECAreaChairEdit.startDate1.focus();
return false;
}
if(document.frmOECAreaChairEdit.endDate.value==""){
alert("Please Select the EndDate");
document.frmOECAreaChairEdit.endDate.focus();
return false;
}

var stDate = document.frmOECAreaChairEdit.startDate1.value;
var enDate = document.frmOECAreaChairEdit.endDate.value;
//alert("stDate"+stDate);
//alert("enDate"+enDate);
var sDate = new Date();
//alert(stDate.substring(0,2));
//alert(stDate.substring(3,5));
//alert(stDate.substring(6,10));

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
	document.frmOECAreaChairEdit.startDate1.focus();
	return false;
}
if(enDate.substring(6,10)!=compYear){
	alert("Enter valid End Date");
	document.frmOECAreaChairEdit.endDate.focus();
	return false;
}
if(stTime>enTime){
	alert("Select valid Event Start Date & End Date");
	document.frmOECAreaChairEdit.startDate1.focus();
	return false;
}

//----------------------------------Area Name and State Name------------------------
if(document.frmOECAreaChairEdit.selAreaId.selectedIndex==0){
alert("Please Select the area Name");
document.frmOECAreaChairEdit.selAreaId.focus();
return false;
}
if(document.frmOECAreaChairEdit.selStateId.selectedIndex==0){
alert("Please Select the State Name");
document.frmOECAreaChairEdit.selStateId.focus();
return false;
}

//===================================Entry Begin Date, Entry End Date and Extended Due Date=========================

if(document.frmOECAreaChairEdit.entryBeginDate.value=="" ){
alert("Please Select the Entry Reg. Begin Date");
document.frmOECAreaChairEdit.entryBeginDate.focus();
return false;
}
var evBegDate = new Date();
evBegDate.setMonth(document.frmOECAreaChairEdit.entryBeginDate.value.substring(0,2)-1);
evBegDate.setDate(document.frmOECAreaChairEdit.entryBeginDate.value.substring(3,5));
evBegDate.setYear(document.frmOECAreaChairEdit.entryBeginDate.value.substring(6,10));
var evBTime = evBegDate.getTime();
if(evBTime>stTime){
	alert("Enter valid Event Entry Begin Date");
	document.frmOECAreaChairEdit.entryBeginDate.focus();
	return false;
}
/*if(document.frmOECAreaChairEdit.entryBeginDate.value.substring(6,10)!=compYear){
	alert("Enter valid Event Entry Begin Date");
	document.frmOECAreaChairEdit.entryBeginDate.focus();
	return false;
}*/

if(document.frmOECAreaChairEdit.entryEndDate.value==""){
alert("Please Select the Entry Reg. End Date");
document.frmOECAreaChairEdit.entryEndDate.focus();
return false;
}
var evEndDate = new Date();
evEndDate.setMonth(document.frmOECAreaChairEdit.entryEndDate.value.substring(0,2)-1);
evEndDate.setDate(document.frmOECAreaChairEdit.entryEndDate.value.substring(3,5));
evEndDate.setYear(document.frmOECAreaChairEdit.entryEndDate.value.substring(6,10));
var evETime = evEndDate.getTime();
/*if(document.frmOECAreaChairEdit.entryEndDate.value.substring(6,10)!=compYear){
	alert("Enter valid Event Entry End Date");
	document.frmOECAreaChairEdit.entryEndDate.focus();
	return false;
}*/

if(evBTime>evETime){
	alert("Enter valid Event Entry Registration End Date");
	document.frmOECAreaChairEdit.entryEndDate.focus();
	return false;
}

if(document.frmOECAreaChairEdit.extDueDate.value!=""){

var extendDueDate = new Date();
extendDueDate.setMonth(document.frmOECAreaChairEdit.extDueDate.value.substring(0,2)-1);
extendDueDate.setDate(document.frmOECAreaChairEdit.extDueDate.value.substring(3,5));
extendDueDate.setYear(document.frmOECAreaChairEdit.extDueDate.value.substring(6,10));
var evExtendTime = extendDueDate.getTime();
/*if(document.frmOECAreaChairEdit.extDueDate.value.substring(6,10)!=compYear){
	alert("Enter valid Extended Due Date");
	document.frmOECAreaChairEdit.extDueDate.focus();
	return false;
}*/

if(evETime>evExtendTime){
	alert("Enter valid Extended Due Date");
	document.frmOECAreaChairEdit.extDueDate.focus();
	return false;
}
}

//----------------------------------Event Levels -----------------------------------
var levelSize1=document.getElementById('allTypesVect').value;
//alert("levelSize"+levelSize1);
var isChecked = false;	
for(var k=1; k<=levelSize1; k++){
	//alert(k);
	var areatemplevel = "txtEvent"+k;
if(document.getElementById(areatemplevel).checked){
			isChecked = true;
			

		}
}
//alert(isChecked);
if(isChecked==false){	
	alert("Please select Divisions");
	return false;
}	

//---------------------------Comments-----------------------------------------------
if(!(document.frmOECAreaChairEdit.txtACComm.value=="")){
if(document.frmOECAreaChairEdit.txtACComm.value.length>1024){
alert("Enter Comments within 1024 Characters");	 
document.frmOECAreaChairEdit.txtACComm.focus();
return false;
}
}
return true;	
}
