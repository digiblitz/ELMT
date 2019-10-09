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
function isnotIntegerChk(str){
stringIntCheck="0123456789 ";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}
function Dospace(str){
	if(str.indexOf("  ")!=-1 || str.indexOf(' ')==0)
	return true;
	else return false;
}

/*function clearMemberID(){
	document.frmUserSignup.chsUserName.value="";
}
function clearUserDet(){
	document.frmUserSignup.fname.value="";
	document.frmUserSignup.lname.value="";
}*/
	
function myvalidate(){
// function for triming the value
String.prototype.trim = function() {
return this.replace(/^\s+|\s+$/g,"");
}
	var memId=document.frmUserSignup.chsUserName.value;
	var firstName=document.frmUserSignup.fname.value;
	var lastName=document.frmUserSignup.lname.value;
	
  if(memId.trim()=="" && firstName.trim()=="" && lastName.trim()=="" ){
	alert("Please enter your HLC ID or First and Last Name" );
	//document.frmUserSignup.chsUserName.focus();
	return false;
	}
	if(isnotIntegerChk(document.frmUserSignup.chsUserName.value)){
	alert("Enter a valid HLC Member ID ");
	document.frmUserSignup.chsUserName.focus();
	return false;
	}
	/*if(Dospace(document.frmUserSignup.chsUserName.value)){
	alert("Enter a valid HLC Member ID 2");
	document.frmUserSignup.chsUserName.focus();
	return false;
	}*/
	/*if(document.frmUserSignup.chsUserName.value.indexOf(' ')==0){
	alert("Enter a valid HLC Member ID 2 ");
	document.frmMembRegi.chsUserName.focus();
	return false;
	}*/
	/*if(document.frmUserSignup.lname.value!="")
	{
	if(Dospace(document.frmUserSignup.lname.value)){
	alert("Enter valid Last Name");
	document.frmUserSignup.lname.focus();
	return false;
	}
	}*/
	if(firstName.trim()!="")
	{
	/*if(Dospace(document.frmUserSignup.fname.value)){
	alert("Enter a valid First Name");
	document.frmUserSignup.fname.focus();
	return false;|| Dospace(document.frmUserSignup.lname.value)
	}*/
		
	if(lastName.trim()==""){
	alert("Enter Last Name");
	document.frmUserSignup.lname.focus();
	return false;
	}
	}
		
return true;
}


//------------------------------ Member Registration Member Id Existance Validate Ajax Script -------------------------

var arHttpRequest;

function HLCMemberDetails()
{
	
	if(document.frmUserSignup.membid.value!="")
	{
    var memberid=document.frmUserSignup.membid.value;

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
					document.frmUserSignup.membid.value="";
					document.frmUserSignup.membid.focus();
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

