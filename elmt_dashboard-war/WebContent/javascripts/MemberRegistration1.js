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
//-----------------------------------------------------------------------------------------------------------------------
//------------------------function for not Alphabets---------------------------------------------------------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=-~.,`0123456789;:][{}"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//-----------------------------------------------------------------------------------------------------------------------
//------------------------function for ALPHA NUMERIC------------------------------------------------------------------
function isnotAlphaNumeric(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'"+"\"";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//-----------------------------------------------------------------------------------------------------------------------
//------------------------function for double space--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
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
//--------------------------------------------------------------------------------------------------
//-----------------------------------for Displaying amount in the Amount field
var sum=0;
fuction Disp1(){
	lendisp1=document.frmMembRegi.selDisp.length;
	chosenstr1="";
	for(i=0;i<lendisp1;i++)
	{ if(document.frmMembRegi.selDisp[i].selected)
        { chosenstr1=document.frmMembRegi.selDisp[i].value;}
	  n = chosenstr1.lastIndexOf("#");
	  substr1= chosenstr.substring((n+1),chosenstr.length);
          sum=Number(substr1);
          document.frmMembRegi.amount_txt.value=sum;
	 }
return false;
}
//------------------------------------------------------------------------------------------------
//----------------------fuction for validation---------------------------------------------------

function myvalidate(){
	
//-------------------------------------------------------------------------------------------------
//------------------------------for Membership Type Select---------------------------------------
	len1=document.frmMembRegi.selDisp.length;
	chosenfee1="";
	for(i=0;i<len1;i++)
	{ if(document.frmMembRegi.selDisp[i].selected)
	    { chosenfee1=document.frmMembRegi.selDisp[i].value;}}
	alert(chosenfee1);
	alert();
	if(chosenfee1=="Amount")
	{ alert("Select a option for Membership Type");
	  document.frmMembRegi.selDisp.focus();
	  return false;}
/*
//---------------------------------------------------------------------------------------------------
//--------------------------------for Member Type------------------------------------------------------
	len2=document.frmMembRegi.memType.length;
	chosenfee2="";
	for(i=0;i<len2;i++)
	{ if(document.frmMembRegi.memType[i].selected)
	    { chosenfee1=document.frmMembRegi.memType[i].value;}}
	if(chosenfee2=="")
	{ alert("Select a option for Member Type");
	  document.frmMembRegi.memType.focus();
	  return false;}
/*
//---------------------------------------------------------------------------------------------------
//------------------------------for Family Member One------------------------------------------
//-----------for salutation-------------------------------------
	lensel1=document.frmMembRegi.select1.length;
	chosensal1="";
	for(i=0;i<lensel1;i++)
	{ if(document.frmMembRegi.select1[i].selected)
	    { chosensal1=document.frmMembRegi.select1[i].value;}}
	if(chosensal1=="")
	{ alert("Select a option for Salutation for Family Member One");
	  document.frmMembRegi.select1.focus();
	  return false;}
//---------------------------------------FIRST NAME----------------------------------

if(document.frmMembRegi.fname1.value=="")
   {alert(" First Name cannot be empty ");
     document.frmMembRegi.fname1.focus();
    return false; }
if(document.frmMembRegi.fname1.value.indexOf(' ')==0)
{alert("Enter First Name ");
 document.frmMembRegi.fname1.focus();
 return false; }

if(isnotAlpha(document.frmMembRegi.fname1.value))
{ alert("Enter Valid First Name ");
  document.frmMembRegi.fname1.focus();
   return false; }
if(Dospace(document.frmMembRegi.fname1.value))
   { alert("Enter Valid First Name ");
     document.frmMembRegi.fname1.focus();
   return false; }
if( document.frmMembRegi.fname1.value.length>80 )
   { alert("Enter within 80 characters for First Name" );
      document.frmMembRegi.fname1.focus();
      return false; }
//--------------------------MIDDLE NAME-----------------------------------------


if(isnotAlpha(document.frmMembRegi.mname1.value))
{ alert("Enter Valid Middle Name ");
  document.frmMembRegi.mname1.focus();
   return false; }
if(Dospace(document.frmMembRegi.mname1.value)||((document.frmMembRegi.mname1.value.indexOf(' '))==0))
   { alert("Enter Valid Middle Name ");
     document.frmMembRegi.mname1.focus();
   return false; }
if( document.frmMembRegi.mname1.value.length>80 )
   { alert("Enter within 80 characters for Middle Name" );
      document.frmMembRegi.mname1.focus();
      return false; }

//----------------------------------LAST NAME--------------------------------------
if(document.frmMembRegi.lname1.value=="")
   {alert(" Last Name cannot be empty ");
     document.frmMembRegi.lname1.focus();
    return false; }
if(document.frmMembRegi.lname1.value.indexOf(' ')==0)
{alert("Enter Last Name  ");
 document.frmMembRegi.lname1.focus();
 return false; }

if(isnotAlpha(document.frmMembRegi.lname1.value))
{ alert("Enter Valid Last Name ");
  document.frmMembRegi.lname1.focus();
   return false; }
if(Dospace(document.frmMembRegi.lname1.value))
   { alert("Enter Valid Last Name ");
     document.frmMembRegi.lname1.focus();
   return false; }
if( document.frmMembRegi.lname1.value.length>80 )
   { alert("Enter within 80 characters of Last Name" );
      document.frmMembRegi.lname1.focus();
      return false; }

//--------------------------------SUFFIX---------------------------------------


if(document.frmMembRegi.suffix1.value.indexOf(' ')==0)
{alert("Enter Suffix ");
 document.frmMembRegi.suffix1.focus();
 return false; }


if(isnotAlphaNumeric(document.frmMembRegi.suffix1.value))
{ alert("Enter Valid Suffix ");
  document.frmMembRegi.suffix1.focus();
   return false; }
if(Dospace(document.frmMembRegi.suffix1.value))
   { alert("Enter Valid Suffix ");
     document.frmMembRegi.suffix1.focus();
   return false; }
if( document.frmMembRegi.suffix1.value.length>20 )
   { alert("Enter within 20 characters for Suffix" );
      document.frmMembRegi.suffix1.focus();
      return false; }


//___________________________________________DATE________________________________________________________
if(document.frmMembRegi.birthmonth1.value=="")
{alert(" Select Month in Date of Birth ");
 document.frmMembRegi.birthmonth1.focus();
 return false;}
 if(!(document.frmMembRegi.birthmonth1.value==""))
 { leno=document.frmMembRegi.birthmonth1.length;
  for(i=0;i<leno;i++)
  { if(document.frmMembRegi.birthmonth1[i].selected)
 { choseno=document.frmMembRegi.birthmonth1[i].value ;}}}
 
 //------month checking----
 var lyear=document.frmMembRegi.birthyear1.value;
 var lcheck=(lyear%4);

if((document.frmMembRegi.birthmonth1.selectedIndex== 2)&&(document.frmMembRegi.birthday1.value >29)&&(lcheck=="0"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday1.focus();
	return false;
 }
var lyear1=document.frmMembRegi.birthyear1.value;
 var lcheck1=(lyear1%4);
 
 if((document.frmMembRegi.birthmonth1.selectedIndex== 2)&&(document.frmMembRegi.birthday1.value >28)&&(lcheck1>0))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday1.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth1.selectedIndex== 4)&&(document.frmMembRegi.birthday1.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday1.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth1.selectedIndex== 6)&&(document.frmMembRegi.birthday1.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday1.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth1.selectedIndex== 9)&&(document.frmMembRegi.birthday1.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday1.focus();
	return false;
 }
 if((document.frmMembRegi.birthmonth1.selectedIndex== 11)&&(document.frmMembRegi.birthday1.value=="31"))
 {
	alert ("Date is not valid") ;
	document.frmMembRegi.birthday1.focus();
	return false;
 }


 if(document.frmMembRegi.birthday1.value=="")
 {alert(" Select Day in Date of Birth");
  document.frmMembRegi.birthday1.focus();
  return false;}
  if(!(document.frmMembRegi.birthday1.value==""))
  { leno=document.frmMembRegi.birthday1.length;
   for(i=0;i<leno;i++)
   { if(document.frmMembRegi.birthday1[i].selected)
  { choseno=document.frmMembRegi.birthday1[i].value ;}}}


 if(document.frmMembRegi.birthyear1.value=="")
 {alert(" Select Year in Date of Birth");
  document.frmMembRegi.birthyear1.focus();
  return false;}
  if(!(document.frmMembRegi.birthyear1.value==""))
  { leno=document.frmMembRegi.birthyear1.length;
   for(i=0;i<leno;i++)
   { if(document.frmMembRegi.birthyear1[i].selected)
  { choseno=document.frmMembRegi.birthyear1[i].value ;}}}

//-----------------------------------------------------------------------------------------------------------
//-----------------------------------------GENDER
chosen="";
len = document.frmMembRegi.gender1.length ;
for(i=0;i<len;i++)
{if(document.frmMembRegi.gender1[i].checked)
  { chosen= document.frmMembRegi.gender1[i].value; }
}
if(chosen=="")
{alert("Select the Gender");
 
 return false;
}
//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER PHONE OR MOBILE NUMBER
if(document.frmMembRegi.phone1.value=="" && document.frmMembRegi.mobile1.value=="")
{ alert("Enter Contact Number");
  document.frmMembRegi.phone1.focus();
  return false;}

//--------------------------------PHONE NUMBER---------------------------------------


if(document.frmMembRegi.phone1.value!="")
{		var s1=document.frmMembRegi.phone1.value.indexOf('(');
		var s2=document.frmMembRegi.phone1.value.indexOf(')');
		var s5=document.frmMembRegi.phone1.value.indexOf('+');
		var s6=document.frmMembRegi.phone1.value.lastIndexOf('+');
		var s7=document.frmMembRegi.phone1.value.indexOf('-');
		var s8=document.frmMembRegi.phone1.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone1.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone1.focus();
			return false;
		}
		if(s5!=s6){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone1.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone1.focus();
			return false;
		}
																					  
 len7=document.frmMembRegi.phone1.value.length;
 strnum = document.frmMembRegi.phone1.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Phone Number");
 document.frmMembRegi.phone1.focus();
 return false;}
 if(document.frmMembRegi.phone1.value.length>40)
  {alert("Enter valid Phone Number");
  document.frmMembRegi.phone1.focus();
  return false;}
 
}

	

//----------------------------------MOBILE---------------------------------------


if(document.frmMembRegi.mobile1.value!="")
{		
                /*var s1=document.frmMembRegi.mobile1.value.indexOf('(');
		var s2=document.frmMembRegi.mobile1.value.indexOf(')');
		var s5=document.frmMembRegi.mobile1.value.indexOf('+');
		var s6=document.frmMembRegi.mobile1.value.lastIndexOf('+');
		var s7=document.frmMembRegi.mobile1.value.indexOf('-');
		var s8=document.frmMembRegi.mobile1.value.lastIndexOf('-');
		var s3=1+s2;
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile1.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile1.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile1.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile1.focus();
			return false;
		}
		if(document.frmMembRegi.mobile1.value.length>40){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile1.focus();
			return false;
		}
 len7=document.frmMembRegi.mobile1.value.length;
 strnum = document.frmMembRegi.mobile1.value;
  var GoodChars = "0123456789";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid mobile Number");
 document.frmMembRegi.mobile1.focus();
 return false;}

}


 //-----------------------------------------FAX--------------------------------
 

if(document.frmMembRegi.fax1.value!="")
{		var s1=document.frmMembRegi.fax1.value.indexOf('(');
		var s2=document.frmMembRegi.fax1.value.indexOf(')');
		var s5=document.frmMembRegi.fax1.value.indexOf('+');
		var s6=document.frmMembRegi.fax1.value.lastIndexOf('+');
		var s7=document.frmMembRegi.fax1.value.indexOf('-');
		var s8=document.frmMembRegi.fax1.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid fax ");
			document.frmMembRegi.fax1.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid fax ");
			document.frmMembRegi.fax1.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid fax ");
			document.frmMembRegi.fax1.focus();
			return false;
		}
		if(s7!==s8){
			alert("Enter valid fax ");
			document.frmMembRegi.fax1.focus();
			return false;
		}

 len7=document.frmMembRegi.fax1.value.length;
 strnum = document.frmMembRegi.fax1.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid fax ");
 document.frmMembRegi.fax1.focus();
 return false;}
 
 if(document.frmMembRegi.fax1.value.length>40)
  {alert("Enter valid Fax ");
  document.frmMembRegi.fax1.focus();
  return false;}
   
 }



//----------------------------------------------------------------------------------------------------------------
//--------------------------------------EMAIL-----------------

 //---------------------------------------------------------------------------------------------------------------

if(document.frmMembRegi.lid1.value=="")
 {alert("Enter your Email ID");
  document.frmMembRegi.lid1.focus();
 return false;}
 
 if(!(document.frmMembRegi.lid1.value== ""))
 { strmail=document.frmMembRegi.lid1.value;
 firstat = strmail.indexOf("@");
 lastat = strmail.lastIndexOf("@");
 strmain=strmail.substring(0,firstat);
 strsub=strmail.substring(firstat,document.frmMembRegi.lid1.value.length);
 if(strmail.length>120)
 {alert("Email is out of range");
  document.frmMembRegi.lid1.focus();
 return false;}
 if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 )
 {alert("Enter valid Email ");
  document.frmMembRegi.lid1.focus();
 return false;}
 if(isnotAlpha(strmain) || isnotAlpha(strsub))
 {alert("Enter valid Email ");
  document.frmMembRegi.lid1.focus();
 return false;}
 k=0;
 strlen=strsub.length;
 for(i=0;i<strlen-1;i++)
 { if(strsub.charAt(i)=='.')
 {k=k+1;}}
 if(k>2)
 {alert("Enter valid Email ");
  document.frmMembRegi.lid1.focus();
 return false;}
 if(firstat==-1 || lastat==-1)
 {alert("Enter valid Email" );
  document.frmMembRegi.lid1.focus();
 return false;}
 if(Number(strmain))
 {alert("Enter valid Email ");
  document.frmMembRegi.lid1.focus();
  return false;}
 if(firstat != lastat )
 {alert("Enter valid Email ");
  document.frmMembRegi.lid1.focus();
 return false;}
 firstdot=strmail.indexOf(".",firstat);
 lastdot=strmail.lastIndexOf(".");
 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
 {alert("Enter valid Email ");
  document.frmMembRegi.lid1.focus();
  return false;}
}
//---------------------------------------------------------------------------------------------------
//------------------------------for Family Member Two------------------------------------------
//-----------for salutation-------------------------------------
	lensel2=document.frmMembRegi.select2.length;
	chosensal2="";
	for(i=0;i<lensel2;i++)
	{ if(document.frmMembRegi.select2[i].selected)
	    { chosensal1=document.frmMembRegi.select2[i].value;}}
	if(chosensal2=="")
	{ alert("Select a option for Salutation for Family Member Two");
	  document.frmMembRegi.select2.focus();
	  return false;}	  

//---------------------------------------FIRST NAME----------------------------------

if(document.frmMembRegi.fname.value=="")
   {alert(" First Name cannot be empty ");
     document.frmMembRegi.fname.focus();
    return false; }
if(document.frmMembRegi.fname.value.indexOf(' ')==0)
{alert("Enter First Name ");
 document.frmMembRegi.fname.focus();
 return false; }

if(isnotAlpha(document.frmMembRegi.fname.value))
{ alert("Enter Valid First Name ");
  document.frmMembRegi.fname.focus();
   return false; }
if(Dospace(document.frmMembRegi.fname.value))
   { alert("Enter Valid First Name ");
     document.frmMembRegi.fname.focus();
   return false; }
if( document.frmMembRegi.fname.value.length>80 )
   { alert("Enter within 80 characters for First Name" );
      document.frmMembRegi.fname.focus();
      return false; }
//--------------------------MIDDLE NAME-----------------------------------------


if(isnotAlpha(document.frmMembRegi.mname.value))
{ alert("Enter Valid Middle Name ");
  document.frmMembRegi.mname.focus();
   return false; }
if(Dospace(document.frmMembRegi.mname.value)||((document.frmMembRegi.mname.value.indexOf(' '))==0))
   { alert("Enter Valid Middle Name ");
     document.frmMembRegi.mname.focus();
   return false; }
if( document.frmMembRegi.mname.value.length>80 )
   { alert("Enter within 80 characters for Middle Name" );
      document.frmMembRegi.mname.focus();
      return false; }

//----------------------------------LAST NAME--------------------------------------
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
      return false; }

//--------------------------------SUFFIX---------------------------------------


if(document.frmMembRegi.suffix.value.indexOf(' ')==0)
{alert("Enter Suffix ");
 document.frmMembRegi.suffix.focus();
 return false; }


if(isnotAlphaNumeric(document.frmMembRegi.suffix.value))
{ alert("Enter Valid Suffix ");
  document.frmMembRegi.suffix.focus();
   return false; }
if(Dospace(document.frmMembRegi.suffix.value))
   { alert("Enter Valid Suffix ");
     document.frmMembRegi.suffix.focus();
   return false; }
if( document.frmMembRegi.suffix.value.length>20 )
   { alert("Enter within 20 characters for Suffix" );
      document.frmMembRegi.suffix.focus();
      return false; }


//___________________________________________DATE________________________________________________________
if(document.frmMembRegi.birthmonth.value=="")
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
  { choseno=document.frmMembRegi.birthyear[i].value ;}}}

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
//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER PHONE OR MOBILE NUMBER
if(document.frmMembRegi.phone.value=="" && document.frmMembRegi.mobile.value=="")
{ alert("Enter Contact Number");
  document.frmMembRegi.phone.focus();
  return false;}

//--------------------------------PHONE NUMBER---------------------------------------


if(document.frmMembRegi.phone.value!="")
{		var s1=document.frmMembRegi.phone.value.indexOf('(');
		var s2=document.frmMembRegi.phone.value.indexOf(')');
		var s5=document.frmMembRegi.phone.value.indexOf('+');
		var s6=document.frmMembRegi.phone.value.lastIndexOf('+');
		var s7=document.frmMembRegi.phone.value.indexOf('-');
		var s8=document.frmMembRegi.phone.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone.focus();
			return false;
		}
		if(s5!=s6){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Phone Number");
			document.frmMembRegi.phone.focus();
			return false;
		}
																					  
 len7=document.frmMembRegi.phone.value.length;
 strnum = document.frmMembRegi.phone.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Phone Number");
 document.frmMembRegi.phone.focus();
 return false;}
 if(document.frmMembRegi.phone.value.length>40)
  {alert("Enter valid Phone Number");
  document.frmMembRegi.phone.focus();
  return false;}

}

	

//----------------------------------MOBILE---------------------------------------


if(document.frmMembRegi.mobile.value!="")
{		
                var s1=document.frmMembRegi.mobile.value.indexOf('(');
		var s2=document.frmMembRegi.mobile.value.indexOf(')');
		var s5=document.frmMembRegi.mobile.value.indexOf('+');
		var s6=document.frmMembRegi.mobile.value.lastIndexOf('+');
		var s7=document.frmMembRegi.mobile.value.indexOf('-');
		var s8=document.frmMembRegi.mobile.value.lastIndexOf('-');
		var s3=1+s2;
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile.focus();
			return false;
		}
		if(document.frmMembRegi.mobile.value.length>40){
			alert("Enter valid mobile Number");
			document.frmMembRegi.mobile.focus();
			return false;
		}
 len7=document.frmMembRegi.mobile.value.length;
 strnum = document.frmMembRegi.mobile.value;
  var GoodChars = "0123456789";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid mobile Number");
 document.frmMembRegi.mobile.focus();
 return false;}

}


 //-----------------------------------------FAX--------------------------------
 

if(document.frmMembRegi.fax.value!="")
{		var s1=document.frmMembRegi.fax.value.indexOf('(');
		var s2=document.frmMembRegi.fax.value.indexOf(')');
		var s5=document.frmMembRegi.fax.value.indexOf('+');
		var s6=document.frmMembRegi.fax.value.lastIndexOf('+');
		var s7=document.frmMembRegi.fax.value.indexOf('-');
		var s8=document.frmMembRegi.fax.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid fax ");
			document.frmMembRegi.fax.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid fax ");
			document.frmMembRegi.fax.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid fax ");
			document.frmMembRegi.fax.focus();
			return false;
		}
		if(s7!==s8){
			alert("Enter valid fax ");
			document.frmMembRegi.fax.focus();
			return false;
		}

 len7=document.frmMembRegi.fax.value.length;
 strnum = document.frmMembRegi.fax.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid fax ");
 document.frmMembRegi.fax.focus();
 return false;}
 
 if(document.frmMembRegi.fax.value.length>40)
  {alert("Enter valid Fax ");
  document.frmMembRegi.fax.focus();
  return false;}
   
 }



//----------------------------------------------------------------------------------------------------------------
//--------------------------------------EMAIL-----------------

 //---------------------------------------------------------------------------------------------------------------

if(document.frmMembRegi.email.value=="")
 {alert("Enter your Email ID");
  document.frmMembRegi.email.focus();
 return false;}
 
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
 {alert("Enter vaemail Email ");
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
 if(k>2)
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
*/
//---------------------------------------------------------------------------------
//-------------for dynamic value to textbox
//---------------------------------------------------------------------------------
/*
function checkclear(){
document.frmMembRegi.chkNo.value="";
document.frmMembRegi.chkDat.value="";
document.frmMembRegi.bank.value="";
}

function cardclear(){
document.frmMembRegi.ctypSelect.selectedIndex=0;
document.frmMembRegi.crdNo.value="";
document.frmMembRegi.cvvNo.value="";
document.frmMembRegi.prName.value="";
document.frmMembRegi.expirymonth.selectedIndex=0;
document.frmMembRegi.expiryyear.selectedIndex=0;
}
//-----------------------------------------------------------------------------------


//------------------------------------------------------------------------------

//---------------for Option of payment

chosen3="";
len3 = document.frmMembRegi.r11.length ;
for(i=0;i<len3;i++)
{if(document.frmMembRegi.r11[i].checked)
  { chosen3= document.frmMembRegi.r11[i].value; }
}
if(chosen3=="")
{alert("Check any of the Payment Option in the Payment Information");
 return false;
}

//---------------------------------------------------------------------------------
//--------------------------for option Check  Select
if(chosen3=="check")
{ if(document.frmMembRegi.chkNo.value=="")
 { alert("Enter valid numbers in Check No of Check Details");
   return false;}
 if(!Number(document.frmMembRegi.chkNo.value))
{ alert("Enter valid numbers in Check Number of Check Details");
   document.frmMembRegi.chkNo.focus();
   return false;}
//--------------------------------
//--------for Check Date

maxdays=0;
if(document.frmMembRegi.chkDat.value=="")
 { alert("Enter valid Check Date in Payment Information");
  document.frmMembRegi.chkDat.focus();
  return false;}

if(!(document.frmMembRegi.chkDat.value==""))
{	 todaysDate=new Date();
	 nowDate=todaysDate.getDate();
	 nowYear=todaysDate.getYear();
	 nowMonth1=todaysDate.getMonth();
	 nowMonth=1+nowMonth1;
	if(nowDate<10)
	{nowDate="0"+nowDate;}
	if(nowMonth<10)
	{nowMonth="0"+nowMonth;}
	
strdate=document.frmMembRegi.chkDat.value;
mm = Number(strdate.substring(0,2));
dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{alert("Enter valid month in the Check Date in the Payment Details");
 document.frmMembRegi.chkDat.focus();
 return false;}
 if(yyyy<nowYear || yyyy>2100)
{alert("Enter valid year in the Check Date in the Payment Details");
 document.frmMembRegi.chkDat.focus();
return false;}
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
{alert("Enter valid date in the Check Date in the Payment Details");
document.frmMembRegi.chkDat.focus();
return false;}
if(dd>maxdays || dd<1)
{ alert("Enter valid date in the Check Date in the Payment Details");
 document.frmMembRegi.chkDat.focus();
 return false;}
 if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear))
{alert("Enter valid date in the Check Date in the Payment Details");
document.frmMembRegi.chkDat.focus();
return false;}
 

}


//------------------------------
//--------for In Favor of
if(document.frmMembRegi.bank.value.indexOf(' ')==0)
 { alert("Enter valid In Favor of info in Payment Information");
  document.frmMembRegi.bank.focus();
  return false;}
if(document.frmMembRegi.bank.value=="")
 { alert("Enter valid In Favor of info in Payment Information");
  document.frmMembRegi.bank.focus();
  return false;}
if(document.frmMembRegi.bank.value.length>80)
 { alert("In Favor of info in Payment Information is out of range");
  document.frmMembRegi.bank.focus();
  return false;}

if(isnotAlpha(document.frmMembRegi.bank.value))
{alert("Enter valid name in the In Favor of  Info in Payment Information");
 document.frmMembRegi.bank .focus();
  return false;}
  if(Dospace(document.frmMembRegi.bank.value))
  {alert("Enter valid name in the In Favor of  Info in Payment Information");
   document.frmMembRegi.bank .focus();
  return false;}
}

//---------------------------------------------------------------------------------
//---------for option Card Select

if(chosen3=="card"){
chosen4="";
len4=document.frmMembRegi.ctypSelect.length;
for (i = 0; i < len4; i++)
 { if (document.frmMembRegi.ctypSelect[i].selected) {
  chosen4 = document.frmMembRegi.ctypSelect[i].value;} }
if(chosen4=="")
{ alert("Select an option in Card Type");
  return false;}

//----------------------------------
//-------for Card Number

if(document.frmMembRegi.crdNo.value=="")
{alert("Enter a Card Number in the Card Details");
 document.frmMembRegi.crdNo.focus();
 return false;}
if(isnotInteger(document.frmMembRegi.crdNo.value))
{ alert("Enter valid Card Number in the Card Details");
 document.frmMembRegi.crdNo.focus();
 return false;}
if(document.frmMembRegi.crdNo.value.length!=16 )
{ alert("Enter  valid 16 numbers to Card Number in the Card Details");
   document.frmMembRegi.crdNo.focus();
   return false;}

//-------------------------------
//--------for CVV Number

if(document.frmMembRegi.cvvNo.value=="")
{alert("Enter a  valid CVV Number in Card Details");
 document.frmMembRegi.cvvNo.focus();
 return false;}
if(document.frmMembRegi.cvvNo.value.length>4 || document.frmMembRegi.cvvNo.value.length<3)
{ alert("Enter valid number in CVV Number in Card Details");
 document.frmMembRegi.cvvNo.focus();
 return false;}
if(isnotInteger(document.frmMembRegi.cvvNo.value))
{alert("Enter valid CVV Number in card details");
 document.frmMembRegi.cvvNo.focus();
 return false;}


//--------------------------
//----for Print Name on Card
if(document.frmMembRegi.prName.value.indexOf(' ')==0)
{alert("Enter valid Print Name in Card Details");
 document.frmMembRegi.prName.focus();
 return false;}
if(document.frmMembRegi.prName.value=="")
{alert("Enter valid Print Name in Card Details");
 document.frmMembRegi.prName.focus();
 return false;}
 if(document.frmMembRegi.prName.value.length>80)
{alert("Print Name in Card Details is out of range");
 document.frmMembRegi.prName.focus();
 return false;}

if(isnotAlpha(document.frmMembRegi.prName.value))
{alert("Enter valid Name in Print Name in Card Details");
 document.frmMembRegi.prName.focus();
 return false;}
 if(Dospace(document.frmMembRegi.prName.value))
 {alert("Enter valid Name in Print Name in Card Details");
  document.frmMembRegi.prName.focus();
 return false;}

//-----------------------------------------
//---for Expiry month drop down
chosen5="";
len5=document.frmMembRegi.expirymonth.length;
for (i = 0; i < len5; i++)
 { if (document.frmMembRegi.expirymonth[i].selected) {
  chosen5 = document.frmMembRegi.expirymonth[i].value;} }
if(chosen5=="")
{ alert("Select an option in Month in ExpiryDateof Pament Information");
  document.frmMembRegi.expirymonth.focus();
 return false;}

//------------------------------------------
//-----------for Expiry Year drop down
chosen6="";
len6=document.frmMembRegi.expiryyear.length;
for (i = 0; i < len6; i++)
 { if (document.frmMembRegi.expiryyear[i].selected) {
  chosen6 = document.frmMembRegi.expiryyear[i].value;} }
if(chosen6=="")
{ alert("Select an option in Year in Expiry Date of Payment Information");
  return false;}
    var rightNow=new Date();
	var theYear=rightNow.getYear();
	var tMonth=rightNow.getMonth();
	var theMonth=1+tMonth;
	var theDate=rightNow.getDate();
	if(document.frmMembRegi.expiryyear.value==theYear)
	{if(document.frmMembRegi.expirymonth.value<theMonth)
	  {alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
	  document.frmMembRegi.expirymonth.focus();
	  return false;}}
}
//---------------------------------------------------------------------------------
*/
return true;
}

