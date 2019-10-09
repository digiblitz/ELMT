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
// JavaScript Document

//------------------------------for check date validation---------
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}
//------------------------for check date---------
function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strMonth=dtStr.substring(0,pos1)
	var strDay=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("The date format should be : mm/dd/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Enter a valid month in Check Date ")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Enter a valid day in Check Date")
		return false
	}
   if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Enter a valid Check Date ")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear ){
		alert("Enter a valid Year in Check Date ")
		return false
	}
	
return true
}
//---------------
//------------------for chk date------------------------------------------------------------------------

function isnotAlphaNumeric1(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?=~-`.,;:][{}"+"\\";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}

//-----------------------
function isnotIntegerDecimal(str){
		stringIntCheck="0123456789.";
		f2=1;
		for(j=0;j<str.length;j++)
		{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
		 {f2=0;}}
		if(f2==0)
		{return true;} else { return false;}
		}

//-------------------------for Total Amount field-----------------------------
function donaName(){
	  chbxnum2=document.frmDonateProg.donCbxCt.value;
	  var userNam=document.frmDonateProg.userNam.value;
	  
	  for(i=0;i<chbxnum2;i++)	{ 
		chbxname = "donCb"+i;
		txtname="donTb"+i;
		dontxtname="donNametb"+i;
//		alert('usrnme'+usrnme);	  
		//alert(chbxname); 
		  if(document.getElementById(''+chbxname).checked){ 
			document.getElementById(''+dontxtname).value=userNam;
		  }
		  else{
			document.getElementById(''+dontxtname).value="";
		  }
	  }
}

var sum;
function Sumup(){
	sum=0;
	document.getElementById('totalAmount').value=sum;
	
	chbxnum2=document.frmDonateProg.donCbxCt.value;
	
	for(i=0;i<chbxnum2;i++)
	{ chbxname = "donCb"+i;
	  txtname="donTb"+i;
	  //alert(chbxname); 
	  if(document.getElementById(''+chbxname).checked)

	  {  	 
	     var txtval= document.getElementById(''+txtname).value;
		 if(isnotIntegerDecimal(txtval) == false)
		 {
	     	sum= Number(txtval) +sum;
			
			 document.getElementById('totalAmount1').value = sum;
			 document.getElementById('totalAmount').value=format(sum,2);
		 }
		 else
		 {
			alert("Enter a valid Donation Amount");
			document.getElementById(''+txtname).focus();
			return false;
		 }
	  }
	}	
	
}

 
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
//---------------------------------------------------------------------------------
//-------------for dynamic value to textbox
//---------------------------------------------------------------------------------
function checkclear(){
document.frmDonateProg.txtChNumber.value="";
document.frmDonateProg.inFavorof.value="";
document.frmDonateProg.nameOnchk.value="";
  var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getFullYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	document.frmDonateProg.checkDate.value = nowMonth+"/"+nowDate+"/"+nowYear;
}

function cardclear(){
document.frmDonateProg.ccTypeId.selectedIndex=0;
document.frmDonateProg.txtCard.value="";
document.frmDonateProg.txtCvvId.value="";
document.frmDonateProg.txtPrName.value="";
document.frmDonateProg.selExpMonthId.selectedIndex=0;
document.frmDonateProg.selExpYearId.selectedIndex=0;
}
//-------------------------------------------------------------------------------------
//----------------------------for Payment Option----------------------------
function myValidate(){
//------------------------------for Donation Amount
	
	
	chbxnum2=document.frmDonateProg.donCbxCt.value;
    //alert(chbxnum2);
	for(i=0;i<chbxnum2;i++){ 
	  chbxname = "donCb"+i;
	  txtname="donTb"+i;
	  dontxtname="donNametb"+i;
	  
	  //alert(chbxname); 
	  if(document.getElementById(''+chbxname).checked){ 
		  var txtval= document.getElementById(''+txtname).value;
			 if(txtval=="" ||txtval==0){
				 alert("Enter the Donation Amount");
				 document.getElementById(''+txtname).focus();
				 return false;
			 }
			  //alert(chbxname);
			 if(!Number(txtval)){
				alert("Enter valid Donation Amount");
				document.getElementById(''+txtname).focus();
				return false;
			 }
			if(txtval.indexOf('.')>8){
				alert("Enter valid Donation Amount");
				document.getElementById(''+txtname).focus();;
				return false;
			}
			if((txtval.indexOf('.'))==-1){
				if(txtval.length>8){
					alert("Enter valid Donation Amount");
					document.getElementById(''+txtname).focus();;
					return false;
				}
			}
			
			if(!(txtval=="")){
				if((txtval.indexOf('.'))!=-1){
					fstr=txtval;
					fstr1=txtval.lastIndexOf('.');
					mm = (fstr.substring(fstr1,txtval.length));
					str=(Number(mm.length));
					if((str)>3){
						alert("Donation Amount is not valid");
						document.getElementById(''+txtname).focus();;
						return false;
					}
				}
			}
			if(document.getElementById(''+dontxtname).value=="" || document.getElementById(''+dontxtname).value.indexOf(" ")==0){
				alert("Enter the Donation Name");
				document.getElementById(''+dontxtname).focus();
				return false;
			}
	  }
	  // return false;
	  
	  if(document.getElementById('totalAmount').value==0)
	  {
		alert("Please Make Donation");
		return false;
	  }
	}
		
//==============================================================================	
chosenrd="";
	lenrd = document.frmDonateProg.r11.length ;
	
	for(ird=0;ird<lenrd;ird++){
		if(document.frmDonateProg.r11[ird].checked){
			chosenrd= document.frmDonateProg.r11[ird].value;
		}
	}
	
	/*if(chosenrd==""){
		alert("Check any of the Payment Option.");
		return false;
	}*/
//=============================== For Card Details ===========================================
//=============================== For Check Number ===========================================

	if(chosenrd=="check"){
				
		if(document.frmDonateProg.txtChNumber.value==""){
			alert("Enter Check Number in Check Details");
			document.frmDonateProg.txtChNumber.focus();
			return false;
		}

		if(isnotAlphaNumeric(document.frmDonateProg.txtChNumber.value)){
			alert("Enter valid Check Number in Check Details.");
			document.frmDonateProg.txtChNumber.focus();
    		return false;
		}

		if(document.frmDonateProg.txtChNumber.value.indexOf('.')!=-1){
			alert("Enter a Valid check number in Check Details.");
			document.frmDonateProg.txtChNumber.focus();
			return false;
		}

		if(document.frmDonateProg.txtChNumber.value.indexOf(" ")==0){
			alert("Enter a Valid check number in Check Details.");
			document.frmDonateProg.txtChNumber.focus();
			return false;
		}

//--------------------------------
//--------for Check Date


	if(document.frmDonateProg.checkDate.value==""){
		alert("Enter a Check Date in Payment Information");
		document.frmDonateProg.checkDate.focus();
		return false;
	}
 if(isnotAlphaNumeric1(document.frmDonateProg.checkDate.value))
	{alert("Enter a valid Check Date in the Payment Details");
	 document.frmDonateProg.checkDate.focus();
	 return false;
     }

if (isDate(document.frmDonateProg.checkDate.value)==false){
			document.frmDonateProg.checkDate.focus()
			return false; 
		}
	if(document.frmDonateProg.inFavorof.value==""){
		alert("Enter a Bank Name info in Payment Information");
		document.frmDonateProg.inFavorof.focus();
		return false;
	}
	
	if(document.frmDonateProg.inFavorof.value.indexOf(' ')==0){
		alert("Enter valid Bank Name info in Payment Information");
		document.frmDonateProg.inFavorof.focus();
		return false;
	}

/*	if(isnotAlpha(document.frmDonateProg.inFavorof.value)){
		alert("Enter valid Bank Name Info in Payment Information");
		document.frmDonateProg.inFavorof .focus();
		return false;
	}*/
	if(document.frmDonateProg.nameOnchk.value.indexOf(' ')==0){
		alert("Enter valid Name on Check info in Payment Information");
		document.frmDonateProg.nameOnchk.focus();
		return false;
	}
	if(document.frmDonateProg.nameOnchk.value==""){
		alert("Enter valid Name on Check info in Payment Information");
		document.frmDonateProg.nameOnchk.focus();
		return false;
	}
	if(document.frmDonateProg.nameOnchk.value.length>80){
		alert(" Name on Check info in Payment Information is out of range");
		document.frmDonateProg.nameOnchk.focus();
		return false;
	}

/*	if(isnotAlpha(document.frmDonateProg.nameOnchk.value)){
		alert("Enter valid name in the Name on Check Info in Payment Information");
		document.frmDonateProg.nameOnchk.focus();
		return false;
	}*/
	if(document.frmDonateProg.nameOnchk.value.indexOf('.')!=-1){
		alert("Enter valid name in the Name on Check Info in Payment Information");
		document.frmDonateProg.nameOnchk.focus();
		return false;
	}

	if(document.frmDonateProg.nameOnchk.value.indexOf(" ")==0){
		alert("Enter valid name in the Name on Check Info in Payment Information");
		document.frmDonateProg.nameOnchk.focus();
		return false;
	}
}
//================================== For Card Details ========================================

	if(chosenrd=="card"){
 		if(document.frmDonateProg.ccTypeId.selectedIndex == 0 ){
			alert("Please select card type." );
			document.frmDonateProg.ccTypeId.focus();
			return false;
		}
//================================== For Card Number =========================================
		if(document.frmDonateProg.txtCard.value==""){
			alert("Enter a Card Number in the Card Details");
			document.frmDonateProg.txtCard.focus();
			return false;
		}
		if(!Number(document.frmDonateProg.txtCard.value)){
			alert("Enter valid Card Number in the Card Details");
			document.frmDonateProg.txtCard.focus();
			return false;
		}
		if(document.frmDonateProg.ccTypeId.selectedIndex == 3 ){
			if(document.frmDonateProg.txtCard.value.length!=15 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmDonateProg.txtCard.focus();
				return false;
			}
		}
		if(document.frmDonateProg.ccTypeId.selectedIndex == 1 || document.frmDonateProg.ccTypeId.selectedIndex == 2 ){
			if(document.frmDonateProg.txtCard.value.length!=16 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmDonateProg.txtCard.focus();
				return false;
			}
		}
			if(document.frmDonateProg.txtCard.value.indexOf('.')!=-1){
				alert("Enter Valid Card Number in the Card Details.");
				document.frmDonateProg.txtCard.focus();
				return false;
			}
	
//================================== For CVV Number =========================================
	
		if(document.frmDonateProg.txtCvvId.value!=""){
				
		if(document.frmDonateProg.txtCvvId.value.indexOf(" ")==0 ){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		if(document.frmDonateProg.txtCvvId.value.indexOf('  ')!=-1){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		if ( document.frmDonateProg.ccTypeId.selectedIndex == 1 || document.frmDonateProg.ccTypeId.selectedIndex == 2 ){
			if(document.frmDonateProg.txtCvvId.value.length !=3){
			alert("Enter valid CVV Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		    }
		}
		if ( document.frmDonateProg.ccTypeId.selectedIndex == 3 ){
			if(document.frmDonateProg.txtCvvId.value.length !=4){
			alert("Enter valid CVV Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
			}
		}
		
		if(document.frmDonateProg.txtCvvId.value.indexOf('.')!=-1){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		
		if(isnotInteger(document.frmDonateProg.txtCvvId.value)){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}	
		}
	
//================================= For Print Name on Card =================================	
		if(document.frmDonateProg.txtPrName.value==""){
			alert("Enter Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(document.frmDonateProg.txtPrName.value.length>80){
			alert("Entered Print Name in Card Details is out of Range.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(isnotAlpha(document.frmDonateProg.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(Number(document.frmDonateProg.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(document.frmDonateProg.txtPrName.value.indexOf(' ')==0){
			alert("Enter a Valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(document.frmDonateProg.txtPrName.value.indexOf('  ')!==-1){
			alert("Enter Valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}

//================================= For Expiry Month =================================	
	
		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.frmDonateProg.selExpMonthId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration Month." );
			document.frmDonateProg.selExpMonthId.focus();
			return false;
		}
	
		if (document.frmDonateProg.selExpYearId.value==theYear){
			 if(document.frmDonateProg.selExpMonthId.value<theMonth){
				alert("Please Select Valid Card Expiration Month.");
				document.frmDonateProg.selExpMonthId.focus();
				return(false);   
			}
		}
		
//================================= For Expiry Year =================================	
	
		if (document.frmDonateProg.selExpYearId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration year." );
			document.frmDonateProg.selExpYearId.focus();
			return false;
		}
	
		if (document.frmDonateProg.selExpYearId.value<theYear){
			alert("Please Select Valid Card Expiration Year.");
			document.frmDonateProg.selExpYearId.focus();
			return false;   
		}
	}

if(document.frmDonateProg.hidVal.value=="userLogin"){
	   if(document.frmDonateProg.ccTypeId.selectedIndex == 0 ){
			alert("Please select card type." );
			document.frmDonateProg.ccTypeId.focus();
			return false;
		}
//================================== For Card Number =========================================
		if(document.frmDonateProg.txtCard.value==""){
			alert("Enter a Card Number in the Card Details");
			document.frmDonateProg.txtCard.focus();
			return false;
		}
		if(!Number(document.frmDonateProg.txtCard.value)){
			alert("Enter valid Card Number in the Card Details");
			document.frmDonateProg.txtCard.focus();
			return false;
		}
		if(document.frmDonateProg.ccTypeId.selectedIndex == 3 ){
			if(document.frmDonateProg.txtCard.value.length!=15 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmDonateProg.txtCard.focus();
				return false;
			}
		}
		if(document.frmDonateProg.ccTypeId.selectedIndex == 1 || document.frmDonateProg.ccTypeId.selectedIndex == 2 ){
			if(document.frmDonateProg.txtCard.value.length!=16 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmDonateProg.txtCard.focus();
				return false;
			}
		}
			if(document.frmDonateProg.txtCard.value.indexOf('.')!=-1){
				alert("Enter Valid Card Number in the Card Details.");
				document.frmDonateProg.txtCard.focus();
				return false;
			}
	
//================================== For CVV Number =========================================
	
		
		if(document.frmDonateProg.txtCvvId.value==""){
				alert("Enter CVV Number in Card Details");
				document.frmDonateProg.txtCvvId.focus();
				return false;
		}
		if(document.frmDonateProg.txtCvvId.value.indexOf(" ")==0 ){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		if(document.frmDonateProg.txtCvvId.value.indexOf('  ')!=-1){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		if ( document.frmDonateProg.ccTypeId.selectedIndex == 1 || document.frmDonateProg.ccTypeId.selectedIndex == 2 ){
			if(document.frmDonateProg.txtCvvId.value.length !=3){
			alert("Enter valid CVV Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		    }
		}
		if ( document.frmDonateProg.ccTypeId.selectedIndex == 3 ){
			if(document.frmDonateProg.txtCvvId.value.length !=4){
			alert("Enter valid CVV Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
			}
		}
		
		if(document.frmDonateProg.txtCvvId.value.indexOf('.')!=-1){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		
		if(isnotInteger(document.frmDonateProg.txtCvvId.value)){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}	
	
		
		
	
	
//================================= For Print Name on Card =================================	
		if(document.frmDonateProg.txtPrName.value==""){
			alert("Enter Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(document.frmDonateProg.txtPrName.value.length>80){
			alert("Entered Print Name in Card Details is out of Range.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(isnotAlpha(document.frmDonateProg.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(Number(document.frmDonateProg.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(document.frmDonateProg.txtPrName.value.indexOf(' ')==0){
			alert("Enter a Valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}
	
		if(document.frmDonateProg.txtPrName.value.indexOf('  ')!==-1){
			alert("Enter Valid Print Name in Card Details.");
			document.frmDonateProg.txtPrName.focus();
			return false;
		}

//================================= For Expiry Month =================================	
	
		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.frmDonateProg.selExpMonthId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration Month." );
			document.frmDonateProg.selExpMonthId.focus();
			return false;
		}
	
		if (document.frmDonateProg.selExpYearId.value==theYear){
			 if(document.frmDonateProg.selExpMonthId.value<theMonth){
				alert("Please Select Valid Card Expiration Month.");
				document.frmDonateProg.selExpMonthId.focus();
				return(false);   
			}
		}
		
//================================= For Expiry Year =================================	
	
		if (document.frmDonateProg.selExpYearId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration year." );
			document.frmDonateProg.selExpYearId.focus();
			return false;
		}
	
		if (document.frmDonateProg.selExpYearId.value<theYear){
			alert("Please Select Valid Card Expiration Year.");
			document.frmDonateProg.selExpYearId.focus();
			return false;   
		}
	}


//---------------------------------------------------------------------------------
	
return true;
}

function format(obj, decimal) {
	//alert("obj" +obj);
 //decimal  - the number of decimals after the digit from 0 to 3
//-- Returns the passed number as a string in the xxx,xxx.xx format.
	   anynum=eval(obj);
	   divider =10;
	   switch(decimal){
			case 0:
				divider =1;
				break;
			case 1:
				divider =10;
				break;
			case 2:
				divider =100;
				break;
			default:  	 //for 3 decimal places
				divider =1000;
		}

	   workNum=Math.abs((Math.round(anynum*divider)/divider));

	   workStr=""+workNum

	   if (workStr.indexOf(".")==-1){workStr+="."}

	   dStr=workStr.substr(0,workStr.indexOf("."));dNum=dStr-0
	   pStr=workStr.substr(workStr.indexOf("."))

	   while (pStr.length-1< decimal){pStr+="0"}

	   if(pStr =='.') pStr ='';

	   //--- Adds a comma in the thousands place.    
	   if (dNum>=1000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000))+","+dStr.substring(dLen-3,dLen)
	   }

	   //-- Adds a comma in the millions place.
	   if (dNum>=1000000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000000))+","+dStr.substring(dLen-7,dLen)
	   }
	   retval = dStr + pStr
	   //-- Put numbers in parentheses if negative.
	   if (anynum<0) {retval="("+retval+")";}

	  
	//You could include a dollar sign in the return value.
	  //retval =  "$"+retval
	  //alert("retval" + retval);
	  return retval;
 }
