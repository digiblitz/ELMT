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

function showLevels(chkBxNam,divId){
		if(document.forms['myform'].elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.forms['myform'].elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function enabDisab(chkBxId,txtBxId){
	  
		if(document.forms['myform'].elements[chkBxId].checked == true){
				document.forms['myform'].elements[txtBxId].disabled = false;	
		}else {
				document.forms['myform'].elements[txtBxId].disabled = "disabled";	
		} 
}
function enabDisabPony(radioNam,txtBxId){
		if(document.forms['myform'].elements[radioNam].value == "yes"){
			//alert('Hi!');
				document.forms['myform'].elements[txtBxId].disabled = false;	
		}else {
				document.forms['myform'].elements[txtBxId].disabled = "disabled";	
		} 
}
function enableRad(radBtnId,txtBxId){
		if(document.forms['myform'].elements[radBtnId].checked == true){
				document.forms['myform'].elements[txtBxId].disabled = false;	
		}else{
				document.forms['myform'].elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.forms['myform'].elements[radBtnNewId].checked == true){
				document.forms['myform'].elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.forms['myform'].elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}
//========================== function for character validation ================================
function isnotAlpha(str){
	stringCheck="!@#$%^*()_+|<>?/=~`0123456789;:][{}"+"\\";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}
function isHorseAlpha(str){
	stringCheck="!@#$%^&*()_+|<>?/=~`;:][{}"+"\\";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

function isnotAlpha1(str){
	stringCheck="!@#$%^&*()_+|<>?/=,~`0123456789;:][{}"+"\\";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

//========================= function for pure Integer Numbers ===================================
function isnotInteger(str){
	stringIntCheck="0123456789";
	f2=1;
	for(j=0;j<str.length;j++){
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{
		return false;
	}
}

//======================== function for two decimal points ======================================
function isnotValidDecimal(str){
	if((str.indexOf("."))!=-1){
		fr1=str.indexOf('.');
		mm = (str.substring(fr1,str.length));
		strnum=(Number(mm.length));
		if(strnum>3){
			return true;
		}
		else{
			return false;
		}
	}
}

//========================== function for Special Character =====================================
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\";
	f4=1;
	for(j=0;j<str.length;j++){
		if(stringSpecialCheck.indexOf(str.charAt(j))!=-1){
			f4=0;
		}
	}
	if(f4==0){
		return true;
	}
	else{
		return false;
	}
}

function isSpecialCharacter(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`;:"+"\\"+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

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

function checkClear(){
	if(document.myform.roughVal.value=='yes'){
		document.myform.chkBalAmt.value="";
	}
	document.myform.checkNumber.value="";
	document.myform.checkDate.value="";
	document.myform.bankName.value="";
	document.myform.nameCheck.value="";	
}

function cardClear(){
	document.myform.ccNumber.value="";
	document.myform.ccCvvid.value="";
	document.myform.ccType.selectedIndex=0;
	document.myform.ccName.value="";
	document.myform.ccExpMonth.selectedIndex=0;
	document.myform.ccExpYear.selectedIndex=0;
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getFullYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	document.myform.checkDate.value = nowMonth+"/"+nowDate+"/"+nowYear;
}

function popupCal(val) {
      if(val==1)
	      document.myform.dateImported.value = "StartDate1";
      if(val==2)
         document.myform.checkDate.value = "StartDate1";
      val = window.open("javascripts/calendar2.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null) {
         alert("Plz Enable Site Popup Allowed");
      }
}

function Disp(){
   
 if(document.myform.status.value=="yes"){
 	    sum= document.myform.totalAmount.value; 
		chkLen = document.myform.phoneCharge.value;
		 
			if(document.myform.phoneCharge.checked){
			sum = Number(chkLen) + Number(sum);
		 
 	 		document.myform.totalAmount.value= format(sum,2);
 			 }

			 else{
				document.myform.totalAmount.value = document.myform.totalAmount.value - document.myform.phoneCharge.value;
				document.myform.totalAmount.value = format(document.myform.totalAmount.value,2);
 			}
}
}
function format(obj, decimal) {
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

	  
	  return retval;
 }
 
function upgrdeval(){
	
	if(document.myform.feeDisp.value=="" || document.myform.feeDisp.value.indexOf(' ')==0){
		alert('Select Any One Of The Membership Types');
		document.myform.amtPay.value = "0.0"
		document.myform.totalAmount.value= "0.0";
		document.myform.fee_txt.value= "0.0";
		return false;
	}
	if(document.myform.yearFoaled.value!="N/A"){
		var thisyear = (new Date()).getFullYear();
		
		var horseVal = document.myform.feeDisp.value;
		var FEHMin = document.myform.FEHMin.value;
		var FEHMax = document.myform.FEHMax.value;
		var YEHMin = document.myform.YEHMin.value;
		var YEHMax = document.myform.YEHMax.value;
		
		curntAge = Number(thisyear)- Number(document.myform.yearFoaled.value);
		
		var splitVal = horseVal.split('#');
		if(splitVal[1]=="Future Event Horse"){
			if(Number(curntAge)==0){
				var alrtMsg = "By rule, your horse will be turning 1 year old on January 1st of the upcoming year,\n"+
							  "and will be eligible to enter Future Event Horse competitions only by upcoming year";
				alert(alrtMsg);
			}
			if(Number(curntAge) >= Number(FEHMin) && Number(curntAge) < Number(FEHMax)){
			}
			if(Number(curntAge) == Number(FEHMax)){
				var alrtMsg = "By rule, your horse will be turning 4 years old on January 1st of the upcoming year,\n "+
							  "and will be ineligible to enter Future Event Horse competitions. If you will not be \n"+
							  "competing this horse in FEH competition prior to November 30th of this year, you should \n"+
							  "register this horse at the Young Event Horse Level or above.";
				alert(alrtMsg);
			}
			if(Number(curntAge)>Number(FEHMax)){
				alert('The age of this horse disqualifies it for competition at the selected Horse Registration level. \n'+
					  'Please select Young Event Horse (5 or 6 year olds), Limited or Full registration level.');
				document.myform.feeDisp.selectedIndex = 0;
				document.myform.feeDisp.focus();
				return false;
			}
		}
		else if(splitVal[1]=="Young Event Horse"){
			if(Number(curntAge)<Number(YEHMin)){
				var alrtMsg1 = "By rule, horses competing at the Young Event Horse level must be at least 4 years old.\n"+
								"If you will be competing this horse prior to November 30th of this year,\n"+
								"it can only be in Future Event Horse level competitions.";
				alert(alrtMsg1);
			}
			if(Number(curntAge) >= Number(YEHMin )&& Number(curntAge) < Number(YEHMax)){
			}
			if(Number(curntAge) == Number(YEHMax)){
				var alrtMsg = "By rule, your horse will be 6 years old on January 1st of the upcoming year, and will be ineligible "+
							  "to enter Young Event Horse level competitions. If you will not be competing this horse in YEH competition "+
							  "prior to November 30th of this year, you should register this horse at the Limited level or above.";
				alert(alrtMsg);
			}
			if(Number(curntAge)>Number(YEHMax)){
				alert('The age of this horse disqualifies it for competition at the selected Horse Registration level.\m'+
					  'Please select Limited or Full registration level.');
				document.myform.feeDisp.selectedIndex = 0;
				document.myform.feeDisp.focus();				
				return false;
			}
		}
	}

	 var amtSct = document.myform.amountSect.value;
	 var totalAmount = document.myform.totalAmount.value;
	
	if(totalAmount>0){
	
//================================== For Check & Card Option =======================
    sec="";
     sec =document.myform.c11.value;
	 if(sec=="adminSec"){
	chosen="";
	len = document.myform.r11.length ;
	for(i=0;i<len;i++){
		if(document.myform.r11[i].checked){
			chosen= document.myform.r11[i].value;
		}
	}
	if(chosen==""){
		alert("Check any of the Payment Option.");
		return false;
	}
//--------------------------Check  Number ---------------------------
	if(chosen=="check"){
		
		if(document.myform.roughVal.value=='yes'){
				if(document.myform.chkBalAmt.value!="" && !Number(document.myform.chkBalAmt.value)){
					alert("Enter valid Check enclosed amount in Check Details");
					document.myform.chkBalAmt.focus();
					return false;
				}
				
				if(document.myform.chkBalAmt.value.indexOf('.')>7){
					alert("Enter valid Check enclosed amount in Check Details");
					document.myform.chkBalAmt.focus();
					return false;
				}
				if((document.myform.chkBalAmt.value.indexOf('.'))==-1){
					if(document.myform.chkBalAmt.value.length>7){
						alert("Enter valid Check enclosed amount in Check Details");
						document.myform.chkBalAmt.focus();
						return false;
					}
				}
		}
		
		
		if(document.myform.checkNumber.value==""){
			alert("Check Number in Check Details cannot be empty");
			document.myform.checkNumber.focus();
			return false;
		}
		if(document.myform.checkNumber.value.length>16){
			alert("Check Number exceeds the maximum of 16 characters.");
			document.myform.checkNumber.focus();
			return false;
		}
		if(document.myform.checkNumber.value.indexOf('.')!=-1){
			alert("Enter Valid check number.");
			document.myform.checkNumber.focus();
			return false;
		}
	 
 		if(document.myform.checkDate.value==""){
			alert("Select Date in Check Details");
			document.myform.checkDate.focus();
			return false;
		}
		
	//	if(document.myform.logby.value=="yes"){
			if((document.myform.checkDate.value!="") || (document.myform.checkDate.value.indexOf(' ')!=0)){
				dtStr = document.myform.checkDate.value;
				var daysInMonth = DaysArray(12);
				var pos1=dtStr.indexOf(dtCh);
				var pos2=dtStr.indexOf(dtCh,pos1+1);
				var strMonth=dtStr.substring(0,pos1);
				var strDay=dtStr.substring(pos1+1,pos2);
				var strYear=dtStr.substring(pos2+1);
				strYr=strYear;
				if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
				if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
				for (var i = 1; i <= 3; i++) {
				if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
				}
				month=parseInt(strMonth);
				day=parseInt(strDay);
				year=parseInt(strYr);
				if (pos1==-1 || pos2==-1){
				alert("The date format should be : mm/dd/yyyy");
				document.myform.checkDate.focus();
				return false;
				}
				if (strMonth.length<1 || month<1 || month>12){
				alert("Please enter a valid month");
				document.myform.checkDate.focus();
				return false;
				}
				if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
				alert("Please enter a valid day");
				document.myform.checkDate.focus();
				return false;
				}
				if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
				alert("Please enter a valid 4 digit Year");
				document.myform.checkDate.focus();
				return false;
				}
				if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
				alert("Please enter a valid date");
				document.myform.checkDate.focus();
				return false;
				}
			}
	 //	}

		if(document.myform.bankName.value==""){
			alert("Bank Name cannot be empty.");
			document.myform.bankName.focus();
			return false;
		}
		if(document.myform.bankName.value.length>80){
			alert("Bank Name exceeds the range");
			document.myform.bankName.focus();
			return false;
		}
		if(isnotAlpha(document.myform.bankName.value)){
			alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;
		}
		if(document.myform.bankName.value.indexOf(' ')==0){
				alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;
		}
		if(document.myform.bankName.value.indexOf('  ')!==-1){
			alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;

		}
		if(Number(document.myform.bankName.value)){
				alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;
		}

//===================================Name on Check====================================
		if(document.myform.nameCheck.value==""){
			alert("Name on Check cannot be empty.");
			document.myform.nameCheck.focus();
			return false;
		}
		if(document.myform.nameCheck.value.length>80){
			alert("Name on Check exceeds the range");
			document.myform.nameCheck.focus();
			return false;
		}
		if(isnotAlpha(document.myform.nameCheck.value)){
			alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
		if(document.myform.nameCheck.value.indexOf(' ')==0){
				alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
		if(document.myform.nameCheck.value.indexOf('  ')!==-1){
				alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
		if(Number(document.myform.nameCheck.value)){
				alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
	}
	 }
//===========================card details=========================
// ======================= For Card No =============================
if(sec=="userSec" || (sec=="adminSec" && chosen=="card")){
	//alert(sec);
	//if(chosen=="card"){
		if (document.myform.ccType.selectedIndex == 0 ){
			alert ( "Please select card type." );
			document.myform.ccType.focus();
			return false;
		}
		if(document.myform.ccNumber.value==""){
			alert("Enter Credit Card Number ");
			document.myform.ccNumber.focus();
			return false;
		}
		if(document.myform.ccNumber.value.indexOf(' ')==0){
			alert("Empty space(s) are not allowed for Card Number.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(document.myform.ccNumber.value.indexOf('  ')!==-1){
			alert("Empty space(s) are not allowed for Card Number.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(!isnotAlpha(document.myform.ccNumber.value)){
			alert("Alphabets are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(isSpecialCharacter(document.myform.ccNumber.value)){
			alert("Special Characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(!Number(document.myform.ccNumber.value)){
			alert("Alphabets and Special Characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		
//===================== For card type ================
		if(document.myform.ccType.value=="American Express"){
			if(document.myform.ccNumber.value.length!=15){
				alert("Credit card number should be 15 Character");
				document.myform.ccNumber.focus();
				return false;
			}
	     }
		 else{
			if(document.myform.ccNumber.value.length!=16){
				alert("Credit card number should be 16 Character");
				document.myform.ccNumber.focus();
				return false;
			}
		 }
		if(document.myform.ccNumber.value.indexOf('.')!=-1){
			alert("Alphabets and special characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}

//--------for CVV Number -------------------------------------
		if(sec=="userSec"){
				if(document.myform.ccCvvid.value==""){
						alert("Enter CVV Number in Card Details");
						document.myform.ccCvvid.focus();
						return false;
					}
					
		   }
			if(document.myform.ccCvvid.value!=""){
				if(document.myform.ccCvvid.value.indexOf(' ')==0){
				alert("Empty space(s) are not allowed for CVV Number.");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccCvvid.value.indexOf('  ')!==-1){
				alert("Empty space(s) are not allowed for CVV Number.");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccCvvid.value.indexOf('.')!=-1){
				alert("Enter Valid Cvv Number in Card Details.");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(isnotInteger(document.myform.ccCvvid.value)){
				alert("Enter Valid Cvv Number in Card Details.");
				document.myform.ccCvvid.focus();
				return false;
			}
				if ( document.myform.ccType.selectedIndex == 3 ){
					if(document.myform.ccCvvid.value.length!=4){
					alert("Enter valid CVV Number in Card Details");
					document.myform.ccCvvid.focus();
					return false;
					}
				}
			
			if(document.myform.ccCvvid.value!=""){
				if ( document.myform.ccType.selectedIndex == 1 || document.myform.ccType.selectedIndex == 2 )	{
					if(document.myform.ccCvvid.value.length!=3)	{
					alert("Enter valid CVV Number in Card Details");
					document.myform.ccCvvid.focus();
					return false;
					}
				}
			}

		}

//----for Print Name on Card ----------------------------------------
		if(document.myform.ccName.value==""){
			alert("Print Name cannot be empty");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.length>80){
			alert("Print Name exceeds the range");
			document.myform.ccName.focus();
			return false;
		}
	
		if(isnotAlpha(document.myform.ccName.value)){
			alert("Enter valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}
	
		if(Number(document.myform.ccName.value)){
			alert("Enter valid card name");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.indexOf(' ')==0){
			alert("Enter Valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.indexOf('  ')!==-1){
			alert("Enter Valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}

//---for Expiry month drop down --------------------------------

		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.myform.ccExpMonth.selectedIndex == 0 ){
			alert ( "Please select a Month." );
			document.myform.ccExpMonth.focus();
			return false;
		}
	
		if (document.myform.ccExpYear.value==theYear){
			 if(document.myform.ccExpMonth.value<theMonth){
				alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
				document.myform.ccExpMonth.focus();
				return(false);   
			}
		}

//-----------for Expiry Year drop down

		if(document.myform.ccExpYear.selectedIndex == 0 ){
			alert ( "Please select a year." );
			document.myform.ccExpYear.focus();
			return false;
		}
		
	
		if (document.myform.ccExpYear.value<theYear){
			alert("You have selected an Expired Year in card details. Please Select Valid Expiration Year.");
			document.myform.ccExpYear.focus();
			return false;   
		}
	//}
}
	if(sec=="adminSec"){
		if(document.myform.activeDatVal.value==""){
			alert("Select Activation Date");
			document.myform.activeDatVal.focus();
			return false;
		}
		if((document.myform.activeDatVal.value!="")||(document.myform.activeDatVal.value.indexOf(' ')!=0)){
		
				dtStr = document.myform.activeDatVal.value;
				var daysInMonth = DaysArray(12);
				var pos1=dtStr.indexOf(dtCh);
				var pos2=dtStr.indexOf(dtCh,pos1+1);
				var strMonth=dtStr.substring(0,pos1);
				var strDay=dtStr.substring(pos1+1,pos2);
				var strYear=dtStr.substring(pos2+1);
				strYr=strYear;
				if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
				if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
				for (var i = 1; i <= 3; i++) {
				if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
				}
				month=parseInt(strMonth);
				day=parseInt(strDay);
				year=parseInt(strYr);
				if (pos1==-1 || pos2==-1){
				alert("The date format should be : mm/dd/yyyy");
				document.myform.activeDatVal.focus();
				return false;
				}
				if (strMonth.length<1 || month<1 || month>12){
				alert("Please enter a valid month");
				document.myform.activeDatVal.focus();
				return false;
				}
				if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
				alert("Please enter a valid day");
				document.myform.activeDatVal.focus();
				return false;
				}
				if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
				alert("Please enter a valid 4 digit Year");
				document.myform.activeDatVal.focus();
				return false;
				}
				if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
				alert("Please enter a valid date");
				document.myform.activeDatVal.focus();
				return false;
				}
		}
	}
}
	return true;
 }
 
var dtCh= "/";
var minYear=1900;
var maxYear=2500;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31;
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
		if (i==2) {this[i] = 29;}
   } 
   return this;
}


function Dispall(){
	j=0;
	num1=0;
	num2=0;
	arrayval = new Array();
	sum=0;
	lenfee=document.myform.feeDisp.length;
	for (i = 0; i < lenfee; i++){
		if(document.myform.feeDisp[i].selected){
			chosenstr= document.myform.feeDisp[i].value;
			n = chosenstr.lastIndexOf("#");
			substr= chosenstr.substring((n+1),chosenstr.length);
			var chosenfee=Number(substr);
 			var temp =format(chosenfee,2);
			document.myform.fee_txt.value = temp;
			sum=chosenfee;
		}
	}
	for(i=1;i<arrayval.length;i++){
		num1=arrayval[i].lastIndexOf("#");
		num2=arrayval[i].length;
		str = arrayval[i].substring(num1+1,num2);
		num = Number(str);
		sum = sum + num; 
	}
	if(document.myform.status.value=="yes"){
		chkLen = document.myform.phoneCharge.value;
			 if(document.myform.phoneCharge.checked){
					 sum = Number(chkLen) + sum;
			 }
	}

		if(document.myform.membAmount.value=="null"){
			temp1 = Number(document.myform.fee_txt.value);
			document.myform.amtPay.value = format(temp1,2);
			document.myform.totalAmount.value= format(temp1,2);
		}		
		else{
			tempAmt =Number(sum) - Number(document.myform.membAmount.value)
			document.myform.amtPay.value = format(tempAmt,2);
			document.myform.totalAmount.value= format(tempAmt,2);
		}
		var adminStatus = document.myform.status.value;
		var totalAmount = document.myform.totalAmount.value;
		if(adminStatus=='yes'){
			document.getElementById('amtSection').style.display="block";
		} else if(adminStatus=='no'){
			document.getElementById('amtSection').style.display="none";
		}
		
		if(document.getElementById('img').style.display == "none"){
		document.getElementById('img').style.display = "block";
		}
		else{
			document.getElementById('img').style.display = "block";
		}
			document.getElementById('img').style.display = "block";
		
		if(totalAmount>0){
			document.myform.amountSect.value = "yes";
		}else{
			document.myform.amountSect.value = "no";
		}
}
