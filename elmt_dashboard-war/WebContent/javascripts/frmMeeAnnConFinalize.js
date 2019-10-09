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
		if(document.forms['frmAnnualConvRegFinalize'].elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.forms['frmAnnualConvRegFinalize'].elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function enabDisab(chkBxId,txtBxId){
	  
		if(document.forms['frmAnnualConvRegFinalize'].elements[chkBxId].checked == true){
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = false;	
		}else {
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = "disabled";	
		} 
}
function enabDisabPony(radioNam,txtBxId){
		if(document.forms['frmAnnualConvRegFinalize'].elements[radioNam].value == "yes"){
			//alert('Hi!');
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = false;	
		}else {
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = "disabled";	
		} 
}
function enableRad(radBtnId,txtBxId){
		if(document.forms['frmAnnualConvRegFinalize'].elements[radBtnId].checked == true){
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = false;	
		}else{
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.forms['frmAnnualConvRegFinalize'].elements[radBtnNewId].checked == true){
				document.forms['frmAnnualConvRegFinalize'].elements[txtBxId].disabled = "disabled";	
		}
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

function onPayCheck(){

//================================== For Additional Ticket Option ==================
	chosen1="";
	len1 = document.frmAnnualConvRegFinalize.rself.length ;
	for(i=0;i<len1;i++){
		if(document.frmAnnualConvRegFinalize.rself[i].checked){
			chosen1= document.frmAnnualConvRegFinalize.rself[i].value;
		}
	}

	if(chosen1==""){
		alert("Check any of the Affitional Ticket Option.");
		return false;
	}
	if(chosen1=="yes"){
		 
		if ( document.frmAnnualConvRegFinalize.addTktRegistrarName.selectedIndex == 0 ){
		alert ( "Please select Addtional Tickets Registered for." );
		document.frmAnnualConvRegFinalize.addTktRegistrarName.focus();
		return false;
		}
		 
		if(document.frmAnnualConvRegFinalize.noofticket1.value==""){
			alert("Enter valid Tickets1");
			document.frmAnnualConvRegFinalize.noofticket1.focus();
			return false;
		}
		
		if(document.frmAnnualConvRegFinalize.noofticket1.value.indexOf(' ')==0){
			alert("Enter valid Tickets1");
			document.frmAnnualConvRegFinalize.noofticket1.focus();
			return false;
		}

		if(document.frmAnnualConvRegFinalize.noofticket1.value.indexOf('  ')!==-1){
			alert("Enter valid Tickets1");
			document.frmAnnualConvRegFinalize.noofticket1.focus();
			return false;
		}
	
		if(!Number(document.frmAnnualConvRegFinalize.noofticket1.value) && document.frmAnnualConvRegFinalize.noofticket1.value!=0){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket1.focus();
			return false;
		}
	
		if(document.frmAnnualConvRegFinalize.noofticket1.value.length>10){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket1.focus();
			return false;
		}
	
		if(document.frmAnnualConvRegFinalize.noofticket1.value.indexOf('.')!=-1){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket1.focus();
			return false;
		}
		if(document.frmAnnualConvRegFinalize.noofticket2.value==""){
			alert("No of Tickets Cannot be empty.");
			document.frmAnnualConvRegFinalize.noofticket2.focus();
			return false;
		}
		
		if(document.frmAnnualConvRegFinalize.noofticket2.value.indexOf(' ')==0){
			alert("Enter valid Tickets2");
			document.frmAnnualConvRegFinalize.noofticket2.focus();
			return false;
		}

		if(document.frmAnnualConvRegFinalize.noofticket2.value.indexOf('  ')!==-1){
			alert("Enter valid Tickets2");
			document.frmAnnualConvRegFinalize.noofticket2.focus();
			return false;
		}
	
		if(!Number(document.frmAnnualConvRegFinalize.noofticket2.value) && document.frmAnnualConvRegFinalize.noofticket2.value!=0){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket2.focus();
			return false;
		}
	
		if(document.frmAnnualConvRegFinalize.noofticket2.value.length>10){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket2.focus();
			return false;
		}
	
		if(document.frmAnnualConvRegFinalize.noofticket2.value.indexOf('.')!=-1){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket2.focus();
			return false;
		}
		if(document.frmAnnualConvRegFinalize.noofticket3.value==""){
			alert("No of Tickets Cannot be empty.");
			document.frmAnnualConvRegFinalize.noofticket3.focus();
			return false;
		}
		
		if(document.frmAnnualConvRegFinalize.noofticket3.value.indexOf(' ')==0){
			alert("Enter valid Tickets3");
			document.frmAnnualConvRegFinalize.noofticket3.focus();
			return false;
		}

		if(document.frmAnnualConvRegFinalize.noofticket3.value.indexOf('  ')!==-1){
			alert("Enter valid Tickets3");
			document.frmAnnualConvRegFinalize.noofticket3.focus();
			return false;
		}
	
		
		if(!Number(document.frmAnnualConvRegFinalize.noofticket3.value) && document.frmAnnualConvRegFinalize.noofticket3.value!=0){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket3.focus();
			return false;
		}
	
		if(document.frmAnnualConvRegFinalize.noofticket3.value.length>10){
			alert("Entered valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket3.focus();
			return false;
		}
	
		if(document.frmAnnualConvRegFinalize.noofticket3.value.indexOf('.')!=-1){
			alert("Enter valid No. of Tickets Required.");
			document.frmAnnualConvRegFinalize.noofticket3.focus();
			return false;
		}
		
		if(eval(document.frmAnnualConvRegFinalize.activityAmount.value)==0){
				alert("Please enter number of tickets");
				document.frmAnnualConvRegFinalize.activityAmount.focus();
				return false;
		}
	}
	
	if(eval(document.frmAnnualConvRegFinalize.totalAmount.value)== 0){
			alert("Amount Cannot be empty.");
			document.frmAnnualConvRegFinalize.totalAmount.focus();
			return false;
	}
	//stTotal
//================================== For Check & Card Option =======================
     sec="";
     sec =document.frmAnnualConvRegFinalize.c11.value;
	if(sec=="adminSec"){
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


	/*if(!(document.frmAnnualConvRegFinalize.checkDate.value=="")){
	
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

	if(!(document.frmAnnualConvRegFinalize.checkDate.value=="")){
		strdate=document.frmAnnualConvRegFinalize.checkDate.value;
		mm = Number(strdate.substring(0,2));
		dd = Number(strdate.substring(3,5));
		yyyy=(strdate.substring(6,document.frmAnnualConvRegFinalize.checkDate.value.length));

		var now = new Date();
		var months1 = now.getMonth();
		var years=now.getYear();
		var months = 7+months1;
		year=1+years;
		
		if((months<12)&&(mm>months)){
			alert("Enter valid Date in Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if((months1==6)&&(mm>1)&&(mm<6)){
			alert("Enter valid Date in Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}
		
		if((months1==7)&&(mm>2)&&(mm<7)){
			alert("Enter valid Date in Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if((months1==8)&&(mm>3)&&(mm<8)){
			alert("Enter valid Date in Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if((months1==9)&&(mm>4)&&(mm<9)){
			alert("Enter a valid Date in Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if((months1==10)&&(mm>5)&&(mm<10)){
			alert("Enter a valid Date in Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if((months1==11)&&(mm>6)&&(mm<11)){
			alert("Enter valid Date in the Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if(yyyy<nowYear){
			alert("Enter valid Date in the Payment Details");

			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if((yyyy==nowYear)&&(mm<nowMonth)){
			alert("Enter valid Date in the Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;


		}

		if((dd<nowDate)&&(mm==nowMonth)){
			alert("Enter valid date in the Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}

		if(dd>31){
			alert("Enter valid date in the Payment Details");
			document.frmAnnualConvRegFinalize.checkDate.focus();
			return false;
		}
	}
}
*/
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
}

//===================================Name on Check====================================

//===========================card details=========================
		
// ======================= For Card No =============================


if(sec=="userSec" || (sec=="adminSec" && chosen=="card")){
	//alert(sec);
		//if(chosen=="card"){			
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
			if(sec=="userSec"){
				if(document.frmAnnualConvRegFinalize.ccCvvid.value==""){
				alert("Enter CVV Number in Card Details");
				document.frmAnnualConvRegFinalize.ccCvvid.focus();
				return false;
				}
			}
	if(document.frmAnnualConvRegFinalize.ccCvvid.value!=""){
				
		if(document.frmAnnualConvRegFinalize.ccCvvid.value.indexOf(" ")==0 ){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmAnnualConvRegFinalize.ccCvvid.focus();
			return false;
		}
		if(document.frmAnnualConvRegFinalize.ccCvvid.value.indexOf('  ')!=-1){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmAnnualConvRegFinalize.ccCvvid.focus();
			return false;
		}
		if ( document.frmAnnualConvRegFinalize.ccType.selectedIndex == 1 || document.frmAnnualConvRegFinalize.ccType.selectedIndex == 2 ){
			if(document.frmAnnualConvRegFinalize.ccCvvid.value.length !=3){
			alert("Enter valid CVV Number in Card Details");
			document.frmAnnualConvRegFinalize.ccCvvid.focus();
			return false;
		    }
		}
		if ( document.frmAnnualConvRegFinalize.ccType.selectedIndex == 3 ){
			if(document.frmAnnualConvRegFinalize.ccCvvid.value.length !=4){
			alert("Enter valid CVV Number in Card Details");
			document.frmAnnualConvRegFinalize.ccCvvid.focus();
			return false;
			}
		}
		
		if(document.frmAnnualConvRegFinalize.ccCvvid.value.indexOf('.')!=-1){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmAnnualConvRegFinalize.ccCvvid.focus();
			return false;
		}
		
		if(isnotInteger(document.frmAnnualConvRegFinalize.ccCvvid.value)){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmAnnualConvRegFinalize.ccCvvid.focus();
			return false;
		}	
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
		//}

}
	return true;
}
	
