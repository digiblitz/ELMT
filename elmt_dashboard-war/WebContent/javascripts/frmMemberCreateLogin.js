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
//---------------------------------------------------------------------------------------
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
//--------------------------------------------------------------------------------
//-------------function for doublespace validation -----------------------------
var str="";
function Dospace(str){
if(str.indexOf("  ")!=-1 || str.indexOf(' ')==0)
{return true;}
else {return false;}
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
//-----------------------------------------------------------------------------------------------
function myvalidate(){
//-------------------for User Name----
//alert(document.frmUserSignup.chsUserName.value);
if(document.frmUserSignup.chsUserName.value=="")
{ alert("Enter a HLC Member Id");
 document.frmUserSignup.chsUserName.focus();
 return false;}
 
if(isnotInteger(document.frmUserSignup.chsUserName.value)|| document.frmUserSignup.chsUserName.value.length > 25 || document.frmUserSignup.chsUserName.value.length < 4 ||Dospace(document.frmUserSignup.chsUserName.value))
 {alert("Enter a valid HLC Member Id");
  document.frmUserSignup.chsUserName.focus();
 return false;}
 
//-------------------for First Name----
if(document.frmUserSignup.fname.value=="")
{ alert("Enter a First Name");
 document.frmUserSignup.fname.focus();
 return false;}
 if(document.frmUserSignup.fname.value.length > 80||Dospace(document.frmUserSignup.fname.value))
 {alert("Enter a valid First Name");
  document.frmUserSignup.fname.focus();
 return false;}
 //-------------------for Last Name----
if(document.frmUserSignup.lname.value=="")
{ alert("Enter a Last Name");
 document.frmUserSignup.lname.focus();
 return false;}
 if(document.frmUserSignup.lname.value.length > 80||Dospace(document.frmUserSignup.lname.value))
 {alert("Enter a valid Last Name");
  document.frmUserSignup.lname.focus();
 return false;}
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
 if(isnotVlaidEmail(document.frmUserSignup.email.value)||Dospace(document.frmUserSignup.email.value))
 {alert("Enter a valid Email");
  document.frmUserSignup.email.focus();
 return false;}
 //------------------------for Zipcode----------------
 if(document.frmUserSignup.zip.value=="")
 { alert("Enter a zipcode");
   document.frmUserSignup.zip.focus();
   return false;
 }
 if(isnotZipcode(document.frmUserSignup.zip.value)||document.frmUserSignup.zip.value.length<2||document.frmUserSignup.zip.value.length>20)
 {alert("Enter a valid zipcode");
   document.frmUserSignup.zip.focus();
   return false;
 }
 //--------------------------------------------------------
 return true;
 
}

  //------------------------------ Member Registration Member Id Existance Validate Ajax Script -------------------------

var arHttpRequest;

function HLCMemberDetails()
{

	if(document.frmUserSignup.membid.value!="" && document.frmUserSignup.membid.value.indexOf(" ")!=0)
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
