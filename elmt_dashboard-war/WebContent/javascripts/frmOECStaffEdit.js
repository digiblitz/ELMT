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

function showHideRadio(radioNam,divId,radVal){
		if(document.forms['frmOECStaffEdit'].elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function clearMemberdetails(){
	document.frmOECStaffEdit.ownerUseaNo2_id.value="";
	
}

function clearUserdetails(){
	document.frmOECStaffEdit.userNameId1.value="";
	
}



function orgMemberDetails()
{
	//alert(document.frmOECStaffEdit.ownerUseaNo2_id.value);
	if(document.getElementById("orgnaizerId").value!="")
	{
    var memberid=document.getElementById("orgnaizerId").value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {membStatus(); } ; 
        arHttpRequest.send(null); 
   } 
	}


 /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function membStatus() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
                if(arnameText=="false")
                {    
					alert("Member Id Doesn't Exists!"); 
					document.getElementById("orgnaizerId").value="";
					document.getElementById("orgnaizerId").focus();
					return false;
                }
				/*else    
				{
					famMemberStatus();
				}*/

            } 
            else 
            { 
                alert("Error loading page\n"+ arHttpRequest.status +":"+ arHttpRequest.statusText); 
            } 
        } 
    } 





//------------------------------------------------------------------------------------
//---------------------------form validation js---------------------------------------

function myvalidate(){


stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
stringCheck3=" ";

//---------------------------------------Event Title ----------------------------------

if(document.frmOECStaffEdit.txtEventTitle.value==""){
alert(" Event Title cannot be empty");
document.frmOECStaffEdit.txtEventTitle.focus();
return false; 
}
if(document.frmOECStaffEdit.txtEventTitle.value.indexOf(' ')==0){
alert("Enter Event Title ");
document.frmOECStaffEdit.txtEventTitle.focus();
return false;
}

if(Dospace(document.frmOECStaffEdit.txtEventTitle.value)){
alert("Enter Valid Event Title ");
document.frmOECStaffEdit.txtEventTitle.focus();
return false;
}
if( document.frmOECStaffEdit.txtEventTitle.value.length>255){
 alert("Enter within 255 characters for Event Title" );
document.frmOECStaffEdit.txtEventTitle.focus();
return false; 
}
	  
	  
//--------------------------start Date and End Date-----------------------------------------
var compYear = document.frmOECStaffEdit.compYear.value;
//alert("compYear"+compYear);
if(document.frmOECStaffEdit.startDate1.value==""){
alert("Please Select the StartDate");
document.frmOECStaffEdit.startDate1.focus();
return false;
}
if(document.frmOECStaffEdit.endDate.value==""){
alert("Please Select the EndDate");
document.frmOECStaffEdit.endDate.focus();
return false;
}

var stDate = document.frmOECStaffEdit.startDate1.value;
var enDate = document.frmOECStaffEdit.endDate.value;
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
	document.frmOECStaffEdit.startDate1.focus();
	return false;
}
if(enDate.substring(6,10)!=compYear){
	alert("Enter valid End Date");
	document.frmOECStaffEdit.endDate.focus();
	return false;
}
if(stTime>enTime){
	alert("Select valid Event Start Date & End Date");
	document.frmOECStaffEdit.startDate1.focus();
	return false;
}

//----------------------------------Area Name and State Name------------------------
if(document.frmOECStaffEdit.selAreaId.selectedIndex==0){
alert("Please Select the area Name");
document.frmOECStaffEdit.selAreaId.focus();
return false;
}
if(document.frmOECStaffEdit.selStateId.selectedIndex==0){
alert("Please Select the State Name");
document.frmOECStaffEdit.selStateId.focus();
return false;
}
if(document.frmOECStaffEdit.selIssue.selectedIndex==0){
alert("Please Select the Omnibus Season Name");
document.frmOECStaffEdit.selIssue.focus();
return false;
}
//===================================Entry Begin Date, Entry End Date and Extended Due Date=========================

if(document.frmOECStaffEdit.entryBeginDate.value=="" ){
alert("Please Select the Entry Reg. Begin Date");
document.frmOECStaffEdit.entryBeginDate.focus();
return false;
}
var evBegDate = new Date();
evBegDate.setMonth(document.frmOECStaffEdit.entryBeginDate.value.substring(0,2)-1);
evBegDate.setDate(document.frmOECStaffEdit.entryBeginDate.value.substring(3,5));
evBegDate.setYear(document.frmOECStaffEdit.entryBeginDate.value.substring(6,10));
var evBTime = evBegDate.getTime();
if(evBTime>stTime){
	alert("Enter valid Event Entry Begin Date");
	document.frmOECStaffEdit.entryBeginDate.focus();
	return false;
}
/*if(document.frmOECStaffEdit.entryBeginDate.value.substring(6,10)!=compYear){
	alert("Enter valid Event Entry Begin Date");
	document.frmOECStaffEdit.entryBeginDate.focus();
	return false;
}*/

if(document.frmOECStaffEdit.entryEndDate.value==""){
alert("Please Select the Entry Reg. End Date");
document.frmOECStaffEdit.entryEndDate.focus();
return false;
}
var evEndDate = new Date();
evEndDate.setMonth(document.frmOECStaffEdit.entryEndDate.value.substring(0,2)-1);
evEndDate.setDate(document.frmOECStaffEdit.entryEndDate.value.substring(3,5));
evEndDate.setYear(document.frmOECStaffEdit.entryEndDate.value.substring(6,10));
var evETime = evEndDate.getTime();
/*if(document.frmOECStaffEdit.entryEndDate.value.substring(6,10)!=compYear){
	alert("Enter valid Event Entry End Date");
	document.frmOECStaffEdit.entryEndDate.focus();
	return false;
}*/

if(evBTime>evETime){
	alert("Enter valid Event Entry Registration End Date");
	document.frmOECStaffEdit.entryEndDate.focus();
	return false;
}

if(document.frmOECStaffEdit.extDueDate.value!=""){

var extendDueDate = new Date();
extendDueDate.setMonth(document.frmOECStaffEdit.extDueDate.value.substring(0,2)-1);
extendDueDate.setDate(document.frmOECStaffEdit.extDueDate.value.substring(3,5));
extendDueDate.setYear(document.frmOECStaffEdit.extDueDate.value.substring(6,10));
var evExtendTime = extendDueDate.getTime();
/*if(document.frmOECStaffEdit.extDueDate.value.substring(6,10)!=compYear){
	alert("Enter valid Extended Due Date");
	document.frmOECStaffEdit.extDueDate.focus();
	return false;
}*/

if(evETime>evExtendTime){
	alert("Enter valid Extended Due Date");
	document.frmOECStaffEdit.extDueDate.focus();
	return false;
}
}

//----------------------------------Event Levels -----------------------------------
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
if(!(document.frmOECStaffEdit.txtStaffComm.value=="")){
if(document.frmOECStaffEdit.txtStaffComm.value.length>1024){
alert("Enter Comments within 1024 Characters");	 
document.frmOECStaffEdit.txtStaffComm.focus();
return false;
}
}
return true;	
}
