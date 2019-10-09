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
function IsNumeric(sText){
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

function IsInteger(sText){
   var ValidChars = "0123456789";
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

function myvalidate(){
	/*
	if(IsNumeric(document.frmAnnualConvRegFinalize.totTckt.value)){
	}
	else{
		alert('Enter valid number of tickets in the ticket column');
		return false;
	}*/
	
	var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
	for (i=1;i<=priceLen;i++){
		var noOfTkt = "noofticket"+i;
		var indivAmt = "indivAmt"+i;
		var calculAmt = "calculAmt"+i;
		var actCheckBox= "actCheckBox"+i;

		if(document.getElementById(actCheckBox).checked==true){
			if(Number(document.getElementById(noOfTkt).value)==0){
				alert('Enter valid Number of tickets');
				document.getElementById(noOfTkt).focus();
				return false;
			}
		}
	}
	if(document.frmAnnualConvRegFinalize.totTckt.value==0){
		alert('Select Any one of the Activity to register');
		return false;
	}
	if(document.frmAnnualConvRegFinalize.totalAmount.value==0){
		alert('Select Any one of the Activity to register');
		return false;
	}
//================================== For Check & Card Option =======================

	chosen="";
	len = document.frmAnnualConvRegFinalize.r11.length ;
	for(i=0;i<len;i++){
		if(document.frmAnnualConvRegFinalize.r11[i].checked){
			chosen= document.frmAnnualConvRegFinalize.r11[i].value;
		}
	}

	if(chosen==""){
		alert("Check any of the Payment Option.");
		return false;
	}

//--------------------------Check  Number ---------------------------

	if(chosen=="check"){
		if(document.frmAnnualConvRegFinalize.checkNumber.value==""){
			alert("Check Number in Check Details cannot be empty");
			document.frmAnnualConvRegFinalize.checkNumber.focus();
			return false;
		}
	if(isAlpha(document.frmAnnualConvRegFinalize.checkNumber.value)){
		alert("Enter valid Check Number.");
		document.frmAnnualConvRegFinalize.checkNumber.focus();
    	return false;
	}
	if(document.frmAnnualConvRegFinalize.checkNumber.value.length>16){
		alert("Check Number exceeds the maximum of 16 characters.");
		document.frmAnnualConvRegFinalize.checkNumber.focus();
		return false;
	}
	if(document.frmAnnualConvRegFinalize.checkNumber.value.indexOf('.')!=-1){
		alert("Enter Valid check number.");
		document.frmAnnualConvRegFinalize.checkNumber.focus();
		return false;
	}
	 
//=============================== For Check Date ========================
	if(document.frmAnnualConvRegFinalize.checkDate.value==""){
		alert("Select Date in Check Details");
		document.frmAnnualConvRegFinalize.checkDate.focus();
		return false;
	}

//===================================In Favor Of====================================

if(document.frmAnnualConvRegFinalize.bankName.value==""){
		alert("Bank Name cannot be empty.");
		document.frmAnnualConvRegFinalize.bankName.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.bankName.value.length>80){
		alert("Bank Name exceeds the range");
		document.frmAnnualConvRegFinalize.bankName.focus();
		return false;
	}

	if(isnotAlpha(document.frmAnnualConvRegFinalize.bankName.value)){
		alert("Enter valid Bank Name");
		document.frmAnnualConvRegFinalize.bankName.focus();
    	return false;
	}

	if(document.frmAnnualConvRegFinalize.bankName.value.indexOf(' ')==0){
			alert("Enter valid Bank Name");
		document.frmAnnualConvRegFinalize.bankName.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.bankName.value.indexOf('  ')!==-1){
			alert("Enter valid Bank Name");
		document.frmAnnualConvRegFinalize.bankName.focus();
		return false;
	}

	if(Number(document.frmAnnualConvRegFinalize.bankName.value)){
			alert("Enter valid Bank Name");
		document.frmAnnualConvRegFinalize.bankName.focus();
		return false;
	}
	if(document.frmAnnualConvRegFinalize.nameCheck.value==""){
		alert("Name on Check cannot be empty.");
		document.frmAnnualConvRegFinalize.nameCheck.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.nameCheck.value.length>80){
		alert("Name on Check exceeds the range");
		document.frmAnnualConvRegFinalize.nameCheck.focus();
		return false;
	}

	if(isnotAlpha(document.frmAnnualConvRegFinalize.nameCheck.value)){
		alert("Enter valid Name on Check");
		document.frmAnnualConvRegFinalize.nameCheck.focus();
    	return false;
	}

	if(document.frmAnnualConvRegFinalize.nameCheck.value.indexOf(' ')==0){
			alert("Enter valid Name on Check");
		document.frmAnnualConvRegFinalize.nameCheck.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.nameCheck.value.indexOf('  ')!==-1){
			alert("Enter valid Name on Check");
		document.frmAnnualConvRegFinalize.nameCheck.focus();
		return false;
	}

	if(Number(document.frmAnnualConvRegFinalize.nameCheck.value)){
			alert("Enter valid Name on Check");
		document.frmAnnualConvRegFinalize.nameCheck.focus();
		return false;
	}

	
}
		if(chosen=="card"){
			
		if ( document.frmAnnualConvRegFinalize.ccType.selectedIndex == 0 ){
		alert ( "Please select card type." );
		document.frmAnnualConvRegFinalize.ccType.focus();
		return false;
		}

		if(document.frmAnnualConvRegFinalize.ccNumber.value==""){
			alert("Enter Credit Card Number ");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}
		
		if(document.frmAnnualConvRegFinalize.ccNumber.value.indexOf(' ')==0){
			alert("Empty space(s) are not allowed for Card Number.");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}

		if(document.frmAnnualConvRegFinalize.ccNumber.value.indexOf('  ')!==-1){
			alert("Empty space(s) are not allowed for Card Number.");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}
		
		
		if(!isnotAlpha(document.frmAnnualConvRegFinalize.ccNumber.value)){
			alert("Alphabets are not allowed.");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}
		
		if(isSpecialCharacter(document.frmAnnualConvRegFinalize.ccNumber.value)){
			alert("Special Characters are not allowed.");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}

          
		if(!Number(document.frmAnnualConvRegFinalize.ccNumber.value)){
			alert("Alphabets and Special Characters are not allowed.");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}
		
		 if(document.frmAnnualConvRegFinalize.ccType.value=="AmEx"){
				if(document.frmAnnualConvRegFinalize.ccNumber.value.length!=15){
				alert("Credit card number should be 15 Character");
				document.frmAnnualConvRegFinalize.ccNumber.focus();
				return false;
				}
	     }
		 else{
			if(document.frmAnnualConvRegFinalize.ccNumber.value.length!=16){
				alert("Credit card number should be 16 Character");
				document.frmAnnualConvRegFinalize.ccNumber.focus();
				return false;
			}
		 }
		 
		if(document.frmAnnualConvRegFinalize.ccNumber.value.indexOf('.')!=-1){
			alert("Alphabets and special characters are not allowed.");
			document.frmAnnualConvRegFinalize.ccNumber.focus();
			return false;
		}

//--------for CVV Number -------------------------------------

	
	if(document.frmAnnualConvRegFinalize.ccCvvid.value.length>4){
		alert("Enter valid CVV Number");
		document.frmAnnualConvRegFinalize.ccCvvid.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.ccCvvid.value.length>=1 && document.frmAnnualConvRegFinalize.ccCvvid.value.length<3){
		alert("Enter valid CVV Number");
		document.frmAnnualConvRegFinalize.ccCvvid.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.ccCvvid.value.indexOf('.')!=-1){
		alert("Enter Valid Cvv Number");
		document.frmAnnualConvRegFinalize.ccCvvid.focus();
		return false;
	}
	
	
	//===================== For card type ================
		

	
	
  
//----for Print Name on Card ----------------------------------------
	if(document.frmAnnualConvRegFinalize.ccName.value==""){
		alert("Print Name cannot be empty");
		document.frmAnnualConvRegFinalize.ccName.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.ccName.value.length>80){
		alert("Print Name exceeds the range");
		document.frmAnnualConvRegFinalize.ccName.focus();
		return false;
	}

	if(isnotAlpha(document.frmAnnualConvRegFinalize.ccName.value)){
		alert("Enter valid Card Name.");
		document.frmAnnualConvRegFinalize.ccName.focus();
	    return false;
    }

	if(Number(document.frmAnnualConvRegFinalize.ccName.value)){
		alert("Enter valid card name");
		document.frmAnnualConvRegFinalize.ccName.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.ccName.value.indexOf(' ')==0){
		alert("Enter Valid Card Name.");
		document.frmAnnualConvRegFinalize.ccName.focus();
		return false;
	}

	if(document.frmAnnualConvRegFinalize.ccName.value.indexOf('  ')!==-1){
		alert("Enter Valid Card Name.");
		document.frmAnnualConvRegFinalize.ccName.focus();
		return false;
	}

//---for Expiry month drop down --------------------------------

	var rightNow=new Date();
	var theYear=rightNow.getYear();
	var tMonth=rightNow.getMonth();
	var theMonth=1+tMonth;
	var theDate=rightNow.getDate();

	if ( document.frmAnnualConvRegFinalize.ccExpMonth.selectedIndex == 0 ){
		alert ( "Please select a Month." );
		document.frmAnnualConvRegFinalize.ccExpMonth.focus();
    	return false;
	}

	if (document.frmAnnualConvRegFinalize.ccExpYear.value==theYear){
		 if(document.frmAnnualConvRegFinalize.ccExpMonth.value<theMonth){
			alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
			document.frmAnnualConvRegFinalize.ccExpMonth.focus();
			return(false);   
		}
	}

//-----------for Expiry Year drop down

	if(document.frmAnnualConvRegFinalize.ccExpYear.selectedIndex == 0 ){
		alert ( "Please select a year." );
		document.frmAnnualConvRegFinalize.ccExpYear.focus();
    	return false;
	}
    

	if (document.frmAnnualConvRegFinalize.ccExpYear.value<theYear){
		alert("You have selected an Expired Year in card details. Please Select Valid Expiration Year.");
		document.frmAnnualConvRegFinalize.ccExpYear.focus();
		return false;   
	}
		}


	return true;
}

function clearTkts(){
	if(document.getElementById('addTktRegistrarName').value!=""){
		var len = document.getElementById('priceIdLen').value;
		for(i=1;i<=len;i++){
			var actCheckBox = "actCheckBox"+i;
			var noOfTkt = "noofticket"+i;
			var indivAmt = "indivAmt"+i;
			var calculAmt = "calculAmt"+i;
						
			if(document.getElementById(actCheckBox).checked==true){
				document.getElementById(actCheckBox).checked =false;
				document.getElementById(noOfTkt).value=0;
				document.getElementById(indivAmt).value="0.00";
				document.getElementById(calculAmt).value="0.00";
			}
		}
	}
}
	function clearBasicLists(){
		var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
		for (i=1;i<=priceLen;i++){
			var noOfTkt = "noofticket"+i;
			var indivAmt = "indivAmt"+i;
			var calculAmt = "calculAmt"+i;
			
			document.getElementById(noOfTkt).value=0;
			document.getElementById(indivAmt).value="0.00";
			document.getElementById(calculAmt).value="0.00";
		}
	}

		function checkAll() {
			 el = document.forms['frmAnnualConvRegFinalize'].elements;
			 for (i=0; i < el.length; i++) {
			   if(el[i].type == "checkbox") {
				  void(el[i].checked=0);
			   }
			 }
		 }
		 
		 

		function resetAmount(){
			document.frmAnnualConvRegFinalize.totalAmount.value = document.frmAnnualConvRegFinalize.stTotal.value ;
		}

		function setTotalAmount(){
/*			var amt = document.frmAnnualConvRegFinalize.calcValue.value;
			document.frmAnnualConvRegFinalize.totalAmount.value = Number(amt).toFixed(2);	*/	
		}

		function clearAmtValues(){
			var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
			for (i=1;i<=priceLen;i++){		
				var noOfTkt = "noofticket"+i;
				var indivAmt = "indivAmt"+i;
				var calculAmt = "calculAmt"+i;
				var actCheckBox ="actCheckBox"+i;			
					if(document.getElementById(actCheckBox).checked==false){
						document.getElementById(noOfTkt).value=0;
						document.getElementById(indivAmt).value="0.00";
						document.getElementById(calculAmt).value="0.00";
					}
			}
		}
		
function checkForInt(ticketObj){
	if(IsInteger(ticketObj.value)){
		var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
		for (i=1;i<=priceLen;i++){
			var noOfTkt = "noofticket"+i;
			var indivAmt = "indivAmt"+i;
			var calculAmt = "calculAmt"+i;
			var actCheckBox ="actCheckBox"+i;
	
			if(document.getElementById(actCheckBox).checked==true){
				if(IsInteger(document.getElementById(noOfTkt).value)){
					setAcAmount(ticketObj);
				}
				else{
					document.getElementById(noOfTkt).value = 0;
					document.getElementById(calculAmt).value = 0;
					calculateAmount();
					document.getElementById(noOfTkt).focus();
				}	
			}
		}
	}
	else{
		alert('Enter valid number of tickets');
		var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
		for (j=1;j<=priceLen;j++){
			var noOfTkt = "noofticket"+j;
			var indivAmt = "indivAmt"+j;
			var calculAmt = "calculAmt"+j;
			var actCheckBox ="actCheckBox"+j;
			if(document.getElementById(actCheckBox).checked==true){
				if(!IsInteger(document.getElementById(noOfTkt).value)){
					document.getElementById(noOfTkt).value = 0;
					document.getElementById(calculAmt).value = 0;
					calculateAmount();
					document.getElementById(noOfTkt).focus();
				}
			}
		}
	}
}		

		function setAcAmount(ticketObj){

			var ticketObjName = "";
			var chkBoxName;

			var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
			for (i=1;i<=priceLen;i++){
				var noOfTkt = "noofticket"+i;
				var indivAmt = "indivAmt"+i;
				var calculAmt = "calculAmt"+i;
				var actCheckBox ="actCheckBox"+i;
				
				if(document.getElementById(actCheckBox).checked==true){
						var tkt = Number(document.getElementById(noOfTkt).value);
						var tktVal = Number(document.getElementById(indivAmt).value);
						var amt = (parseInt(tkt)*parseFloat(tktVal)).toFixed(2);
						document.getElementById(calculAmt).value = amt;
						calculateAmount();
				}
				else{
					calculateAmount();	

				}
					clearAmtValues();
			}
		}

		function calculateAmount(){
			var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
			var amt=0;
			var tempAmt = 0;
			var compAmt = 0;
			for (i=1;i<=priceLen;i++){
				var noOfTkt = "noofticket"+i;
				var indivAmt = "indivAmt"+i;
				var calculAmt = "calculAmt"+i;
				var actCheckBox ="actCheckBox"+i;
				
				if(document.getElementById(actCheckBox).checked==true){
					//amt = Number(amt) + Number(document.getElementById(indivAmt).value);
					var tkts = Number(document.getElementById(noOfTkt).value);
					var indAmt = Number(document.getElementById(indivAmt).value);
					tempAmt = Number((parseInt(tkts)*parseFloat(indAmt)).toFixed(2)).toFixed(2);
					document.getElementById(calculAmt).value = tempAmt;
				}
			}
			finalCalulate();
		}

function finalCalulate(){
	
	var totAmt = 0;
	var totTckt = 0;
	var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
	for (i=1;i<=priceLen;i++){
		var calculAmt = "calculAmt"+i;
		var actCheckBox ="actCheckBox"+i;
		var noOfTkt = "noofticket"+i;	
		if(document.getElementById(actCheckBox).checked==true){
			calculAmt = document.getElementById(calculAmt).value;
			totAmt = Number(calculAmt)+Number(totAmt);
			totTckt = Number(totTckt) + Number(document.getElementById(noOfTkt).value);
		}
	}
	document.frmAnnualConvRegFinalize.totTckt.value = Number(totTckt);
	document.frmAnnualConvRegFinalize.totalAmount.value = Number(totAmt).toFixed(2);
}

var httpRequest;
var gindivAmt;
var gcalculAmt;
var gtransId;
var gpriceId;
var gamtBx;
var gnoOfTkt;
	function getActivityAmount(param,paramthis,paramname){
					var  url = null;
				
					var memId = document.frmAnnualConvRegFinalize.memberShipTypeID.value;
					   
			 		url = "annualAjax.do?method=calcAmount&userTypeId="+escape(memId)+"&specId="+escape(param);

					if(paramthis.checked==true){
						if (window.ActiveXObject){ 
							httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
						} 
						else if (window.XMLHttpRequest){ 
							httpRequest = new XMLHttpRequest(); 
						}
						
						gindivAmt = "";
						gcalculAmt = "";
						gPriceId = "";
						gtransId = "";
						gamtBx = "";
						gpriceId="";
						
						gindivAmt = "indivAmt"+paramname;
						gcalculAmt = "calculAmt"+paramname;
						gtransId = "trans"+paramname;
						gpriceId = "priceId"+paramname;
						gnoOfTkt = "noofticket"+paramname;
						
						httpRequest.onreadystatechange = setOtherAmountonName;
						httpRequest.open("GET", url, true);
						httpRequest.send(null);
						
					}
					else{					
						/*document.getElementById(gindivAmt).value = "0.00";
						document.getElementById(gcalculAmt).value = "0.00";
						document.getElementById(gnoOfTkt).value = 0;
						calculateAmount();*/
					}
					setAcAmount(gnoOfTkt);
					//setTotalAmount();
				   } 

		function setOtherAmountonName(){
		
			if(httpRequest.readyState==1){
				return;
			}
			if (httpRequest.readyState == 4){
				if(httpRequest.status == 200){
					var amntObj =0.0;
					var xmlDoc = httpRequest.responseXML;
					var priceVal = xmlDoc.getElementsByTagName('priceId')[0].childNodes[0].nodeValue;
					var regAmnt = xmlDoc.getElementsByTagName('amount')[0].childNodes[0].nodeValue;	
					if(priceVal==null && regAmnt==0){
						amntObj = 0.0;
						priceVal = "";
					}
					else{
						amntObj = eval(regAmnt).toFixed(2);
						priceIdField = priceVal;
					}
					document.getElementById(gindivAmt).value = amntObj;
					document.getElementById(gpriceId).value = priceIdField;
					document.getElementById(gnoOfTkt).value = 1;
					calculateAmount();
				}
				if(httpRequest.status==500) {
					amntObj.value="";
					return; 
				}
			}
			else{
			//alert("Error loading page\n");
			}
		}				
				  
		function denyBack(){
			window.history.clear();
			window.history.forward(1);
		}
		
function cardClear(){
	
	document.frmAnnualConvRegFinalize.checkNumber.disabled=false;
	document.frmAnnualConvRegFinalize.checkDate.disabled=false;
	document.frmAnnualConvRegFinalize.bankName.disabled=false;	
	document.frmAnnualConvRegFinalize.nameCheck.disabled=false;	
	
	document.frmAnnualConvRegFinalize.ccNumber.value="";
	document.frmAnnualConvRegFinalize.ccCvvid.value="";
	document.frmAnnualConvRegFinalize.ccType.selectedIndex=0;
	document.frmAnnualConvRegFinalize.ccName.value="";
	document.frmAnnualConvRegFinalize.ccExpMonth.selectedIndex=0;
	document.frmAnnualConvRegFinalize.ccExpYear.selectedIndex=0;

}		

function showHideRadio(radioNam,divId,radVal){
		if(document.forms['frmAnnualConvRegFinalize'].elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}



function isAlpha(str){
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

function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`0123456789;:"+"\\"+"\'";
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

function phoneCheck(str1){
	stringCheck1="`~!@#$%^&*|:;{}[]<>?/=~.,'`;:"+"\\"+"\'";
	f2=1;
	for(j=0;j<str1.length;j++){
		if(stringCheck1.indexOf(str1.charAt(j))!=-1){
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

function popupCal(val) {
      if(val==1)
		frmAnnualConvRegFinalize.checkDate.value = "StartDate1";
		val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null) {
         alert("Plz Enable Site Popup Allowed");
      }
}

function checkClear(){
	document.frmAnnualConvRegFinalize.checkNumber.disabled=true;
	document.frmAnnualConvRegFinalize.checkDate.disabled=true;
	document.frmAnnualConvRegFinalize.bankName.disabled=true;
	document.frmAnnualConvRegFinalize.nameCheck.disabled=true;	
	document.frmAnnualConvRegFinalize.ccNumber.disabled=false;
	document.frmAnnualConvRegFinalize.ccCvvid.disabled=false;
	document.frmAnnualConvRegFinalize.ccType.disabled=false;
	document.frmAnnualConvRegFinalize.ccName.disabled=false;
	document.frmAnnualConvRegFinalize.ccExpMonth.disabled=false;
	document.frmAnnualConvRegFinalize.ccExpYear.disabled=false;
	document.frmAnnualConvRegFinalize.checkNumber.value="";
	document.frmAnnualConvRegFinalize.checkDate.value="";
	document.frmAnnualConvRegFinalize.bankName.value="";
	document.frmAnnualConvRegFinalize.nameCheck.value="";	
}

function cardClear(){
	document.frmAnnualConvRegFinalize.checkNumber.disabled=false;
	document.frmAnnualConvRegFinalize.checkDate.disabled=false;
	document.frmAnnualConvRegFinalize.bankName.disabled=false;	
	document.frmAnnualConvRegFinalize.nameCheck.disabled=false;	
	
	document.frmAnnualConvRegFinalize.ccNumber.value="";
	document.frmAnnualConvRegFinalize.ccCvvid.value="";
	document.frmAnnualConvRegFinalize.ccType.selectedIndex=0;
	document.frmAnnualConvRegFinalize.ccName.value="";
	document.frmAnnualConvRegFinalize.ccExpMonth.selectedIndex=0;
	document.frmAnnualConvRegFinalize.ccExpYear.selectedIndex=0;
}