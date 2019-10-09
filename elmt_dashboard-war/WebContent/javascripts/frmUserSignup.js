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
//-------------------function for character validation in Names-------------------
function isnotName(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}


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
//-----------------------------------------------------------------------------------------------
function displayButton(){
if(document.frmUserSignup.chsUserName.value.length >=4 )
{document.frmUserSignup.chkAvail_id.disabled=false;}
else
{document.frmUserSignup.chkAvail_id.disabled=true;}
}
//-----------------------------------------------------------------------------------------------


function myvalidate(){

//-------------------for User Name----
//alert(document.frmUserSignup.chsUserName.value);
if(document.frmUserSignup.chsUserName.value=="")
{ alert("Enter a User Name");
 document.frmUserSignup.chsUserName.focus();
 return false;}

if(document.frmUserSignup.chsUserName.value.indexOf(" ")==0)
{ alert("Enter a Valid User Name");
 document.frmUserSignup.chsUserName.focus();
 return false;}
 

if(isnotAlphaNumeric(document.frmUserSignup.chsUserName.value)|| document.frmUserSignup.chsUserName.value.length > 25 || document.frmUserSignup.chsUserName.value.length < 4 )
 {alert("Enter a valid User Name");
  document.frmUserSignup.chsUserName.focus();
 return false;}
 

				 
//-------------------for First Name----
if(document.frmUserSignup.fname.value=="")
{ alert("Enter a First Name");
 document.frmUserSignup.fname.focus();
 return false;}
if(document.frmUserSignup.fname.value.indexOf(" ")==0)
 {alert("Enter a valid First Name");
  document.frmUserSignup.fname.focus();
 return false;}
 if(isnotName(document.frmUserSignup.fname.value))
 {alert("Enter a valid First Name");
  document.frmUserSignup.fname.focus();
 return false;}
 if(document.frmUserSignup.fname.value.length > 80)
 {alert("First Name should be less than 80 characters");
  document.frmUserSignup.fname.focus();
 return false;}
 //-------------------for Last Name----
if(document.frmUserSignup.lname.value=="")
{ alert("Enter a Last Name");
 document.frmUserSignup.lname.focus();
 return false;}
if(document.frmUserSignup.lname.value.indexOf(" ")==0)
{ alert("Enter a valid Last Name");
 document.frmUserSignup.lname.focus();
 return false;}
 if(isnotName(document.frmUserSignup.lname.value))
 {alert("Enter a valid Last Name");
  document.frmUserSignup.lname.focus();
 return false;}
 if(document.frmUserSignup.lname.value.length > 80)
 {alert("Last Name should be less than 80 characters");
  document.frmUserSignup.lname.focus();
 return false;}

//---------------------for Password---
/*if(document.frmUserSignup.pass.value=="")
{ alert("Enter a Password");
 document.frmUserSignup.pass.focus();
 return false;}
 if(document.frmUserSignup.pass.value.length>12 || document.frmUserSignup.pass.value.length<6)
 {alert("Enter a valid Password");
  document.frmUserSignup.pass.focus();
 return false;}
//------------------------for retype Password--
if(document.frmUserSignup.pass.value!=document.frmUserSignup.repass.value)
{alert("Password Mismatch");
 document.frmUserSignup.repass.focus();
 return false;}*/
//-----------------------for Date of Birth--
//___________________________________________DATE________________________________________________________
if(document.frmUserSignup.birthmonth.value=="")
{alert(" Select Month in Date of Birth ");
 document.frmUserSignup.birthmonth.focus();
 return false;}
 if(!(document.frmUserSignup.birthmonth.value==""))
 { leno=document.frmUserSignup.birthmonth.length;
  for(i=0;i<leno;i++)
  { if(document.frmUserSignup.birthmonth[i].selected)
 { choseno=document.frmUserSignup.birthmonth[i].value ;}}}
 
 //------month checking----
 var lyear=document.frmUserSignup.birthyear.value;
 var lcheck=(lyear%4);

if((document.frmUserSignup.birthmonth.selectedIndex== 2)&&(document.frmUserSignup.birthday.value >29)&&(lcheck=="0"))
 {
	alert ("Date is not valid") ;
	document.frmUserSignup.birthday.focus();
	return false;
 }
var lyear1=document.frmUserSignup.birthyear.value;
 var lcheck1=(lyear1%4);
 
 if((document.frmUserSignup.birthmonth.selectedIndex== 2)&&(document.frmUserSignup.birthday.value >28)&&(lcheck1>0))
 {
	alert ("Date is not valid") ;
	document.frmUserSignup.birthday.focus();
	return false;
 }
 if((document.frmUserSignup.birthmonth.selectedIndex== 4)&&(document.frmUserSignup.birthday.value=="31"))

 {
	alert ("Date is not valid") ;
	document.frmUserSignup.birthday.focus();
	return false;
 }
 if((document.frmUserSignup.birthmonth.selectedIndex== 6)&&(document.frmUserSignup.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmUserSignup.birthday.focus();
	return false;
 }
 if((document.frmUserSignup.birthmonth.selectedIndex== 9)&&(document.frmUserSignup.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmUserSignup.birthday.focus();
	return false;
 }
 if((document.frmUserSignup.birthmonth.selectedIndex== 11)&&(document.frmUserSignup.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmUserSignup.birthday.focus();
	return false;
 }


 if(document.frmUserSignup.birthday.value=="")
 {alert(" Select Day in Date of Birth");
  document.frmUserSignup.birthday.focus();
  return false;}
  if(!(document.frmUserSignup.birthday.value==""))
  { leno=document.frmUserSignup.birthday.length;
   for(i=0;i<leno;i++)
   { if(document.frmUserSignup.birthday[i].selected)
  { choseno=document.frmUserSignup.birthday[i].value ;}}}


 if(document.frmUserSignup.birthyear.value=="")
 {alert(" Select Year in Date of Birth");
  document.frmUserSignup.birthyear.focus();
  return false;}
  if(!(document.frmUserSignup.birthyear.value==""))
  { leno=document.frmUserSignup.birthyear.length;
   for(i=0;i<leno;i++)
   { if(document.frmUserSignup.birthyear[i].selected)
  { choseno=document.frmUserSignup.birthyear[i].value ;}}}

//----------------------for Email ----
if(document.frmUserSignup.email.value=="")
{ alert("Enter a Email");
 document.frmUserSignup.email.focus();
 return false;}
 if(isnotVlaidEmail(document.frmUserSignup.email.value))
 {alert("Enter a valid Email");
  document.frmUserSignup.email.focus();
 return false;}

//___________________________________________________ZipCode_________________________________________________________
if(document.frmUserSignup.zip.value==""){
		alert("Enter Zipcode");
		document.frmUserSignup.zip.focus();
		return false;
	}
	
 if(document.frmUserSignup.zip.value.indexOf(" ")==0)
 {alert("Enter Valid Zipcode");
  document.frmUserSignup.zip.focus();
 return false;}

//-----------------------------------------------------------------------------------------------------------
/*if(!document.frmUserSignup.checkbox8.checked)
{alert("Please accept the terms and conditions applicable");
 return false;}*/
 
return true;

}

//-------------------------------- User status validation Ajax Script ------------------------------------------------

var httpRequest;

function usrStat()
{

if(document.frmUserSignup.chsUserName.value!="" && document.frmUserSignup.chsUserName.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmUserSignup.chsUserName.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.do?process=checkusrnam&chsUserName="+chsUserName; 

        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processRequest; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML) 
    { 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 

		if(salutationText != "false")
		{
			alert("User Name already Exists Chose Another !");
			document.frmUserSignup.chsUserName.value="";
			document.frmUserSignup.chsUserName.focus();
		}
		else
		{
			alert("Congrats Your User Name Is Available !!");
			document.frmUserSignup.fname.focus();
		}
      
    } 
 

//-------------------------------- get Users secret question Ajax Script ------------------------------------------------

var shttpRequest;

function secQa()
{

var chosen2="";
var len2 = document.frmUserSignup.info_rad.length ;
if(len2==undefined)
{
	if(document.frmUserSignup.info_rad.checked)
	{
		chosen2=document.frmUserSignup.info_rad.value;
		//alert(document.frmUserSignup.info_rad.value);
	}

	else
	{	
		document.getElementById("secretQuest").style.display = "none";	
		alert("Please select any profile to proceed.");
		document.frmUserSignup.memb_rad[1].checked=false;
			
	}

}

else
{
for(i=0;i<len2;i++)
{if(document.frmUserSignup.info_rad[i].checked)
  { chosen2= document.frmUserSignup.info_rad[i].value; }

}
if(chosen2=="")
{
		document.getElementById("secretQuest").style.display = "none";	
		alert("Please select any profile to proceed.");
		//document.frmUserSignup.prof_rad[1].checked=true;	
}
}

   var usrId=chosen2;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
		if(chosen2!="")
		{
		//alert("aj called");

        url= "UsrSignupAjax.do?process=secques&usrId="+usrId; 

        if (window.ActiveXObject) 
        { 
            shttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            shttpRequest = new XMLHttpRequest(); 
        } 
     
        shttpRequest.open("GET", url, true); 
        
        shttpRequest.onreadystatechange =processRequest1; 
        shttpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest1() 
    { 
   
        if (shttpRequest.readyState == 4) 
        { 
            if(shttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = shttpRequest.responseXML.getElementsByTagName("secqus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 

				  var salutationText = salutationXML.childNodes[0].nodeValue; 
			// alert(salutationText);
					if(salutationText != "" && salutationText != "null")
					{							
						document.frmUserSignup.secques.value=salutationText;			
					}
					else
					{
						document.frmUserSignup.secques.value="NA";	
					}
              
            } 
            else 
            { 
                alert("Error loading page\n"+ shttpRequest.status +":"+ shttpRequest.statusText); 
            } 
        } 
    } 
//-----------------------------------------------------------------------------------------------------------------------
//-------------------------------------------for User Registeration :Acccount Matching Result

function validateUser(){
//--------------------------------------------for Information
/*
lendata = document.frmUserSignup.info_rad.length;
if(lendata==undefined)
{
	if(!document.frmUserSignup.info_rad.checked)
	{
			alert("Select any of the profile");
			return false;
	}

}
else
{

chosendata="";
for(i=0;i<lendata;i++)
{if(document.frmUserSignup.info_rad[i].checked)
  { chosendata= document.frmUserSignup.info_rad[i].value; }
}
if(chosendata=="")
{alert("Select any of the profile");
 return false;
}
  
}*/

//-----------------------------------for same  person yes /no

lenquest = document.frmUserSignup.prof_rad.length;
chosenquest="";
for(i=0;i<lenquest;i++)
{if(document.frmUserSignup.prof_rad[i].checked)
  { chosenquest= document.frmUserSignup.prof_rad[i].value; }
}
if(chosenquest=="")
{alert("Select any of the option yes / no of Profile Match");
 return false;
}  

if(chosenquest=="yes")
{  
  lengthVal = document.frmUserSignup.memb_rad.length;
chosenValue="";
for(i=0;i<lengthVal;i++)
{if(document.frmUserSignup.memb_rad[i].checked)
  { chosenValue= document.frmUserSignup.memb_rad[i].value; }
}
if(chosenValue=="")
{alert("Select any of the option yes / no of Existing Member");
 return false;
}  
}

lensel = document.frmUserSignup.memb_rad.length;
chosen="";
for(i=0;i<lensel;i++)
{if(document.frmUserSignup.memb_rad[i].checked)
  { chosen= document.frmUserSignup.memb_rad[i].value; }
}


if(chosen=="yes")
{if(document.frmUserSignup.existMembId.value=="" || document.frmUserSignup.existMembId.value.indexOf(' ')==0)
 {alert("Enter your Member Id");
  document.frmUserSignup.existMembId.focus();
   return false;}
if(document.frmUserSignup.existMembId.value.length>80)
{alert("Enter valid Member Id");
  document.frmUserSignup.existMembId.focus();
   return false;}
}

   if(chosen=="no")
   {
	   if(document.frmUserSignup.answerId.value=="" || document.frmUserSignup.answerId.value.indexOf(' ') == 0)
	 	{alert("Enter your Answer");
	 	 document.frmUserSignup.answerId.focus();
	   return false;}
		if(document.frmUserSignup.answerId.value.length>80)
		{alert("Enter valid Answer");
 	   document.frmUserSignup.answerId.focus();
        return false;}
   }
  
  
return true;
}

function chstat()
{
var lenques=document.frmUserSignup.info_rad.length;

if(lenques==undefined)
{
	if(document.frmUserSignup.info_rad.checked)
	{
		document.frmUserSignup.prof_rad[0].checked=false;
		document.frmUserSignup.secques.value="";
	}
	
}
else
{

for(i=0;i<lenques;i++)
{
if(document.frmUserSignup.info_rad[i].checked)
 { chosenquest= document.frmUserSignup.info_rad[i].value; }

}
if(chosenquest!="")
{
	document.frmUserSignup.prof_rad[0].checked=false;
	document.frmUserSignup.prof_rad[1].checked=false;
	
	document.frmUserSignup.memb_rad[0].checked=false;
	document.frmUserSignup.memb_rad[1].checked=false;
	
	document.frmUserSignup.secques.value="";
	document.frmUserSignup.answer.value="";
	document.frmUserSignup.existMembId.value="";
}
}

}



 //------------------------------ Member Registration Member Id Existance Validate Ajax Script -------------------------

var arHttpRequest;

function HLCMemberDetails()
{

	if(document.frmUserSignup.existMembId.value!="" && document.frmUserSignup.existMembId.value.indexOf(" ")!=0)
	{
    var memberid=document.frmUserSignup.existMembId.value;

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
					document.frmUserSignup.existMembId.value="";
					document.frmUserSignup.existMembId.focus();
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


function selProf()
{
	
	var chosen2="";
var len2 = document.frmUserSignup.info_rad.length ;
if(len2==undefined)
{
	if(document.frmUserSignup.info_rad.checked)
	{
		chosen2=document.frmUserSignup.info_rad.value;
		//alert(document.frmUserSignup.info_rad.value);
	}

	else
	{	
		document.getElementById("existMembId").style.display = "none";	
		alert("Please select any profile to proceed.");
		document.frmUserSignup.memb_rad[0].checked=false;
			
	}

}

else
{
for(i=0;i<len2;i++)
{if(document.frmUserSignup.info_rad[i].checked)
  { chosen2= document.frmUserSignup.info_rad[i].value; }

}
if(chosen2=="")
{
		document.getElementById("existMembId").style.display = "none";	
		alert("Please select any profile to proceed.");
		document.frmUserSignup.memb_rad[0].checked=true;	
}
}
	
}


function clrRad()
{
	var chsnVal="";
	var lenClr=document.frmUserSignup.memb_rad.length;
	for(i=0;i<lenClr;i++)
{
if(document.frmUserSignup.memb_rad[i].checked)
 {
	 chsnVal= document.frmUserSignup.memb_rad[i].value; }
 }
if(chsnVal!="")
{
	document.frmUserSignup.memb_rad[0].checked=false;
	document.frmUserSignup.memb_rad[1].checked=false;

}
}