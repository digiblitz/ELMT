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
		if(document.frmEventPendPay.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmEventPendPay.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmEventPendPay.elements[chkBxId].checked == true){
				document.frmEventPendPay.elements[txtBxId].disabled = false;	
		}else {
				document.frmEventPendPay.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){
		if(document.frmEventPendPay.elements[radBtnId].checked == true){
				document.frmEventPendPay.elements[txtBxId].disabled = false;	
		}
		else{
				document.frmEventPendPay.elements[txtBxId].disabled = "disabled";	
		} 
}
function disable(radBtnNewId,txtBxId){
		if(document.frmEventPendPay.elements[radBtnNewId].checked == true){
				document.frmEventPendPay.elements[txtBxId].disabled = "disabled";	
		}
}	
function showHideRadio(radioNam,divId,radVal){
		if(document.frmEventPendPay.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function checkClear(){
	document.frmEventPendPay.ccType.value="";
	document.frmEventPendPay.ccNumber.value="";
	document.frmEventPendPay.ccCvvid.value="";
	document.frmEventPendPay.ccName.value="";
	document.frmEventPendPay.ccExpMonth.value="";
	document.frmEventPendPay.ccExpYear.value="";
}

function cardClear(){
	document.frmEventPendPay.checkNumber.value="";
	//document.frmEventPendPay.checkDate.value="";
	document.frmEventPendPay.bankName.value="";
	document.frmEventPendPay.nameOnchk.value="";
	
}

function amtFocus(){
	document.frmEventPendPay.amount.focus();
}

function popupCal(val) {
      if(val==1)
	      frmEventPendPay.checkDate.value = "StartDate1";

      if(val==2)
         frmEventPendPay.competitionDate.value = "StartDate1";

      val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null) {
         alert("Plz Enable Site Popup Allowed");
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

function onValidate(){
// =================== Amount Validation======================

stringCheck1="!@#$%^&* _`|<>?/=~.,\\";

/*	if(document.frmEventPendPay.amount.value==""){
		alert("The Total Amount Field Can't Be Blank");
		document.frmEventPendPay.amount.focus();
		return false;
	}

	if(!Number(document.frmEventPendPay.amount.value)){
		alert("Enter a valid Number in total Amount Field");
		document.frmEventPendPay.amount.focus();
		return false;
	}

	if(!(document.frmEventPendPay.amount.value=="")){
		if((document.frmEventPendPay.amount.value.indexOf('.'))!=-1){
			fstr=document.frmEventPendPay.amount.value;
			fstr1=document.frmEventPendPay.amount.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmEventPendPay.amount.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Amount is Not Valid.");
				document.frmEventPendPay.amount.focus();
				return false;
			}
		}
	}
*/
// =================== Radio Button Textboxes Validation ===========

//---------------for Option of payment -----------------------------
	if(document.frmEventPendPay.amount.value==0.0){
			alert("Amount cannot be Empty");
			document.frmEventPendPay.amount.focus();
			return false;
	 }
	chosen="";
	len = document.frmEventPendPay.r11.length ;
	for(i=0;i<len;i++){
		if(document.frmEventPendPay.r11[i].checked){
			chosen= document.frmEventPendPay.r11[i].value;
		}
	}

	if(chosen==""){
		alert("Check any of the Payment Option in the Payment Information");
		return false;
	}

//--------------------------Check  Number ---------------------------

	if(chosen=="check"){
		if(document.frmEventPendPay.checkNumber.value==""){
			alert("Enter valid numbers in Check Number of Check Details");
			document.frmEventPendPay.checkNumber.focus();
			return false;
		}

	if(!Number(document.frmEventPendPay.checkNumber.value)){
		alert("Enter valid numbers in Check Number of Check Details");
		document.frmEventPendPay.checkNumber.focus();
		return false;
	}

	if(document.frmEventPendPay.checkNumber.value.length>16){
		alert("Enter Check Number within 16 Digits");
		document.frmEventPendPay.checkNumber.focus();
		return false;
	}

	if(document.frmEventPendPay.checkNumber.value.indexOf('.')!=-1){
		alert("Enter a Valid check number.");
		document.frmEventPendPay.checkNumber.focus();
		return false;
	}

	/*if(document.frmEventPendPay.checkDate.value==""){
		alert("Select a Date in the Check Details");
		document.frmEventPendPay.checkDate.focus();
		return false;
	}


	if(!(document.frmEventPendPay.checkDate.value=="")){
	
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	
	if(nowDate<10){
		nowDate="0"+nowDate;
	}

	if(nowMonth<10){
		nowMonth="0"+nowMonth;
	}

	if(!(document.frmEventPendPay.checkDate.value=="")){
		strdate=document.frmEventPendPay.checkDate.value;
		mm = Number(strdate.substring(0,2));
		dd = Number(strdate.substring(3,5));
		yyyy=(strdate.substring(6,document.frmEventPendPay.checkDate.value.length));

		var now = new Date();
		var months1 = now.getMonth();
		var years=now.getYear();
		var months = 7+months1;
		year=1+years;
		
		if((months<12)&&(mm>months)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((months1==6)&&(mm>1)&&(mm<6)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}
		
		if((months1==7)&&(mm>2)&&(mm<7)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((months1==8)&&(mm>3)&&(mm<8)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((months1==9)&&(mm>4)&&(mm<9)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((months1==10)&&(mm>5)&&(mm<10)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((months1==11)&&(mm>6)&&(mm<11)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}




		if(yyyy<nowYear){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((yyyy==nowYear)&&(mm<nowMonth)){
			alert("Enter a valid Date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		if((dd<nowDate)&&(mm==nowMonth)){
			alert("Enter a valid date in the Payment Details");
			document.frmEventPendPay.checkDate.focus();
			return false;
		}

		else if(dd>31){
				alert("Enter valid date in the Payment Details");
				document.frmEventPendPay.checkDate.focus();
				return false;
			}
		}
	}*/

//--------for Bank Name -------------------------------------------

	if(document.frmEventPendPay.bankName.value==""){
		alert("Enter valid Bank Name in Payment Information");
		document.frmEventPendPay.bankName.focus();
		return false;
	}

	if(document.frmEventPendPay.bankName.value.length>80){
		alert("Entered Bank Name Should be Below 80 Characters.");
		document.frmEventPendPay.bankName.focus();
		return false;
	}

	if(isnotAlpha(document.frmEventPendPay.bankName.value)){
		alert("Enter a valid Bank Name in Payment Information.");
		document.frmEventPendPay.bankName.focus();
    	return false;
	}

	if(document.frmEventPendPay.bankName.value.indexOf(' ')==0){
		alert("Enter Valid Bank Name in Payment Information.");
		document.frmEventPendPay.bankName.focus();
		return false;
	}

	if(document.frmEventPendPay.bankName.value.indexOf('  ')!==-1){
		alert("Enter Valid Bank Name in Payment Information.");
		document.frmEventPendPay.bankName.focus();
		return false;
	}

	if(Number(document.frmEventPendPay.bankName.value)){
		alert("Enter valid Bank Name in Payment Information");
		document.frmEventPendPay.bankName.focus();
		return false;
	}
	
	if(document.frmEventPendPay.nameOnchk.value==""){
		alert("Enter Valid Name on Check in Payment Information.");
		document.frmEventPendPay.nameOnchk.focus();
		return false;
	}
	
	if(document.frmEventPendPay.nameOnchk.value.indexOf(" ")==0){
		alert("Enter Valid Name on Check in Payment Information.");
		document.frmEventPendPay.nameOnchk.focus();
		return false;
	}
	
}
//---------for option Card Select ----------------------------------

	if(chosen=="card"){
		len4=document.frmEventPendPay.ccType.length;
		for (i = 0; i < len4; i++){
			if (document.frmEventPendPay.ccType[i].selected){
  			chosen4 = document.frmEventPendPay.ccType[i].value;
	  	}
	}
	
	if(chosen==""){
		alert("Select an option in Card Type");
		document.frmEventPendPay.ccType.focus();
		return false;
	}

	if ( document.frmEventPendPay.ccType.selectedIndex == 0 ){
        alert ( "Please select card type." );
		document.frmEventPendPay.ccType.focus();
        return false;
    }

//-------for Card Number ------------------------------------------

	if(document.frmEventPendPay.ccNumber.value==""){
		alert("Enter a Card Number in the Card Details");
		document.frmEventPendPay.ccNumber.focus();
		return false;
	}

	if(!Number(document.frmEventPendPay.ccNumber.value)){
		alert("Enter valid Card Number in the Card Details");
		document.frmEventPendPay.ccNumber.focus();
		return false;
	}

	if(document.frmEventPendPay.ccNumber.value.length!=16 ){
		alert("Enter valid 16 digit number to Card Number in the Card Details");
		document.frmEventPendPay.ccNumber.focus();
		return false;
	}

	if(document.frmEventPendPay.ccNumber.value.indexOf('.')!=-1){
		alert("Enter Valid Number in Card Number.");
		document.frmEventPendPay.ccNumber.focus();
		return false;
	}

//--------for CVV Number -------------------------------------

	if(document.frmEventPendPay.ccCvvid.value!=""){
	if(document.frmEventPendPay.ccCvvid.value.length>4){
		alert("Enter valid number in CVV Number in Card Details");
		document.frmEventPendPay.ccCvvid.focus();
		return false;
	}

	if(document.frmEventPendPay.ccCvvid.value.length<3){
		alert("Enter valid number in CVV Number in Card Details");
		document.frmEventPendPay.ccCvvid.focus();
		return false;
	}

	if(document.frmEventPendPay.ccCvvid.value.indexOf('.')!=-1){
		alert("Enter Valid Number in Cvv Number.");
		document.frmEventPendPay.ccCvvid.focus();
		return false;
	}
	}
//----for Print Name on Card ----------------------------------------

	if(document.frmEventPendPay.ccName.value==""){
		alert("Enter valid Print Name in Card Details");
		document.frmEventPendPay.ccName.focus();
		return false;
	}

	if(document.frmEventPendPay.ccName.value.length>80){
		alert("Entered Print Name in Card Details is out of Range.");
		document.frmEventPendPay.ccName.focus();
		return false;
	}

	if(isnotAlpha(document.frmEventPendPay.ccName.value)){
		alert("Enter a valid Card Name.");
		document.frmEventPendPay.ccName.focus();
	    return false;
    }

	if(Number(document.frmEventPendPay.ccName.value)){
		alert("Enter a valid card name");
		document.frmEventPendPay.ccName.focus();
		return false;
	}

	if(document.frmEventPendPay.ccName.value.indexOf(' ')==0){
		alert("Enter a Valid Card Name.");
		document.frmEventPendPay.ccName.focus();
		return false;
	}

	if(document.frmEventPendPay.ccName.value.indexOf('  ')!==-1){
		alert("Enter Valid Card Name.");
		document.frmEventPendPay.ccName.focus();
		return false;
	}

//---for Expiry month drop down --------------------------------

	var rightNow=new Date();
	var theYear=rightNow.getYear();
	var tMonth=rightNow.getMonth();
	var theMonth=1+tMonth;
	var theDate=rightNow.getDate();

	if ( document.frmEventPendPay.ccExpMonth.selectedIndex == 0 ){
		alert ( "Please select a Month." );
		document.frmEventPendPay.ccExpMonth.focus();
    	return false;
	}

	if (document.frmEventPendPay.ccExpYear.value==theYear){
		 if(document.frmEventPendPay.ccExpMonth.value<theMonth){
			alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
			document.frmEventPendPay.ccExpMonth.focus();
			return(false);   
		}
	}

//-----------for Expiry Year drop down

	if ( document.frmEventPendPay.ccExpYear.selectedIndex == 0 ){
		alert ( "Please select a year." );
		document.frmEventPendPay.ccExpYear.focus();
    	return false;
	}


	if (document.frmEventPendPay.ccExpYear.value<theYear){
		alert("You have selected an Expired Year in card details. Please Select Valid Expiration Year.");
		document.frmEventPendPay.ccExpMonth.focus();
		return(false);   
	}

}


	return true;
}