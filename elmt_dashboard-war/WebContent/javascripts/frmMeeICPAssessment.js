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
//-----------Host ID Contact Details Ajax Script ------------------------------------------------

function showHideRadio(radioNam,divId,radVal){
		if(document.forms['frmMeeICPAssessment'].elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}

var httpRequest;

function details()
{
	if(IsNumeric(document.frmMeeICPAssessment.hostid.value))
		mid=document.frmMeeICPAssessment.hostid.value;
	else
		alert('Enter Valid Id');
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
       url= "RiderInfoAjaxAction.do?rid="+mid; 

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
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("salutation")[0]; 
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
        var rdetails=new Array();
        rdetails=null;
        rdetails=salutationText.split("#");
        
        if(rdetails[14]==NaN||rdetails[14]==undefined||rdetails[14]==null||rdetails[14]==""||rdetails[14].length==0)
        {
        document.frmMeeICPAssessment.ctry.value="";
        }
        else
        document.frmMeeICPAssessment.ctry.value=rdetails[14];

        if(rdetails[1]==NaN&&rdetails[2]==NaN&&rdetails[3]==NaN||rdetails[1]==undefined&&rdetails[2]==undefined&&rdetails[3]==undefined||rdetails[1]==null&&rdetails[2]==null&&rdetails[3]==null)
        {
        document.frmMeeICPAssessment.fname.value="";
		document.frmMeeICPAssessment.lname.value="";
        }else
		{
        document.frmMeeICPAssessment.fname.value=rdetails[1];
		document.frmMeeICPAssessment.lname.value=rdetails[3];
		}
        if(rdetails[5]==NaN&&rdetails[6]==NaN||rdetails[5]==undefined&&rdetails[6]==undefined||rdetails[5]==null&&rdetails[6]==null&&rdetails[6].length==0)
        {
        document.frmMeeICPAssessment.addr.value="";
        }else
        document.frmMeeICPAssessment.addr.value=rdetails[5]+rdetails[6];

        if(rdetails[7]==NaN||rdetails[7]==undefined||rdetails[7]==null)
        {
        document.frmMeeICPAssessment.city.value="";
        }else
        document.frmMeeICPAssessment.city.value=rdetails[7]; 

        if(rdetails[8]==NaN||rdetails[8]==undefined||rdetails[8]==null)
        {
        document.frmMeeICPAssessment.state.value="";
        }else
        document.frmMeeICPAssessment.state.value=rdetails[8];
    
        if(rdetails[9]==NaN||rdetails[9]==undefined||rdetails[9]==null)
        {
        document.frmMeeICPAssessment.zip.value="";
        }else
        document.frmMeeICPAssessment.zip.value=rdetails[9];

        if(rdetails[10]==NaN||rdetails[10]==undefined||rdetails[10]==null)
        {
        document.frmMeeICPAssessment.phone.value="";
        }else
        document.frmMeeICPAssessment.phone.value=rdetails[10];

        if(rdetails[13]==NaN||rdetails[13]==undefined||rdetails[13]==null)
        {
        document.frmMeeICPAssessment.fax.value="";
        }else
        document.frmMeeICPAssessment.fax.value=rdetails[13];

        if(rdetails[12]==NaN||rdetails[12]==undefined||rdetails[12]==null)
        {
        document.frmMeeICPAssessment.email.value="";
        }else
        document.frmMeeICPAssessment.email.value=rdetails[12];

    } 


//-------------function for doublespace validation -----------------------------
var str="";
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}

///------------------------------------------------------------------------------
//--------------------Function For IsNumeric Validaation-------------------------

function IsNumeric(sText)
{
   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char;

 
   for (i = 0; i < sText.length && IsNumber == true; i++) 
      { 
      Char = sText.charAt(i); 
      if (ValidChars.indexOf(Char) == -1) 
         {
         IsNumber = false;
         }
      }
   return IsNumber;
   
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
//------------------------function for Alpha Numeric-----------------------------------
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
//-----------------------------function for zipcode--------------------------------------------
function isnotZipcode(str){
	stringZip="0123456789";
	fzip=1;
for(j=0;j<str.length;j++)
{if(stringZip.indexOf(str.charAt(j))!=-1)
{fzip=0;}}
if(fzip==0)
{return false;}else{return true;}
}
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
			 if(k>2)
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
//------------------------------for Calendar1 Popup------------------------------------
function popupCal(val){
     
    if(val==1)
         document.frmMeeICPAssessment.date.value = "StartDate1";
    if(val==2)
         document.frmMeeICPAssessment.checkDate.value = "StartDate1";
		 
	  val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null){
         alert("Plz Enable Site Popup Allowed");
		
      }
}

//----------------------------------------------------------------------------------------------------------------

function isOtherscheck(){
	if(document.frmMeeICPAssessment.Otherchckbx.checked){
		document.frmMeeICPAssessment.txtOther.disabled=false;
	}
	else{
		document.frmMeeICPAssessment.txtOther.disabled=true;
	}
}


//---------------------------------------------------------------------------------
//-------------for dynamic value to textbox
//---------------------------------------------------------------------------------
function checkclear(){
document.frmMeeICPAssessment.txtChNumber.value="";
document.frmMeeICPAssessment.checkDate.value="";
document.frmMeeICPAssessment.inFavorof.value="";
}

function cardclear(){
document.frmMeeICPAssessment.ccTypeId.selectedIndex=0;
document.frmMeeICPAssessment.txtCard.value="";
document.frmMeeICPAssessment.txtCvvId.value="";
document.frmMeeICPAssessment.txtPrName.value="";
document.frmMeeICPAssessment.selExpMonthId.selectedIndex=0;
document.frmMeeICPAssessment.selExpYearId.selectedIndex=0;
}

//---------------------------------Assesmant Level-----------------------------------------------
function myvalidate(){
	
	if(document.frmMeeICPAssessment.assesmant.value==""){
		alert("Enter Assesment Level.");
		document.frmMeeICPAssessment.assesmant.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.assesmant.value.indexOf(' ')==0){
		alert("Enter valid Assesment Level.");
		document.frmMeeICPAssessment.assesmant.focus();
		return false;
	}
   if(isnotAlpha(document.frmMeeICPAssessment.assesmant.value)){
	   alert("Enter valid Assesment Level.");
		document.frmMeeICPAssessment.assesmant.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.assesmant.value.length>80){
		alert("Enter valid Assesment Level.");
		document.frmMeeICPAssessment.assesmant.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.assesmant.value.indexOf('  ')!=-1){
		alert("Enter valid Assesment Level.");
		document.frmMeeICPAssessment.assesmant.focus();
		return false;
	}
//-------------------------------------------------------------------------------------------------------

//--------for Assessment  Date

maxdays=0;
if(document.frmMeeICPAssessment.date.value=="")
 { alert("Enter valid Date in Assessment Information");
  document.frmMeeICPAssessment.date.focus();
  return false;}

if(!(document.frmMeeICPAssessment.date.value==""))
{	 todaysDate=new Date();
	 nowDate=todaysDate.getDate();
	 nowYear=todaysDate.getYear();
	 nowMonth1=todaysDate.getMonth();
	 nowMonth=1+nowMonth1;
	if(nowDate<10)
	{nowDate="0"+nowDate;}
	if(nowMonth<10)
	{nowMonth="0"+nowMonth;}
	
strdate=document.frmMeeICPAssessment.date.value;
mm = Number(strdate.substring(0,2));
dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{alert("Enter valid month in the Date in the Assessment  Details");
 document.frmMeeICPAssessment.date.focus();
 return false;}
 if(yyyy<nowYear || yyyy>2100)
{alert("Enter valid year in the Date in the Assessment Details");
 document.frmMeeICPAssessment.date.focus();
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
{alert("Enter valid date in the Date in the Assessment Details");
document.frmMeeICPAssessment.date.focus();
return false;}
 if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear))
{alert("Enter valid date in the Date in the Assessment Details");
document.frmMeeICPAssessment.date.focus();
return false;}
 

}


//------------------------------
	if(document.frmMeeICPAssessment.select71.selectedIndex==0){
		alert("Enter Number of Days.");
		document.frmMeeICPAssessment.select71.focus();
		return false;
	}



//--------------------------for HLC Area ----------------------------------------
	if(document.frmMeeICPAssessment.select72.selectedIndex==0){
		alert("Select Usea Area");
		document.frmMeeICPAssessment.select72.focus();
		return false;
	}
//------------------------------------------------------------------------------------
//------------------------------for Location------------------------------------
	if(document.frmMeeICPAssessment.location.value==""){
		alert("Enter Location ");
		document.frmMeeICPAssessment.location.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.location.value.indexOf(' ')==0){
		alert("Enter valid Location ");
		document.frmMeeICPAssessment.location.focus();
		return false;
	}
   if(isnotAlphaNumeric(document.frmMeeICPAssessment.location.value)){
	   alert("Enter valid Location ");
		document.frmMeeICPAssessment.location.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.location.value.length>255){
		alert("Enter valid Location ");
		document.frmMeeICPAssessment.location.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.location.value.indexOf('  ')!=-1){
		alert("Enter valid Location ");
		document.frmMeeICPAssessment.location.focus();
		return false;
	}

//------------------------------------------------------------------------------------
//------------------------------for HOst ID------------------------------------
if(document.frmMeeICPAssessment.hostId.value==""){
		alert("Enter HostId.");
		document.frmMeeICPAssessment.hostId.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.hostId.value.indexOf(' ')==0){
		alert("Enter valid HostId.");
		document.frmMeeICPAssessment.hostId.focus();
		return false;
	}
   if(isnotAlphaNumeric(document.frmMeeICPAssessment.hostId.value)){
	   alert("Enter valid HostId.");
		document.frmMeeICPAssessment.hostId.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.hostId.value.length>80){
		alert("Enter valid HostId.");
		document.frmMeeICPAssessment.hostId.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.hostId.value.indexOf('  ')!=-1){
		alert("Enter valid HostId.");
		document.frmMeeICPAssessment.hostId.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.Otherchckbx.checked){
		if(document.frmMeeICPAssessment.txtOther.value==""){
			alert("Enter Other Field");
			document.frmMeeICPAssessment.txtOther.focus();
			return false;
		}
	}

//---------------------------------------------------------------------------------------------------
//---------------------------------------for Asses Detail----------------------------------------
	if(document.frmMeeICPAssessment.txtarea.value==""){
		alert("Enter Assessor Detail.");
		document.frmMeeICPAssessment.txtarea.focus();
		return false;
	}
	if(Dospace(document.frmMeeICPAssessment.txtarea.value)){
		alert("Enter Assessor Detail.");
		document.frmMeeICPAssessment.txtarea.focus();
		return false;
	}
//-----------------------------------------------------------------------------------------------------
//------------------------------------------for Name----------------------------------------------
if(document.frmMeeICPAssessment.name1.value==""){
		alert("Enter Land Owner Name");
		document.frmMeeICPAssessment.name1.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.name1.value.indexOf(' ')==0){
		alert("Enter valid Land Owner Name");
		document.frmMeeICPAssessment.name1.focus();
		return false;
	}
   if(isnotAlpha(document.frmMeeICPAssessment.name1.value)){
	   alert("Enter valid Name in Land Owner Detail.");
		document.frmMeeICPAssessment.name1.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.name1.value.length>80){
		alert("Enter valid Name in Land Owner Detail.");
		document.frmMeeICPAssessment.name1.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.name1.value.indexOf('  ')!=-1){
		alert("Enter valid Name in Land Owner Detail.");
		document.frmMeeICPAssessment.name1.focus();
		return false;
	}
//-----------------------------------------------------------------------------------------------------
//------------------------------------------for Business Name----------------------------------------------
if(document.frmMeeICPAssessment.bizname.value==""){
		alert("Enter Business Name in Land Owner Detail");
		document.frmMeeICPAssessment.bizname.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.bizname.value.indexOf(' ')==0){
		alert("Enter valid Business Name in Land Owner Detail");
		document.frmMeeICPAssessment.bizname.focus();
		return false;
	}
   if(isnotAlphaNumeric(document.frmMeeICPAssessment.bizname.value)){
	   alert("Enter valid Business Name in Land Owner Detail");
		document.frmMeeICPAssessment.bizname.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.bizname.value.length>80){
		alert("Enter valid Business Name in Land Owner Detail");
		document.frmMeeICPAssessment.bizname.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.bizname.value.indexOf('  ')!=-1){
		alert("Enter valid Business Name in Land Owner Detail");
		document.frmMeeICPAssessment.bizname.focus();
		return false;
	}
//---------------------------------------------------------------------------------------------------------------
//-----------------------------------------for Address----------------------------------------------
if(document.frmMeeICPAssessment.address.value==""){
		alert("Enter Address in Land Owner Detail.");
		document.frmMeeICPAssessment.address.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.address.value.length>255){
		alert("Enter valid Address in Land Owner Detail.");
		document.frmMeeICPAssessment.address.focus();
		return false;
	}
//============================ For land owner Organizer Country ==========================

	var cdln = "";
	if(typeof(window.document.frmMeeICPAssessment.select11) == 'object'){
		if (window.document.frmMeeICPAssessment.select11.value != ""){
			var clid;
			clid = window.document.frmMeeICPAssessment.select11[window.document.frmMeeICPAssessment.select11.selectedIndex].value;
			cdln = clid;
		}
		if(cdln == "---Select---"){
			alert("Please select Country Name in Land Owner Detail.");
			window.document.frmMeeICPAssessment.select11.focus();
			return false;
		}
	}

	if ( document.frmMeeICPAssessment.select11.selectedIndex == 0 ){
        alert ( "Please select Country Name in Land Owner Detail." );
		document.frmMeeICPAssessment.select11.focus();
        return false;
    }

//=============================== For Land owner Organizer State ===============================
/*	var edln = "";
	if(typeof(window.document.frmMeeICPAssessment.select21) == 'object'){
		if (window.document.frmMeeICPAssessment.select21.value != ""){
			var lid;
			lid = window.document.frmMeeICPAssessment.select5[window.document.frmMeeICPAssessment.select21.selectedIndex].value;
			edln = lid;
		}
		if(edln.length == 1){
			alert("Please select Organizer's State Name.");
			window.document.frmMeeICPAssessment.select21.focus();
			return false;
		}
	}
*/

	if(document.frmMeeICPAssessment.select21.selectedIndex == 0 ){
        alert ( "Please select State Name in Land Owner Detail ." );
		document.frmMeeICPAssessment.select21.focus();
        return false;
    }
//-----------------------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------------------	
//------------------------------for City------------------------------------
	if(document.frmMeeICPAssessment.city2.value==""){
		alert("Enter City in Land Owner Detail.");
		document.frmMeeICPAssessment.city2.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.city2.value.indexOf(' ')==0){
		alert("Enter valid City in Land Owner Detail.");
		document.frmMeeICPAssessment.city2.focus();
		return false;
	}
   if(isnotAlpha(document.frmMeeICPAssessment.city2.value)){
	   alert("Enter valid City in Land Owner Detail.");
		document.frmMeeICPAssessment.city2.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.city2.value.length>100){
		alert("Enter valid City in Land Owner Detail.");
		document.frmMeeICPAssessment.city2.focus();
		return false;
	}

	if(document.frmMeeICPAssessment.city2.value.indexOf('  ')!=-1){
		alert("Enter valid City in Land Owner Detail.");
		document.frmMeeICPAssessment.city2.focus();
		return false;
	}
//---------------------------------------------------------------------------------------------------------------
//----------------------------------for ZipCode----------------------------------------------------------
	if(document.frmMeeICPAssessment.zip2.value==""){
		alert("Enter Zipcode in Land Owner Detail.");
		document.frmMeeICPAssessment.zip2.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.zip2.length>10 || document.frmMeeICPAssessment.zip2.length<2 ){
		alert("Enter Zipcode in Land Owner Detail.");
		document.frmMeeICPAssessment.zip2.focus();
		return false;
	}
	if(isnotAlphaNumeric(document.frmMeeICPAssessment.zip2.value)){
		alert("Enter valid Zipcode in Land Owner Detail.");
		document.frmMeeICPAssessment.zip2.focus();
		return false;
	}
	if(isnotZipcode(document.frmMeeICPAssessment.zip2.value)){
		alert("Enter valid Zipcode in Land Owner Detail.");
		document.frmMeeICPAssessment.zip2.focus();
		return false;
	}
	

	if(document.frmMeeICPAssessment.zip2.value.indexOf('.')!=-1){
		alert("Enter valid Zipcode in Land Owner Detail.");
		document.frmMeeICPAssessment.zip2.focus();
		return false;
	}
//-------------------------------------------------------------------------------------------------------
//----------------------IF ENTER EITHER PHONE 

if(document.frmMeeICPAssessment.phone2.value=="" )
{ alert("Enter Contact Number in Land Owner Detail");
  document.frmMeeICPAssessment.phone2.focus();
  return false;}

//--------------------------------PHONE NUMBER---------------------------------------


if(document.frmMeeICPAssessment.phone2.value!="")
{		var s1=document.frmMeeICPAssessment.phone2.value.indexOf('(');
		var s2=document.frmMeeICPAssessment.phone2.value.indexOf(')');
		var s5=document.frmMeeICPAssessment.phone2.value.indexOf('+');
		var s6=document.frmMeeICPAssessment.phone2.value.lastIndexOf('+');
		var s7=document.frmMeeICPAssessment.phone2.value.indexOf('-');
		var s8=document.frmMeeICPAssessment.phone2.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid Phone Number in Land Owner Detail");
			document.frmMeeICPAssessment.phone2.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid Phone Number in Land Owner Detail");
			document.frmMeeICPAssessment.phone2.focus();
			return false;
		}
		if(s5!=s6){
			alert("Enter valid Phone Number in Land Owner Detail");
			document.frmMeeICPAssessment.phone2.focus();
			return false;
		}
		if(s7!=s8){
			alert("Enter valid Phone Number in Land Owner Detail");
			document.frmMeeICPAssessment.phone2.focus();
			return false;
		}
																					  
 len7=document.frmMeeICPAssessment.phone2.value.length;
 strnum = document.frmMeeICPAssessment.phone2.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid Phone Number in Land Owner Detail");
 document.frmMeeICPAssessment.phone2.focus();
 return false;}
 if(document.frmMeeICPAssessment.phone2.value.length>40)
  {alert("Enter valid Phone Number in Land Owner Detail");
  document.frmMeeICPAssessment.phone2.focus();
  return false;}
 
}
//-----------------------------------------FAX--------------------------------
 
		

	var s1=document.frmMeeICPAssessment.fax2.value.indexOf('(');
		var s2=document.frmMeeICPAssessment.fax2.value.indexOf(')');
		var s5=document.frmMeeICPAssessment.fax2.value.indexOf('+');
		var s6=document.frmMeeICPAssessment.fax2.value.lastIndexOf('+');
		var s7=document.frmMeeICPAssessment.fax2.value.indexOf('-');
		var s8=document.frmMeeICPAssessment.fax2.value.lastIndexOf('-');
		var s3=1+s2;
		var s4=1+s1;
		if(s1==s3){
			alert("Enter valid fax in Land Owner Detail");
			document.frmMeeICPAssessment.fax2.focus();
			return false;
		}
		if(s2==s4){
			alert("Enter valid fax in Land Owner Detail");
			document.frmMeeICPAssessment.fax2.focus();
			return false;
		}
		if(s5!==s6){
			alert("Enter valid fax in Land Owner Detail");
			document.frmMeeICPAssessment.fax2.focus();
			return false;
		}
		if(s7!==s8){
			alert("Enter valid fax in Land Owner Detail");
			document.frmMeeICPAssessment.fax2.focus();
			return false;
		}

 len7=document.frmMeeICPAssessment.fax2.value.length;
 strnum = document.frmMeeICPAssessment.fax2.value;
  var GoodChars = "0123456789()-+ ";
 valid = 1;
 for(j=0;j<len7;j++)
{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
   { valid=0;} }
if(valid!=1)
{alert("Enter valid fax in Land Owner Detail");
 document.frmMeeICPAssessment.fax2.focus();
 return false;}
 
 if(document.frmMeeICPAssessment.fax2.value.length>40)
  {alert("Enter valid Fax in Land Owner Detail");
  document.frmMeeICPAssessment.fax2.focus();
  return false;}
  
//---------------------------------------------------------------------------------------------------
//-----------------------------------------for Accept Term ------------------------------
if(!document.frmMeeICPAssessment.acceptterm.checked){
	alert("Please Check the Accept Terms ");
	//document.frmMeeICPAssessment.acceptterm.focus();
	return false;}
//---------------------------------------------for Total Amount----------------------------------
/*if(document.frmMeeICPAssessment.txtamount.value=="")
 {alert("Enter Total Amount in Land Owner Detail");
  document.frmMeeICPAssessment.txtamount.focus();
 return false;}
if(isnotInteger(document.frmMeeICPAssessment.txtamount.value))
 {alert("Enter Total Amount in Land Owner Detail");
  document.frmMeeICPAssessment.txtamount.focus();
 return false;}
*/
//------------------------------------------------------------------------------------------------------------
//================================= For radio Button =========================================

	chosen3="";
	len3 = document.frmMeeICPAssessment.r11.length ;
	for(i3=0;i3<len3;i3++){
		if(document.frmMeeICPAssessment.r11[i3].checked){
			chosen3= document.frmMeeICPAssessment.r11[i3].value;
		}
	}

	if(chosen3==""){
		alert("Check any of the Payment Option.");
		return false;
	}

//=============================== For Card Details ===========================================
//=============================== For Check Number ===========================================

	if(chosen3=="check"){
		if(document.frmMeeICPAssessment.txtChNumber.value==""){
			alert("Enter valid Check Number in Check Details");
			document.frmMeeICPAssessment.txtChNumber.focus();
			return false;
		}

		if(isnotAlphaNumeric(document.frmMeeICPAssessment.txtChNumber.value)){
			alert("Enter valid Check Number in Check Details.");
			document.frmMeeICPAssessment.txtChNumber.focus();
    		return false;
		}

		if(document.frmMeeICPAssessment.txtChNumber.value.indexOf('.')!=-1){
			alert("Enter a valid check number in Check Details.");
			document.frmMeeICPAssessment.txtChNumber.focus();
			return false;
		}

//--------------------------------
//--------for Check Date

maxdays=0;
if(document.frmMeeICPAssessment.checkDate.value=="")
 { alert("Enter valid Check Date in Payment Information");
  document.frmMeeICPAssessment.checkDate.focus();
  return false;}

if(!(document.frmMeeICPAssessment.checkDate.value==""))
{	 todaysDate=new Date();
	 nowDate=todaysDate.getDate();
	 nowYear=todaysDate.getYear();
	 nowMonth1=todaysDate.getMonth();
	 nowMonth=1+nowMonth1;
	if(nowDate<10)
	{nowDate="0"+nowDate;}
	if(nowMonth<10)
	{nowMonth="0"+nowMonth;}
	 
	 
strdate=document.frmMeeICPAssessment.checkDate.value;
mm = Number(strdate.substring(0,2));
dd = Number(strdate.substring(3,5));
yyyy=Number(strdate.substring(6,10));
if(mm >12 || mm< 1)
{alert("Enter valid month in the Check Date in the Payment Details");
 document.frmMeeICPAssessment.checkDate.focus();
 return false;}
 if(yyyy<nowYear || yyyy>2100)
{alert("Enter valid year in the Check Date in the Payment Details");
 document.frmMeeICPAssessment.checkDate.focus();
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
document.frmMeeICPAssessment.checkDate.focus();
return false;}
if(dd>maxdays || dd<1)
{ alert("Enter valid date in the Check Date in the Payment Details");
 document.frmMeeICPAssessment.checkDate.focus();
 return false;}
 if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear))
{alert("Enter valid date in the Check Date in the Payment Details");
document.frmMeeICPAssessment.checkDate.focus();
return false;}
 

}


//------------------------------
//--------for In Favor of
if(document.frmMeeICPAssessment.inFavorof.value.indexOf(' ')==0)
 { alert("Enter valid In Favor of info in Payment Information");
  document.frmMeeICPAssessment.inFavorof.focus();
  return false;}
if(document.frmMeeICPAssessment.inFavorof.value=="")
 { alert("Enter valid In Favor of info in Payment Information");
  document.frmMeeICPAssessment.inFavorof.focus();
  return false;}
if(document.frmMeeICPAssessment.inFavorof.value.length>80)
 { alert("In Favor of info in Payment Information is out of range");
  document.frmMeeICPAssessment.inFavorof.focus();
  return false;}

if(isnotAlpha(document.frmMeeICPAssessment.inFavorof.value))
{alert("Enter valid name in the In Favor of  Info in Payment Information");
 document.frmMeeICPAssessment.inFavorof .focus();
  return false;}
  if(Dospace(document.frmMeeICPAssessment.inFavorof.value))
  {alert("Enter valid name in the In Favor of  Info in Payment Information");
   document.frmMeeICPAssessment.inFavorof .focus();
  return false;}
	}
//================================== For Card Details ========================================
//================================== For Card Number =========================================
	if(chosen3=="card"){
		if(document.frmMeeICPAssessment.txtCard.value==""){
			alert("Enter a Card Number in the Card Details");
			document.frmMeeICPAssessment.txtCard.focus();
			return false;
		}

		if(!Number(document.frmMeeICPAssessment.txtCard.value)){
			alert("Enter valid Card Number in the Card Details");
			document.frmMeeICPAssessment.txtCard.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtCard.value.length!=16 ){
			alert("Enter valid Card Number in the Card Details");
			document.frmMeeICPAssessment.txtCard.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtCard.value.indexOf('.')!=-1){
			alert("Enter valid Card Number in the Card Details.");
			document.frmMeeICPAssessment.txtCard.focus();
			return false;
		}
	
//================================== For CVV Number =========================================
	
		if(document.frmMeeICPAssessment.txtCvvId.value==""){
			alert("Enter valid CVV Number in Card Details");
			document.frmMeeICPAssessment.txtCvvId.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtCvvId.value.length>4){
			alert("Enter valid CVV Number in Card Details");
			document.frmMeeICPAssessment.txtCvvId.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtCvvId.value.length<3){
			alert("Enter valid CVV Number in Card Details");
			document.frmMeeICPAssessment.txtCvvId.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtCvvId.value.indexOf('.')!=-1){
			alert("Enter valid Cvv Number in Card Details.");
			document.frmMeeICPAssessment.txtCvvId.focus();
			return false;
		}
	
//================================== For Card Type =========================================

		if ( document.frmMeeICPAssessment.ccTypeId.selectedIndex == 0 ){
			alert ( "Please select Card Type." );
			document.frmMeeICPAssessment.ccTypeId.focus();
			return false;
		}
		
	
//================================= For Print Name on Card =================================	
		if(document.frmMeeICPAssessment.txtPrName.value==""){
			alert("Enter Print Name in Card Details.");
			document.frmMeeICPAssessment.txtPrName.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtPrName.value.length>80){
			alert("Entered Print Name in Card Details is out of Range.");
			document.frmMeeICPAssessment.txtPrName.focus();
			return false;
		}
	
		if(isnotAlpha(document.frmMeeICPAssessment.txtPrName.value)){
			alert("Enter valid Print Name in Card Details.");
			document.frmMeeICPAssessment.txtPrName.focus();
			return false;
		}
	
		if(Number(document.frmMeeICPAssessment.txtPrName.value)){
			alert("Enter valid Print Name in Card Details.");
			document.frmMeeICPAssessment.txtPrName.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtPrName.value.indexOf(' ')==0){
			alert("Enter valid Print Name in Card Details.");
			document.frmMeeICPAssessment.txtPrName.focus();
			return false;
		}
	
		if(document.frmMeeICPAssessment.txtPrName.value.indexOf('  ')!==-1){
			alert("Enter valid Print Name in Card Details.");
			document.frmMeeICPAssessment.txtPrName.focus();
			return false;
		}

//================================= For Expiry Month =================================	
	
		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.frmMeeICPAssessment.selExpMonthId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration Month." );
			document.frmMeeICPAssessment.selExpMonthId.focus();
			return false;
		}
	
		if (document.frmMeeICPAssessment.selExpYearId.value==theYear){
			 if(document.frmMeeICPAssessment.selExpMonthId.value<theMonth){
				alert("Please Select valid Card Expiration Month.");
				document.frmMeeICPAssessment.selExpMonthId.focus();
				return(false);   
			}
		}
		
//================================= For Expiry Year =================================	
	
		if ( document.frmMeeICPAssessment.selExpYearId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration year." );
			document.frmMeeICPAssessment.selExpYearId.focus();
			return false;
		}
	
		if (document.frmMeeICPAssessment.selExpYearId.value<theYear){
			alert("Please Select valid Card Expiration Year.");
			document.frmMeeICPAssessment.selExpYearId.focus();
			return(false);   
		}
	}




//---------------------------------------------------------------------------------
 

return true;
}

function price()
{

		if ( document.frmMeeICPAssessment.days.selectedIndex == 0 ){
			
			document.frmMeeICPAssessment.tot_amt.value=0;
			
		}

		if ( document.frmMeeICPAssessment.days.selectedIndex == 1 ){
			
			document.frmMeeICPAssessment.tot_amt.value=100;
			
		}


		if ( document.frmMeeICPAssessment.days.selectedIndex > 1 ){
			
			document.frmMeeICPAssessment.tot_amt.value=150;
			
		}

}

