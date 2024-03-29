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
//------------------------------ User Registration Ajax Script -------------------------

var arHttpRequest;

function usrdetails()
{

if(document.frmMembRegi.email.value!="" && document.frmMembRegi.email.value.indexOf(" ")!=0)
{

    var email=document.frmMembRegi.email.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?email="+email; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {emailRequest(); } ; 
        arHttpRequest.send(null); 
   } 
}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailRequest() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("email")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
                 
              //  alert(arnameText);
                if(arnameText!=null)
                {    
                alert("Email Id Exists! Choose Other Id"); 
            	document.frmMembRegi.email.focus();
                return false;
                }    
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 




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
stringCheck="!@#$%^&*()_+|<>?/=~,0123456789;:][{}"+"\\"+"+"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//-----------------------------------is not alpha1----------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~.,`;:][{}"+"\\"+"\'"+"\"";
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
//----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------



function myvalidate(){


stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
stringCheck3=" ";




//-----------------------------------SALUTATION-----------------------------------------------------
if(document.frmMembRegi.USelect.value=="")
{alert(" Select the Salutation ");
 document.frmMembRegi.USelect.focus();
 return false;}


if(!(document.frmMembRegi.USelect.value==""))
{ leno=document.frmMembRegi.USelect.length;
 for(i=0;i<leno;i++)
 { if(document.frmMembRegi.USelect[i].selected)
 { choseno=document.frmMembRegi.USelect[i].value ;}}


}

//---------------------------------------FIRST NAME----------------------------------
//-------------------for First Name----
if(document.frmMembRegi.fname.value=="")
{ alert("Enter a First Name");
 document.frmMembRegi.fname.focus();
 return false;}
if(document.frmMembRegi.fname.value.indexOf(" ")==0)
 {alert("Enter a valid First Name");
  document.frmMembRegi.fname.focus();
 return false;}
 if(document.frmMembRegi.fname.value.length > 80)
 {alert("First Name should be less than 80 characters");
  document.frmMembRegi.fname.focus();
 return false;}
 //-------------------for Last Name----
if(document.frmMembRegi.lname.value=="")
{ alert("Enter a Last Name");
 document.frmMembRegi.lname.focus();
 return false;}
if(document.frmMembRegi.lname.value.indexOf(" ")==0)
{ alert("Enter a valid Last Name");
 document.frmMembRegi.lname.focus();
 return false;}
 if(document.frmMembRegi.lname.value.length > 80)
 {alert("Last Name should be less than 80 characters");
  document.frmMembRegi.lname.focus();
 return false;}
//--------------------------MIDDLE NAME-----------------------------------------


/*if(isnotAlpha(document.frmMembRegi.mname.value))
{ alert("Enter Valid Middle Name ");
  document.frmMembRegi.mname.focus();
   return false; }*/
   
if(Dospace(document.frmMembRegi.mname.value)||((document.frmMembRegi.mname.value.indexOf(' '))==0))
   { alert("Enter Valid Middle Name ");
     document.frmMembRegi.mname.focus();
   return false; }
if( document.frmMembRegi.mname.value.length>80 )
   { alert("Enter within 80 characters for Middle Name" );
      document.frmMembRegi.mname.focus();
      return false; }

/*//----------------------------------LAST NAME--------------------------------------
if(document.frmMembRegi.lname.value=="")
   {alert(" Last Name cannot be empty ");
     document.frmMembRegi.lname.focus();
    return false; }
if(document.frmMembRegi.lname.value.indexOf(' ')==0)
{alert("Enter Last Name  ");
 document.frmMembRegi.lname.focus();
 return false; }

if(isnotAlpha(document.frmMembRegi.lname.value))
{ alert("Enter Valid Last Name ");
  document.frmMembRegi.lname.focus();
   return false; }
if(Dospace(document.frmMembRegi.lname.value))
   { alert("Enter Valid Last Name ");
     document.frmMembRegi.lname.focus();
   return false; }
if( document.frmMembRegi.lname.value.length>80 )
   { alert("Enter within 80 characters of Last Name" );
      document.frmMembRegi.lname.focus();
      return false; }*/
//--------------------------------SUFFIX---------------------------------------


/*if(document.frmMembRegi.sname.value.indexOf(' ')==0)
{alert("Enter Suffix ");
 document.frmMembRegi.sname.focus();
 return false; }


if(isnotAlphaNumeric(document.frmMembRegi.sname.value))
{ alert("Enter Valid Suffix ");
  document.frmMembRegi.sname.focus();
   return false; }
if(Dospace(document.frmMembRegi.sname.value))
   { alert("Enter Valid Suffix ");
     document.frmMembRegi.sname.focus();
   return false; }
if( document.frmMembRegi.sname.value.length>20 )
   { alert("Enter within 20 characters for Suffix" );
      document.frmMembRegi.sname.focus();
      return false; }
*/

//___________________________________________DATE________________________________________________________
/*if(document.frmMembRegi.birthmonth.value=="")
{alert(" Select Month in Date of Birth ");
 document.frmMembRegi.birthmonth.focus();
 return false;}
 if(!(document.frmMembRegi.birthmonth.value==""))
 { leno=document.frmMembRegi.birthmonth.length;
  for(i=0;i<leno;i++)
  { if(document.frmMembRegi.birthmonth[i].selected)
 { choseno=document.frmMembRegi.birthmonth[i].value ;}}}
 
 //------month checking----
 var lyear=document.frmMembRegi.birthyear.value;
 var lcheck=(lyear%4);

if((document.frmMembRegi.birthmonth.selectedIndex== 2)&&(document.frmMembRegi.birthday.value >29)&&(lcheck=="0"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
var lyear1=document.frmMembRegi.birthyear.value;
 var lcheck1=(lyear1%4);
 
 if((document.frmMembRegi.birthmonth.selectedIndex== 2)&&(document.frmMembRegi.birthday.value >28)&&(lcheck1>0))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.selectedIndex== 4)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.selectedIndex== 6)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.selectedIndex== 9)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth.selectedIndex== 11)&&(document.frmMembRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday.focus();
	return false;
 }


 if(document.frmMembRegi.birthday.value=="")
 {alert(" Select Day in Date of Birth");
  document.frmMembRegi.birthday.focus();
  return false;}
  if(!(document.frmMembRegi.birthday.value==""))
  { leno=document.frmMembRegi.birthday.length;
   for(i=0;i<leno;i++)
   { if(document.frmMembRegi.birthday[i].selected)
  { choseno=document.frmMembRegi.birthday[i].value ;}}}


 if(document.frmMembRegi.birthyear.value=="")
 {alert(" Select Year in Date of Birth");
  document.frmMembRegi.birthyear.focus();
  return false;}
  if(!(document.frmMembRegi.birthyear.value==""))
  { leno=document.frmMembRegi.birthyear.length;
   for(i=0;i<leno;i++)
   { if(document.frmMembRegi.birthyear[i].selected)
  { choseno=document.frmMembRegi.birthyear[i].value ;}}}*/

//-----------------------------------------------------------------------------------------------------------
//-----------------------------------------GENDER
chosen="";
len = document.frmMembRegi.gender.length ;
for(i=0;i<len;i++)
{if(document.frmMembRegi.gender[i].checked)
  { chosen= document.frmMembRegi.gender[i].value; }
}
if(chosen=="")
{alert("Select the Gender");
 return false;
}

//----------------------------------------------------------------------------------------------------------------
//--------------------------------------EMAIL-----------------

//--------------------------------------EMAIL-----------------

 //---------------------------------------------------------------------------------------------------------------
/* if(document.frmMembRegi.email.value=="")
 {alert("Enter your Email ID");
  document.frmMembRegi.email.focus();
 return false;}
 
if(document.frmMembRegi.email.value.indexOf(" ")==0)
 {alert("Enter a Valid Email ID");
  document.frmMembRegi.email.focus();
 return false;}
*/
 if(!(document.frmMembRegi.email.value== ""))
 { strmail=document.frmMembRegi.email.value;
 firstat = strmail.indexOf("@");
 lastat = strmail.lastIndexOf("@");
 strmain=strmail.substring(0,firstat);
 strsub=strmail.substring(firstat,document.frmMembRegi.email.value.length);
 if(strmail.length>120)
 {alert("Email is out of range");
  document.frmMembRegi.email.focus();
 return false;}
 if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 )
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 if(isnotSpecial(strmain) || isnotSpecial(strsub))
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 k=0;
 strlen=strsub.length;
 for(i=0;i<strlen-1;i++)
 { if(strsub.charAt(i)=='.')
 {k=k+1;}}
 if(k>3)
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 if(firstat==-1 || lastat==-1)
 {alert("Enter valid Email" );
  document.frmMembRegi.email.focus();
 return false;}
 if(Number(strmain))
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
  return false;}
 if(firstat != lastat )
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
 return false;}
 firstdot=strmail.indexOf(".",firstat);
 lastdot=strmail.lastIndexOf(".");
 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
 {alert("Enter valid Email ");
  document.frmMembRegi.email.focus();
  return false;}
}

var length = document.frmMembRegi.usrname.value.length;
//alert('Length'+length);
if(length!=0){
	if(length<4){
		alert("Login ID should be atleast 4 characters");
		document.frmMembRegi.usrname.focus();
		return false;
	}
	if(length>25){
		alert("Login ID exceeds the Number of Characters");
		document.frmMembRegi.usrname.focus();
		return false;
	}
}
if(isnotAlphaNumeric( document.frmMembRegi.usrname.value)){
		alert("Login ID doesnot any special characters");
		document.frmMembRegi.usrname.focus();
		return false;
}

if(isnotAlphaNumeric(document.frmMembRegi.usrCode.value)){
		alert("User Code cannot have any special characters");
		document.frmMembRegi.usrCode.focus();
		return false;
}

	if(document.frmMembRegi.usrCode.value>25){
		alert("User Code exceeds the Number of Characters");
		document.frmMembRegi.usrCode.focus();
		return false;
	}
//------------------------------------------------------------------------------------------------
//----------------------------------------Password
/*if(document.frmMembRegi.pwd.value=="")
{alert("Enter Password ");
 document.frmMembRegi.pwd.focus();
 return false;}
if(document.frmMembRegi.pwd.value.length<6)

{alert("Enter Valid Password ");
document.frmMembRegi.pwd.focus();
 return false;}
if(document.frmMembRegi.pwd.value.length>12)
{alert("Enter Valid Password ");
document.frmMembRegi.pwd.focus();
 return false;}
 if(Dospace(document.frmMembRegi.pwd.value))
     { alert("Enter Password ");
       document.frmMembRegi.pwd.focus();
   return false; }
   if(isnotAlphaNumeric(document.frmMembRegi.pwd.value))
   { alert("Enter Valid Password");
     document.frmMembRegi.pwd.focus();
   return false; }
   strnme=document.frmMembRegi.pwd.value;
      	fnme=1;
      	for(j=0;j<document.frmMembRegi.pwd.value.length;j++)
      	{ if(stringCheck3.indexOf(strnme.charAt(j))!=-1)
      	   { fnme=0;} }
      	if(fnme==0)
      	{alert("Enter Valid Password ");
      	 document.frmMembRegi.pwd.focus();
	return false;}*/
//---------------------------------------------------------------------------------------------------
//------------------------------------RETYPE Password---------------------------------------


/*if(document.frmMembRegi.cpwd.value=="")
{alert(" Enter Re-Type Password ");
 document.frmMembRegi.cpwd.focus();
 return false;}
var pwd1=document.frmMembRegi.pwd.value;
var rpwd=document.frmMembRegi.cpwd.value;
if(pwd1!=rpwd)
{
	alert("Password Mismatch");
	document.frmMembRegi.cpwd.focus();
	return false;
}*/
	


//--------------------------------------------------------------------------------------------------------

//______________________________________________Question_________________________________________________

/*if(document.frmMembRegi.QSelect.value=="")
{alert(" Select your Secret Question ");
 document.frmMembRegi.QSelect.focus();
 return false;}


if(!(document.frmMembRegi.QSelect.value==""))
{ leno=document.frmMembRegi.QSelect.length;
 for(i=0;i<leno;i++)
 { if(document.frmMembRegi.QSelect[i].selected)
 { choseno=document.frmMembRegi.QSelect[i].value ;}}


}
//_________________________________________________Answer_____________________________________________________

if(document.frmMembRegi.ans.value=="")
{alert(" Enter your Answer to the secret Question ");
 document.frmMembRegi.ans.focus();
 return false;}
if(document.frmMembRegi.ans.value.length>255)
 {alert("Enter Answer with in 255 characters ");
 document.frmMembRegi.ans.focus();
return false;}
if(Dospace(document.frmMembRegi.ans.value))
 	   { alert("Enter Valid Answer ");
 	     document.frmMembRegi.ans.focus();
	   return false; }
	   
	   if(document.frmMembRegi.ans.value.indexOf(' ')==0){
		alert("Enter Valid Answer ");
    document.frmMembRegi.ans.focus();
    return false; }*/
	   
//--------------------------------------------------primary----------------------------------------------------
//____________________________________________________Address1_____________________________________________________



	if(document.frmMembRegi.padd_txt.value==""){
		alert(" Enter Primary Address ");
		document.frmMembRegi.padd_txt.focus();
		return false;
		}
	if(document.frmMembRegi.padd_txt.value.length>255){
		alert("Enter Primary Address with in 255 characters");
		document.frmMembRegi.padd_txt.focus();
		return false;
		}
	if(document.frmMembRegi.padd_txt.value.indexOf(' ')==0){
		alert("Enter Valid Primary Address ");
    document.frmMembRegi.padd_txt.focus();
    return false; }
	
	if(Dospace(document.frmMembRegi.padd_txt.value))
 	   { alert("Enter Valid Primary Address");
 	     document.frmMembRegi.padd_txt.focus();
	   return false; }
//____________________________________________________Address2_____________________________________________________

	if(document.frmMembRegi.padd_txt2.value.length>255){
		alert("Enter Primary Address with in 255 characters");
		document.frmMembRegi.padd_txt2.focus();
		return false;
		}
		
	if(Dospace(document.frmMembRegi.padd_txt2.value)){
		alert("Enter Valid Primary Address ");
		document.frmMembRegi.padd_txt2.focus();
		return false; 
	}
	if(document.frmMembRegi.padd_txt2.value.indexOf(" ")==0){
		alert("Enter Valid Primary Address ");
		document.frmMembRegi.padd_txt2.focus();
		return false;
		}
//__________________________________________city___________________________________________________________________


	
	if(document.frmMembRegi.pcity_txt.value=="")
	   {alert(" Enter City Name in Primary Address ");
	     document.frmMembRegi.pcity_txt.focus();
	    return false; }
	if(document.frmMembRegi.pcity_txt.value.indexOf(' ')==0)
	{alert("Enter City ");
	 document.frmMembRegi.pcity_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmMembRegi.pcity_txt.value))
	{ alert("Enter Valid city in Primary Address ");
	  document.frmMembRegi.pcity_txt.focus();
	   return false; }
/*	if(Dospace(document.frmMembRegi.pcity_txt.value))
	   { alert("Enter Valid City in Primary Address");
	     document.frmMembRegi.pcity_txt.focus();
	   return false; }*/
	if( document.frmMembRegi.pcity_txt.value.length>80 )
	   { alert("Enter within 80 characters for City in Primary Address" );
	      document.frmMembRegi.pcity_txt.focus();
      return false; }
	

//___________________________________________________ZipCode_________________________________________________________
if(document.frmMembRegi.pzip_txt.value==""){
		alert("Enter Zipcode in Primary Address");
		document.frmMembRegi.pzip_txt.focus();
		return false;
	    }
     
    if((document.frmMembRegi.pzip_txt.value.length <3&& document.frmMembRegi.pcountry_sel.value!="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmMembRegi.pzip_txt.focus();
        return false;
		}
 
    if((document.frmMembRegi.pzip_txt.value.length >20 && document.frmMembRegi.pcountry_sel.value!="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmMembRegi.pzip_txt.focus();
        return false;
		}
	if((document.frmMembRegi.pzip_txt.value.length !=5&& document.frmMembRegi.pcountry_sel.value=="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmMembRegi.pzip_txt.focus();
        return false;
		}
         
	if(document.frmMembRegi.pzip_txt.value.indexOf(" ")==0){
		alert("Enter Valid Zipcode in Primary Address");
		document.frmMembRegi.pzip_txt.focus();
		return false; 
	}

	

//___________________________________________________Country______________________________________________________

//__________________________________________________________________________________________________________________




 	var cdln = "";
 	if(typeof(window.document.frmMembRegi.pcountry_sel) == 'object'){
 		if (window.document.frmMembRegi.pcountry_sel.value != ""){
 			var cemail;
 			cemail = window.document.frmMembRegi.pcountry_sel[window.document.frmMembRegi.pcountry_sel.selectedIndex].value;
 			cdln = cemail;
 		}
 		if(cdln == "Select One"){
 			alert("Please select Country in Primary Address");
 			window.document.frmMembRegi.pcountry_sel.focus();
 			return false;
 		}
 	}

 	if ( document.frmMembRegi.pcountry_sel.selectedIndex == 0 ){
         alert ( "Please select Country Name in Primary Address" );
 		document.frmMembRegi.pcountry_sel.focus();
         return false;
     }

 	var edln = "";
 	if(typeof(window.document.frmMembRegi.pstate_sel) == 'object'){
 		if (window.document.frmMembRegi.pstate_sel.value != ""){
 			var email;
 			email = window.document.frmMembRegi.pstate_sel[window.document.frmMembRegi.pstate_sel.selectedIndex].value;
 			edln = email;
 		}
 		if(edln.length == 1 ){
 			alert("Please select State in Primary Address");
 			window.document.frmMembRegi.pstate_sel.focus();
 			return false;
 		}
 	}

//___________________________________________________ State ______________________________________________________

//__________________________________________________________________________________________________________________




 	var cdln = "";
 	if(typeof(window.document.frmMembRegi.pstate_sel) == 'object'){
 		if (window.document.frmMembRegi.pstate_sel.value != ""){
 			var cemail;
 			cemail = window.document.frmMembRegi.pstate_sel[window.document.frmMembRegi.pstate_sel.selectedIndex].value;
 			cdln = cemail;
 		}
 		if(cdln == "Select One"){
 			alert("Please select State in Primary Address");
 			window.document.frmMembRegi.pstate_sel.focus();
 			return false;
 		}
 	}

 	if ( document.frmMembRegi.pstate_sel.selectedIndex == 0 ){
         alert ( "Please select State Name in Primary Address" );
 		document.frmMembRegi.pstate_sel.focus();
         return false;
     }

 	var edln = "";
 	if(typeof(window.document.frmMembRegi.pstate_sel) == 'object'){
 		if (window.document.frmMembRegi.pstate_sel.value != ""){
 			var email;
 			email = window.document.frmMembRegi.pstate_sel[window.document.frmMembRegi.pstate_sel.selectedIndex].value;
 			edln = email;
 		}
 		if(edln.length == 1 ){
 			alert("Please select State in Primary Address");
 			window.document.frmMembRegi.pstate_sel.focus();
 			return false;
 		}
 	}


//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER Primary Phone Number OR Primary mobile Number
if(document.frmMembRegi.pphone_txt.value=="" )
{ alert("Enter Primary Phone Number");
  document.frmMembRegi.pphone_txt.focus();
  return false;}
  if(Dospace(document.frmMembRegi.pphone_txt.value)){
			alert("Enter Valid Phone Number in Primary Address");
	     	document.frmMembRegi.pphone_txt.focus();
	  		return false;
			}
	if(document.frmMembRegi.pphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number in Primary Address");
		document.frmMembRegi.pphone_txt.focus();
		return false; 
	}

//--------------------------------Primary Phone Number---------------------------------------


if(document.frmMembRegi.pphone_txt.value!="")
{	/*	var s1=document.frmMembRegi.pphone_txt.value.indexOf('(');
		var s2=document.frmMembRegi.pphone_txt.value.indexOf(')');
		var s5=document.frmMembRegi.pphone_txt.value.indexOf('+');
		var s6=document.frmMembRegi.pphone_txt.value.lastIndexOf('+');
		var s7=document.frmMembRegi.pphone_txt.value.indexOf('-');
		var s8=document.frmMembRegi.pphone_txt.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Primary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Primary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
			return false;
		}
		if(s5!=s6){
			alert("Enter valid Primary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Primary Phone Number");
			document.frmMembRegi.pphone_txt.focus();
			return false;
		}*/
																					  
 len7=document.frmMembRegi.pphone_txt.value.length;
 strnum = document.frmMembRegi.pphone_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Phone Number");
 document.frmMembRegi.pphone_txt.focus();
 return false;}
 if(document.frmMembRegi.pphone_txt.value.length>40)
  {alert("Enter valid Primary Phone Number");
  document.frmMembRegi.pphone_txt.focus();
  return false;}
  if(isnotAlpha1(document.frmMembRegi.pphone_txt.value))
{ alert("Enter Valid Primary Phone Number ");
  document.frmMembRegi.pphone_txt.focus();
   return false; }
}
	

//----------------------------------Primary MOBILE---------------------------------------


if(document.frmMembRegi.pmob_txt.value!="")
{	/*	var s1=document.frmMembRegi.pmob_txt.value.indexOf('(');
		var s2=document.frmMembRegi.pmob_txt.value.indexOf(')');
		var s5=document.frmMembRegi.pmob_txt.value.indexOf('+');
		var s6=document.frmMembRegi.pmob_txt.value.lastIndexOf('+');
		var s7=document.frmMembRegi.pmob_txt.value.indexOf('-');
		var s8=document.frmMembRegi.pmob_txt.value.lastIndexOf('-');
		var s3=1+s2;
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Primary mobile Number");
			document.frmMembRegi.pmob_txt.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Primary mobile Number");
			document.frmMembRegi.pmob_txt.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid Primary mobile Number");
			document.frmMembRegi.pmob_txt.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Primary mobile Number");
			document.frmMembRegi.pmob_txt.focus();
			return false;
		}
		if(document.frmMembRegi.pmob_txt.value.length>40){
			alert("Enter valid Primary mobile Number");
			document.frmMembRegi.pmob_txt.focus();
			return false;
		}*/
 len7=document.frmMembRegi.pmob_txt.value.length;
 strnum = document.frmMembRegi.pmob_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary mobile Number");
 document.frmMembRegi.pmob_txt.focus();
 return false;}
 
 if(isnotAlpha1(document.frmMembRegi.pmob_txt.value))
{ alert("Enter Valid Primary mobile Number ");
  document.frmMembRegi.pmob_txt.focus();
   return false; }
 }



 //-----------------------------------------Primary fax--------------------------------
 


if(document.frmMembRegi.pfax_txt.value!="")
{	/*	var s1=document.frmMembRegi.pfax_txt.value.indexOf('(');
		var s2=document.frmMembRegi.pfax_txt.value.indexOf(')');
		var s5=document.frmMembRegi.pfax_txt.value.indexOf('+');
		var s6=document.frmMembRegi.pfax_txt.value.lastIndexOf('+');
		var s7=document.frmMembRegi.pfax_txt.value.indexOf('-');
		var s8=document.frmMembRegi.pfax_txt.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Primary Primary fax ");
			document.frmMembRegi.pfax_txt.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Primary Primary fax ");
			document.frmMembRegi.pfax_txt.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid Primary Primary fax ");
			document.frmMembRegi.pfax_txt.focus();
			return false;
		}
		if(s7!==s8){
			alert("Enter valid Primary Primary fax ");
			document.frmMembRegi.pfax_txt.focus();
			return false;
		}*/

 len7=document.frmMembRegi.pfax_txt.value.length;
 strnum = document.frmMembRegi.pfax_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Primary fax ");
 document.frmMembRegi.pfax_txt.focus();
 return false;}
 
 if(document.frmMembRegi.pfax_txt.value.length>40)
  {alert("Enter valid Primary fax ");
  document.frmMembRegi.pfax_txt.focus();
  return false;}
  if(isnotAlpha1(document.frmMembRegi.pfax_txt.value))
{ alert("Enter Valid Primary fax ");
  document.frmMembRegi.pfax_txt.focus();
   return false; }
  
 }


//------------------------------------------------------checking in sec.add------------------------------------------
/*if(document.frmMembRegi.addr.value=="Primary"){
	if(Number(document.frmMembRegi.scountry_txt.value))
	{ alert("Enter Valid Country  in Secondary Address ");
	  document.frmMembRegi.scountry_txt.focus();
	   return false; }
	   	   if(Number(document.frmMembRegi.sstate_txt.value))
	{ alert("Enter Valid State in Secondary Address ");
	  document.frmMembRegi.sstate_txt.focus();
	   return false; }
	   if(Number(document.frmMembRegi.scity_txt.value))
	{ alert("Enter Valid City in Secondary Address ");
	  document.frmMembRegi.scity_txt.focus();
	   return false; }
	   if(!Number(document.frmMembRegi.szip_txt.value))
{alert("Enter valid Zipcode in Secondary Address");
 document.frmMembRegi.szip_txt.focus();
 return false;}
	   
	   }*/
	
//------------------------------------------------------SECONDARY-----------------------------------------------------
//____________________________________________________Address1_____________________________________________________

var chosen2="";
var len2 = document.frmMembRegi.addr.length ;
for(i=0;i<len2;i++)
{if(document.frmMembRegi.addr[i].checked)
  { chosen2= document.frmMembRegi.addr[i].value; }
}

if(chosen2=="Primary"){

if(document.frmMembRegi.sadd_txt.value=="")
{alert(" Enter Secondary Address ");
 document.frmMembRegi.sadd_txt.focus();
 return false;}
if(document.frmMembRegi.sadd_txt.value.length>255)
{alert("Enter Secondary Address with in 255 characters");
document.frmMembRegi.sadd_txt.focus();
return false;}
	if(Dospace(document.frmMembRegi.sadd_txt.value)){
		alert("Enter Valid Secondary Address ");
		document.frmMembRegi.sadd_txt.focus();
		return false;
	}
	if(document.frmMembRegi.sadd_txt.value.indexOf(" ")==0){
		alert("Enter Valid Secondary Address");
		document.frmMembRegi.sadd_txt.focus();
		return false; 
	}
//____________________________________________________Address2_____________________________________________________

if(document.frmMembRegi.sadd_txt1.value.length>255)
{alert("Enter Secondary Address with in 255 characters");
document.frmMembRegi.sadd_txt1.focus();
return false;}

	if(document.frmMembRegi.sadd_txt1.value.indexOf(" ")==0){
		alert("Enter Valid Secondary Address");
		document.frmMembRegi.sadd_txt1.focus();
		return false; 
	}
//__________________________________________city___________________________________________________________________


	
	if(document.frmMembRegi.scity_txt.value=="")
	   {alert(" City cannot be empty in Secondary Address");
	     document.frmMembRegi.scity_txt.focus();
	    return false; }
	if(document.frmMembRegi.scity_txt.value.indexOf(' ')==0)
	{alert("Enter City in Secondary Address");
	 document.frmMembRegi.scity_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmMembRegi.scity_txt.value))
	{ alert("Enter Valid City in Secondary Address");
	  document.frmMembRegi.scity_txt.focus();
	   return false; }
/*	if(Dospace(document.frmMembRegi.scity_txt.value))
	   { alert("Enter  Valid City in Secondary Address ");
	     document.frmMembRegi.scity_txt.focus();
	   return false; }*/
	if( document.frmMembRegi.scity_txt.value.length>80 )
	   { alert("Enter within 80 characters for City in Secondary Address" );
	      document.frmMembRegi.scity_txt.focus();
      return false; }
	  
	 

	//___________________________________________________ZipCode_________________________________________________________
if(document.frmMembRegi.szip_txt.value==""){
			alert("Enter Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
	    }
     		
    	if((document.frmMembRegi.szip_txt.value.length <3&& document.frmMembRegi.sCountry_sel.value!="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
		}
 
    	if((document.frmMembRegi.szip_txt.value.length >20 && document.frmMembRegi.sCountry_sel.value!="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
		}
		 if((document.frmMembRegi.szip_txt.value.length !=5&& document.frmMembRegi.sCountry_sel.value=="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false;
		}
 		       
		if(Dospace(document.frmMembRegi.szip_txt.value)){
			alert("Enter Valid Zipcode in Secondary Address");
	     	document.frmMembRegi.szip_txt.focus();
	  		return false;
			}	
		if(document.frmMembRegi.szip_txt.value.indexOf(" ")==0){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmMembRegi.szip_txt.focus();
			return false; 
		}
	
	

//___________________________________________________Country______________________________________________________
if(document.frmMembRegi.sCountry_sel.selectedIndex==0)
	   {alert(" Country cannot be empty in Secondary Address");
	     document.frmMembRegi.sCountry_sel.focus();
	    return false; }
	/*if(document.frmMembRegi.scountry_txt.value.indexOf(' ')==0)
	{alert("Enter Country  in Secondary Address ");
	 document.frmMembRegi.scountry_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmMembRegi.scountry_txt.value))
	{ alert("Enter Valid Country  in Secondary Address ");
	  document.frmMembRegi.scountry_txt.focus();
	   return false; }
   if(Dospace(document.frmMembRegi.scountry_txt.value))
	   { alert("Enter Valid Country  in Secondary Address ");
	     document.frmMembRegi.scountry_txt.focus();
	   return false; }
	if( document.frmMembRegi.scountry_txt.value.length>80 )
	   { alert("Enter within 80 characters for Country  in Secondary Address" );
	      document.frmMembRegi.scountry_txt.focus();
      return false; }*/

 //------------------------------------------------State-------------------------------------------------------------	 
	if(document.frmMembRegi.sState_sel.selectedIndex==0)
	   {alert(" State cannot be empty in Secondary Address");
	     document.frmMembRegi.sState_sel.focus();
	    return false; }
	/*if(document.frmMembRegi.sstate_txt.value.indexOf(' ')==0)
	{alert("Enter State in Secondary Address");
	 document.frmMembRegi.sstate_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmMembRegi.sstate_txt.value))
	{ alert("Enter  Valid State in Secondary Address");
	  document.frmMembRegi.sstate_txt.focus();
	   return false; }

	if(Dospace(document.frmMembRegi.sstate_txt.value))
	   { alert("Enter  Valid State in Secondary Address ");
	     document.frmMembRegi.sstate_txt.focus();
	   return false; }
	if( document.frmMembRegi.sstate_txt.value.length>80 )
	   { alert("Enter within 80 characters for State in Secondary Address" );
	      document.frmMembRegi.sstate_txt.focus();
      return false; }*/	 
//----------------------IF ENTER EITHER Secondary Phone Number OR Secondary mobile Number
if(document.frmMembRegi.sphone_txt.value=="")
{ alert("Enter Secondary Phone Number");
  document.frmMembRegi.sphone_txt.focus();
  return false;}
if(Dospace(document.frmMembRegi.sphone_txt.value)){
			alert("Enter Valid Phone Number in Secondary Address");
	     	document.frmMembRegi.sphone_txt.focus();
	  		return false;
			}
			if(document.frmMembRegi.sphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number in Secondary Address");
		document.frmMembRegi.sphone_txt.focus();
		return false; 
	}
//--------------------------------Secondary Phone Number---------------------------------------


if(document.frmMembRegi.sphone_txt.value!="")
{	/*	var s1=document.frmMembRegi.sphone_txt.value.indexOf('(');
		var s2=document.frmMembRegi.sphone_txt.value.indexOf(')');
		var s5=document.frmMembRegi.sphone_txt.value.indexOf('+');
		var s6=document.frmMembRegi.sphone_txt.value.lastIndexOf('+');
		var s7=document.frmMembRegi.sphone_txt.value.indexOf('-');
		var s8=document.frmMembRegi.sphone_txt.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.sphone_txt.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.sphone_txt.focus();
			return false;
		}
		if(s5!=s6){
			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.sphone_txt.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Secondary Phone Number");
			document.frmMembRegi.sphone_txt.focus();
			return false;
		}
					*/																  
 len7=document.frmMembRegi.sphone_txt.value.length;
 strnum = document.frmMembRegi.sphone_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Secondary Phone Number");
 document.frmMembRegi.sphone_txt.focus();
 return false;}
 if(document.frmMembRegi.sphone_txt.value.length>40)
  {alert("Enter valid Secondary Phone Number");
  document.frmMembRegi.sphone_txt.focus();
  return false;}
  if(isnotAlpha1(document.frmMembRegi.sphone_txt.value))
{ alert("Enter Valid Secondary Phone Number ");
  document.frmMembRegi.sphone_txt.focus();
   return false; }
}

	

//----------------------------------Secondary MOBILE---------------------------------------


if(document.frmMembRegi.smob_txt.value!="")
{	/*	var s1=document.frmMembRegi.smob_txt.value.indexOf('(');
		var s2=document.frmMembRegi.smob_txt.value.indexOf(')');
		var s5=document.frmMembRegi.smob_txt.value.indexOf('+');
		var s6=document.frmMembRegi.smob_txt.value.lastIndexOf('+');
		var s7=document.frmMembRegi.smob_txt.value.indexOf('-');
		var s8=document.frmMembRegi.smob_txt.value.lastIndexOf('-');
		var s3=1+s2;
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Secondary mobile Number");
			document.frmMembRegi.smob_txt.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Secondary mobile Number");
			document.frmMembRegi.smob_txt.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid Secondary mobile Number");
			document.frmMembRegi.smob_txt.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Secondary mobile Number");
			document.frmMembRegi.smob_txt.focus();
			return false;
		}
		if(document.frmMembRegi.smob_txt.value.length>40){
			alert("Enter valid Secondary mobile Number");
			document.frmMembRegi.smob_txt.focus();
			return false;
		}*/
 len7=document.frmMembRegi.smob_txt.value.length;
 strnum = document.frmMembRegi.smob_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Secondary mobile Number");
 document.frmMembRegi.smob_txt.focus();
 return false;}
 
 if(isnotAlpha1(document.frmMembRegi.smob_txt.value))
{ alert("Enter Valid Secondary mobile Number ");
  document.frmMembRegi.smob_txt.focus();
   return false; }
 }



 //-----------------------------------------Secondary fax--------------------------------
 


if(document.frmMembRegi.sfax_txt.value!="")
{	/*	var s1=document.frmMembRegi.sfax_txt.value.indexOf('(');
		var s2=document.frmMembRegi.sfax_txt.value.indexOf(')');
		var s5=document.frmMembRegi.sfax_txt.value.indexOf('+');
		var s6=document.frmMembRegi.sfax_txt.value.lastIndexOf('+');
		var s7=document.frmMembRegi.sfax_txt.value.indexOf('-');
		var s8=document.frmMembRegi.sfax_txt.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Primary Secondary fax ");
			document.frmMembRegi.sfax_txt.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Primary Secondary fax ");
			document.frmMembRegi.sfax_txt.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid Primary Secondary fax ");
			document.frmMembRegi.sfax_txt.focus();
			return false;
		}
		if(s7!==s8){
			alert("Enter valid Primary Secondary fax ");
			document.frmMembRegi.sfax_txt.focus();
			return false;
		}*/

 len7=document.frmMembRegi.sfax_txt.value.length;
 strnum = document.frmMembRegi.sfax_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Secondary fax ");
 document.frmMembRegi.sfax_txt.focus();
 return false;}
 
 if(document.frmMembRegi.sfax_txt.value.length>40)
  {alert("Enter valid Secondary fax ");
  document.frmMembRegi.sfax_txt.focus();
  return false;}
  if(isnotAlpha1(document.frmMembRegi.sfax_txt.value))
{ alert("Enter Valid Secondary fax ");
  document.frmMembRegi.sfax_txt.focus();
   return false; }
  
 }
}

return true;

}
// AJAX Validation for the User Sign up through admin
var httpRequest;

function usrStat()
{

if(document.frmMembRegi.usrname.value!="" && document.frmMembRegi.usrname.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmMembRegi.usrname.value;

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
        
        httpRequest.onreadystatechange =processUser; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUser() 
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
			document.frmMembRegi.usrname.value="";
			document.frmMembRegi.usrname.focus();
		}
		      
    } 
	
//================== user code existance ==================================
var codeRequest;  
 
  function checkUserCode(param){

		if(param.value.length==0 || param.value.indexOf(" ")==0)
				return;
			
				 //alert("document.frmMembRegi.sessUserCode.value :"+document.frmMembRegi.sessUserCode.value);
				 
				url1 = "UsrSignupAjax.do?process=chkUsrCode&UserCode="+escape(param.value);
			
				if (window.ActiveXObject){ 
					codeRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
				} 
				else if (window.XMLHttpRequest){ 
					codeRequest = new XMLHttpRequest(); 
				} 
			
				codeRequest.open("GET", url1, true);
					
				codeRequest.onreadystatechange = processRequested;    
				codeRequest.send(null);  
 } 
				   
	 function processRequested(){ 
		if (codeRequest.readyState == 4){ 
//		    alert(codeRequest.readyState);
			if(codeRequest.status == 200){ 
	//			alert(codeRequest.status);
					
					var xmlDoc = codeRequest.responseXML.documentElement;
					var userCode = codeRequest.responseXML.getElementsByTagName("userCode")[0].childNodes[0].nodeValue;
					
					
					if(userCode == "exists")
					{
					  alert("UserCode Exists Choose Another");
					  document.frmMembRegi.userCode.focus();
					  return false;
					}
            } 
            else 
            { 
                alert("Error loading page\n"+ codeRequest.status +":"+ codeRequest.statusText); 
            } 
		}	
	}				
