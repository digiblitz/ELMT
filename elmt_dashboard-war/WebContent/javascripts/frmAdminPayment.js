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
//-------------------function for character validation--------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=~,`0123456789;:][{}"+"\\";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//---------------------------------------------------------------------------------------	
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
		alert("Enter a valid month in Check Date")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Enter a valid day in Check Date")
		return false
	}
   if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Enter a valid Check Date")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear ){
		alert("Enter a valid Year in Check Date ")
		return false
	}
	
return true
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
function isnotAlphaNumeric1(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?=~-`.,;:][{}"+"\\";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
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

function isnotIntegerChk(str){
stringIntCheck="0123456789.";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}

function clrRad()
{
	
	chosenrd="";
	lenrd = document.frmMembRegi.r11.length ;
	for(ird=0;ird<lenrd;ird++){
		if(document.frmMembRegi.r11[ird].checked){
			chosenrd= document.frmMembRegi.r11[ird].value;
		}
	}
	
if(chosenrd=="check")
{
		
		document.frmMembRegi.cardselect.selectedIndex=0;
		document.frmMembRegi.cardNo.value="";
		document.frmMembRegi.cVVNo.value="";
		document.frmMembRegi.printName.value="";
		document.frmMembRegi.expirymonth.selectedIndex=0;
		document.frmMembRegi.expiryyear.selectedIndex=0;
			
}
else
{
		document.frmMembRegi.checkNo.value="";
		document.frmMembRegi.checkDate.value="";
		document.frmMembRegi.inFavorof.value="";
		document.frmMembRegi.nameOnchk.value="";
    var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getFullYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	document.frmMembRegi.checkDate.value = nowMonth+"/"+nowDate+"/"+nowYear;
		
}

}

function pendAmt()
{
	
	var chosenrd="";
	
	lenrd = document.frmMembRegi.r11.length;
	
	ird=0;
	
	for(ird=0;ird<lenrd;ird++){
		if(document.frmMembRegi.r11[ird].checked){
			chosenrd= document.frmMembRegi.r11[ird].value;
		}
	}

if(chosenrd == "card")
{
	
	val = Number(document.frmMembRegi.pendAmt.value);
	
	if(val>0)
	{
		document.frmMembRegi.tot_amt.value = document.frmMembRegi.pendAmt.value;
	}
}

}

function myvalidate()
{
//==============================================================================	
chosenrd="";
	lenrd = document.frmMembRegi.r11.length ;
	for(ird=0;ird<lenrd;ird++){
		if(document.frmMembRegi.r11[ird].checked){
			chosenrd= document.frmMembRegi.r11[ird].value;
		}
	}

	/*if(chosenrd==""){
		alert("Check any of the Payment Option.");
		return false;
	}*/	
//=============================== For Card Details ===========================================
//=============================== For Check Number ===========================================

	if(chosenrd=="check"){
		if(document.frmMembRegi.usrStat.value=="null")
		{
		if(isnotIntegerChk(document.frmMembRegi.chckAmount.value)){
		alert("Enter a valid Check Amount");
		document.frmMembRegi.chckAmount.focus();
		return false;
			}
		}
				
		if(document.frmMembRegi.txtChNumber.value==""){
			alert("Enter Check Number in Check Details");
			document.frmMembRegi.txtChNumber.focus();
			return false;
		}

		if(isnotAlphaNumeric(document.frmMembRegi.txtChNumber.value)){
			alert("Enter valid Check Number in Check Details.");
			document.frmMembRegi.txtChNumber.focus();
    		return false;
		}

		if(document.frmMembRegi.txtChNumber.value.indexOf('.')!=-1){
			alert("Enter a Valid check number in Check Details.");
			document.frmMembRegi.txtChNumber.focus();
			return false;
		}

		if(document.frmMembRegi.txtChNumber.value.indexOf(" ")==0){
			alert("Enter a Valid check number in Check Details.");
			document.frmMembRegi.txtChNumber.focus();
			return false;
		}

//--------------------------------
//--------for Check Date

maxdays=0;
	if(document.frmMembRegi.checkDate.value==""){
		alert("Enter a Check Date in Payment Information");
		document.frmMembRegi.checkDate.focus();
		return false;
	}
//---------------------------------------
 if(isnotAlphaNumeric1(document.frmMembRegi.checkDate.value))
	{alert("Enter a valid Check Date in the Payment Information");
	 document.frmMembRegi.checkDate.focus();
	 return false;
     }

if (isDate(document.frmMembRegi.checkDate.value)==false){
			document.frmMembRegi.checkDate.focus()
			return false; 
		}
//-------------------------------------
  
	
	
	
	if(document.frmMembRegi.inFavorof.value==""){
		alert("Enter a Bank Name info in Payment Information");
		document.frmMembRegi.inFavorof.focus();
		return false;
	}
	
	if(document.frmMembRegi.inFavorof.value.indexOf(' ')==0){
		alert("Enter valid Bank Name info in Payment Information");
		document.frmMembRegi.inFavorof.focus();
		return false;
	}

	if(isnotAlpha(document.frmMembRegi.inFavorof.value)){
		alert("Enter valid Bank Name Info in Payment Information");
		document.frmMembRegi.inFavorof .focus();
		return false;
	}
	if(document.frmMembRegi.nameOnchk.value.indexOf(' ')==0){
		alert("Enter valid Name on Check info in Payment Information");
		document.frmMembRegi.nameOnchk.focus();
		return false;
	}
	if(document.frmMembRegi.nameOnchk.value==""){
		alert("Enter valid Name on Check info in Payment Information");
		document.frmMembRegi.nameOnchk.focus();
		return false;
	}
	if(document.frmMembRegi.nameOnchk.value.length>80){
		alert(" Name on Check info in Payment Information is out of range");
		document.frmMembRegi.nameOnchk.focus();
		return false;
	}

	if(isnotAlpha(document.frmMembRegi.nameOnchk.value)){
		alert("Enter valid name in the Name on Check Info in Payment Information");
		document.frmMembRegi.nameOnchk.focus();
		return false;
	}
	
	if(document.frmMembRegi.nameOnchk.value.indexOf(" ")==0){
		alert("Enter valid name in the Name on Check Info in Payment Information");
		document.frmMembRegi.nameOnchk.focus();
		return false;
	}
	
	
}
//================================== For Card Details ========================================

	if(chosenrd=="card"){
 		if(document.frmMembRegi.ccTypeId.selectedIndex == 0 ){
			alert("Please select card type." );
			document.frmMembRegi.ccTypeId.focus();
			return false;
		}
//================================== For Card Number =========================================
		if(document.frmMembRegi.txtCard.value==""){
			alert("Enter a Card Number in the Card Details");
			document.frmMembRegi.txtCard.focus();
			return false;
		}
		if(!Number(document.frmMembRegi.txtCard.value)){
			alert("Enter valid Card Number in the Card Details");
			document.frmMembRegi.txtCard.focus();
			return false;
		}
		if(document.frmMembRegi.ccTypeId.selectedIndex == 3 ){
			if(document.frmMembRegi.txtCard.value.length!=15 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmMembRegi.txtCard.focus();
				return false;
			}
		}
		if(document.frmMembRegi.ccTypeId.selectedIndex == 1 || document.frmMembRegi.ccTypeId.selectedIndex == 2 ){
			if(document.frmMembRegi.txtCard.value.length!=16 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmMembRegi.txtCard.focus();
				return false;
			}
		}
			if(document.frmMembRegi.txtCard.value.indexOf('.')!=-1){
				alert("Enter Valid Card Number in the Card Details.");
				document.frmMembRegi.txtCard.focus();
				return false;
			}
	
//================================== For CVV Number =========================================
	if(document.frmMembRegi.txtCvvId.value!=""){
				
		if(document.frmMembRegi.txtCvvId.value.indexOf(" ")==0 ){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}
		if(document.frmMembRegi.txtCvvId.value.indexOf('  ')!=-1){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}
		if ( document.frmMembRegi.ccTypeId.selectedIndex == 1 || document.frmMembRegi.ccTypeId.selectedIndex == 2 ){
			if(document.frmMembRegi.txtCvvId.value.length !=3){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		    }
		}
		if ( document.frmMembRegi.ccTypeId.selectedIndex == 3 ){
			if(document.frmMembRegi.txtCvvId.value.length !=4){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
			}
		}
		
		if(document.frmMembRegi.txtCvvId.value.indexOf('.')!=-1){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}
		
		if(isnotInteger(document.frmMembRegi.txtCvvId.value)){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}	
		}
	
//================================= For Print Name on Card =================================	
		if(document.frmMembRegi.txtPrName.value==""){
			alert("Enter Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(document.frmMembRegi.txtPrName.value.length>80){
			alert("Entered Print Name in Card Details is out of Range.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(isnotAlpha(document.frmMembRegi.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(Number(document.frmMembRegi.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(document.frmMembRegi.txtPrName.value.indexOf(' ')==0){
			alert("Enter a Valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(document.frmMembRegi.txtPrName.value.indexOf('  ')!==-1){
			alert("Enter Valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}

//================================= For Expiry Month =================================	
	
		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.frmMembRegi.selExpMonthId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration Month." );
			document.frmMembRegi.selExpMonthId.focus();
			return false;
		}
	
		if (document.frmMembRegi.selExpYearId.value==theYear){
			 if(document.frmMembRegi.selExpMonthId.value<theMonth){
				alert("Please Select Valid Card Expiration Month.");
				document.frmMembRegi.selExpMonthId.focus();
				return(false);   
			}
		}
		
//================================= For Expiry Year =================================	
	
		if (document.frmMembRegi.selExpYearId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration year." );
			document.frmMembRegi.selExpYearId.focus();
			return false;
		}
	
		if (document.frmMembRegi.selExpYearId.value<theYear){
			alert("Please Select Valid Card Expiration Year.");
			document.frmMembRegi.selExpYearId.focus();
			return false;   
		}
	}

//================================== For Card Details ========================================

	if(document.frmMembRegi.hidVal.value=="userLogin"){
 		if(document.frmMembRegi.ccTypeId.selectedIndex == 0 ){
			alert("Please select card type." );
			document.frmMembRegi.ccTypeId.focus();
			return false;
		}
//================================== For Card Number =========================================
		if(document.frmMembRegi.txtCard.value==""){
			alert("Enter a Card Number in the Card Details");
			document.frmMembRegi.txtCard.focus();
			return false;
		}
		if(!Number(document.frmMembRegi.txtCard.value)){
			alert("Enter valid Card Number in the Card Details");
			document.frmMembRegi.txtCard.focus();
			return false;
		}
		if(document.frmMembRegi.ccTypeId.selectedIndex == 3 ){
			if(document.frmMembRegi.txtCard.value.length!=15 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmMembRegi.txtCard.focus();
				return false;
			}
		}
		if(document.frmMembRegi.ccTypeId.selectedIndex == 1 || document.frmMembRegi.ccTypeId.selectedIndex == 2 ){
			if(document.frmMembRegi.txtCard.value.length!=16 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmMembRegi.txtCard.focus();
				return false;
			}
		}
			if(document.frmMembRegi.txtCard.value.indexOf('.')!=-1){
				alert("Enter Valid Card Number in the Card Details.");
				document.frmMembRegi.txtCard.focus();
				return false;
			}
	
//================================== For CVV Number =========================================
	
		if(document.frmMembRegi.txtCvvId.value==""){
				alert("Enter CVV Number in Card Details");
				document.frmMembRegi.txtCvvId.focus();
				return false;
		}
		if(document.frmMembRegi.txtCvvId.value.indexOf(" ")==0 ){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}
		if(document.frmMembRegi.txtCvvId.value.indexOf('  ')!=-1){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}
		if ( document.frmMembRegi.ccTypeId.selectedIndex == 1 || document.frmMembRegi.ccTypeId.selectedIndex == 2 ){
			if(document.frmMembRegi.txtCvvId.value.length !=3){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		    }
		}
		if ( document.frmMembRegi.ccTypeId.selectedIndex == 3 ){
			if(document.frmMembRegi.txtCvvId.value.length !=4){
			alert("Enter valid CVV Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
			}
		}
		
		if(document.frmMembRegi.txtCvvId.value.indexOf('.')!=-1){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}
		
		if(isnotInteger(document.frmMembRegi.txtCvvId.value)){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmMembRegi.txtCvvId.focus();
			return false;
		}	
	
//================================= For Print Name on Card =================================	
		if(document.frmMembRegi.txtPrName.value==""){
			alert("Enter Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(document.frmMembRegi.txtPrName.value.length>80){
			alert("Entered Print Name in Card Details is out of Range.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(isnotAlpha(document.frmMembRegi.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(Number(document.frmMembRegi.txtPrName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(document.frmMembRegi.txtPrName.value.indexOf(' ')==0){
			alert("Enter a Valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}
	
		if(document.frmMembRegi.txtPrName.value.indexOf('  ')!==-1){
			alert("Enter Valid Print Name in Card Details.");
			document.frmMembRegi.txtPrName.focus();
			return false;
		}

//================================= For Expiry Month =================================	
	
		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.frmMembRegi.selExpMonthId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration Month." );
			document.frmMembRegi.selExpMonthId.focus();
			return false;
		}
	
		if (document.frmMembRegi.selExpYearId.value==theYear){
			 if(document.frmMembRegi.selExpMonthId.value<theMonth){
				alert("Please Select Valid Card Expiration Month.");
				document.frmMembRegi.selExpMonthId.focus();
				return(false);   
			}
		}
		
//================================= For Expiry Year =================================	
	
		if (document.frmMembRegi.selExpYearId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration year." );
			document.frmMembRegi.selExpYearId.focus();
			return false;
		}
	
		if (document.frmMembRegi.selExpYearId.value<theYear){
			alert("Please Select Valid Card Expiration Year.");
			document.frmMembRegi.selExpYearId.focus();
			return false;   
		}
	}


//---------------------------------------------------------------------------------
	
return true;
}