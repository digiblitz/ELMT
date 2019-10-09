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

if(document.frmEmpRegi.usrname.value!="" && document.frmEmpRegi.usrname.value.indexOf(" ")!=0)
{

    var email=document.frmEmpRegi.usrname.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+email; 

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
                alert("Login Name Exists.Choose Another !"); 
            	document.frmEmpRegi.usrname.focus();
                return false;
                }    
            } 
            else 
            { 
                alert("Error loading page\n"+ arHttpRequest.status +":"+ arHttpRequest.statusText); 
            } 
        } 
    } 

//-------------------------
//-------------------function for character validation in Names------------------
function isnotName(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}


//------------------------ALPHA NUMERIC------------------------------------------------------------------


function isnotAlphaNumeric(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?/=~.,;:][{}"+"\\"+"\'"+"\"";
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
//--------------------------------------isnotAlpha1---------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*()+|<>?/=-~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//------------------------isnotAlpha2-------------------------
function isnotAlpha2(str){
stringCheck="!@#$%^&*()+|<>?/=_~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//------------------------ISNOT ALPHA-------------------------------------------------------------------------

function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\"+"\"";
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
/*if(document.frmEmpRegi.USelect.value=="")
{alert(" Select the Salutation ");
 document.frmEmpRegi.USelect.focus();
 return false;}
*/

/*if(!(document.frmEmpRegi.USelect.value==""))
{ leno=document.frmEmpRegi.USelect.length;
 for(i=0;i<leno;i++)
 { if(document.frmEmpRegi.USelect[i].selected)
 { choseno=document.frmEmpRegi.USelect[i].value ;}}


}*/

//---------------------------------------FIRST NAME----------------------------------

if(document.frmEmpRegi.fname.value=="")
   {alert(" First Name cannot be empty ");
     document.frmEmpRegi.fname.focus();
    return false; }
if(document.frmEmpRegi.fname.value.indexOf(' ')==0)
{alert("Enter First Name ");
 document.frmEmpRegi.fname.focus();
 return false; }

if(isnotAlpha(document.frmEmpRegi.fname.value))
{ alert("Enter Valid First Name ");
  document.frmEmpRegi.fname.focus();
   return false; }
if(Dospace(document.frmEmpRegi.fname.value))
   { alert("Enter Valid First Name ");
     document.frmEmpRegi.fname.focus();
   return false; }
if( document.frmEmpRegi.fname.value.length>80 )
   { alert("Enter within 80 characters for First Name" );
      document.frmEmpRegi.fname.focus();
      return false; }
//--------------------------MIDDLE NAME-----------------------------------------


/*if(isnotAlpha(document.frmEmpRegi.mname.value))
{ alert("Enter Valid Middle Name ");
  document.frmEmpRegi.mname.focus();
   return false; }*/
   
if(Dospace(document.frmEmpRegi.mname.value)||((document.frmEmpRegi.mname.value.indexOf(' '))==0))
   { alert("Enter Valid Middle Name ");
     document.frmEmpRegi.mname.focus();
   return false; }
   
   if(isnotName(document.frmEmpRegi.mname.value))
 {alert("Enter a valid Middle Name");
  document.frmEmpRegi.mname.focus();
 return false;}
 
if( document.frmEmpRegi.mname.value.length>80 )
   { alert("Enter within 80 characters for Middle Name" );
      document.frmEmpRegi.mname.focus();
      return false; }

//----------------------------------LAST NAME--------------------------------------
if(document.frmEmpRegi.lname.value=="")
   {alert(" Last Name cannot be empty ");
     document.frmEmpRegi.lname.focus();
    return false; }
if(document.frmEmpRegi.lname.value.indexOf(' ')==0)
{alert("Enter Last Name  ");
 document.frmEmpRegi.lname.focus();
 return false; }

if(isnotAlpha(document.frmEmpRegi.lname.value))
{ alert("Enter Valid Last Name ");
  document.frmEmpRegi.lname.focus();
   return false; }
if(Dospace(document.frmEmpRegi.lname.value))
   { alert("Enter Valid Last Name ");
     document.frmEmpRegi.lname.focus();
   return false; }
if( document.frmEmpRegi.lname.value.length>80 )
   { alert("Enter within 80 characters of Last Name" );
      document.frmEmpRegi.lname.focus();
      return false; }

//--------------------------------SUFFIX---------------------------------------


if(document.frmEmpRegi.sname.value.indexOf(' ')==0)
{alert("Enter Suffix ");
 document.frmEmpRegi.sname.focus();
 return false; }


if(isnotAlphaNumeric(document.frmEmpRegi.sname.value))
{ alert("Enter Valid Suffix ");
  document.frmEmpRegi.sname.focus();
   return false; }
if(Dospace(document.frmEmpRegi.sname.value))
   { alert("Enter Valid Suffix ");
     document.frmEmpRegi.sname.focus();
   return false; }
if( document.frmEmpRegi.sname.value.length>20 )
   { alert("Enter within 20 characters for Suffix" );
      document.frmEmpRegi.sname.focus();
      return false; }


//___________________________________________DATE________________________________________________________
/*if(document.frmEmpRegi.birthmonth.value=="")
{alert(" Select Month in Date of Birth ");
 document.frmEmpRegi.birthmonth.focus();
 return false;}
 if(!(document.frmEmpRegi.birthmonth.value==""))
 { leno=document.frmEmpRegi.birthmonth.length;
  for(i=0;i<leno;i++)
  { if(document.frmEmpRegi.birthmonth[i].selected)
 { choseno=document.frmEmpRegi.birthmonth[i].value ;}}}
 
 //------month checking----
 var lyear=document.frmEmpRegi.birthyear.value;
 var lcheck=(lyear%4);

if((document.frmEmpRegi.birthmonth.selectedIndex== 2)&&(document.frmEmpRegi.birthday.value >29)&&(lcheck=="0"))
 {
	alert ("Date is not valid") ;
	document.frmEmpRegi.birthday.focus();
	return false;
 }
var lyear1=document.frmEmpRegi.birthyear.value;
 var lcheck1=(lyear1%4);
 
 if((document.frmEmpRegi.birthmonth.selectedIndex== 2)&&(document.frmEmpRegi.birthday.value >28)&&(lcheck1>0))
 {
	alert ("Date is not valid") ;
	document.frmEmpRegi.birthday.focus();
	return false;
 }
 if((document.frmEmpRegi.birthmonth.selectedIndex== 4)&&(document.frmEmpRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmEmpRegi.birthday.focus();
	return false;
 }
 if((document.frmEmpRegi.birthmonth.selectedIndex== 6)&&(document.frmEmpRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmEmpRegi.birthday.focus();
	return false;
 }
 if((document.frmEmpRegi.birthmonth.selectedIndex== 9)&&(document.frmEmpRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmEmpRegi.birthday.focus();
	return false;
 }
 if((document.frmEmpRegi.birthmonth.selectedIndex== 11)&&(document.frmEmpRegi.birthday.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmEmpRegi.birthday.focus();
	return false;
 }


 if(document.frmEmpRegi.birthday.value=="")
 {alert(" Select Day in Date of Birth");
  document.frmEmpRegi.birthday.focus();
  return false;}
  if(!(document.frmEmpRegi.birthday.value==""))
  { leno=document.frmEmpRegi.birthday.length;
   for(i=0;i<leno;i++)
   { if(document.frmEmpRegi.birthday[i].selected)
  { choseno=document.frmEmpRegi.birthday[i].value ;}}}


 if(document.frmEmpRegi.birthyear.value=="")
 {alert(" Select Year in Date of Birth");
  document.frmEmpRegi.birthyear.focus();
  return false;}
  if(!(document.frmEmpRegi.birthyear.value==""))
  { leno=document.frmEmpRegi.birthyear.length;
   for(i=0;i<leno;i++)
   { if(document.frmEmpRegi.birthyear[i].selected)
  { choseno=document.frmEmpRegi.birthyear[i].value ;}}}

//-----------------------------------------------------------------------------------------------------------
//-----------------------------------------GENDER
chosen="";
len = document.frmEmpRegi.gender.length ;
for(i=0;i<len;i++)
{if(document.frmEmpRegi.gender[i].checked)
  { chosen= document.frmEmpRegi.gender[i].value; }
}
if(chosen=="")
{alert("Select the Gender");
 
 return false;
}*/
//----------- Employee Id --------------------
if(document.frmEmpRegi.empId.value=="")
{ alert("Enter a Employee Id");
 document.frmEmpRegi.empId.focus();
 return false;}

if(document.frmEmpRegi.empId.value.indexOf(" ")==0)
{ alert("Enter a Valid Employee Id");
 document.frmEmpRegi.empId.focus();
 return false;}

 

//----------- login name ---------------------

if(document.frmEmpRegi.usrname.value=="")
{ alert("Enter a User Name");
 document.frmEmpRegi.usrname.focus();
 return false;}

if(document.frmEmpRegi.usrname.value.indexOf(" ")==0)
{ alert("Enter a Valid User Name");
 document.frmEmpRegi.usrname.focus();
 return false;}
 

if(isnotAlphaNumeric(document.frmEmpRegi.usrname.value))
 {alert("Enter a valid User Name");
  document.frmEmpRegi.usrname.focus();
 return false;}

if(document.frmEmpRegi.usrname.value.length>25)
 {alert("Enter a valid User Name");
  document.frmEmpRegi.usrname.focus();
 return false;}
 
 if(document.frmEmpRegi.usrname.value.length<4)
 {alert("Enter a valid User Name");
  document.frmEmpRegi.usrname.focus();
 return false;}
 
//----------------------------------------------------------------------------------------------------------------
//--------------------------------------EMAIL-----------------

 //---------------------------------------------------------------------------------------------------------------
 if(document.frmEmpRegi.email.value=="")
 {alert("Enter your Email ID");
  document.frmEmpRegi.email.focus();
 return false;}
 
if(document.frmEmpRegi.email.value.indexOf(" ")==0)
 {alert("Enter a Valid Email ID");
  document.frmEmpRegi.email.focus();
 return false;}

 if(!(document.frmEmpRegi.email.value== ""))
 { strmail=document.frmEmpRegi.email.value;
 firstat = strmail.indexOf("@");
 lastat = strmail.lastIndexOf("@");
 strmain=strmail.substring(0,firstat);
 strsub=strmail.substring(firstat,document.frmEmpRegi.email.value.length);
 if(strmail.length>120)
 {alert("Email is out of range");
  document.frmEmpRegi.email.focus();
 return false;}
 if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 )
 {alert("Enter valid Email ");
  document.frmEmpRegi.email.focus();
 return false;}
 if(isnotSpecial(strmain) || isnotSpecial(strsub))
 {alert("Enter valid Email ");
  document.frmEmpRegi.email.focus();
 return false;}
 k=0;
 strlen=strsub.length;
 for(i=0;i<strlen-1;i++)
 { if(strsub.charAt(i)=='.')
 {k=k+1;}}
 if(k>3)
 {alert("Enter valid Email ");
  document.frmEmpRegi.email.focus();
 return false;}
 if(firstat==-1 || lastat==-1)
 {alert("Enter valid Email" );
  document.frmEmpRegi.email.focus();
 return false;}
 if(Number(strmain))
 {alert("Enter valid Email ");
  document.frmEmpRegi.email.focus();
  return false;}
 if(firstat != lastat )
 {alert("Enter valid Email ");
  document.frmEmpRegi.email.focus();
 return false;}
 firstdot=strmail.indexOf(".",firstat);
 lastdot=strmail.lastIndexOf(".");
 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
 {alert("Enter valid Email ");
  document.frmEmpRegi.email.focus();
  return false;}
}
 
 
if(document.frmEmpRegi.password.value=="")
 {alert("Enter a password");
  document.frmEmpRegi.usrname.focus();
 return false;}

 if(document.frmEmpRegi.counBaseLoc.value=="")
 {alert("Please choose Base Country");
  document.frmEmpRegi.counBaseLoc.focus();
 return false;}

  if(document.frmEmpRegi.baseLoc.value=="")
 {alert("Please choose Base Country Location");
  document.frmEmpRegi.baseLoc.focus();
 return false;}

 if(document.frmEmpRegi.curCoun.value=="")
 {alert("Please choose Current Country");
  document.frmEmpRegi.curCoun.focus();
 return false;}

 if(document.frmEmpRegi.curLoc.value=="")
 {alert("Please choose Current Country Location");
  document.frmEmpRegi.curLoc.focus();
 return false;}

 if(document.frmEmpRegi.role.value=="")
 {alert("Please choose Role");
  document.frmEmpRegi.role.focus();
 return false;}

 if(document.frmEmpRegi.lob.value=="")
 {alert("Please choose LOB(s)");
  document.frmEmpRegi.lob.focus();
 return false;}

 if(document.frmEmpRegi.dept.value=="")
 {alert("Please choose Department(s)");
  document.frmEmpRegi.dept.focus();
 return false;}

 if(document.frmEmpRegi.repSup.value=="")
 {alert("Please choose Reporting Supervisor(s)");
  document.frmEmpRegi.repSup.focus();
 return false;}

 if(document.frmEmpRegi.supEmail.value=="")
 {alert("Please choose Reporting Supervisor(s) Email");
  document.frmEmpRegi.supEmail.focus();
 return false;}
 




//------------------------------------------------------------------------------------------------
//----------------------------------------Password
/*if(document.frmEmpRegi.pwd.value=="")
{alert("Enter Password ");
 document.frmEmpRegi.pwd.focus();
 return false;}
if(document.frmEmpRegi.pwd.value.length<6)

{alert("Enter Valid Password ");
document.frmEmpRegi.pwd.focus();
 return false;}
if(document.frmEmpRegi.pwd.value.length>12)
{alert("Enter Valid Password ");
document.frmEmpRegi.pwd.focus();
 return false;}
 if(Dospace(document.frmEmpRegi.pwd.value))
     { alert("Enter Password ");
       document.frmEmpRegi.pwd.focus();
   return false; }

/*   if(isnotAlphaNumeric(document.frmEmpRegi.pwd.value))
   { alert("Enter Valid Password");
     document.frmEmpRegi.pwd.focus();
   return false; }*/

 /*  strnme=document.frmEmpRegi.pwd.value;
      	fnme=1;
      	for(j=0;j<document.frmEmpRegi.pwd.value.length;j++)
      	{ if(stringCheck3.indexOf(strnme.charAt(j))!=-1)
      	   { fnme=0;} }
      	if(fnme==0)
      	{alert("Enter Valid Password ");
      	 document.frmEmpRegi.pwd.focus();
	return false;}
//---------------------------------------------------------------------------------------------------
//------------------------------------RETYPE Password---------------------------------------


if(document.frmEmpRegi.cpwd.value=="")
{alert(" Enter Re-Type Password ");
 document.frmEmpRegi.cpwd.focus();
 return false;}
var pwd1=document.frmEmpRegi.pwd.value;
var rpwd=document.frmEmpRegi.cpwd.value;
if(pwd1!=rpwd)
{
	alert("Password Mismatch");
	document.frmEmpRegi.cpwd.focus();
	return false;
}
	


//--------------------------------------------------------------------------------------------------------

//______________________________________________Question_________________________________________________

if(document.frmEmpRegi.QSelect.value=="")
{alert(" Select your Secret Question ");
 document.frmEmpRegi.QSelect.focus();
 return false;}


if(!(document.frmEmpRegi.QSelect.value==""))
{ leno=document.frmEmpRegi.QSelect.length;
 for(i=0;i<leno;i++)
 { if(document.frmEmpRegi.QSelect[i].selected)
 { choseno=document.frmEmpRegi.QSelect[i].value ;}}


}
//_________________________________________________Answer_____________________________________________________

if(document.frmEmpRegi.ans.value=="")
{alert(" Enter your Answer to the secret Question ");
 document.frmEmpRegi.ans.focus();
 return false;}
if(document.frmEmpRegi.ans.value.length>255)
 {alert("Enter Answer with in 255 characters ");
 document.frmEmpRegi.ans.focus();
return false;}
if(Dospace(document.frmEmpRegi.ans.value))
 	   { alert("Enter Valid Answer ");
 	     document.frmEmpRegi.ans.focus();
	   return false; }
	   
	   if(document.frmEmpRegi.ans.value.indexOf(' ')==0){
		alert("Enter Valid Answer ");
    document.frmEmpRegi.ans.focus();
    return false; }*/
	   
//--------------------------------------------------primary----------------------------------------------------
//____________________________________________________Address1_____________________________________________________



	if(document.frmEmpRegi.padd_txt.value==""){
		alert(" Enter Primary Address ");
		document.frmEmpRegi.padd_txt.focus();
		return false;
		}
	if(document.frmEmpRegi.padd_txt.value.length>255){
		alert("Enter Primary Address with in 255 characters");
		document.frmEmpRegi.padd_txt.focus();
		return false;
		}
	if(document.frmEmpRegi.padd_txt.value.indexOf(' ')==0){
		alert("Enter Valid Primary Address ");
    document.frmEmpRegi.padd_txt.focus();
    return false; }
	
	if(Dospace(document.frmEmpRegi.padd_txt.value))
 	   { alert("Enter Valid Primary Address");
 	     document.frmEmpRegi.padd_txt.focus();
	   return false; }
//____________________________________________________Address2_____________________________________________________

	if(document.frmEmpRegi.padd_txt2.value.length>255){
		alert("Enter Primary Address with in 255 characters");
		document.frmEmpRegi.padd_txt2.focus();
		return false;
		}
		
	if(Dospace(document.frmEmpRegi.padd_txt2.value)){
		alert("Enter Valid Primary Address ");
		document.frmEmpRegi.padd_txt2.focus();
		return false; 
	}
	if(document.frmEmpRegi.padd_txt2.value.indexOf(" ")==0){
		alert("Enter Valid Primary Address ");
		document.frmEmpRegi.padd_txt2.focus();
		return false;
		}
//__________________________________________city___________________________________________________________________


	
	if(document.frmEmpRegi.pcity_txt.value=="")
	   {alert(" Enter City Name in Primary Address ");
	     document.frmEmpRegi.pcity_txt.focus();
	    return false; }
	if(document.frmEmpRegi.pcity_txt.value.indexOf(' ')==0)
	{alert("Enter City ");
	 document.frmEmpRegi.pcity_txt.focus();
	 return false; }
	
	if(isnotAlpha2(document.frmEmpRegi.pcity_txt.value))
	{ alert("Enter Valid City in Primary Address ");
	  document.frmEmpRegi.pcity_txt.focus();
	   return false; }
	if(Dospace(document.frmEmpRegi.pcity_txt.value))
	   { alert("Enter Valid City in Primary Address");
	     document.frmEmpRegi.pcity_txt.focus();
	   return false; }
	if( document.frmEmpRegi.pcity_txt.value.length>80 )
	   { alert("Enter within 80 characters for City in Primary Address" );
	      document.frmEmpRegi.pcity_txt.focus();
      return false; }
	

//___________________________________________________ZipCode_________________________________________________________
if(document.frmEmpRegi.pzip_txt.value==""){
		alert("Enter Zipcode in Primary Address");
		document.frmEmpRegi.pzip_txt.focus();
		return false;
	    }
     
    if((document.frmEmpRegi.pzip_txt.value.length <3&& document.frmEmpRegi.pcountry_sel.value!="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmEmpRegi.pzip_txt.focus();
        return false;
		}
 
    if((document.frmEmpRegi.pzip_txt.value.length >20 && document.frmEmpRegi.pcountry_sel.value!="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmEmpRegi.pzip_txt.focus();
        return false;
		}
	if((document.frmEmpRegi.pzip_txt.value.length !=5&& document.frmEmpRegi.pcountry_sel.value=="USA")){
		alert("Enter Valid Zipcode in Primary Address");
        document.frmEmpRegi.pzip_txt.focus();
        return false;
		}
         
	if(document.frmEmpRegi.pzip_txt.value.indexOf(" ")==0){
		alert("Enter Valid Zipcode in Primary Address");
		document.frmEmpRegi.pzip_txt.focus();
		return false; 
	}
//___________________________________________________Country______________________________________________________

//__________________________________________________________________________________________________________________

 	var cdln = "";
 	if(typeof(window.document.frmEmpRegi.pcountry_sel) == 'object'){
 		if (window.document.frmEmpRegi.pcountry_sel.value != ""){
 			var cemail;
 			cemail = window.document.frmEmpRegi.pcountry_sel[window.document.frmEmpRegi.pcountry_sel.selectedIndex].value;
 			cdln = cemail;
 		}
 		if(cdln == "Select One"){
 			alert("Please select Country in Primary Address");
 			window.document.frmEmpRegi.pcountry_sel.focus();
 			return false;
 		}
 	}

 	if ( document.frmEmpRegi.pcountry_sel.selectedIndex == 0 ){
         alert ( "Please select Country Name in Primary Address" );
 		document.frmEmpRegi.pcountry_sel.focus();
         return false;
     }

 	var edln = "";
 	if(typeof(window.document.frmEmpRegi.pstate_sel) == 'object'){
 		if (window.document.frmEmpRegi.pstate_sel.value != ""){
 			var email;
 			email = window.document.frmEmpRegi.pstate_sel[window.document.frmEmpRegi.pstate_sel.selectedIndex].value;
 			edln = email;
 		}
 		if(edln.length == 1 ){
 			alert("Please select State in Primary Address");
 			window.document.frmEmpRegi.pstate_sel.focus();
 			return false;
 		}
 	}
//___________________________________________________ State ______________________________________________________

//__________________________________________________________________________________________________________________


 	var cdln = "";
 	if(typeof(window.document.frmEmpRegi.pstate_sel) == 'object'){
 		if (window.document.frmEmpRegi.pstate_sel.value != ""){
 			var cemail;
 			cemail = window.document.frmEmpRegi.pstate_sel[window.document.frmEmpRegi.pstate_sel.selectedIndex].value;
 			cdln = cemail;
 		}
 		if(cdln == "Select One"){
 			alert("Please select State in Primary Address");
 			window.document.frmEmpRegi.pstate_sel.focus();
 			return false;
 		}
 	}

 	if ( document.frmEmpRegi.pstate_sel.selectedIndex == 0 ){
         alert ( "Please select State Name in Primary Address" );
 		document.frmEmpRegi.pstate_sel.focus();
         return false;
     }

 	var edln = "";
 	if(typeof(window.document.frmEmpRegi.pstate_sel) == 'object'){
 		if (window.document.frmEmpRegi.pstate_sel.value != ""){
 			var email;
 			email = window.document.frmEmpRegi.pstate_sel[window.document.frmEmpRegi.pstate_sel.selectedIndex].value;
 			edln = email;
 		}
 		if(edln.length == 1 ){
 			alert("Please select State in Primary Address");
 			window.document.frmEmpRegi.pstate_sel.focus();
 			return false;
 		}
 	}
	
//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER Primary Phone Number OR Primary mobile Number
if(document.frmEmpRegi.pphone_txt.value=="" )
{ alert("Enter Primary Phone Number");
  document.frmEmpRegi.pphone_txt.focus();
  return false;}
  if(Dospace(document.frmEmpRegi.pphone_txt.value)){
			alert("Enter Valid Phone Number in Primary Address");
	     	document.frmEmpRegi.pphone_txt.focus();
	  		return false;
			}
	if(document.frmEmpRegi.pphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number in Primary Address");
		document.frmEmpRegi.pphone_txt.focus();
		return false; 
	}

//--------------------------------Primary Phone Number---------------------------------------


if(document.frmEmpRegi.pphone_txt.value!=""){
 len7=document.frmEmpRegi.pphone_txt.value.length;
 strnum = document.frmEmpRegi.pphone_txt.value;
  var GoodChars = "0123456789()- ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Phone Number");
 document.frmEmpRegi.pphone_txt.focus();
 return false;}
 if(document.frmEmpRegi.pphone_txt.value.length>40)
  {alert("Enter valid Primary Phone Number");
  document.frmEmpRegi.pphone_txt.focus();
  return false;}
}

	

//----------------------------------Primary MOBILE---------------------------------------


if(document.frmEmpRegi.pmob_txt.value!="")
{		
 len7=document.frmEmpRegi.pmob_txt.value.length;
 strnum = document.frmEmpRegi.pmob_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary mobile Number");
 document.frmEmpRegi.pmob_txt.focus();
 return false;}
 
 if(isnotAlpha1(document.frmEmpRegi.pmob_txt.value))
{ alert("Enter Valid Primary mobile Number ");
  document.frmEmpRegi.pmob_txt.focus();
   return false; }
 }



 //-----------------------------------------Primary fax--------------------------------
 


if(document.frmEmpRegi.pfax_txt.value!="")
{		

 len7=document.frmEmpRegi.pfax_txt.value.length;
 strnum = document.frmEmpRegi.pfax_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Primary fax ");
 document.frmEmpRegi.pfax_txt.focus();
 return false;}
 
 if(document.frmEmpRegi.pfax_txt.value.length>40)
  {alert("Enter valid Primary fax ");
  document.frmEmpRegi.pfax_txt.focus();
  return false;}
  if(isnotAlpha1(document.frmEmpRegi.pfax_txt.value))
{ alert("Enter Valid Primary fax ");
  document.frmEmpRegi.pfax_txt.focus();
   return false; }
  
 }


//------------------------------------------------------checking in sec.add------------------------------------------
/*if(document.frmEmpRegi.addr.value=="Primary"){
	if(Number(document.frmEmpRegi.scountry_txt.value))
	{ alert("Enter Valid Country  in Secondary Address ");
	  document.frmEmpRegi.scountry_txt.focus();
	   return false; }
	   	   if(Number(document.frmEmpRegi.sstate_txt.value))
	{ alert("Enter Valid State in Secondary Address ");
	  document.frmEmpRegi.sstate_txt.focus();
	   return false; }
	   if(Number(document.frmEmpRegi.scity_txt.value))
	{ alert("Enter Valid City in Secondary Address ");
	  document.frmEmpRegi.scity_txt.focus();
	   return false; }
	   if(!Number(document.frmEmpRegi.szip_txt.value))
{alert("Enter valid Zipcode in Secondary Address");
 document.frmEmpRegi.szip_txt.focus();
 return false;}
	   
	   }*/
	
//------------------------------------------------------SECONDARY-----------------------------------------------------
//____________________________________________________Address1_____________________________________________________

var chosen2="";
var len2 = document.frmEmpRegi.addr.length ;
for(i=0;i<len2;i++)
{if(document.frmEmpRegi.addr[i].checked)
  { chosen2= document.frmEmpRegi.addr[i].value; }
}

if(chosen2=="Primary"){

if(document.frmEmpRegi.sadd_txt.value=="")
{alert(" Enter Secondary Address ");
 document.frmEmpRegi.sadd_txt.focus();
 return false;}
if(document.frmEmpRegi.sadd_txt.value.length>255)
{alert("Enter Secondary Address with in 255 characters");
document.frmEmpRegi.sadd_txt.focus();
return false;}
	if(Dospace(document.frmEmpRegi.sadd_txt.value)){
		alert("Enter Valid Secondary Address ");
		document.frmEmpRegi.sadd_txt.focus();
		return false;
	}
	if(document.frmEmpRegi.sadd_txt.value.indexOf(" ")==0){
		alert("Enter Valid Secondary Address");
		document.frmEmpRegi.sadd_txt.focus();
		return false; 
	}
//____________________________________________________Address2_____________________________________________________

if(document.frmEmpRegi.sadd_txt1.value.length>255)
{alert("Enter Secondary Address with in 255 characters");
document.frmEmpRegi.sadd_txt1.focus();
return false;}

	if(document.frmEmpRegi.sadd_txt1.value.indexOf(" ")==0){
		alert("Enter Valid Secondary Address");
		document.frmEmpRegi.sadd_txt1.focus();
		return false; 
	}
//__________________________________________city___________________________________________________________________


	
	if(document.frmEmpRegi.scity_txt.value=="")
	   {alert(" City cannot be empty in Secondary Address");
	     document.frmEmpRegi.scity_txt.focus();
	    return false; }
	if(document.frmEmpRegi.scity_txt.value.indexOf(' ')==0)
	{alert("Enter City in Secondary Address");
	 document.frmEmpRegi.scity_txt.focus();
	 return false; }
	
	if(isnotAlpha2(document.frmEmpRegi.scity_txt.value))
	{ alert("Enter Valid City in Secondary Address");
	  document.frmEmpRegi.scity_txt.focus();
	   return false; }
	if(Dospace(document.frmEmpRegi.scity_txt.value))
	   { alert("Enter  Valid City in Secondary Address ");
	     document.frmEmpRegi.scity_txt.focus();
	   return false; }
	if( document.frmEmpRegi.scity_txt.value.length>80 )
	   { alert("Enter within 80 characters for City in Secondary Address" );
	      document.frmEmpRegi.scity_txt.focus();
      return false; }
	  
	

//___________________________________________________ZipCode_________________________________________________________
if(document.frmEmpRegi.szip_txt.value==""){
			alert("Enter Zipcode in Secondary Address");
			document.frmEmpRegi.szip_txt.focus();
			return false;
	    }
     		
    	if((document.frmEmpRegi.szip_txt.value.length <3&& document.frmEmpRegi.sCountry_sel.value!="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmEmpRegi.szip_txt.focus();
			return false;
		}
 
    	if((document.frmEmpRegi.szip_txt.value.length >20 && document.frmEmpRegi.sCountry_sel.value!="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmEmpRegi.szip_txt.focus();
			return false;
		}
		 if((document.frmEmpRegi.szip_txt.value.length !=5&& document.frmEmpRegi.sCountry_sel.value=="USA")){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmEmpRegi.szip_txt.focus();
			return false;
		}
 		       
		if(Dospace(document.frmEmpRegi.szip_txt.value)){
			alert("Enter Valid Zipcode in Secondary Address");
	     	document.frmEmpRegi.szip_txt.focus();
	  		return false;
			}	
		if(document.frmEmpRegi.szip_txt.value.indexOf(" ")==0){
			alert("Enter Valid Zipcode in Secondary Address");
			document.frmEmpRegi.szip_txt.focus();
			return false; 
		}		

//___________________________________________________Country______________________________________________________
if(document.frmEmpRegi.sCountry_sel.selectedIndex==0)
	   {alert(" Country cannot be empty in Secondary Address");
	     document.frmEmpRegi.sCountry_sel.focus();
	    return false; }
	/*if(document.frmEmpRegi.scountry_txt.value.indexOf(' ')==0)
	{alert("Enter Country  in Secondary Address ");
	 document.frmEmpRegi.scountry_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmEmpRegi.scountry_txt.value))
	{ alert("Enter Valid Country  in Secondary Address ");
	  document.frmEmpRegi.scountry_txt.focus();
	   return false; }
   if(Dospace(document.frmEmpRegi.scountry_txt.value))
	   { alert("Enter Valid Country  in Secondary Address ");
	     document.frmEmpRegi.scountry_txt.focus();
	   return false; }
	if( document.frmEmpRegi.scountry_txt.value.length>80 )
	   { alert("Enter within 80 characters for Country  in Secondary Address" );
	      document.frmEmpRegi.scountry_txt.focus();
      return false; }*/

	  //------------------------------------------------State-------------------------------------------------------------	 
	if(document.frmEmpRegi.sState_sel.selectedIndex==0)
	   {alert(" State cannot be empty in Secondary Address");
	     document.frmEmpRegi.sState_sel.focus();
	    return false; }
	/*if(document.frmEmpRegi.sstate_txt.value.indexOf(' ')==0)
	{alert("Enter State in Secondary Address");
	 document.frmEmpRegi.sstate_txt.focus();
	 return false; }
	
	if(isnotAlpha(document.frmEmpRegi.sstate_txt.value))
	{ alert("Enter  Valid State in Secondary Address");
	  document.frmEmpRegi.sstate_txt.focus();
	   return false; }

	if(Dospace(document.frmEmpRegi.sstate_txt.value))
	   { alert("Enter  Valid State in Secondary Address ");
	     document.frmEmpRegi.sstate_txt.focus();
	   return false; }
	if( document.frmEmpRegi.sstate_txt.value.length>80 )
	   { alert("Enter within 80 characters for State in Secondary Address" );
	      document.frmEmpRegi.sstate_txt.focus();
      return false; }*/	 
//----------------------IF ENTER EITHER Secondary Phone Number OR Secondary mobile Number
if(document.frmEmpRegi.sphone_txt.value=="")
{ alert("Enter Secondary Phone Number");
  document.frmEmpRegi.sphone_txt.focus();
  return false;}
if(Dospace(document.frmEmpRegi.sphone_txt.value)){
			alert("Enter Valid Phone Number in Secondary Address");
	     	document.frmEmpRegi.sphone_txt.focus();
	  		return false;
			}
			if(document.frmEmpRegi.sphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number in Secondary Address");
		document.frmEmpRegi.sphone_txt.focus();
		return false; 
	}
//--------------------------------Secondary Phone Number---------------------------------------


if(document.frmEmpRegi.sphone_txt.value!=""){		  
	len7=document.frmEmpRegi.sphone_txt.value.length;
	strnum = document.frmEmpRegi.sphone_txt.value;
	var GoodChars = "0123456789()- ";
	valid = 1;
	for(j=0;j<len7;j++){
		if(GoodChars.indexOf(strnum.charAt(j))==-1){
			valid=0;
		}
	}
	if(valid!=1){
		alert("Enter valid Secondary Phone Number");
		document.frmEmpRegi.sphone_txt.focus();
		return false;
	}
	if(document.frmEmpRegi.sphone_txt.value.length>40){
		alert("Enter valid Secondary Phone Number");
		document.frmEmpRegi.sphone_txt.focus();
		return false;
	}
}

	

//----------------------------------Secondary MOBILE---------------------------------------


if(document.frmEmpRegi.smob_txt.value!="")
{		
 len7=document.frmEmpRegi.smob_txt.value.length;
 strnum = document.frmEmpRegi.smob_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Secondary mobile Number");
 document.frmEmpRegi.smob_txt.focus();
 return false;}
 
 if(isnotAlpha1(document.frmEmpRegi.smob_txt.value))
{ alert("Enter Valid Secondary mobile Number ");
  document.frmEmpRegi.smob_txt.focus();
   return false; }
 }



 //-----------------------------------------Secondary fax--------------------------------
 


if(document.frmEmpRegi.sfax_txt.value!="")
{		

 len7=document.frmEmpRegi.sfax_txt.value.length;
 strnum = document.frmEmpRegi.sfax_txt.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Primary Secondary fax ");
 document.frmEmpRegi.sfax_txt.focus();
 return false;}
 
 if(document.frmEmpRegi.sfax_txt.value.length>40)
  {alert("Enter valid Secondary fax ");
  document.frmEmpRegi.sfax_txt.focus();
  return false;}
  if(isnotAlpha1(document.frmEmpRegi.sfax_txt.value))
{ alert("Enter Valid Secondary fax ");
  document.frmEmpRegi.sfax_txt.focus();
   return false; }
  
 }
}

return true;

}

//-------------------------------- User status validation Ajax Script ------------------------------------------------

var httpRequest;

function usrStat()
{

if(document.frmEmpRegi.usrname.value!="" && document.frmEmpRegi.usrname.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmEmpRegi.usrname.value;

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
			document.frmEmpRegi.usrname.value="";
			document.frmEmpRegi.usrname.focus();
		}
		      
    } 
    
  //-------------------------------- Employee status validation Ajax Script ------------------------------------------------

    var httpRequest;

    function empStat()
    {

    if(document.frmEmpRegi.empId.value!="" && document.frmEmpRegi.empId.value.indexOf(' ')!=0)
    	{

       var chkEmpId=document.frmEmpRegi.empId.value;

       /** 
        * This method is called when the author is selected 
        * It creates XMLHttpRequest object to communicate with the  
        * servlet  
        */ 
            url= "UsrSignupAjax.do?process=checkEmpId&chkEmpId="+chkEmpId; 

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
                     var salutationXML = httpRequest.responseXML.getElementsByTagName("empDet")[0]; 
                    // var userNameXML = httpRequest.responseXML.getElementsByTagName("userName")[0]; 
                    // var salutationText = salutationXML.childNodes[0].nodeValue; 
                    // var userName = userNameXML.childNodes[0].nodeValue; 
                    // var password = userNameXML.childNodes[1].nodeValue; 
                    // var email = userNameXML.childNodes[2].nodeValue; 
         
                    //Update the HTML 
					// alert("salutationText updateHTML before"+salutationText);
                  //  checkStatus(salutationXML,userNameXML); 
                     checkStatus(salutationXML);
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
        function checkStatus(salutationXML) 
        { 
            //The node valuse will give actual data 
			// alert("afrer updateHTML");
            var salutationText = salutationXML.childNodes[0].nodeValue; 
            var employeeId = salutationXML.childNodes[1].nodeValue; 
            var userName = salutationXML.childNodes[2].nodeValue; 
            var password = salutationXML.childNodes[3].nodeValue; 
            var email = salutationXML.childNodes[4].nodeValue; 
			

    		if(salutationText != "false")
    		{
				//alert("if part*"+employeeId.length+"*");
    			if(employeeId!="" && employeeId!=null){
    			alert("Employee Id already Exists Chose Another !");
    			document.frmEmpRegi.empId.value="";
				document.frmEmpRegi.usrname.value="";
				document.frmEmpRegi.email.value="";
    			document.frmEmpRegi.empId.focus();
    		    }
    		   else
    			{
    			alert("Employee Id does not exist!");
				document.frmEmpRegi.empId.value="";
				document.frmEmpRegi.usrname.value="";
				document.frmEmpRegi.email.value="";
    			document.frmEmpRegi.empId.focus();
    			}
    		}
    		else
    			{
    			//alert("else part*"+employeeId+"*");
//    			empDet(chkEmpId);
    			if(employeeId!="" && employeeId!=null){
//alert("else if");
    			document.frmEmpRegi.usrname.value=userName;
    			document.frmEmpRegi.password.value=password;
    			document.frmEmpRegi.email.value=email; 
    			}
    			else
    				{
    				alert("Employee Id does not exist !");
					document.frmEmpRegi.empId.value="";
					document.frmEmpRegi.usrname.value="";
					document.frmEmpRegi.email.value="";
    			    document.frmEmpRegi.empId.focus();
                        } 
    			}
        }

        
      //------------------------------ Ajax Script for Location loading-------------------------
        
        
        var req;
          
        function retrieveBaseLoc(methodName,param) {  
        	
          if(param.value.length>0 && param.value!="") {
         
         var paramName = param.name;
          var url = null;
         

       
             url = "dropDown.do?method="+escape(methodName)+"&cId="+escape(param.value);   
           
       
              if (window.XMLHttpRequest) {
                   req = new XMLHttpRequest();
        		   
               } else if (window.ActiveXObject) {
                   req = new ActiveXObject("Microsoft.XMLHTTP");
               }
             
                   req.onreadystatechange = displayLocList;
                 
        		                   
               req.open("GET", url, true);
             
              
            	
               req.send(null);

            } else { 
        		
        	    setToDefault('lobId');
               
            }
          }  
          
          
          function displayLocList() {
			 
            if (req.readyState == 4) { // Complete
				// alert("Please choose base location.");
              if (req.status == 200) { // OK response  
               var xmlDoc = req.responseXML.documentElement;
        	   var xRows= xmlDoc.getElementsByTagName('entry');
        	    var objDDL = document.getElementById("baseLoc");  
        	    objDDL.innerHTML="";
        		
        			var rootObj=document.createElement("option");	
        		var attrib=document.createAttribute("value");	
        			attrib.value="";
        		rootObj.setAttributeNode(attrib);	
        		newtext=document.createTextNode('Select One');
        		rootObj.appendChild(newtext);
        		objDDL.appendChild(rootObj);
        			
        	    for (var i=0; i<xRows.length; i++) {
        			var nameNodes = xRows[i].getElementsByTagName("optionValue");
        			var valueNodes = xRows[i].getElementsByTagName("optionText");
        			if (nameNodes.length > 0 && valueNodes.length > 0) {
        			  var theValue = nameNodes[0].firstChild.nodeValue;
        			  var theText = valueNodes[0].firstChild.nodeValue;          
        			}
        			var option = new Option(theText,theValue);
        			 try {
						 
        				objDDL.add(option,null);     
        				
        			 }catch(e){
        				objDDL.add(option,-1);
        			 }
        		  }
        		  
              } else {
                alert("Problem: " + req.statusText);
              }
            }
          }
          
          //------------------------------ Ajax Script for Lob loading-------------------------
            
            
            var req;
              
            function retrieveLobList() {  
            	var baseId = document.frmEmpRegi.counBaseLoc.value;
            	var curId = document.frmEmpRegi.curCoun.value;
            	
             
             
            
              var url = null;
             
           
                
                	  url = "dropDown.do?method=callLobList&bId="+baseId+"&cId="+curId;   
                	
           
                  if (window.XMLHttpRequest) {
                       req = new XMLHttpRequest();
            		   
                   } else if (window.ActiveXObject) {
                       req = new ActiveXObject("Microsoft.XMLHTTP");
                   }
                 
                                        
                    	   req.onreadystatechange = displayLobList;
                  	
            		                   
                   req.open("GET", url, true);
                  

                	 
                   req.send(null);

               
              }  
              
          
          function displayLobList() {
			   
            if (req.readyState == 4) {
				//alert("Please choose lob(s).");// Complete
              if (req.status == 200) { // OK response  
               var xmlDoc = req.responseXML.documentElement;
        	   var xRows= xmlDoc.getElementsByTagName('entry');
        	    var objDDL = document.getElementById("lob");  
        	    objDDL.innerHTML="";
        		
        			var rootObj=document.createElement("option");	
        		var attrib=document.createAttribute("value");	
        			attrib.value="";
        		rootObj.setAttributeNode(attrib);	
        		//newtext=document.createTextNode('Select One');
        		//rootObj.appendChild(newtext);
        		objDDL.appendChild(rootObj);
        			
        	    for (var i=0; i<xRows.length; i++) {
        			var nameNodes = xRows[i].getElementsByTagName("optionValue");
        			var valueNodes = xRows[i].getElementsByTagName("optionText");
        			if (nameNodes.length > 0 && valueNodes.length > 0) {
        			  var theValue = nameNodes[0].firstChild.nodeValue;
        			  var theText = valueNodes[0].firstChild.nodeValue;          
        			}
        			var option = new Option(theText,theValue);
        			 try {
        				objDDL.add(option,null);     
        				
        			 }catch(e){
        				objDDL.add(option,-1);
        			 }
        		  }
        		  
              } else {
                alert("Problem: " + req.statusText);
              }
            }
          }
         
        //------------------------------ Ajax Script for Current Location loading-------------------------
          
          
          var req;
            
          function retrieveCurLoc(methodName,param) {  
            if(param.value.length>0 && param.value!="") {
           
           var paramName = param.name;
            var url = null;

         
               url = "dropDown.do?method="+escape(methodName)+"&cId="+escape(param.value);   
          
         
                if (window.XMLHttpRequest) {
                     req = new XMLHttpRequest();
          		   
                 } else if (window.ActiveXObject) {
                     req = new ActiveXObject("Microsoft.XMLHTTP");
                 }
               
                     req.onreadystatechange = displayCurLocList;
          		                   
                 req.open("GET", url, true);
                 req.send(null);

              } else { 
          		
          	    setToDefault('lobId');
                 
              }
            }  
            
            
            function displayCurLocList() {
				
              if (req.readyState == 4) { // Complete
			 //  alert("Please choose current location.");
                if (req.status == 200) { // OK response  
                 var xmlDoc = req.responseXML.documentElement;
          	   var xRows= xmlDoc.getElementsByTagName('entry');
          	    var objDDL = document.getElementById("curLoc");  
          	    objDDL.innerHTML="";
          		
          			var rootObj=document.createElement("option");	
          		var attrib=document.createAttribute("value");	
          			attrib.value="";
          		rootObj.setAttributeNode(attrib);	
          		newtext=document.createTextNode('Select One');
          		rootObj.appendChild(newtext);
          		objDDL.appendChild(rootObj);
          			
          	    for (var i=0; i<xRows.length; i++) {
          			var nameNodes = xRows[i].getElementsByTagName("optionValue");
          			var valueNodes = xRows[i].getElementsByTagName("optionText");
          			if (nameNodes.length > 0 && valueNodes.length > 0) {
          			  var theValue = nameNodes[0].firstChild.nodeValue;
          			  var theText = valueNodes[0].firstChild.nodeValue;          
          			}
          			var option = new Option(theText,theValue);
          			 try {
          				objDDL.add(option,null);     
          				
          			 }catch(e){
          				objDDL.add(option,-1);
          			 }
          		  }
          		  
                } else {
                  alert("Problem: " + req.statusText);
                }
              }
            }
              
            
            
            //------------------------------ Ajax Script for Dept loading-------------------------
            
            
            var req;
              
            function retrieveDept(methodName,param) {  
             // if(param.value.length>0 && param.value!="") {
             
             var paramVal = document.getElementById('lob');
			 var selectedArray = new Array();
			var i;
			  var count = 0;
			  var url = null;
			 for (i=0; i<paramVal.options.length; i++) {
				 //alert("count=="+count);
			    if (paramVal.options[i].selected) {
			      selectedArray[count] = paramVal.options[i].value;
				 //  alert('paramVal ' +paramVal.options[i].value);
			      count++;
			    }
			  }
			    var roleVal=document.getElementById('role').value;
				//alert(roleVal);
         //  alert('selectedArray.toString()'+selectedArray.toString());
                 url = "dropDown.do?method="+escape(methodName)+"&lobId="+escape(selectedArray.toString())+"&role="+escape(roleVal);   
				 //alert(url);
            
           
                  if (window.XMLHttpRequest) {
                       req = new XMLHttpRequest();
            		   
                   } else if (window.ActiveXObject) {
                       req = new ActiveXObject("Microsoft.XMLHTTP");
                   }
                 
                       req.onreadystatechange = displayDeptList;
            		                   
                   req.open("GET", url, true);
                   req.send(null);

               // } else { 
            		
            	    //setToDefault('lobId');
                   
               // }
              }  
              
              
              function displayDeptList() {
				  
                if (req.readyState == 4) { // Complete
				// alert("Please choose departments(s).");
                  if (req.status == 200) { // OK response  
                   var xmlDoc = req.responseXML.documentElement;
            	   var xRows= xmlDoc.getElementsByTagName('entry');
            	    var objDDL = document.getElementById("dept");  
            	    objDDL.innerHTML="";
            		
            			var rootObj=document.createElement("option");	
            		var attrib=document.createAttribute("value");	
            			attrib.value="";
            		rootObj.setAttributeNode(attrib);	
            		//newtext=document.createTextNode('Select One');
            		//rootObj.appendChild(newtext);
            		objDDL.appendChild(rootObj);
            			
            	    for (var i=0; i<xRows.length; i++) {
            			var nameNodes = xRows[i].getElementsByTagName("optionValue");
            			var valueNodes = xRows[i].getElementsByTagName("optionText");
            			if (nameNodes.length > 0 && valueNodes.length > 0) {
            			  var theValue = nameNodes[0].firstChild.nodeValue;
            			  var theText = valueNodes[0].firstChild.nodeValue;          
            			}
            			var option = new Option(theText,theValue);
            			 try {
            				objDDL.add(option,null);     
            				
            			 }catch(e){
            				objDDL.add(option,-1);
            			 }
            		  }
            		  
                  } else {
                    alert("Problem: " + req.statusText);
                  }
                }
              }
              
     
              //------------------------------ Ajax Script for Dept loading-------------------------
              
              
              var req;
                
              function retrieveDeptSup(methodName,param) {  
				  
               // if(param.value.length>0 && param.value!="") {
               
               var paramValLob = document.getElementById('lob');
               var paramValDept = document.getElementById('dept');
              
  			 var selectedArrayI = new Array();
  			var selectedArrayJ = new Array();
			var selectedArrayJText = new Array();
  			var i,j;
  			 var countI = 0;
  			 var countJ = 0;
  			  var url = null;
  			 for (i=0; i<paramValLob.options.length; i++) {
  			    if (paramValLob.options[i].selected) {
  			      selectedArrayI[countI] = paramValLob.options[i].value;
  				  // alert('paramVal ' +paramValLob.options[i].value);
  			      countI++;
  			    }
  			  }
  			 for (j=0; j<paramValDept.options.length; j++) {
   			    if (paramValDept.options[j].selected) {
   			      selectedArrayJ[countJ] = paramValDept.options[j].value;
				  selectedArrayJText[countJ] = paramValDept.options[j].text;
   				  // alert('paramVal ' +paramValDept.options[j].value);
   			      countJ++;
   			    }
   			  }
			    var roleVal=document.getElementById('role').value;
				//alert(roleVal);
			  //alert('selectedArrayJText.toString()'+selectedArrayJText.toString())
			  document.getElementById('deptName').value = selectedArrayJText.toString();
                // alert(document.getElementById('deptName').value);
                 document.getElementById('roleName').value = document.getElementById('role').options[document.getElementById('role').selectedIndex].text;
             //alert('selectedArrayI.toString()'+selectedArrayI.toString()+'selectedArrayJ.toString()'+selectedArrayJ.toString());
                   url = "dropDown.do?method="+escape(methodName)+"&lobId="+escape(selectedArrayI.toString())+"&depId="+escape(selectedArrayJ.toString())+"&role="+escape(roleVal);   
              
             
                    if (window.XMLHttpRequest) {
                         req = new XMLHttpRequest();
              		   
                     } else if (window.ActiveXObject) {
                         req = new ActiveXObject("Microsoft.XMLHTTP");
                     }
                   
                         req.onreadystatechange = displayDeptSubList;
              		                   
                     req.open("GET", url, true);
                     req.send(null);

                 // } else { 
              		
              	    //setToDefault('lobId');
                     
                 // }
                }  
                
                
                function displayDeptSubList() {
                	
                  if (req.readyState == 4) { // Complete
				//  alert("Please choose Supervisor(s) Name and Email.");
                    if (req.status == 200) { // OK response  
                     var xmlDoc = req.responseXML.documentElement;
              	   var xRows= xmlDoc.getElementsByTagName('entry');
              	    var objDDL = document.getElementById("repSup");  
              	   var objDDL1 = document.getElementById("supEmail");  
              	    objDDL.innerHTML="";
              	  objDDL1.innerHTML="";
              		
              			var rootObj=document.createElement("option");	
              		var attrib=document.createAttribute("value");	
              			attrib.value="";
              		rootObj.setAttributeNode(attrib);	
              		//newtext=document.createTextNode('Select One');
              		//rootObj.appendChild(newtext);
              		objDDL.appendChild(rootObj);
					objDDL1.appendChild(rootObj);

              	    for (var i=0; i<xRows.length; i++) {
              			var nameNodes = xRows[i].getElementsByTagName("optionValue");
              			var valueNodes = xRows[i].getElementsByTagName("optionText");
              			if (nameNodes.length > 0 && valueNodes.length > 0) {
              			 var  theValue = nameNodes[0].firstChild.nodeValue;
              			  var theText = valueNodes[0].firstChild.nodeValue;          
              			}
              			var theValueSpilt=theValue.split("_");
              			var supName=theValueSpilt[0];
              			
              			
              			
						//alert(theValue+"== "+supName);
						//document.frmEmpRegi.sEmail.value=theText;
              		//	var option = new Option(theValue,supName);
					    var option = new Option(supName,theValue);
						var option1 = new Option(theText,theText);

              			 try {
              				objDDL.add(option,null);     
							objDDL1.add(option1,null);     
              				
              			 }catch(e){
              				objDDL.add(option,-1);
							objDDL1.add(option1,-1);
              			 }
              		  }
              		  
                    } else {
                      alert("Problem: " + req.statusText);
                    }
                  }
                }
  