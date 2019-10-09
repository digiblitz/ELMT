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
//------------------------function for double space--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
//------------------------------------------------------------------------------------------------------------
//----------------------------------for Membership Type Amount -----------------------------------------------

function DispType(){
	len1=document.frmMembRegi.selDisp.length;
	chosentype1="";
	for(i=0;i<len1;i++)
	{ if(document.frmMembRegi.selDisp[i].selected)
	    { chosentype1=document.frmMembRegi.selDisp[i].value;
            n = chosentype1.lastIndexOf("#");
	  substr1= chosentype1.substring((n+1),chosentype1.length);
          sum1=Number(substr1);
          document.frmMembRegi.amount_txt.value=sum1;}}
return false;
}
//------------------------------------------------------------------------------------------------------------
//----------------------------------for Donation Amount-----------------------------------------------
function DispDonation(){
len3=document.frmMembRegi.donAmt_sel.length;
chosenfee3="";
for(i=0;i<len3;i++)
{if(document.frmMembRegi.donAmt_sel[i].selected)
{chosenfee3=document.frmMembRegi.donAmt_sel[i].value;}}

if(chosenfee3=="Others")
{document.frmMembRegi.donAmnt_txt.disabled=false;
//alert("other");
if(document.frmMembRegi.donAmnt_txt.value=="")
{alert("Enter the Amount of Donation");
document.frmMembRegi.donAmnt_txt.focus(); } }
else {document.frmMembRegi.donAmnt_txt.disabled=true;
  //    alert("Donation");  
     }

return false;
}
//---------------------------------------------------------------------------------------------------------
//---------------------------------------for Memberships to Mailing Address------------------------------
function DispMailing(){
	lenmai=document.frmMembRegi.mailAddr.length;
	chosenmai="";
	for(i=0;i<lenmai;i++)
	{ if(document.frmMembRegi.mailAddr[i].selected)
	    { chosenmai=document.frmMembRegi.mailAddr[i].value;
//               alert("+"+chosenmai);
            n = chosenmai.lastIndexOf("#");
	  substr2= chosenmai.substring((n+1),chosenmai.length);
          sum2=Number(substr2);
//          alert(":"+substr2);
//          alert("="+sum2);
          document.frmMembRegi.amountTwo_txt.value=sum2; } }
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
	if(chosenfee1=="Amount")
	{ alert("Select a option for Membership Type");
	  document.frmMembRegi.selDisp.focus();
	  return false;}

//-------------------------------------------------------------------------------------------------------------
//--------------------------------for Member Type------------------------------------------------------
	len2=document.frmMembRegi.memType.length;
	chosenfee2="";
	for(i=0;i<len2;i++)
	{ if(document.frmMembRegi.memType[i].selected)
	    { chosenfee2=document.frmMembRegi.memType[i].value;}}
	if(chosenfee2!="" && document.frmMembRegi.memId.value=="")
	{ alert("Enter the MemberID for Member Type");
	  document.frmMembRegi.memId.focus();
	  return false; }
        if(chosenfee2!="" && isnotAlphaNumeric(document.frmMembRegi.memId.value))
	{ alert("Enter the MemberID for Member Type");
	  document.frmMembRegi.memId.focus();
	  return false; }

//
//---------------------------------------------------------------------------------------------------
//------------------------------for Family Member One------------------------------------------
//-----------for salutation-------------------------------------
	lensal=document.frmMembRegi.select1.length;
	chosensal="";
	for(i=0;i<lensal;i++)
	{ if(document.frmMembRegi.select1[i].selected)
	    { chosensal=document.frmMembRegi.select1[i].value;}}
       alert(chosensal);
	if(chosensal=="")
	{ alert("Select a option for Salutation for Family Member One");
	  document.frmMembRegi.select1.focus();
	  return false;}
/*
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
*/

return true;
}
