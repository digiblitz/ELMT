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
/*  Javascript Document 
******************************************************************************************************************
	Product Name: Integrated Enterprise Dashboard - Association / Club V0.5
	Organization: digiBlitz Technologies (P) Ltd.
    Created by: K.N.Sathish
    Created Date: 07/08/2006  
*****************************************************************************************************************
*/

function showLevels(chkBxNam,divId){
		if(document.frmfrmMsgRequestDatabases.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmfrmMsgRequestDatabases.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmfrmMsgRequestDatabases.elements[chkBxId].checked == true){
				document.frmfrmMsgRequestDatabases.elements[txtBxId].disabled = false;	
		}else {
				document.frmfrmMsgRequestDatabases.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){
		if(document.frmfrmMsgRequestDatabases.elements[radBtnId].checked == true){
				document.frmfrmMsgRequestDatabases.elements[txtBxId].disabled = false;	
		}else{
				document.frmfrmMsgRequestDatabases.elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.frmfrmMsgRequestDatabases.elements[radBtnNewId].checked == true){
				document.frmfrmMsgRequestDatabases.elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmfrmMsgRequestDatabases.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 

 //script for database validation

function checkClear()
{
document.form[0].checkNo.value="";
document.form[0].checkDate.value="";
document.form[0].favorof.value="";
}

function cardClear(){
document.form[0].cardselect.selectedIndex=0;
document.form[0].cardNo.value="";
document.form[0].cardCvvNo.value="";
document.form[0].ccName.value="";
document.form[0].ccExpMonth.selectedIndex=0;
document.form[0].ccExpYear.selectedIndex=0;
}

function myvalidate()
{
//---------------------------------------------------------------------------------
//--------------------------for option Check  Select
if(chosen3=="check")
{ if(document.form[0].checkNo.value=="")
 { alert("Enter valid numbers in Check No of Check Details");
   return false;}
 if(!Number(document.form[0].checkNo.value))
{ alert("Enter valid numbers in Check Number of Check Details");
   document[].checkNo.focus();
   return false;}
//--------------------------------
//--------for Check Date

maxdays=0;
if(document.form[0].checkDate.value=="")
 { alert("Enter valid Check Date in Payment Information");
  document.form[0].checkDate.focus();
  return false;}

if(!(document.form[0].checkDate.value==""))
{	 todaysDate=new Date();
	 nowDate=todaysDate.getDate();
	 nowYear=todaysDate.getYear();
	 nowMonth1=todaysDate.getMonth();
	 nowMonth=1+nowMonth1;
	if(nowDate<10)
	{nowDate="0"+nowDate;}
	if(nowMonth<10)
	{nowMonth="0"+nowMonth;}
	
strdate=document.form[0].checkDate.value;
mm = Number(strdate.substring(0,2));
dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{alert("Enter valid month in the Check Date in the Payment Details");
 document.form[0].checkDate.focus();
 return false;}
 if(yyyy<nowYear || yyyy>2100)
{alert("Enter valid year in the Check Date in the Payment Details");
 document.form[0].checkDate.focus();
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
document.form[0].checkDate.focus();
return false;}
if(dd>maxdays || dd<1)
{ alert("Enter valid date in the Check Date in the Payment Details");
 document.form[0].checkDate.focus();
 return false;}
 if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear))
{alert("Enter valid date in the Check Date in the Payment Details");
document.form[0].checkDate.focus();
return false;}
 

}


//------------------------------
//--------for In Favor of
if(document.form[0].favourOf.value.indexOf(' ')==0)
 { alert("Enter valid In Favor of info in Payment Information");
  document.form[0].favourOf.focus();
  return false;}
if(document.form[0].favourOf.value=="")
 { alert("Enter valid In Favor of info in Payment Information");
  document.form[0].favourOf.focus();
  return false;}
if(document.form[0].favourOf.value.length>80)
 { alert("In Favor of info in Payment Information is out of range");
  document.form[0].favourOf.focus();
  return false;}

if(isnotAlpha(document.form[0].favourOf.value))
{alert("Enter valid name in the In Favor of  Info in Payment Information");
 document.form[0].favourOf .focus();
  return false;}
  if(Dospace(document.form[0].favourOf.value))
  {alert("Enter valid name in the In Favor of  Info in Payment Information");
   document.form[0].inFavorof .focus();
  return false;}
}
//---------------------------------------------------------------------------------
//---------for option Card Select

if(chosen3=="card"){

len4=document.form[0].cardselect.length;
for (i = 0; i < len4; i++)
 { if (document.form[0].cardselect[i].selected) {
  chosen4 = document.form[0].cardselect[i].value;} }
if(chosen4=="")
{ alert("Select an option in Card Type");
  return false;}

//----------------------------------
//-------for Card Number

if(document.form[0].cardNo.value=="")
{alert("Enter a Card Number in the Card Details");
 document.form[0].cardNo.focus();
 return false;}
if(isnotInteger(document.form[0].cardNo.value))
{ alert("Enter valid Card Number in the Card Details");
 document.form[0].cardNo.focus();
 return false;}
if(document.form[0].cardNo.value.length!=16 )
{ alert("Enter  valid 16 numbers to Card Number in the Card Details");
   document.form[0].cardNo.focus();
   return false;}

//-------------------------------
//--------for CVV Number

if(document.form[0].cardCvvNo.value=="")
{alert("Enter a  valid CVV Number in Card Details");
 document.form[0].cardCvvNo.focus();
 return false;}
if(document.form[0].cardCvvNo.value.length>4 || document.form[0].cardCvvNo.value.length<3)
{ alert("Enter valid number in CVV Number in Card Details");
 document.form[0].cardCvvNo.focus();
 return false;}
if(isnotInteger(document.form[0].cardCvvNo.value))
{alert("Enter valid CVV Number in card details");
 document.form[0].cardCvvNo.focus();
 return false;}


//--------------------------
//----for Print Name on Card
if(document.form[0].ccName.value.indexOf(' ')==0)
{alert("Enter valid Print Name in Card Details");
 document.form[0].ccName.focus();
 return false;}
if(document.form[0].ccName.value=="")
{alert("Enter valid Print Name in Card Details");
 document.form[0].ccName.focus();
 return false;}
 if(document.form[0].ccName.value.length>80)
{alert("Print Name in Card Details is out of range");
 document.form[0].ccName.focus();
 return false;}

if(isnotAlpha(document.form[0].ccName.value))
{alert("Enter valid Name in Print Name in Card Details");
 document.form[0].ccName.focus();
 return false;}
 if(Dospace(document.form[0].ccName.value))
 {alert("Enter valid Name in Print Name in Card Details");
  document.form[0].ccName.focus();
 return false;}

//-----------------------------------------
//---for Expiry month drop down

len5=document.form[0].ccExpMonth.length;
for (i = 0; i < len5; i++)
 { if (document.form[0].ccExpMonth[i].selected) {
  chosen5 = document.form[0].ccExpMonth[i].value;} }
if(chosen5=="")
{ alert("Select an option in Month in ExpiryDateof Pament Information");
  document.form[0].ccExpMonth.focus();
 return false;}

//------------------------------------------
//-----------for Expiry Year drop down

len6=document.form[0].ccExpYear.length;
for (i = 0; i < len6; i++)
 { if (document.form[0].ccExpYear[i].selected) {
  chosen6 = document.form[0].ccExpYear[i].value;} }
if(chosen6=="")
{ alert("Select an option in Year in Expiry Date of Payment Information");
  return false;}
    var rightNow=new Date();
	var theYear=rightNow.getYear();
	var tMonth=rightNow.getMonth();
	var theMonth=1+tMonth;
	var theDate=rightNow.getDate();
	if(document.form[0].ccExpYear.value==theYear)
	{if(document.form[0].ccExpYear.value<theMonth)
	  {alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
	  document.form[0].ccExpYear.focus();
	  return false;}}
}
//---------------------------------------------------------------------------------
return true;


}







}


		
