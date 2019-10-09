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
//--------------------------------------------------------------------------------
//-------------function for doublespace validation -----------------------------
var str="";
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
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
//-----------------------------------------------------------------------------------------------
//--------------------------function for two decimal points
function isnotValidDecimal(str){
if((str.indexOf("."))!=-1)
{ fr1=str.indexOf('.');
  mm = (str.substring(fr1,str.length));
  strnum=(Number(mm.length));
  if(strnum>3)
  {return true;}else {return false;}}
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
//-----------------------------------------------------------------------------------------------
//-------------------------function for Special Character
function isnotSpecial(str){
stringSpecialCheck="!#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'";
f4=1;
for(j=0;j<str.length;j++)
{if(stringSpecialCheck.indexOf(str.charAt(j))!=-1)
{f4=0;}}
if(f4==0)
{return true;}else{return false;}
}
//---------------------------------------------------------------------------------
//-------------for dynamic value to textbox
//---------------------------------------------------------------------------------
function checkclear(){
document.myform.checkNo.value="";
document.myform.checkDate.value="";
document.myform.inFavorof.value="";
}

function cardclear(){
document.myform.cardselect.selectedIndex=0;
document.myform.cardNo.value="";
document.myform.cVVNo.value="";
document.myform.printName.value="";
document.myform.expirymonth.selectedIndex=0;
document.myform.expiryyear.selectedIndex=0;
}

//----------------------------------------------------------------------------------------------
//----------------------------Display

function Dispall()
{j=0;
num1=0;
num2=0;
arrayval = new Array();

for(i=2;i<9;i++)
{ if(document.myform.elements[i].status)
  {j=j+1;
   arrayval[j]=document.myform.elements[i].value; }}
   sum=0;
 lenfee=document.myform.feeDisp.length;
  for (i = 0; i < lenfee; i++)
 { if(document.myform.feeDisp[i].selected)

 { chosenstr= document.myform.feeDisp[i].value;
   n = chosenstr.lastIndexOf("#");
   substr= chosenstr.substring((n+1),chosenstr.length);
   chosenfee=Number(substr);

   sum=chosenfee;}}

for(i=1;i<arrayval.length;i++)
{ num1=arrayval[i].lastIndexOf("#");
  num2=arrayval[i].length;
  str = arrayval[i].substring(num1+1,num2);
  num = Number(str);
  sum = sum + num; }
 document.myform.totalAmount.value=sum;
}

//------------------------------------------------------------------------------------------------------
//---------------------------
function Disp()
{ lenfee=document.myform.feeDisp.length;
  for (i = 0; i < lenfee; i++)
 { if(document.myform.feeDisp[i].selected)

 { chosenstr = document.myform.feeDisp[i].value;
   n = chosenstr.lastIndexOf("#");
   substr= chosenstr.substring((n+1),chosenstr.length);
   chosenfee=Number(substr);
   sum=chosenfee;
   
   document.myform.classification1.checked=false;
   document.myform.classification2.checked=false;
   document.myform.classification3.checked=false;
   document.myform.classification4.checked=false;
   document.myform.totalAmount.value=sum;} }


 return false; }

//---------------------------------------------------------------------------------

function myvalidate(form){

stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";

//----------------------------------------------------------------------
//----------------for Form fee option
var chosenstr ="";
n=0;
lenfee=document.myform.feeDisp.length;
for (i = 0; i < lenfee; i++)
 { if (document.myform.feeDisp[i].selected) {
  chosenstr = document.myform.feeDisp[i].value;
  n=chosenstr.lastIndexOf("#");
  substr=chosenstr.substring((n+1),chosenstr.length);
  sum=Number(substr);   } }

if(chosenstr=="")
{ alert("Select an option in Horse Registeration Type");
  document.myform.feeDisp.focus();
 return false;}


//---------------------------------------------------------------------------------

//------------for Imported From in Horse Description --
if(document.myform.importedFrom.value.indexOf(' ')==0)
{alert("Enter valid Imported From in the Horse Description");
 document.myform.importedFrom.focus();
return false;}
if(document.myform.importedFrom.value.length>80)
{alert("Imported From in the Horse Description is out of range");
 document.myform.importedFrom.focus();
return false;}

if(document.myform.importedFrom.value=="")
{alert("Imported From in the Horse Description should not be empty");
 document.myform.importedFrom.focus();
return false;}

 if(isnotAlpha(document.myform.importedFrom.value))
{alert("Enter valid Imported From in the Horse Description");
 document.myform.importedFrom.focus();
return false;}
if(Dospace(document.myform.importedFrom.value))
{alert("Enter valid Imported From in the Horse Description");
 document.myform.importedFrom.focus();
return false;}


//---------------------------------------------------------------------------------------------

//------------for Date Imported
maxdays=0;
thisyear = (new Date()).getYear();

if(document.myform.dateImported.value=="")
{alert("Enter the date in the Date Imported in the Horse Description");
 document.myform.dateImported.focus();
 return false;}

  strdate=document.myform.dateImported.value;
  mm = Number(strdate.substring(0,2));
 dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{alert("Enter valid month in the Date Imported in the Horse Description");
 document.myform.dateImported.focus();
 return false;}
 if(yyyy<1900 || yyyy>thisyear)
{alert("Enter valid year in the Date Imported in the Horse Description");
 document.myform.dateImported.focus();
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

if(dd>maxdays || dd<1)
{ alert("Enter valid date in the Date Imported in the Horse Description");
 document.myform.dateImported.focus();
 return false;}

//---------------------------------------------------------------------------------------------

//-----------for Foreign Grade of Horse Description--
if(document.myform.foreignGrade.value=="")
{alert("Enter a Foreign Grade ");
 document.myform.foreignGrade.focus();
 return false;}

 if(isnotAlphaNumeric(document.myform.foreignGrade.value))
{alert("Enter a valid Foreign Grade ");
 document.myform.foreignGrade.focus();
 return false;}
if(document.myform.foreignGrade.value.length>20)
{alert("Foreign Grade is out of range");
 document.myform.foreignGrade.focus();
 return false;}


//---------------------------------------------------------------------------------

//---------for Foreign Points in HorseDescription

if(document.myform.foreignPoints.value=="")
{alert("Enter valid Number in the Foreign Points of the Horse Description");
  document.myform.foreignPoints.focus();
 return false;}

if(!Number(document.myform.foreignPoints.value))
 {alert("Enter valid Number in the Foreign Points of the Horse Description");
  document.myform.foreignPoints.focus();
 return false;}
 if(isnotValidDecimal(document.myform.foreignPoints.value))
 {alert("Enter valid Number in the Foreign Points of the Horse Description");
  document.myform.foreignPoints.focus();
 return false;}

//---------------------------------------------------------------------------------------------

//-----------for Assigned Grade of Horse Description---
if(document.myform.assignedGrade.value=="")
{alert("Enter Assigned Grade ");
 document.myform.assignedGrade.focus();
return false;}
if(isnotAlphaNumeric(document.myform.assignedGrade.value))
{alert("Enter valid Assigned Grade ");
 document.myform.assignedGrade.focus();
return false;}
if(document.myform.assignedGrade.value.length>20)
{alert("Assign Grade is out of range ");
 document.myform.assignedGrade.focus();
 return false;}

//---------------------------------------------------------------------------------

//---------for Assigned Points in HorseDescription

if(document.myform.assignedPoints.value=="")
{alert("Enter the Assigned Points of the Horse Description");
  document.myform.assignedPoints.focus();
 return false;}
if(!Number(document.myform.assignedPoints.value))
 {alert("Enter valid Number in the Assigned Points of the Horse Description");
  document.myform.assignedPoints.focus();
 return false;}
 if(isnotValidDecimal(document.myform.assignedPoints.value))
 {alert("Enter valid Number in the Assigned Points of the Horse Description");
  document.myform.assignedPoints.focus();
 return false;}







//------------------------------------------------------------------------------

//---------------for Option of payment

chosen3="";
len3 = document.myform.r11.length ;
for(i=0;i<len3;i++)
{if(document.myform.r11[i].checked)
  { chosen3= document.myform.r11[i].value; }
}
if(chosen3=="")
{alert("Check any of the Payment Option in the Payment Information");
 return false;
}

//---------------------------------------------------------------------------------
//--------------------------for option Check  Select
if(chosen3=="check")
{ if(document.myform.checkNo.value=="")
 { alert("Enter valid numbers in Check No of Check Details");
   return false;}
 if(!Number(document.myform.checkNo.value))
{ alert("Enter valid numbers in Check Number of Check Details");
   document.myform.checkNo.focus();
   return false;}
//--------------------------------
//--------for Check Date

maxdays=0;
if(document.myform.checkDate.value=="")
 { alert("Enter valid Check Date in Payment Information");
  document.myform.checkDate.focus();
  return false;}

if(!(document.myform.checkDate.value==""))
{	 todaysDate=new Date();
	 nowDate=todaysDate.getDate();
	 nowYear=todaysDate.getYear();
	 nowMonth1=todaysDate.getMonth();
	 nowMonth=1+nowMonth1;
	if(nowDate<10)
	{nowDate="0"+nowDate;}
	if(nowMonth<10)
	{nowMonth="0"+nowMonth;}
	
strdate=document.myform.checkDate.value;
mm = Number(strdate.substring(0,2));
dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{alert("Enter valid month in the Check Date in the Payment Details");
 document.myform.checkDate.focus();
 return false;}
 if(yyyy<nowYear || yyyy>2100)
{alert("Enter valid year in the Check Date in the Payment Details");
 document.myform.checkDate.focus();
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
document.myform.checkDate.focus();
return false;}
if(dd>maxdays || dd<1)
{ alert("Enter valid date in the Check Date in the Payment Details");
 document.myform.checkDate.focus();
 return false;}
 if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear))
{alert("Enter valid date in the Check Date in the Payment Details");
document.myform.checkDate.focus();
return false;}
 

}


//------------------------------
//--------for In Favor of
if(document.myform.inFavorof.value.indexOf(' ')==0)
 { alert("Enter valid In Favor of info in Payment Information");
  document.myform.inFavorof.focus();
  return false;}
if(document.myform.inFavorof.value=="")
 { alert("Enter valid In Favor of info in Payment Information");
  document.myform.inFavorof.focus();
  return false;}
if(document.myform.inFavorof.value.length>80)
 { alert("In Favor of info in Payment Information is out of range");
  document.myform.inFavorof.focus();
  return false;}

if(isnotAlpha(document.myform.inFavorof.value))
{alert("Enter valid name in the In Favor of  Info in Payment Information");
 document.myform.inFavorof .focus();
  return false;}
  if(Dospace(document.myform.inFavorof.value))
  {alert("Enter valid name in the In Favor of  Info in Payment Information");
   document.myform.inFavorof .focus();
  return false;}
}

//---------------------------------------------------------------------------------
//---------for option Card Select

if(chosen3=="card"){

len4=document.myform.cardselect.length;
for (i = 0; i < len4; i++)
 { if (document.myform.cardselect[i].selected) {
  chosen4 = document.myform.cardselect[i].value;} }
if(chosen4=="")
{ alert("Select an option in Card Type");
  return false;}

//----------------------------------
//-------for Card Number

if(document.myform.cardNo.value=="")
{alert("Enter a Card Number in the Card Details");
 document.myform.cardNo.focus();
 return false;}
if(isnotInteger(document.myform.cardNo.value))
{ alert("Enter valid Card Number in the Card Details");
 document.myform.cardNo.focus();
 return false;}
if(document.myform.cardNo.value.length!=16 )
{ alert("Enter  valid 16 numbers to Card Number in the Card Details");
   document.myform.cardNo.focus();
   return false;}

//-------------------------------
//--------for CVV Number

if(document.myform.cVVNo.value=="")
{alert("Enter a  valid CVV Number in Card Details");
 document.myform.cVVNo.focus();
 return false;}
if(document.myform.cVVNo.value.length>4 || document.myform.cVVNo.value.length<3)
{ alert("Enter valid number in CVV Number in Card Details");
 document.myform.cVVNo.focus();
 return false;}
if(isnotInteger(document.myform.cVVNo.value))
{alert("Enter valid CVV Number in card details");
 document.myform.cVVNo.focus();
 return false;}


//--------------------------
//----for Print Name on Card
if(document.myform.printName.value.indexOf(' ')==0)
{alert("Enter valid Print Name in Card Details");
 document.myform.printName.focus();
 return false;}
if(document.myform.printName.value=="")
{alert("Enter valid Print Name in Card Details");
 document.myform.printName.focus();
 return false;}
 if(document.myform.printName.value.length>80)
{alert("Print Name in Card Details is out of range");
 document.myform.printName.focus();
 return false;}

if(isnotAlpha(document.myform.printName.value))
{alert("Enter valid Name in Print Name in Card Details");
 document.myform.printName.focus();
 return false;}
 if(Dospace(document.myform.printName.value))
 {alert("Enter valid Name in Print Name in Card Details");
  document.myform.printName.focus();
 return false;}

//-----------------------------------------
//---for Expiry month drop down

len5=document.myform.expirymonth.length;
for (i = 0; i < len5; i++)
 { if (document.myform.expirymonth[i].selected) {
  chosen5 = document.myform.expirymonth[i].value;} }
if(chosen5=="")
{ alert("Select an option in Month in ExpiryDateof Pament Information");
  document.myform.expirymonth.focus();
 return false;}

//------------------------------------------
//-----------for Expiry Year drop down

len6=document.myform.expiryyear.length;
for (i = 0; i < len6; i++)
 { if (document.myform.expiryyear[i].selected) {
  chosen6 = document.myform.expiryyear[i].value;} }
if(chosen6=="")
{ alert("Select an option in Year in Expiry Date of Payment Information");
  return false;}
    var rightNow=new Date();
	var theYear=rightNow.getYear();
	var tMonth=rightNow.getMonth();
	var theMonth=1+tMonth;
	var theDate=rightNow.getDate();
	if(document.myform.expiryyear.value==theYear)
	{if(document.myform.expirymonth.value<theMonth)
	  {alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
	  document.myform.expirymonth.focus();
	  return false;}}
}
//---------------------------------------------------------------------------------
return true;

}


